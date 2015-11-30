<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>SG Member Search</title>
    <!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
	<script src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.10/js/dataTables.bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.10/css/dataTables.bootstrap.min.css">

</head>
 
<body>
    <div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
            <legend>List of Members </legend>
	            <table id="member-list" class="table table-striped table-bordered">
	                <thead>
	                    <tr>
	                        <th>Status</th>
	                        <th>Race</th>
	                        <th>Vegan</th>
	                        <th>Height</th>
	                        <th>Weight</th>
	                    </tr>
	                </thead>
	                <tbody>
	                <c:forEach items="${members}" var="member">
	                    <tr>
	                        <td>${member.status}</td>
	                        <td>${member.race.ethnicGroup}</td>
	                        <td>${member.isVeg}</td>
	                        <td>${member.height}</td>
	                        <td>${member.weight}</td>
	                    </tr>
	                </c:forEach>
	                </tbody>
	            </table>
        	</div>
    	</div>
   	</div>
   	<script>
	   	$(document).ready(function() {
	    	$('#member-list').DataTable();
		});
   	</script>
</body>
</html>