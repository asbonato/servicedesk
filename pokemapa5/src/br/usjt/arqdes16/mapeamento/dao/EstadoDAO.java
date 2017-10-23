package br.usjt.arqdes16.mapeamento.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import br.usjt.arqdes16.mapeamento.model.Estado;

@Repository
public class EstadoDAO {
	@PersistenceContext
	EntityManager manager;

	public void criar(Estado estado){
		manager.persist(estado);
	}
	
	public void atualizar(Estado estado){
		manager.merge(manager.find(Estado.class, estado.getId()));
	}
	
	public void excluir(Estado estado){
		manager.remove(manager.find(Estado.class, estado.getId()));
	}
	
	public Estado selecionar(String id){
		return manager.find(Estado.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Estado> selecionarTodas(){
		return manager.createQuery("select uf from Estado uf").getResultList();
	}
}
