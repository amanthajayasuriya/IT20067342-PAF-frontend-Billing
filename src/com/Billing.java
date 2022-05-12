package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Date;
public class Billing {
	
	
	//DB connection
	
		//establishing connection
			private Connection connect() { 
				
				Connection con = null; 
				try{ 
					Class.forName("com.mysql.jdbc.Driver"); 
			 
					//Provide the correct details: DBServer/DBName, username, password 
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid_paf_project", "root", ""); 
				} catch (Exception e) {
					e.printStackTrace();
				} 
				return con; 
			}
		
		//read all the users account numbers
		
		//Add unit count 
			
		    //Add unit count to the user's account
			public String insertUnitCount(String accno, String uname, String unit, String bmonth) { 
						
						String output = ""; 
						
						
						
						try { 
							Connection con = connect(); 
							
							if (con == null) {
								return "Error while connecting to the database for inserting."; } 
								
								// create a prepared statement
								String query;
							
								query = " insert into billing_tb(`billID`,`AccountNumber`,`name`,`unitCount`,`month`,`date`)" + " values (?, ?, ?, ?, ?,?)" ; 
								PreparedStatement preparedStmt = con.prepareStatement(query);
								 
								// binding values
								preparedStmt.setInt(1, 0); 
								preparedStmt.setInt(2, Integer.parseInt(accno)); 
								preparedStmt.setString(3, uname); 
								preparedStmt.setFloat(4, Float.parseFloat(unit)); 
								preparedStmt.setString(5, bmonth); 
								
								Date date = new Date();
								preparedStmt.setDate(6, new java.sql.Date(date.getTime()));
								
								
								//float no = Float.valueOf(unit.toString());
								//String billAmount= String.valueOf(calculateBill(no));
								
								//preparedStmt.setFloat(6, Float.parseFloat(billAmount)); 
								//preparedStmt.setString(7, issuedDate);
								
								
								
							
								// execute the statement
								preparedStmt.execute(); 
								con.close(); 
								
								String newItems = readUnitCount(); 
								 output = "{\"status\":\"success\", \"data\": \"" + 
								 newItems + "\"}"; 
								
								
						} catch (Exception e) { 
							output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
							System.err.println(e.getMessage()); 
						}
						return output; 
					} 

		
		

		//read alredy billed customers

		public String readUnitCount()
		{ 
				String output = ""; 
	 
			 try
			 { 
			
		     Connection con = connect(); 
			 if (con == null) 
			 { 
				 return "Error while connecting to the database for reading."; 
			 } 
			 
			 
			 // Prepare the html table to be displayed
			 output = "<table border='1'>"
					// + "<th>Bill ID</th>" 
			 		 + "<th>Account Number</th>" 
					 +"<th>Name</th>"
					 + "<th>Unit Count</th>"
					 + "<th> Month</th>" 
					 + "<th> Bill Date</th>" 
					// +"<th> Bill Amount</th>"
					+ "<th>Update</th><th>Remove</th></tr>"; 
			 
			 String query = "select * from billing_tb "; 
			 
			 Statement stmt = (Statement) con.createStatement(); 
			 ResultSet res = ((java.sql.Statement) stmt).executeQuery(query); 
			 
			 // iterate through the rows in the result set
			 while (res.next()) 
			 { 
				 String billID = Integer.toString(res.getInt("billID")); 
				 String AccountNumber = Integer.toString(res.getInt("AccountNumber")); 
				 String name = res.getString("name"); 
				 String unitCount = Integer.toString(res.getInt("unitCount")); 
				 String month = res.getString("month"); 
				 String date = res.getString("date"); 
				// String billAmount = Float.toString(res.getFloat("billAmount"));
				//  String issuedDate = res.getString("issuedDate"); 
				 
				 
				 // Add a row into the html table
				 output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='"+billID+"'>"+ AccountNumber+"</td>"; 
				// output += "<td>" + AccountNumber+ "</td>"; 
				 output += "<td>" + name + "</td>"; 
				 output += "<td>" + unitCount + "</td>";
				 output += "<td>" + month + "</td>"; 
				 output += "<td>" + date + "</td>"; 
				// output += "<td>" + billAmount + "</td>";
				 // buttons
				 output += "<td><form  method='post' action='updateItems.jsp'>"
				 		+ "<input name='btnUpdate' type='button' value='Update' "
						 + "class='btnUpdate btn btn-secondary' data-billid='" + billID + "'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' "
						 + "class='btnRemove btn btn-danger' data-billid='" + billID + "'></td></tr>"; 
			 } 
			 
			// con.close(); 
			
			     // Complete the html table
			     output += "</table>"; 
			 } 
			 
			catch (Exception e) 
			 { 
				 output = "Error while reading the Billing details."; 
				 System.err.println(e.getMessage()); 
			 } 
			
			
			return output; 
		}

	
		//updateBillDetails
		public String updateBill(String bid ,String accno, String uname, String unit, String bmonth)
		{ 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for updating."; 
				 
			 } 
			 // create a prepared statement
			 String query = "UPDATE billing_tb SET AccountNumber=?,name=?,unitCount=?,month=? ,date=? where billID=?";
				
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
				// binding values
			
				preparedStmt.setInt(1, Integer.parseInt(accno)); 
				preparedStmt.setString(2, uname); 
				preparedStmt.setFloat(3, Float.parseFloat(unit)); 
				preparedStmt.setString(4, bmonth); 
				
				//float no = Float.valueOf(unit.toString());
				//float billAmount= calculateBill1(no);
				
				//preparedStmt.setFloat(5,billAmount); 
				//preparedStmt.setString(6, issuedDate);
				Date date = new Date();  
				 preparedStmt.setDate(5, new java.sql.Date(date.getTime()));
				preparedStmt.setInt(6, Integer.parseInt(bid)); 
			
				// execute the statement
				preparedStmt.execute(); 
				
				con.close(); 
				 String newItems = readUnitCount(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";  
		} catch (Exception e) { 
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
			System.err.println(e.getMessage()); 
		}
		return output; 
	}  

	

		//Calculate bill amount according to usage of unit
		private float calculateBill1(float no) {
			
		float sum=0;
			 if (no <= 54) {
				 return  sum=(float) (no*7.85);
			 }
			 if (54 < no && no <= 81) {
				 
				 return sum= (float) ((54 * 7.85) + ((no - 54) * 10)+ 90);
			 }
			 if (81 < no && no <= 108) {
				 
				 return sum= (float) ((54 * 7.85) + (27 * 10)+ ((no - 81)*27.75) + 480);
			 }
			 
			 if (108 < no && no <= 162) {
				 
				 return sum= (float) ((54 * 7.85) + (27 * 10)+ (27 * 27.75) + ((no - 108)*32) + 480);
			 }
			 
			 if (no >162 )
			     return sum =  (float) ((54 * 7.85) + (27 * 10)+ (27 * 27.75) +  (54*32) + ((no - 162)*45) +540 );
			 
			return sum;
			
			
		}	
			
		
		//Delete billing details
		
		public String deleteBilling(String billID) {
			String output = "";
	
			try {
				Connection con = connect();
	
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
	
				// create a prepared statement
				String query = "delete from billing_tb where billID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
	
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(billID));
	
				// execute the statement
				preparedStmt.execute();
				con.close();
	
				String newItems = readUnitCount(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}"; 

	
			} catch (Exception e) {
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
				System.err.println(e.getMessage());
			}
	
			return output;
		}

			

				
		
}

