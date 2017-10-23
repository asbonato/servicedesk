package br.usjt.arqdes16.mapeamento.service;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.usjt.arqdes16.mapeamento.dao.TipoDAO;
import br.usjt.arqdes16.mapeamento.model.Tipo;

@Service
public class TipoService {
	private TipoDAO dao;
	@Autowired
	public TipoService(TipoDAO dao){
		this.dao = dao;
	}
	public List<Tipo> listarTipos() throws IOException{
		return dao.selecionarTodos();
	}
}
