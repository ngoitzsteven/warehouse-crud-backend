package com.warehouse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.warehouse.connection.WarehouseDBCreds;
import com.warehouse.model.Warehouse;

public class WarehouseDAOImpl implements WarehouseDAO {

	public static WarehouseDBCreds creds = WarehouseDBCreds.getInstance();

	/**
	 * It takes a SQL query, executes it, and returns the results as a list of
	 * Warehouse objects
	 * 
	 * @return A list of warehouses
	 */
	@Override
	public List<Warehouse> findWarehouses() {
		String sql = "select * from warehouse";
		try (Connection conn = creds.getConnection()) {
			LinkedList<Warehouse> warehouses = new LinkedList<>();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// one item is if (rs.next())
			while (rs.next()) {
				// int id, String productName, String partNumber
				Warehouse warehouse = new Warehouse(
						rs.getInt("warehouse_Id"), 
						rs.getString("warehouse_name"),
						rs.getString("location"), 
						rs.getInt("capacity_Max"), 
						rs.getInt("capacity_Current"),
						rs.getInt("supply"), 
						rs.getInt("product_Id"));
				warehouses.add(warehouse);
			}
			System.out.println("findWarehouses");
			return warehouses;

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return null;
	}

	/**
	 * This function is used to find a warehouse by its id
	 * 
	 * @param id int
	 * @return The method is returning a Warehouse object.
	 */
	@Override
	public Warehouse findById(int id) {
		String sql = "SELECT * from warehouse WHERE ID = ?";

		try (Connection conn = creds.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Warehouse(rs.getInt("warehouseId"), rs.getString("warehouse_name"), rs.getString("location"),
						rs.getInt("capacityMax"), rs.getInt("capacityCurrent"), rs.getInt("supply"),
						rs.getInt("product_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// The above code is deleting a product from the database.
	@Override
	public Warehouse findByName(String name) {
		String sql = "SELECT * from warehouse WHERE warehouse_name = ?";

		try (Connection conn = creds.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Warehouse(rs.getInt("warehouseId"), rs.getString("warehouse_name"), rs.getString("location"),
						rs.getInt("capacityMax"), rs.getInt("capacityCurrent"), rs.getInt("supply"),
						rs.getInt("product_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This function deletes a product from the database by its id
	 * 
	 * @param id int
	 */
	@Override
	public void deleteById(int id) {
		String sql = "DELETE FROM warehouse WHERE id = ?";
		try (Connection conn = creds.getConnection()) {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int row = ps.executeUpdate();
			if (row != 0) {
				conn.commit();
			} else
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Deleting a product from the database by its name.
//	@Override
//	public void deleteByName(String name) {
//			String sql = "DELETE FROM product WHERE id = ?";
//			try  (Connection conn = creds.getConnection()){
//				conn.setAutoCommit(false); 
//				PreparedStatement ps = conn.prepareStatement(sql);
//				ps.setString(1, name);
//				int row = ps.executeUpdate();
//				if (row != 0) {
//					conn.commit();
//				}
//				else
//					conn.rollback();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			
//		}

	@Override
	public Warehouse addWarehouse(Warehouse warehouse) {
		String sql = "insert into warehouse (warehouse_id, warehouse_name, location, capacity_max, capacity_current, supply, product_id)"
				+ " values (?,?,?,?,?,?,?')";
		try (Connection conn = creds.getConnection()) {
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(sql);
			// how to target id, product name and part number
			ps.setInt(1, warehouse.getWarehouseId());
			ps.setString(2, warehouse.getWarehouseName());
			ps.setString(3, warehouse.getLocation());
			ps.setInt(4, warehouse.getCapacityMax());
			ps.setInt(5, warehouse.getCapacityCurrent());
			ps.setInt(6,warehouse.getSupply());
			ps.setInt(7, warehouse.getProduct_id());

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected != 0) {
				conn.commit();
				return warehouse;
			} else
				conn.rollback();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void changeSupply(int id, int ammount, int warehouse_id) {
		String sql = "UPDATE warehouse set supply = supply - ? where product_id = ? AND warehouse_id = ? ";
		try (Connection conn = creds.getConnection()) {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			//how to target id, product name and part number
			ps.setInt(1, id);
			ps.setInt(2, ammount);
			ps.setInt(3, warehouse_id);
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected != 0) {
				conn.commit();
			}
			else
				conn.rollback();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Warehouse searchByProductId(int id) {
		String sql = "SELECT * from warehouse WHERE product_id = ?";

		try (Connection conn = creds.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Warehouse(rs.getInt("id"), rs.getString("warehouse_name"), rs.getString("location"),
						rs.getInt("capacityMax"), rs.getInt("capacityCurrent"), rs.getInt("supply"),
						rs.getInt("product_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}
