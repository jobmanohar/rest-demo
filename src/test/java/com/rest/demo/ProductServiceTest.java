package com.rest.demo;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.Before;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.rest.demo.Exception.ResourceNotFoundException;
import com.rest.demo.model.Product;
import com.rest.demo.repository.ProductRepository;
import com.rest.demo.service.ProductServiceImpl;

public class ProductServiceTest {
	
	@InjectMocks
	ProductServiceImpl productService;
	
	@Mock
	ProductRepository mockedProductRepository;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void getNonExistingProductTest() {
		assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> productService.getProduct(1L));
	}
	
	@Test
	public void addProductTest() {
		Mockito.when(mockedProductRepository.save(any(Product.class))).thenReturn(getDummyProduct().get());
		assertNotNull(productService.addProduct(getDummyProduct().get()));
	}
	
	@Test
	public void updateProductTest() {
		Mockito.when(mockedProductRepository.findById(any())).thenReturn(getDummyProduct());
		productService.updateProduct(getDummyProduct().get());
	}
	
	@Test
	public void updateNonExistingProductTest() {
		assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> productService.updateProduct(getDummyProduct().get()));
	}
	
	@Test
	public void removeProductTest() {
		Mockito.when(mockedProductRepository.findById(any())).thenReturn(getDummyProduct());
		productService.removeProduct(1L);
	}
	
	@Test
	public void removeNonExistingProductTest() {
		assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> productService.removeProduct(1L));
	}
	
	@Test
	public void getProductTest() {
		Mockito.when(mockedProductRepository.findById(any(Long.class))).thenReturn(getDummyProduct());
		
		assertThat(productService.getProduct(1L)).isEqualToComparingFieldByFieldRecursively(getDummyProduct().get());
	}
	
	private Optional<Product> getDummyProduct() {
		Product result = new Product(4, 15000L, "Dell Laptop", "computer");
		Optional<Product> obj = Optional.of(result);
		return obj;
	}
}
