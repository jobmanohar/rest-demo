package com.rest.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.hamcrest.Matchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.demo.controller.ProductController;
import com.rest.demo.model.Product;
import com.rest.demo.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
    @MockBean
    ProductService mockedProductService;
	
	@Before
	public void setup() {
		Product result = getDummyProduct();
		Mockito.when(mockedProductService.addProduct(any(Product.class))).thenReturn(result);
	}
	
	@Test
	public void addProduct() throws Exception 
	{
		
		Product item = new Product(4, 15000L, "Dell Laptop", "computer");
	    mvc.perform( MockMvcRequestBuilders
	        .post("/api/v1/addItem")
	        .content(asJsonString(item))
	        .contentType(MediaType.APPLICATION_JSON)
	        .accept(MediaType.APPLICATION_JSON))
	        .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", Matchers.is(4)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("Dell Laptop")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.category",Matchers.is("computer")));
	}
	
	@Test
	public void updateProduct() throws Exception 
	{
		Mockito.when(mockedProductService.getProduct(any(Long.class))).thenReturn(getDummyProduct());
		Product item = new Product(6, 15000L, "Dell Laptop", "computer");
	    mvc.perform( MockMvcRequestBuilders
	        .put("/api/v1/updateItem/1")
	        .content(asJsonString(item))
	        .contentType(MediaType.APPLICATION_JSON)
	        .accept(MediaType.APPLICATION_JSON))
	        .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void updateNonExistingProduct() throws Exception 
	{
		Mockito.when(mockedProductService.getProduct(any(Long.class))).thenReturn(null);
		
		Product item = new Product(6, 15000L, "Dell Laptop", "computer");
	    mvc.perform( MockMvcRequestBuilders
	        .put("/api/v1/updateItem/111")
	        .content(asJsonString(item))
	        .contentType(MediaType.APPLICATION_JSON)
	        .accept(MediaType.APPLICATION_JSON))
	        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	@Test
	public void deleteProduct() throws Exception 
	{
		Mockito.when(mockedProductService.getProduct(any(Long.class))).thenReturn(getDummyProduct());
		
	    mvc.perform(MockMvcRequestBuilders.delete("/api/v1/deleteItem/1")
	        .accept(MediaType.APPLICATION_JSON))
	        .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void deleteNonExistingProduct() throws Exception 
	{
		Mockito.when(mockedProductService.getProduct(any(Long.class))).thenReturn(null);
		
	    mvc.perform(MockMvcRequestBuilders.delete("/api/v1/deleteItem/1")
	        .accept(MediaType.APPLICATION_JSON))
	        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	 
	private Product getDummyProduct() {
		Product result = new Product(4, 15000L, "Dell Laptop", "computer");
		return result;
	}

	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	 
}
