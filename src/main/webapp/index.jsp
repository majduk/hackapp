<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>loginPage.jsp</title>
<link rel="stylesheet" type="text/css" href="resources/css/screen.css" />
</head>
<body>
    <div id="container">
        <div align="right" class="dualbrand">
            <img src="resources/gfx/dualbrand_logo.png" />
        </div>
        <div id="content">
<% if (session.getAttribute("authenticated") == null) { %>
    <form id="login" action="login.do" method="POST">
        <h2>Login</h2>
        <table>
            <tr>
                <td style="text-align: right;"><label for="email">email:</label>
                </td>
                <td><input type="text" id=email name="email"
                    value="${member.email}" /></td>
            </tr>
            <tr>
                <td style="text-align: right;"><label
                    for="password">Password:</label></td>
                <td><input id="password" name="password"
                    type="password" value="${member.password}" /></td>
            </tr>
        </table>
        <p>
            <input id="login" type="submit" value="Login" /> 
	    <a href="registrationForm.jsp">Register</a> 
        </p>
        <p>
            <label style="color: green;width: 100%;text-align: left;">${infoMessage}</label> 
        </p>
        <p>
             <label style="color: red; width: 100%;text-align: left;">${errorMessage}</label>
        </p>
    </form>
    <% } else {%>
<h4>Oxygen Flow Processing Batches</h4>
<table class="simpletablestyle">
<thead>
<tr>
<th>id</th>
<th>Timestamp</th>
<th>Batch Identifier</th>
</tr>
</thead>
<tbody>
<c:forEach items="${transactions}" var="transaction">
        <tr>
                <td><c:out value="${transaction.id}"/></td>
                <td><c:out value="${transaction.timestamp}"/></td>
                <td><c:out value="${transaction.exid}"/></td>
        </tr>
</c:forEach>
</tbody>
</table>

    <% } %>    
        </div>
	        <div id="footer">
            <img src="resources/gfx/logo.png" alt="Weld logo" />
        </div>
    </div>
</body>
</html>
