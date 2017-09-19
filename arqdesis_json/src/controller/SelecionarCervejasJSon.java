package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.JSonFacade;
import model.Cerveja;
import model.Especialista;

/**
 * Servlet implementation class SelecionarCervejasJSon
 */
@WebServlet("/selecao.json")
public class SelecionarCervejasJSon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String url = request.getRequestURL().toString();
		url = url.substring(0, url.lastIndexOf('/'));
		
		String estilo = request.getParameter("estilo");
		String cor = request.getParameter("cor");
		String pais = request.getParameter("pais");
		Especialista esp = new Especialista();
		TreeSet<Cerveja> lista = esp.listarMarcas(estilo, cor, pais);
		PrintWriter out = response.getWriter();
		for(Cerveja cerveja:lista){
			cerveja.setImagem(url+"/img/"+cerveja.getImagem()+".jpg");
		}
		String jsonFile = JSonFacade.listToJSon(lista);
		System.out.println(jsonFile);
		out.println(jsonFile);

	}

}
