package com.objectway.codemotion.beans;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("PRODUCTS")
public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	private String name;
	private String isin;
	private String asset; 
	private String region;
	private Double volatility;
	
	public Product() {
	}
	
	public Product(String region, String asset) {
		this.region = region;
		this.asset = asset;
	}
}
