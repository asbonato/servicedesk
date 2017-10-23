package br.usjt.arqdes16.mapeamento.service;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.usjt.arqdes16.mapeamento.dao.CidadeDAO;
import br.usjt.arqdes16.mapeamento.model.Cidade;

@Service
public class CidadeService {
	private CidadeDAO dao;
	
	@Autowired
	public CidadeService(CidadeDAO dao){
		this.dao = dao;
	}
	public List<Cidade> listarCidades() throws IOException{
		return dao.selecionarTodas();
	}
}
