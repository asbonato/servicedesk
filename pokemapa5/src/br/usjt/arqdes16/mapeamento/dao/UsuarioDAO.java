package br.usjt.arqdes16.mapeamento.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import br.usjt.arqdes16.mapeamento.model.Usuario;

@Repository
public class UsuarioDAO {
	@PersistenceContext
	EntityManager manager;
	
	public void criar(Usuario usuario){
		manager.persist(usuario);
	}
	
	public void atualizar(Usuario usuario){
		manager.merge(manager.find(Usuario.class, usuario.getUsername()));
	}
	
	public void excluir(Usuario usuario){
		manager.remove(manager.find(Usuario.class, usuario.getUsername()));
	}
	
	public Usuario selecionar(String id){
		return manager.find(Usuario.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> selecionarTodos(){
		return manager.createQuery("select u from Usuario u").getResultList();
	}
	
	public boolean validar(Usuario usuario){
		String jpql = "select u from Usuario u where u.username = :user and u.password = :senha";
		Query query = manager.createQuery(jpql);
		query.setParameter("user", usuario.getUsername());
		query.setParameter("senha", usuario.getPassword());
		@SuppressWarnings("unchecked")
		List<Usuario> result = query.getResultList();
		return (result != null && result.size() == 1);
	}
}
