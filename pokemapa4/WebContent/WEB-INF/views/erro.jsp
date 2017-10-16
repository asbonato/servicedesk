<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Erro</title>
   
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
    <!-- Barra superior com os menus de navegacao -->
	<c:import url="menu.jsp"/>
    <!-- Container Principal -->
    <div id="main" class="container">
    		<br><br>
		<div class="alert alert-danger" role="alert">
			<h2 class="text-center"><span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			<span class="sr-only">Erro:</span> Aconteceu um erro.</h2>
		</div>
		<p class="lead text-danger bg-danger text-justify"><strong>Causa:</strong> ${erro.message}</p>
		<p class="text-danger bg-danger text-justify"><strong>Pilha de Execução:</strong><br>
		<c:forEach var="trace" items="${erro.stackTrace}">
		  ${trace.className}.${trace.methodName}
		 <c:if test="${trace.nativeMethod}">
		 	(Método Nativo)<br>
		 </c:if>
		 <c:if test="${not trace.nativeMethod}">
		 	(${trace.fileName}:${trace.lineNumber})<br>
		 </c:if>
		</c:forEach>	
		</p>
	</div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>