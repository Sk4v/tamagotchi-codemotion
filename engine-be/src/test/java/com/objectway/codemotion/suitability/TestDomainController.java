package com.objectway.codemotion.suitability;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.objectway.codemotion.beans.Product;
import com.objectway.codemotion.beans.Region;
import com.objectway.codemotion.beans.RiskProfile;
import com.objectway.codemotion.beans.TimeHorizon;
import com.objectway.codemotion.controller.DomainController;
import com.objectway.codemotion.response.Response;


public class TestDomainController extends AbstractSuitabilityTest{
	
	@Autowired
	DomainController domainController;

	@Test
	public void getProducts(){
		Response<List<Product>> res = domainController.products();
		assertEquals(HttpStatus.OK, res.getStatus());
	}
	
	@Test
	public void getRegions(){
		Response<List<Region>> res = domainController.regions();
		assertEquals(HttpStatus.OK, res.getStatus());
		List<Region> data = res.getData();
		ArrayList<Region> expectedData = new ArrayList<Region>(EnumSet.allOf(Region.class));
		assertTrue(data.containsAll(expectedData));
	}
	
	@Test
	public void getRiskProfile(){
		Response<List<RiskProfile>> res = domainController.riskProfile();
		assertEquals(HttpStatus.OK, res.getStatus());
		List<RiskProfile> data = res.getData();
		ArrayList<RiskProfile> expectedData = new ArrayList<RiskProfile>(EnumSet.allOf(RiskProfile.class));
		assertTrue(data.containsAll(expectedData));
	}
	
	@Test
	public void getTimeHorizon(){
		Response<List<TimeHorizon>> res = domainController.timeHorizon();
		assertEquals(HttpStatus.OK, res.getStatus());
		List<TimeHorizon> data = res.getData();
		ArrayList<TimeHorizon> expectedData = new ArrayList<TimeHorizon>(EnumSet.allOf(TimeHorizon.class));
		assertTrue(data.containsAll(expectedData));
	}
	
	
}
