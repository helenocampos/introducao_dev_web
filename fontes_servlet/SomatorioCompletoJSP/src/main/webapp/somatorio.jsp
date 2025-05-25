<%-- 
    Document   : somatorio
    Created on : May 25, 2025, 2:28:29 PM
    Author     : HelenoCampos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="cabecalho.html"/>

        <form action='somatoriocompleto' method='post'>
            <input type='text' name='inicio' placeholder='inicio'>
            <input type='text' name='fim' placeholder='fim'>
            <input type='submit'>
        </form>
        <br>
        <c:if test="${soma!=null}">
            O somatório de ${inicio} a ${fim} é ${soma}. <br>
            Você usou o serviço ${acessosSession} vezes nessa sessão. <br>
            Você usou o serviço ${acessosCookies} vezes nesse browser. <br>
            O serviço foi usado ${acessosContext} vezes desde que o servidor foi iniciado.
        </c:if>
        <jsp:include page="rodape.html"/>
    </body>
</html>
