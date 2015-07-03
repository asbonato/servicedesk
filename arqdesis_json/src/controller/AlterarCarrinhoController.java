package controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Especialista;
import model.Item;

/**
 * Servlet implementation class AlterarCarrinhoController
 */
@WebServlet("/alterar.do")
public class AlterarCarrinhoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		RequestDispatcher view;
		String acao = request.getParameter("acao");
		switch (acao) {
		case "Alterar":
			TreeSet<Item> carrinho = (TreeSet<Item>) session
					.getAttribute("carrinho");
			if (carrinho == null) {
				carrinho = new TreeSet<Item>();
			}

			Especialista especialista = new Especialista();
			Enumeration<String> parametros = request.getParameterNames();
			while (parametros.hasMoreElements()) {
				String nome = parametros.nextElement();
				try {
					int quantidade = Integer.parseInt(request
							.getParameter(nome));
					especialista.alterar(carrinho, nome, quantidade);
				} catch (NumberFormatException e) {

				}
			}
			session.setAttribute("carrinho", carrinho);
			view = request.getRequestDispatcher("carrinho.jsp");
			view.forward(request, response);
			break;
		case "Fechar compra":
			session.removeAttribute("carrinho");
			view = request.getRequestDispatcher("final.jsp");
			view.forward(request, response);
			break;
		}
	}

}
