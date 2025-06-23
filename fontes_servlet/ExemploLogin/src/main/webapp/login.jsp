<%-- 
    Document   : login.jsp
    Created on : Jun 23, 2025, 2:52:23 PM
    Author     : HelenoCampos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <span style="color:red">${erro}</span>
        <form action="login" method="post">
            <label> Usuário: <input type="text" name="username" placeholder="Usuario"></label>
            <br>
            <label> Senha: <input type="password" name="password" placeholder="Senha"></label>
            <br>
            <input type="submit" value="Enviar">
        </form>
    </body>
</html>
