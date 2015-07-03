<%@ page import="java.util.*, java.text.*"%>
<%@page import="model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/main.css">
<title>Guia de Cervejas</title>
</head>
<body>
	<%
		NumberFormat formatter = NumberFormat
				.getCurrencyInstance(new Locale("pt", "BR"));
	%>
	<a href="index.html">Voltar</a>
	<form action="">
		<h2>
			Total:
			<%=formatter.format((Double) session.getAttribute("total"))%></h2>
		<h2>Compra finalizada.</h2>
		<h2>Obrigado!</h2>
	</form>
</body>
</html>