package br.usjt.arqdes16.mapeamento.controller;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.usjt.arqdes16.mapeamento.model.Local;
import br.usjt.arqdes16.mapeamento.service.LocalService;

@RestController
public class MapeamentoRest {
	
	private LocalService ls;
	
	@Autowired
	public MapeamentoRest(LocalService ls) {
		this.ls = ls;
	}

	@RequestMapping(method=RequestMethod.GET, value="rest/locais")
	public @ResponseBody List<Local> listagem(String chave) {
		List<Local> lista = null;
		try{
			if(chave == null || chave.equals("")){
				lista = ls.listarLocais();
			} else {
				lista = ls.listarLocais(chave);
			}
		} catch(IOException e){
			e.printStackTrace();
		}
		return lista;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="rest/locais/{id}")
	public @ResponseBody Local listaLocal(@PathVariable("id") Long id) {
		Local local = null, param;
		try{
			param = new Local();
			param.setId(id.intValue());
			local = ls.mostrar(param);
		} catch(IOException e){
			e.printStackTrace();
		}
		return local;
	}
	
	@Transactional
	@RequestMapping(method=RequestMethod.POST, value="rest/locais")
	public ResponseEntity<Local> criarLocal(@RequestBody Local local){
		try{
			System.out.println("local = "+local);
			System.out.println("cidade = " + local.getCidade());
			System.out.println("estado = " + local.getCidade().getEstado());
			System.out.println("tipo = " + local.getTipo());
			
			ls.criar(local);
			return new ResponseEntity<Local>(local, HttpStatus.OK);
		} catch(IOException e){
			e.printStackTrace();
			return new ResponseEntity<Local>(local, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
