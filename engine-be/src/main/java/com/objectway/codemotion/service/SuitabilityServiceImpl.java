package com.objectway.codemotion.service;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.objectway.codemotion.beans.Product;
import com.objectway.codemotion.repository.SuitabilityRepository;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.EvaluatedDecision;
import io.camunda.zeebe.client.api.response.EvaluatedDecisionOutput;
import io.camunda.zeebe.client.api.response.MatchedDecisionRule;

@Service
public class SuitabilityServiceImpl implements SuitabilityService {
	
	@Value("${camunda.threshold.dmn.id}")
	private String thresholdDecisionId;
	
	@Value("${camunda.asset.dmn.id}")
	private String assetDecisionId;
	
	@Autowired
	private ZeebeClient zeebeClient;
	
	@Autowired
    private SuitabilityRepository suitabilityRepository;

	@Override
	public HashMap<String, String> getDecisionOutput(HashMap<String,String> inputMap) {		
		
		EvaluatedDecision thresholdDecision = evaluateDecision(inputMap,thresholdDecisionId);
		EvaluatedDecision assetDecision = evaluateDecision(inputMap,assetDecisionId);
		
		//Retrieve rules from Camunda output
		HashMap<String,String> thresholdMap = retrieveOutput(thresholdDecision);
		HashMap<String,String> assetMap = retrieveOutput(assetDecision);
		
		HashMap<String,String> rulesMap = new HashMap<String, String>();
		rulesMap.putAll(thresholdMap);
		rulesMap.putAll(assetMap);
		
		return rulesMap;		
	}
	
	private EvaluatedDecision evaluateDecision(HashMap<String,String> inputMap, String decisionId) {
		EvaluatedDecision decision = zeebeClient.newEvaluateDecisionCommand()
			    .decisionId(decisionId)  
			    .variables(inputMap)                  
			    .send()
			    .join()
			    .getEvaluatedDecisions()
			    .get(0);
		
		return decision;
	}
	
	@Override
	public List<Product> filterProducts(Product productFilter) {
		
		ExampleMatcher matcher = ExampleMatcher.matching()
		        .withIgnoreNullValues() 
		        .withMatcher("region", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase()) 
		        .withMatcher("asset", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
		        .withIgnorePaths("volatility");		
		
		Example<Product> exampleProduct = Example.of(productFilter, matcher);
		List<Product> data = (List<Product>) suitabilityRepository.findAll(exampleProduct);
		
		return data;
	}
	
	@Override
	public List<Product> filterProductsByVolatility(List<Product> products, String condition) {
		Predicate<Product> predicate = createVolatilityPredicate(condition);
        return products.stream()
                       .filter(predicate)
                       .collect(Collectors.toList());        
	}
	
	private static HashMap<String,String> retrieveOutput(EvaluatedDecision decision){
		
		HashMap<String,String> rulesMap = new HashMap<String, String>();
		
		for(MatchedDecisionRule rule : decision.getMatchedRules()) {
			for(EvaluatedDecisionOutput output : rule.getEvaluatedOutputs()){
				 if (output.getOutputValue() != null && !output.getOutputValue().toString().equalsIgnoreCase("null"))
					rulesMap.put(output.getOutputName(), output.getOutputValue().replace("\"", ""));
			}
		}
		
		return rulesMap;
	}
	
	private static Predicate<Product> createVolatilityPredicate(String condition) {
        String[] parts = condition.split(",");

        Predicate<Product> predicate = product -> true; 

        for (String part : parts) {
            part = part.trim();
            if (part.startsWith(">=")) {
                double value = Double.parseDouble(part.substring(2).trim());
                predicate = predicate.and(product -> product.getVolatility() >= value);
            } else if (part.startsWith("<=")) {
                double value = Double.parseDouble(part.substring(2).trim());
                predicate = predicate.and(product -> product.getVolatility() <= value);
            } else if (part.startsWith(">")) {
                double value = Double.parseDouble(part.substring(1).trim());
                predicate = predicate.and(product -> product.getVolatility() > value);
            } else if (part.startsWith("<")) {
                double value = Double.parseDouble(part.substring(1).trim());
                predicate = predicate.and(product -> product.getVolatility() < value);
            }
        }
        return predicate;
    }


}
