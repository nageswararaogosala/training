<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Training Information</title>
</head>

<body>

 <div align="center">

        <table border="1" bordercolor="dimgray">
            
             <th>Employee Id</th>
             <th>Employee  Name</th>
             <th>Mail</th>
             <th>Designation</th>
               <th>Role</th>
                 <th>Location</th>
             
             <th>Update</th>
              <th>Delete</th>
           
 
  <c:forEach var="tdetails"  items="${tdetail}">
   <tr>  
   <td>${Edetails.Employee.EmployeeId}</td>
       <td>${Edetails.Employee.EmployeeName}</td>
       <td>${Edetails.Employee.Mail}
          <td>${Edetails.Employee.Designation}</td>
         <td>${Edetails.Employee.Role}</td>
          <td>${Edetails.Employee.Location}</td>
           
         <td><a href="editEdetails?trainingId=${Edetails.EmployeeId}"  style="color: #006400">Update Details</a></td>
         <td><a href="deleteEdetails?trainingId=${Edetails.EmployeeId}" style="color: #cc0000">Delete</a></td>
            </tr>
         </c:forEach>
       
         
       
         
</table>
</div>
<center>
<a href="newTdetails">add new details</a>
</center>
</body>
</html>