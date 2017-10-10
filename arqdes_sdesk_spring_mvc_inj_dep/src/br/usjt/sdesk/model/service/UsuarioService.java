package br.usjt.sdesk.model.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.sdesk.model.dao.UsuarioDAO;
import br.usjt.sdesk.model.entity.Usuario;

@Service
public class UsuarioService {
	private UsuarioDAO dao;
	
	@Autowired
	public UsuarioService(UsuarioDAO dao){
		this.dao = dao;
	}
	
	public boolean validarUsuario(Usuario usuario) throws IOException{
		return dao.validarUsuario(usuario);
	}
}
