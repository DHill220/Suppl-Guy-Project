package com.aca.supplyguy.controller;

import java.util.List;

import com.aca.supplyguy.model.Category;
import com.aca.supplyguy.model.Product;
import com.aca.supplyguy.service.ProductService;


import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/supplyguy")
public class SupplyGuyController {
	
	private ProductService service = new ProductService();
	
	
	@GET
	@Path("/products")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProducts() {
		return service.getProducts();
	}	
	
//	@GET
//	@Path("/category/{category}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Product> getProductByCategory(@PathParam("category") Category category){
//		return service.getProductByCategory(category);
//	}
	
	@GET
	@Path("/products/{productIdValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProductById(@PathParam("productIdValue")Integer product_id){
		return service.getProductById(product_id);
	}
	
//	@GET
//	@Path("/products/{titleValue}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Product> getProductByTitle(@PathParam("titlevalue")String title){
//		return service.getProductByTitle(title);
//	}
	
	@POST
	@Path("/products")
	@Consumes(MediaType.APPLICATION_JSON)
	public Product createProduct(Product newProduct) {
		return service.createProduct(newProduct);
	}
	
	@PUT
	@Path("/products/{product_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Product updateProduct(Product updateProduct) {
		return service.updateProduct(updateProduct);
	}
	
	@DELETE
	@Path("/products/{product_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product deleteProduct(@PathParam("product_id")Integer productId){
		return service.deleteProduct(productId);
	}
	
	@GET
	@Path("/report")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getReport(@QueryParam("createTime") Integer createTime,
								@QueryParam("updateTime") Integer updateTime){
		return service.getReport(createTime, updateTime);
	}

}
