package com.objectway.codemotion.controller.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.objectway.codemotion.beans.Product;
import com.objectway.codemotion.beans.Region;
import com.objectway.codemotion.beans.RiskProfile;
import com.objectway.codemotion.beans.TimeHorizon;
import com.objectway.codemotion.controller.DomainController;
import com.objectway.codemotion.repository.SuitabilityRepository;
import com.objectway.codemotion.response.Response;

@RestController
public class DomainControllerImpl implements DomainController {
	
	@Autowired
    private SuitabilityRepository suitabilityRepository;

	@Override
	public Response<List<Product>> products() {
		Response<List<Product>> rr = new Response<>();
		rr.setData((List<Product>) suitabilityRepository.findAll());
		return rr;
	}

	@Override
	public Response<List<Region>> regions() {
		Response<List<Region>> rr = new Response<>();
		rr.setData(Arrays.asList(Region.values()));
		return rr;
	}

	@Override
	public Response<List<RiskProfile>> riskProfile() {
		Response<List<RiskProfile>> rr = new Response<>();
		rr.setData(Arrays.asList(RiskProfile.values()));
		return rr;
	}

	@Override
	public Response<List<TimeHorizon>> timeHorizon() {
		Response<List<TimeHorizon>> rr = new Response<>();
		rr.setData(Arrays.asList(TimeHorizon.values()));
		return rr;
	}

	
	
}
