package com.rest.demo.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.demo.Exception.ResourceNotFoundException;
import com.rest.demo.model.Product;
import com.rest.demo.service.ProductService;

@RestController("api/v1")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/addItem", method = RequestMethod.POST)
	public ResponseEntity<Product> addProduct(@RequestBody Product item) { 
		Product savedItem = productService.addProduct(item);
		
		return new ResponseEntity<Product>(savedItem, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateItem/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateItem(@PathVariable("id") Long productId, @RequestBody Product item) throws ResourceNotFoundException {
		
		Product product = productService.getProduct(productId);
		if(product == null) {
			throw new ResourceNotFoundException("Employee not found for this id"); 		
		}
		else {
			product.setQuantity(item.getQuantity());
			productService.updateProduct(product);
			
		}
		
		return new ResponseEntity<String>("updated Item", HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteItem/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteItem(@PathVariable(value = "id") Long productId) throws ResourceNotFoundException {
		Product product = productService.getProduct(productId);
		if(product == null) {
			throw new ResourceNotFoundException("Employee not found for this id"); 		
		}
		else {
			productService.removeProduct(product);
		}
		return new ResponseEntity<String>("Deleted Item", HttpStatus.OK);
	
	}
	
	
	/*
	 * @RequestMapping(value="/item/{id}", method = RequestMethod.GET) public
	 * ResponseEntity getProductInfo(@PathVariable("id") Long productId){ Product p
	 * = productService.getItem(productId);
	 * 
	 * return new ResponseEntity(p, HttpStatus.OK); }
	 */
	
}
