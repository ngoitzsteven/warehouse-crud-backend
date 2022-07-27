package com.warehouse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.sql.DriverManager;

import com.warehouse.connection.WarehouseDBCreds;
import com.warehouse.model.Product;

public class ProductDAOImpl implements ProductDAO{
	
	public static WarehouseDBCreds creds = WarehouseDBCreds.getInstance(); 
	
	@Override
	public List<Product> findProducts() {
		/*
		define sql query as string,
		try with resources to make connection
		use prepared statements 
		define result set
		while result set next
		map result set to model
		return model object / list of model object
		finish with catch block 
		*/
		String sql = "select * from product";		
		try(Connection conn = WarehouseDBCreds.getInstance().getConnection()){
			LinkedList<Product> products = new LinkedList<>();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			//one item is if (rs.next())
			while(rs.next()) {
				//int id, String productName,  String partNumber
				Product product = new Product(
						rs.getInt("id"), 
						rs.getString("product_name"), 
						rs.getString("part_number"));
				products.add(product);

			}
			System.out.println("findProducts");
			return products;
				
		} catch(SQLException e){
			e.printStackTrace();
						
		}
		
		return null;
	}

	/**
	 * It takes an id, and returns a product object
	 * 
	 * @param id the id of the product you want to find
	 * @return A product object
	 */
	@Override
	public Product findById(int id) {
		String sql = "SELECT * FROM PRODUCT WHERE ID = ?";
		
		try (Connection conn = creds.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				return new Product(rs.getInt("id"), rs.getString("product_name"), 
						rs.getString("part_number"));
				
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * It takes a string, and returns a product object
	 * 
	 * @param name the name of the product
	 * @return A Product object
	 */
	@Override
	public Product findByPartName(String name) {
		String sql = "SELECT * FROM PRODUCT WHERE PRODUCT_NAME = ?";
		
		try (Connection conn = creds.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return new Product(rs.getInt("id"), rs.getString("product_name"), 
						rs.getString("part_number"));
			}
			

		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}


	/**
	 * It deletes a product from the database by id
	 * 
	 * @param id the id of the product to be deleted
	 */
	@Override
	public void deleteById(int id) {
		String sql = "DELETE FROM product WHERE id = ?";
		try  (Connection conn = creds.getConnection()){
			conn.setAutoCommit(false); 
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int row = ps.executeUpdate();
			if (row != 0) {
				conn.commit();
			}
			else
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * I'm trying to add a product to the database, but I'm not sure how to target the id, product name
	 * and part number
	 * 
	 * @param product the object that is being passed in
	 */
	@Override
	public Product addProduct(Product product) {
		String sql = "INSERT INTO product (id, product_name, part_number) VALUES (?,?,?)";
		try (Connection conn = creds.getConnection()){
			conn.setAutoCommit(false); 
			
			PreparedStatement ps = conn.prepareStatement(sql);
			//how to target id, product name and part number
			ps.setInt(1, product.getId());
			ps.setString(2, product.getProductName());
			ps.setString(3, product.getPartNumber());
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected != 0) {
				conn.commit();
				return product;
			}
			else
				conn.rollback();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
