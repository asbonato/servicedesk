<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Pokestops e Ginásios</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

</head>

<body>
	<!-- Modal -->
	<div class="modal fade" id="delete-modal" tabindex="-1" role="dialog"
		aria-labelledby="modalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Fechar">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="modalLabel">Excluir Local</h4>
				</div>
				<div class="modal-body">Deseja realmente excluir este local?</div>
				<div class="modal-footer">
					<form action="remover_local" method="post">
						<input type="hidden" name="id" id="id_excluir" />
						<button type="submit" class="btn btn-primary">Sim</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">N&atilde;o</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- /.modal -->
	<!-- Barra superior com os menus de navegação -->
	<c:import url="../menu.jsp" />
	<!-- Container Principal -->
	<div id="main" class="container">
		<form action="listar_locais" method="post">
			<div id="top" class="row">
				<div class="col-md-3">
					<h2>Locais</h2>
				</div>

				<div class="col-md-6">
					<div class="input-group h2">
						<input name="chave" class="form-control" id="search" type="text"
							placeholder="Pesquisar Locais (deixe vazio para trazer todos)">
						<span class="input-group-btn">
							<button class="btn btn-primary" type="submit">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</span>
					</div>
				</div>

				<div class="col-md-3">
					<a href="novo_local" class="btn btn-primary pull-right h2">Novo
						Local</a>
				</div>
			</div>
			<!-- /#top -->
		</form>
		<hr />
		<c:if test="${not empty locais}">
			<div id="list" class="row">

				<div class="table-responsive col-md-12">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>ID</th>
								<th>Nome</th>
								<th>Tipo</th>
								<th>Latitude</th>
								<th>Longitude</th>
								<th>Cidade</th>
								<th class="actions">A&ccedil;&otilde;es</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="local" items="${locais }">
								<tr>
									<td>${local.id }</td>
									<td>${local.nome }</td>
									<td>${local.tipo.nome }</td>
									<td>${local.latitude }</td>
									<td>${local.longitude }</td>
									<td>${local.cidade.nome}-${local.cidade.estado.id}</td>
									<td class="actions"><a class="btn btn-success btn-xs"
										href="mostrar_local?id=${local.id }">Exibir</a> <a
										class="btn btn-warning btn-xs"
										href="alterar_local?id=${local.id }">Alterar</a>
										<button id="btn${pokemon.id }%>" type="button"
											class="btn btn-danger btn-xs" data-toggle="modal"
											data-target="#delete-modal" data-local="${local.id }">Excluir</button>
									</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>

				</div>
			</div>
			<!-- /#list -->

		</c:if>
		<!-- /#bottom -->
	</div>
	<!-- /#main -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript">
                $("#delete-modal").on('show.bs.modal', function(event) {
                    var button = $(event.relatedTarget); //botao que disparou a modal
                    var recipient = button.data('local');
                    $("#id_excluir").val(recipient);
                });
            </script>
</body>

</html>