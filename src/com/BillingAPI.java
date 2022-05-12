package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BillingAPI
 */
@WebServlet("/BillingAPI")
public class BillingAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Billing billobj = new Billing();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillingAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String output = billobj.insertUnitCount(request.getParameter("AccountNumber"), 
				 request.getParameter("name"), 
				request.getParameter("unitCount"), 
				request.getParameter("month"),
				request.getParameter("billAmount"));
			

response.getWriter().write(output); 
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		 Map paras = getParasMap(request); 
		 String billAmount= paras.get("unitCount").toString();
		 float unitCount=Float.parseFloat(billAmount);
		 Float amount = billobj.calculateBill1(unitCount);
		 String output = billobj.updateBill(paras.get("hidItemIDSave").toString(), 
											 paras.get("AccountNumber").toString(), 
											paras.get("name").toString(), 
											paras.get("unitCount").toString(),
											paras.get("month").toString(),
											amount.toString());
											
											
		 
		response.getWriter().write(output); 
		
		
		
	
	
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request); 
		 String output = billobj.deleteBilling(paras.get("billID").toString()); 
		response.getWriter().write(output); 
		
	}
	
	// Convert request parameters to a Map
			private static Map getParasMap(HttpServletRequest request) 
			{ 
			 Map<String, String> map = new HashMap<String, String>(); 
			try
			 { 
			 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
			 String queryString = scanner.hasNext() ? 
			 scanner.useDelimiter("\\A").next() : ""; 
			 scanner.close(); 
			 String[] params = queryString.split("&"); 
			 for (String param : params) 
			 { 
			
			 String[] p = param.split("="); 
			 map.put(p[0], p[1]); 
			 } 
			 } 
			catch (Exception e) 
			 { 
			 } 
			return map; 
			}
	

}
