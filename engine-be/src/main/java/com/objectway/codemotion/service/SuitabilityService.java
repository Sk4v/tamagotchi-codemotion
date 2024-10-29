package com.objectway.codemotion.service;

import java.util.HashMap;
import java.util.List;

import com.objectway.codemotion.beans.Product;

public interface SuitabilityService {
	
	HashMap<String,String> getDecisionOutput(HashMap<String,String> inputMap);

	List<Product> filterProducts(Product productFilter);
	
	List<Product> filterProductsByVolatility(List<Product> products, String operator);
}
