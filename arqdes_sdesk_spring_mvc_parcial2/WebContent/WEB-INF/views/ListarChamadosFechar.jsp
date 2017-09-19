<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Fechar Chamados</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
    <!-- Barra superior com os menus de navegação -->
    <c:import url="Menu.jsp" />
    <!-- Container Principal -->
    <div id="main" class="container">
        <h3 class="page-header">Fechar Chamado(s) da Fila ${fila.nome}</h3>
        <c:if test="${not empty chamados}">
            <p>Selecione os chamados que quiser fechar.</p>
            <form class="form-inline" action="fechar_chamados" method="post">
                <div class="table-responsive col-md-12">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Número</th>
                                <th>Descrição</th>
                                <th>Abertura</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="chamado" items="${chamados}">
                                <tr>
                                    <td>
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" name="${chamado.numero}"> ${chamado.numero}
                                            </label>
                                        </div>
                                    </td>
                                    <td>${chamado.descricao }</td>
                                    <td>
                                        <fmt:formatDate value="${chamado.dataAbertura }" pattern="dd/MM/yyyy" />
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div id="actions" class="row">
                    <div class="col-md-12">
                        <button type="submit" class="btn btn-primary" name="acao" value="FecharChamados">Fechar Chamados</button>
                        <a href="index" class="btn btn-default">Cancelar</a>
                    </div>
                </div>
            </form>
        </c:if>
        <c:if test="${empty chamados}">
        	<div class="alert alert-info" role="alert">N&atilde;o h&aacute; chamados nesta fila.</div>
            <a href="listar_filas_fechar" class="btn btn-default">Voltar</a>
        </c:if>
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>