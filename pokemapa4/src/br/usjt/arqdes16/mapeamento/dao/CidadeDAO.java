package br.usjt.arqdes16.mapeamento.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import br.usjt.arqdes16.mapeamento.model.Cidade;

@Repository
public class CidadeDAO {
	@PersistenceContext
	EntityManager manager;

	public void criar(Cidade cidade){
		manager.persist(cidade);
	}
	
	public void atualizar(Cidade cidade){
		manager.merge(manager.find(Cidade.class, cidade.getId()));
	}
	
	public void excluir(Cidade cidade){
		manager.remove(manager.find(Cidade.class, cidade.getId()));
	}
	
	public Cidade selecionar(int id){
		return manager.find(Cidade.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cidade> selecionarTodas(){
		return manager.createQuery("select c from Cidade c").getResultList();
	}
}
