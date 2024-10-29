package com.objectway.codemotion.suitability;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import com.objectway.codemotion.beans.Product;
import com.objectway.codemotion.beans.Region;
import com.objectway.codemotion.repository.SuitabilityRepository;

public class TestSuitabilityRepository extends AbstractSuitabilityTest{
	
	@Autowired
	SuitabilityRepository suitabilityRepository;
	
	@Test
	public void testGetProducts() {
		Iterable<Product> products = suitabilityRepository.findAll();
		assertNotNull(products);
		for(Product pr : products) {
			assertNotNull(pr.getIsin());
			assertNotNull(pr.getName());
			assertNotNull(pr.getVolatility());
		}
	}
	
	@Test
	public void testMatcher() {
		
		ExampleMatcher matcher = ExampleMatcher.matching()
		        .withIgnoreNullValues() 
		        .withMatcher("region", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase()) 
		        .withMatcher("asset", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
		        .withIgnorePaths("volatility");		
		
		Product productFilter = new Product(Region.WORLD.toString(), "equity");
	
		Example<Product> exampleProduct = Example.of(productFilter, matcher);
		List<Product> data = (List<Product>) suitabilityRepository.findAll(exampleProduct);
		
		assertNotNull(data);		
		for(Product p : data) {
			assertEquals("World", p.getRegion().toString());
			assertEquals("Equity", p.getAsset());
		}
	}
	
	
}
