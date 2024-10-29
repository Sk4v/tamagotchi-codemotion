package com.objectway.codemotion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.objectway.codemotion.beans.Product;

@Repository
public interface SuitabilityRepository extends CrudRepository<Product, Long>, QueryByExampleExecutor<Product> {
	
}
