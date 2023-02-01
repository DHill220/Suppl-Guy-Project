package com.aca.supplyguy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.aca.supplyguy.model.Category;
import com.aca.supplyguy.model.Product;

public class ProductDaoImpl implements ProductDao {

	
	private static String selectAllProducts = 
			"SELECT product_id, category, product, createTime, updateTime " + 
			"FROM products ";
	
	private static String selectProductByTitle = 
			"SELECT product_id, category, product, createTime, updateTime " +
			"FROM products " +
			"WHERE product = ? ";
			
	private static String selectProductByCategory = 
			"SELECT product_id, category, product, createTime, updateTime " +
			"FROM products " + 
			"WHERE category = ? ";
	
	private static String selectProductById = 
			"SELECT product_id, category, product, createTime, updateTime " +
			"FROM products " +
			"WHERE product_id = ? ";
	
	private static String deleteProductById = 
			"DELETE FROM products " +
			"WHERE product_id = ? ";
	
	private static String updateProductById = 
			"UPDATE products " +
			"SET product = ?," +
			"category = ? " +
			"WHERE product_id = ? ";
	
	private static String insertProduct = 
			"INSERT INTO products (product, category) " +
			"VALUES " +
			"(? , ?)";
	
	private static String selectNewProductId =
			"SELECT LAST_INSERT_ID() AS 'product_Id";

	private String selectProductByCreateTime =
			"SELECT createTime " +
			"FROM products ";
	
	@Override
	public List<Product> getProducts() {
		List<Product> myProduct = new ArrayList<Product>();
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = MariaDBUtil.getConnection();
		
		try {
			statement = conn.createStatement();
			result = statement.executeQuery(selectAllProducts);
			myProduct= makeProduct(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return myProduct;		
	}

	@Override
	public List<Product> getProductByCategory(Category category) {
		List<Product> myProduct = new ArrayList<Product>();
		ResultSet result = null;
		PreparedStatement ps = null;
		
		Connection conn = MariaDBUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(selectProductByCategory);
			ps.setString(1, category.toString());
			
			result = ps.executeQuery();
			myProduct = makeProduct(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return myProduct;		
	}

	@Override
	public List<Product> getProductById(Integer product_id) {
		List<Product> myProduct = new ArrayList<Product>();
		ResultSet result = null;
		PreparedStatement ps = null;
		
		Connection conn = MariaDBUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(selectProductById);
			ps.setInt(1, product_id);
			
			result = ps.executeQuery();
			myProduct = makeProduct(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return myProduct;		
	}

	@Override
	public List<Product> getProductByTitle(String title) {
		List<Product> myProduct = new ArrayList<Product>();
		ResultSet result = null;
		PreparedStatement ps = null;
		
		Connection conn = MariaDBUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(selectProductByTitle);
			title = "%" + title + "%";
			ps.setString(1, title);
			
			result = ps.executeQuery();
			myProduct = makeProduct(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myProduct;		
	}

	@Override
	public Product createProduct(Product newProduct) {
		int updateRowCount = 0;
		PreparedStatement ps = null;
		
		Connection conn = MariaDBUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(insertProduct);
			ps.setString(1, newProduct.getTitle());
			ps.setString(2, newProduct.getCategory().toString());
			
			
			updateRowCount = ps.executeUpdate();
			System.out.println("insert row count: " + updateRowCount);
			
			int productId = getNewProductId(conn);
			newProduct.setId(productId);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return newProduct;		
	}

	@Override
	public Product updateProduct(Product updateProduct) {
		List<Product> products = getProductById(updateProduct.getId());
//		Product productToUpdate = null;
		
		if(products.size() > 0) {
//			productToUpdate = products.get(0);
			int updateRowCount = 0;
			PreparedStatement ps = null;
			
			Connection conn = MariaDBUtil.getConnection();
			try {
				ps = conn.prepareStatement(updateProductById);
				ps.setString(1, updateProduct.getTitle());				
				ps.setString(2, updateProduct.getCategory().toString());
				ps.setInt(3, updateProduct.getId());
				updateRowCount = ps.executeUpdate();
				System.out.println("update row count: " + updateRowCount);
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		return updateProduct;		
	}


	@Override
	public Product deleteProduct(Integer id) {
		List<Product> products = getProductById(id);
		Product productToDelete = null;
		
		if(products.size() > 0);
			productToDelete = products.get(0);
			int updateRowCount = 0;
			PreparedStatement ps = null;
			
			Connection conn = MariaDBUtil.getConnection();
			try {
				ps = conn.prepareStatement(deleteProductById);
				ps.setInt(1, id);
				updateRowCount = ps.executeUpdate();
				System.out.println("rows deleted: " + updateRowCount);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return productToDelete;		
	}
	
	public int getNewProductId(Connection conn) {
		ResultSet result = null;
		Statement statement = null;
		int newProductId = 000;
		
		try {
			statement = conn.createStatement();
			result = statement.executeQuery(selectNewProductId);
			
			while(result.next()) {
				newProductId = result.getInt("product_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return newProductId;
	}
	
	private List<Product> makeProduct(ResultSet result) throws SQLException {
		List<Product> myProduct = new ArrayList<Product>();
		
		while(result.next()) {
			Product products = new Product();
			
			products.setId(result.getInt("product_id"));
			products.setTitle(result.getString("product"));
			
			String categoryString = result.getString("category");
			products.setCategory(Category.convertStringToCategory(categoryString));
			//TODO set update and create date time 
			products.setUpdateDateTime(result.getObject("updateTime", LocalDateTime.class));
			products.setCreateDateTime(result.getObject("createTime", LocalDateTime.class));
			
			
			myProduct.add(products);
		}
		
		return myProduct;
	}

	@Override
	public List<Product> getReport(Integer createTime, Integer updateTime) {
		List<Product> myProduct = new ArrayList<Product>();
		ResultSet result = null;
		PreparedStatement ps = null;
		
		Connection conn = MariaDBUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(selectProductByCreateTime );
			ps.setInt(1, createTime);
			ps.setInt(2, updateTime);
			
			result = ps.executeQuery();
			myProduct = makeProduct(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return myProduct;
	}

	@Override
	public Product deleteProductById(Integer ProductId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}	

	