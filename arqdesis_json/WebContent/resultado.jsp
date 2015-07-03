<%@page import="java.util.*, java.text.*"%>
<%@page import="model.*"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/main.css">
<title>Guia de Cervejas</title>
</head>
<body>
	<form method="post" action="adicionar.do"  id="form_imagens">
		<a href="index.html">Voltar</a>
		<h2>Cervejas Recomendadas</h2>
		<%
			TreeSet<Cerveja> lista = (TreeSet<Cerveja>) request
					.getAttribute("listaDeMarcas");
				
			NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

			for (Cerveja cerveja : lista) {

				out.println("<div class=\"img\">");
				out.println("<div class=\"nome\">" + cerveja.getNome()
						+ "</div>");
				out.println("<a target=\"_blank\" href=\"https://www.google.com.br/search?q=cerveja "
						+ cerveja.getNome() + "\">");
				out.println("<img src=\"./img/" + cerveja.getImagem()
						+ ".jpg\" alt=\"" + cerveja.getNome()
						+ "\" width=\"210\" height=\"210\"></a>");
				out.println("<div class=\"desc\">Estilo: "
						+ cerveja.getEstilo() + "</div>");
				out.println("<div class=\"desc\">Cor: " + cerveja.getCor()
						+ "</div>");
				out.println("<div class=\"desc\">País: " + cerveja.getPais()
						+ "</div>");
				out.println("<div class=\"desc\">Preço: "
						+ formatter.format(cerveja.getPreco()) + "</div>");
				out.println("<div class=\"quantidade\"><label>Quantidade:</label><input type=\"number\" name=\""
						+ cerveja.getNome()
						+ "\" min=\"0\" value=\"0\" size=\"3\" \"/></div>");
				out.println("</div>");
			}
		%>
		<div>
			<input type="submit" value="Adicionar" id="submit_imagens">
		</div>
	</form>
</body>
</html>