<%-- 
    Document   : menu.jsp
    Created on : Jun 23, 2025, 2:52:34 PM
    Author     : HelenoCampos
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
    </head>
    <body>
    <c:if test="${not empty usuario}">
        <h1>Bem vindo, <c:out value="${usuario.username}"/>!</h1>
        <br>
        <a href="logout">Sair</a>
    </c:if>
        
    </body>
</html>
