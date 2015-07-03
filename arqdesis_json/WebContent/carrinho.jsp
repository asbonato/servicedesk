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
	<a href="index.html">Voltar</a>
	<h2>Carrinho de Compras</h2>
	<form method="post" action="alterar.do"  id="form_carrinho">
	<table>
	<thead><tr><th>&nbsp;</th><th>Cervejas Escolhidas</th><th>Preço</th><th>Quantidade</th></tr></thead>
	<tbody>
	<%
		TreeSet<Item> lista = (TreeSet<Item>) session
				.getAttribute("carrinho");
		if(lista == null){
			lista = new TreeSet<Item>();
		}
		double total = 0.0;
		NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		for (Item item : lista) {
			
			Cerveja cerveja = item.getCerveja();

			out.println("<tr><td><img src=\"./img/" + cerveja.getImagem()
					+ ".jpg\" alt=\"" + cerveja.getNome()
					+ "\" width=\"70\" height=\"70\"></a></td>");
			out.println("<td><div class=\"nomecar\">"+cerveja.getNome()+"</div></td><td><div class=\"precocar\">"+formatter.format(cerveja.getPreco())+
					"</div></td><td><div class=\"quantidadecar\"><input type=\"number\" name=\""+ cerveja.getNome()
					+ "\" min=\"0\" value=\""+item.getQuantidade()+"\" size=\"3\" \"/></div></td></tr>");
			total += cerveja.getPreco()*item.getQuantidade();
		}
		out.println("<tr><td>&nbsp;</td><td>&nbsp;</td><td><div class=\"totalcar\">Total</div></td><td><div class=\"valortotalcar\">"+
				formatter.format(total)+"</div></td></tr>");
		session.setAttribute("total", total);
	%>
	</tbody>
	</table>
	<h6>Zere a quantidade para remover do carrinho.</h6>
	<div>
			<input type="submit" name="acao" value="Alterar">
			<input type="submit" name="acao" value="Fechar compra">
		</div>
	</form>
</body>
</html>