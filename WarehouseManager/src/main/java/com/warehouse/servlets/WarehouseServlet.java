package com.warehouse.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehouse.dao.WarehouseDAO;
import com.warehouse.dao.WarehouseDAOImpl;
import com.warehouse.model.Warehouse;

@WebServlet(urlPatterns = "/warehouse/*")
public class WarehouseServlet extends HttpServlet {

	private static final long serialVersionUID = 8697948623649022156L;
	// instantiate the dao
	WarehouseDAO dao = new WarehouseDAOImpl();
	// Instantiate object mapper
	ObjectMapper mapper = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String ask = req.getPathInfo();
			String[] ware = ask.split("/");
			String identifier = ware[1];
			int id = Integer.parseInt(identifier);
			Warehouse warehouse = dao.findById(id);
			resp.setContentType("application/json");
			resp.getWriter().print(mapper.writeValueAsString(warehouse));
		} catch (NumberFormatException e) {
			String ask = req.getPathInfo();
			String[] produ = ask.split("/");
			String identifier = produ[1];
			Warehouse warehouse = dao.findByName(identifier);
			resp.setContentType("application/json");
			resp.getWriter().print(mapper.writeValueAsString(warehouse));
		} catch (ArrayIndexOutOfBoundsException e) {
			List<Warehouse> warehouse = dao.findWarehouses();
			resp.setContentType("application/json");
			resp.getWriter().print(mapper.writeValueAsString(warehouse));
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream reqBody = req.getInputStream();
		Warehouse newWarehouse = mapper.readValue(reqBody, Warehouse.class);
		newWarehouse = dao.addWarehouse(newWarehouse);
		if (newWarehouse != null) {
			resp.setContentType("application/json");
			resp.getWriter().print(mapper.writeValueAsString(newWarehouse));
			resp.setStatus(201); // The default is 200
			System.out.println(newWarehouse + " Has been added");
		} else {

			resp.getWriter().print(mapper.writeValueAsString("Unable to create "));
		}

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
}
