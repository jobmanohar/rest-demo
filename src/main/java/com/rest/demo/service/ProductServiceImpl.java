package com.rest.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.demo.Exception.ResourceNotFoundException;
import com.rest.demo.model.Product;
import com.rest.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
    private ProductRepository repository;

	@Override
	public Product getProduct(Long id) {
		Optional<Product> product = repository.findById(id);
		
		if(product.isPresent()) {
            return product.get();
        } else {
            throw new ResourceNotFoundException("No product exists for given id :" + id);
        }
	}

	@Override
	public Product addProduct(Product item) {
		Product itemToSave = repository.save(item);
		return itemToSave;
	}

	@Override
	public void updateProduct(Product product) {
		Optional<Product> item = repository.findById(product.getId());
        
        if(item.isPresent())
        {
        	Product p = item.get();
            p.setQuantity(product.getQuantity());
            p = repository.save(p);
        } else {
        	throw new ResourceNotFoundException("No product exists for given id :" + product.getId());
        }
		
	}

	@Override
	public void removeProduct(Long productId) {
		Optional<Product> employee = repository.findById(productId);
        
        if(employee.isPresent())
        {
            repository.deleteById(productId);
        } else {
            throw new ResourceNotFoundException("No product exists for given id:" + productId);
        }
		
	}

}
