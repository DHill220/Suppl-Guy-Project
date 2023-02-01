package com.aca.supplyguy.service;

import java.util.List;

import com.aca.supplyguy.model.RequestError;
import com.aca.supplyguy.dao.ProductDao;
import com.aca.supplyguy.dao.ProductDaoImpl;
import com.aca.supplyguy.model.Category;
import com.aca.supplyguy.model.Product;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class ProductService {
	
	private ProductDao productDao = new ProductDaoImpl();
	
	public List<Product> getProducts() {
		return productDao.getProducts();
	}
	
	public List<Product> getProductByCategory(Category category){
		return productDao.getProductByCategory(category);
	}
	
	public List<Product> getProductById(Integer product_id){
		return productDao.getProductById(product_id);
	}
	
	public List<Product> getProductByTitle(String title){
		return productDao.getProductByTitle(title);
	}
	
	public Product createProduct(Product newProduct) {
		return productDao.createProduct(newProduct);
	}
	
	public Product updateProduct(Product updateProduct) {
		validateTitle(updateProduct.getTitle());
		validateProductId(updateProduct.getId());
		return productDao.updateProduct(updateProduct);
	}
	
	private void validateProductId(Integer product_id) {
		List<Product> product = productDao.getProductById(product_id);
		if(product.size() == 0) {
			RequestError error = new RequestError();
			error.setId(3);
			error.setMessage("Invalid Id " + "'" + product_id + "'" + " for product id. Product Id didn't exist.");
			Response response = Response.status(400).entity(error).build();
			throw new WebApplicationException(response);
		}
	}
	
	private void validateTitle(String title) {
		if (null == title || title.length() < 1) {
			RequestError error = new RequestError();
			error.setId(2);
			error.setMessage("Invalid value " + "'" + title + "'" + ". Value must contain one or more characters.");
			Response response = Response.status(400).entity(error).build();
			throw new WebApplicationException(response);
			}		
	}
	
	public Product deleteProduct(Integer product_id) {
		validateProductId(product_id);
		return productDao.deleteProduct(product_id);
	}

	public List<Product> getReport(Integer createTime, Integer updateTime) {
		validateCreateTime(createTime);
		validateCreateTime(updateTime);
		return productDao.getReport(createTime, updateTime);
	}
	
	private void validateCreateTime(Integer createTime) {
		Integer LocalDate;
		LocalDate = 12/1/22;
		
		if(createTime < LocalDate || createTime > 2200) {
			RequestError error = new RequestError();
			error.setId(1);
			error.setMessage("Invalid value for release date " + "'" + createTime +
					"'" + ". Value must be between < 12/1/22 and > year 2200");
			Response response = Response.status(400).entity(error).build();
			throw new WebApplicationException(response);
		}
	}	
}

