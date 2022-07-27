package com.warehouse.model;

public class Warehouse {
	
	private int warehouseId;
	private String warehouseName;
	private String location;
	private int capacityMax;
	private int capacityCurrent;
	private int supply;
	private int product_id;
	
	
	public Warehouse() {
		super();
	}


	public Warehouse(int warehouseId, String warehouseName, String location, int capacityMax, int capacityCurrent,
			int supply, int product_id) {
		super();
		this.warehouseId = warehouseId;
		this.warehouseName = warehouseName;
		this.location = location;
		this.capacityMax = capacityMax;
		this.capacityCurrent = capacityCurrent;
		this.supply = supply;
		this.product_id = product_id;
	}




	public int getWarehouseId() {
		return warehouseId;
	}


	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}


	public String getWarehouseName() {
		return warehouseName;
	}


	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public int getCapacityMax() {
		return capacityMax;
	}


	public void setCapacityMax(int capacityMax) {
		this.capacityMax = capacityMax;
	}


	public int getCapacityCurrent() {
		return capacityCurrent;
	}


	public void setCapacityCurrent(int capacityCurrent) {
		this.capacityCurrent = capacityCurrent;
	}

		public int getProduct_id() {
		return product_id;
	}


	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}


	public int getSupply() {
		return supply;
	}


	public void setSupply(int supply) {
		this.supply = supply;
	}


	@Override
	public String toString() {
		return "Warehouse [warehouseId=" + warehouseId + ", warehouseName=" + warehouseName + ", location=" + location
				+ ", capacityMax=" + capacityMax + ", capacityCurrent=" + capacityCurrent + ", supply=" + supply
				+ ", product_id=" + product_id + "]";
	}
	
	
	

	
}
