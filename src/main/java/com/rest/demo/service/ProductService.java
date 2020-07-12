package com.rest.demo.service;

import com.rest.demo.model.Product;

public interface ProductService {
	
	public Product getProduct(Long id);
	public Product addProduct(Product p);
	public void updateProduct(Product p);
	public void removeProduct(Product product);
	
}