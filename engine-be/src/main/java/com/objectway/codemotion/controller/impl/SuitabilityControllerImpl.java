package com.objectway.codemotion.controller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.objectway.codemotion.beans.Product;
import com.objectway.codemotion.controller.SuitabilityController;
import com.objectway.codemotion.request.Request;
import com.objectway.codemotion.response.Response;
import com.objectway.codemotion.service.SuitabilityServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SuitabilityControllerImpl implements SuitabilityController {
	
	@Autowired
	SuitabilityServiceImpl suitabilityService;

	@Override
	public Response<List<Product>> suitabilityCheck(Request req) {			
		
		log.info("request {}", req);						
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("riskProfile", req.getRiskProfile() != null ? req.getRiskProfile().toString().toLowerCase() : null);
		map.put("timeHorizon", req.getTimeHorizon() != null ? req.getTimeHorizon().toString().toLowerCase() : null);
		
		//Retrieve rules from Camunda output
		HashMap<String,String> rulesMap = suitabilityService.getDecisionOutput(map);
		log.info("rules {}", rulesMap);		
		
		Product productFilter = new Product(req.getRegion().toString(), rulesMap.get("asset"));
		List<Product> filteredProducts = suitabilityService.filterProducts(productFilter); 		

		List<Product> data = new ArrayList<Product>();		
		if(rulesMap.get("volatility") != null)
			data = suitabilityService.filterProductsByVolatility(filteredProducts, rulesMap.get("volatility")); 
		else
			data = filteredProducts;
				
		Response<List<Product>> rr = new Response<List<Product>>();
		rr.setData(data);
		rr.setMessage(data.size() + " products found");
		log.info("response {}", rr);		
		return rr;
	}
	
	
}
