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
import com.warehouse.dao.ProductDAO;
import com.warehouse.dao.ProductDAOImpl;
import com.warehouse.model.Product;



@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 3222533321735096613L;
	//instantiate the dao
	ProductDAO dao = new ProductDAOImpl();
	// Instantiate object mapper
	ObjectMapper mapper = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		try {
			String ask = req.getPathInfo();
			String[] produ = ask.split("/");
			String identifier = produ[1];
			int id = Integer.parseInt(identifier);
			Product product = dao.findById(id);
			resp.setContentType("application/json");
	 		resp.getWriter().print(mapper.writeValueAsString(product));
		} catch (NumberFormatException e) {
			String ask = req.getPathInfo();
			String[] produ = ask.split("/");
			String identifier = produ[1];
			Product product = dao.findByPartName(identifier);
			resp.setContentType("application/json");
	 		resp.getWriter().print(mapper.writeValueAsString(product));	
		} catch (ArrayIndexOutOfBoundsException e) {
			List<Product> product = dao.findProducts();
			resp.setContentType("application/json");
	 		resp.getWriter().print(mapper.writeValueAsString(product));	
	 	}

	}
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		InputStream reqBody = req.getInputStream();
		Product newProduct = mapper.readValue(reqBody, Product.class);
		newProduct = dao.addProduct(newProduct);
		if (newProduct != null) {
			resp.setContentType("application/json");
			resp.getWriter().print(mapper.writeValueAsString(newProduct));
			resp.setStatus(201); // The default is 200
			System.out.println(newProduct + " Has been added");
		} else {
			
			resp.getWriter().print(mapper.writeValueAsString("Unable to create "));
		}
	}	
	

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String val = req.getPathInfo();
		String[] sortString = val.split("/");
		String identifier = sortString[1];
		try {
			int id = Integer.parseInt(identifier);
			dao.deleteById(id);
		} catch (NumberFormatException e) {
			resp.getWriter().print(mapper.writeValueAsString("Product Not Found"));

		}
	}
}
