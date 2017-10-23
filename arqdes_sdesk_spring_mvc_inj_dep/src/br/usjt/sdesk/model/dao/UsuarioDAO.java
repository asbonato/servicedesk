package br.usjt.sdesk.model.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.usjt.sdesk.model.entity.Usuario;

@Repository
public class UsuarioDAO {
	@PersistenceContext
	EntityManager manager;
	
	public boolean validarUsuario(Usuario usuario) throws IOException{
		String jpql = "select u from Usuario where u.nome = :user and u.senha = :pass";
		Query query = manager.createQuery(jpql);
		query.setParameter("user", usuario.getNome());
		query.setParameter("pass", usuario.getSenha());
		List<Usuario> result = query.getResultList();
		return (result != null && result.size() == 1);
		
	}
	
	
}







