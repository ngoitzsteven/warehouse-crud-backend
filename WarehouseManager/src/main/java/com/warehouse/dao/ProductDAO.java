package com.warehouse.dao;

import java.util.List;

import com.warehouse.model.Product;

public interface ProductDAO {
	//CRUD Operations
	//Find List of Products
	public List<Product> findProducts();
	//Find Specific Product
	public Product findById(int id);
	//find product by part name 
	public Product findByPartName(String name);
	//delete a product 
	public void deleteById(int id);

	//add a product
	public Product addProduct(Product product);
	
	
	
}
