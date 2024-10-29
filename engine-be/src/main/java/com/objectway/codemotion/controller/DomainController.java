package com.objectway.codemotion.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.objectway.codemotion.beans.Product;
import com.objectway.codemotion.beans.Region;
import com.objectway.codemotion.beans.RiskProfile;
import com.objectway.codemotion.beans.TimeHorizon;
import com.objectway.codemotion.response.Response;

@CrossOrigin
@RequestMapping("/domain")
public interface DomainController {

	@GetMapping("/products")
	Response<List<Product>> products();
	
	@GetMapping("/regions")
	Response<List<Region>> regions();
	
	@GetMapping("/riskProfiles")
	Response<List<RiskProfile>> riskProfile();
	
	@GetMapping("/timeHorizon")
	Response<List<TimeHorizon>> timeHorizon();
}
