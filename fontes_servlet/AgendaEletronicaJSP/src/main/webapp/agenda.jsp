<%-- 
    Document   : agenda
    Created on : May 24, 2025, 8:52:28 PM
    Author     : HelenoCampos
--%>

<%@page import="io.github.helenocampos.domain.Contato"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table, td, th {
                border: 1px solid black;
                border-collapse: collapse;
                padding: 10px 10px 10px 10px;
            }
        </style>
    </head>
    <body>
        <form action='agenda' method='post'>
            <input type='hidden' name='acao' value='inserir'>
            <input type='text' name='nome' placeholder='nome'>
            <input type='text' name='endereco' placeholder='endereco'>
            <input type='text' name='telefone' placeholder='telefone'>
            <input type='submit'>
        </form>

        <br>
        <table>
            <tr><th>Nome</th> <th>Endereço</th> <th>Telefone</th><th>Opções</th></tr>
            <c:forEach var="contato" items="${contatos}">
            <tr>
                <td>${contato.nome}</td>
                <td>${contato.endereco}</td>
                <td>${contato.telefone}</td>
                <td><a href="agenda?acao=apagar&id=${contato.id}">Apagar</a></td>
            </tr>
            </c:forEach>
            
        </table>
    </body>
</html>
