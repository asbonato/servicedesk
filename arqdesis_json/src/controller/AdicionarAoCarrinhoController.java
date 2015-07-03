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
 * Servlet implementation class AdicionarAoCarrinho
 */
@WebServlet("/adicionar.do")
public class AdicionarAoCarrinhoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		TreeSet<Item> carrinho = (TreeSet<Item>)session.getAttribute("carrinho");
		if (carrinho == null){
			carrinho = new TreeSet<Item>();
		}
		
		Especialista especialista = new Especialista();
		Enumeration<String> parametros = request.getParameterNames();
		while(parametros.hasMoreElements()) {
			String nome = parametros.nextElement();
			int quantidade = Integer.parseInt(request.getParameter(nome));
			if(quantidade > 0){
				especialista.adicionar(carrinho, nome, quantidade);
			}
		}
		session.setAttribute("carrinho", carrinho);
		RequestDispatcher view = request.getRequestDispatcher("carrinho.jsp");
		view.forward(request, response);
	}

}
