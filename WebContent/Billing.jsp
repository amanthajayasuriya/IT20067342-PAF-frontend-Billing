<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import = "com.Billing" %>
<%@ page import="java.sql.*" %>
<% Class.forName("com.mysql.cj.jdbc.Driver"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ElectroGrid</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/Billing.js"></script>
</head>
<body >
<div class="bg-light">
     <div class="container">
     <div class="row">
     <div class="col-12">
	
     <center><h1 style="font-size:380%;">ELECTRIC BILL GENERATION</h1></center>
	<h1> Bill Generate: </h1>
<br>
		
			 <form id="formItem" name="formItem" method="post" action="Billing.jsp">
			 Account Number: 
			<input id="AccountNumber" name="AccountNumber" type="text" 
			 class="form-control form-control-sm">
			<br> Customer Name: 
			<input id="name" name="name" type="text" 
			 class="form-control form-control-sm">
			<br> Unit Count: 
			<input id="unitCount" name="unitCount" type="text" 
			 class="form-control form-control-sm">
			<br> Month: 
			<input id="month" name="month" type="text" 
			 class="form-control form-control-sm">
			<br>
			
			
			<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary" >
			<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
			</form>
  		
  		<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div class="table table-Stiped" id="divItemsGrid">
				 <%
				 Billing billobj = new Billing(); 
				 out.print(billobj.readUnitCount()); 
				 %>
				</div>
	</div>
	</div>
	</div>
</div>
</body>
</html>