package com.objectway.codemotion.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.objectway.codemotion.beans.Product;
import com.objectway.codemotion.request.Request;
import com.objectway.codemotion.response.Response;

@CrossOrigin
@RequestMapping("/suitability")
public interface SuitabilityController {

	@PostMapping("/check")
	Response<List<Product>> suitabilityCheck(@RequestBody Request req);
}
