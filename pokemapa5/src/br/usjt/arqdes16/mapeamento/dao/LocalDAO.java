package br.usjt.arqdes16.mapeamento.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import br.usjt.arqdes16.mapeamento.model.Local;

@Repository
public class LocalDAO {
	@PersistenceContext
	EntityManager manager;

	public void criar(Local local){
		manager.persist(local);
	}
	
	public void atualizar(Local local){
		System.out.println("vai atualizar: "+local);
		manager.merge(local);
	}
	
	public void excluir(Local local){
		manager.remove(manager.find(Local.class, local.getId()));
	}
	
	public Local selecionar(int id){
		return manager.find(Local.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Local> listarLocais(){
		return manager.createQuery("select l from Local l").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Local> listarLocais(String chave){
		String jpql = "select l from Local l where l.nome like :parte";
		Query query = manager.createQuery(jpql);
		query.setParameter("parte", "%"+chave+"%");
		List<Local> result = query.getResultList();
		return result;
	}
}
