package com.warehouse.dao;

import java.util.List;

import com.warehouse.model.Warehouse;

public interface WarehouseDAO {
	
	//find warehouses
	public List<Warehouse> findWarehouses();
	//find warehouse by ID
	public Warehouse findById(int id);
	//find warehouse by name
	public Warehouse findByName(String name);
	//delete warehouse
	public Warehouse addWarehouse(Warehouse warehouse);
	//modify supply with id 
	public Warehouse searchByProductId(int id);
	
	public void deleteById(int id);
	void changeSupply(int id, int ammount, int warehouse_id);
	
	
	
	
	
}
