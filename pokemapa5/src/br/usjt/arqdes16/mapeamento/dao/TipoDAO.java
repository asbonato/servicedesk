package br.usjt.arqdes16.mapeamento.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import br.usjt.arqdes16.mapeamento.model.Tipo;

@Repository
public class TipoDAO {
	@PersistenceContext
	EntityManager manager;

	public void criar(Tipo tipo){
		manager.persist(tipo);
	}
	
	public void atualizar(Tipo tipo){
		manager.merge(manager.find(Tipo.class, tipo.getId()));
	}
	
	public void excluir(Tipo tipo){
		manager.remove(manager.find(Tipo.class, tipo.getId()));
	}
	
	public Tipo selecionar(int id){
		return manager.find(Tipo.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Tipo> selecionarTodos(){
		return manager.createQuery("select t from Tipo t").getResultList();
	}
}
