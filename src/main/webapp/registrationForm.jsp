<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1" session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>registrationPage.jsp</title>
<link rel="stylesheet" type="text/css" href="resources/css/screen.css" />
</head>
<body>
    <div id="container">
        <div align="right" class="dualbrand">
            <img src="resources/gfx/dualbrand_logo.png" />
        </div>
        <div id="content">

    <form id="reg" action="register.do" method="POST">
        <h2>Member Registration</h2>
        <table>
            <tr>
                <td style="text-align: right;"><label for="name">Name:</label>
                </td>
                <td><input type="text" id=name name="name"
                    value="${newMember.name}" /></td>
            </tr>
            <tr>
                <td style="text-align: right;"><label for="email">Email:</label>
                </td>

                <td><input type="text" id="email" name="email"
                    value="${newMember.email}" /> <!-- <h:message  for="email" errorClass="invalid" />-->
                </td>
            </tr>
            <tr>
                <td style="text-align: right;"><label
                    for="password">Password:</label></td>
                <td><input id="password" name="password"
                    type="text" value="${newMember.password}" /></td>
            </tr>
        </table>
        <p>
            <input id="register" type="submit" value="Register" /> 
        </p>
        <p>
            <label style="color: green;width: 100%;text-align: left;">${infoMessage}</label> 
        </p>
        <p>
             <label style="color: red; width: 100%;text-align: left;">${errorMessage}</label>
        </p>
    </form>
        </div>
        <div id="footer">
            <img src="resources/gfx/logo.png" alt="Weld logo" />
        </div>
    </div>
</body>
</html>
