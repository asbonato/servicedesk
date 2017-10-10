package br.usjt.sdesk.model.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.sdesk.model.dao.ChamadoDAO;
import br.usjt.sdesk.model.entity.Chamado;
import br.usjt.sdesk.model.entity.Fila;

@Service
public class ChamadoService {
	ChamadoDAO dao;
	
	@Autowired
	public ChamadoService(ChamadoDAO dao){
		this.dao = dao;
	}
	
	public int novoChamado(Chamado chamado) throws IOException{
		chamado.setDataAbertura(new Date());
		chamado.setDataFechamento(null);
		chamado.setStatus(Chamado.ABERTO);
		
		return dao.inserirChamado(chamado);
	}
	
	public ArrayList<Chamado> listarChamadosAbertos(Fila fila) throws IOException{
		return dao.listarChamadosAbertos(fila);
	}
	
	public ArrayList<Chamado> listarChamados(Fila fila) throws IOException{
		return dao.listarChamados(fila);
	}

	public void fecharChamados(ArrayList<Integer> lista) throws IOException {
		dao.fecharChamados(lista);
	}

}
