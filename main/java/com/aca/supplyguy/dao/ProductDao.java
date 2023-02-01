package com.aca.supplyguy.dao;

import java.util.List;

import com.aca.supplyguy.model.*;
import com.aca.supplyguy.model.*;

public interface ProductDao {
	public List<Product> getProducts();
	public List<Product> getProductByCategory(Category category);
	public List<Product> getProductById(Integer product_id);
	public List<Product> getProductByTitle(String title);
	public Product createProduct(Product newProduct);
	public Product updateProduct(Product updateProduct);
	public Product deleteProductById(Integer product_id);
	Product deleteProduct(Integer product_id);
	public List<Product> getReport(Integer createTime, Integer updateTime);

}
