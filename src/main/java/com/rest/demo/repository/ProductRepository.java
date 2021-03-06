package com.rest.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rest.demo.model.Product;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

}
