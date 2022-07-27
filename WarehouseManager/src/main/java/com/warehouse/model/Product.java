package com.warehouse.model;

public class Product {
	///pojo = plain ole' java object
	
	private int id;
	private String productName;
	private String partNumber;

	public Product() {
		super();
	}
	
	public Product(int id, String productName, String partNumber) {
		super();
		this.id = id;
		this.productName = productName;
		this.partNumber = partNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", partNumber=" + partNumber + "]";
	}
	
}
	
