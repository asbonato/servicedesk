package br.usjt.sdesk.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.usjt.sdesk.model.entity.Usuario;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object controller) throws Exception{

		String path = request.getContextPath();
		String uri = request.getRequestURI();
		
		if(uri.contains("js") || uri.contains("css") || uri.contains("html")
				|| uri.contains("jpg") || uri.contains("index")){
			return true;
		}
		
		HttpSession session = request.getSession();
		Usuario logado = (Usuario)session.getAttribute(Usuario.LOGADO);
		
		if(logado != null || uri.endsWith("loginForm") 
				|| uri.endsWith("fazer_login")){
			return true;
		}
		response.sendRedirect("loginForm");
		
		return false;
	}

}
