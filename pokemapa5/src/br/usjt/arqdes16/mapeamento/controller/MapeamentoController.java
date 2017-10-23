package br.usjt.arqdes16.mapeamento.controller;

import java.io.IOException;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import br.usjt.arqdes16.mapeamento.model.Cidade;
import br.usjt.arqdes16.mapeamento.model.Local;
import br.usjt.arqdes16.mapeamento.model.Tipo;
import br.usjt.arqdes16.mapeamento.service.CidadeService;
import br.usjt.arqdes16.mapeamento.service.LocalService;
import br.usjt.arqdes16.mapeamento.service.TipoService;

@Transactional
@Controller
public class MapeamentoController {
	private TipoService ts;
	private CidadeService cs;
	private LocalService ls;
	
	@Autowired
	public MapeamentoController(TipoService ts, CidadeService cs,
			LocalService ls) {
		this.ts = ts;
		this.cs = cs;
		this.ls = ls;
	}

	@RequestMapping("index")
	public String home(){
		return "redirect:listar_locais";
	}
	
	@RequestMapping("novo_local")
	public String form(Model model) {

		try {
			List<Tipo> tipos = ts.listarTipos();
			model.addAttribute("tipos", tipos);
			List<Cidade> cidades = cs.listarCidades();
			model.addAttribute("cidades", cidades);
			return "local/localcriar";

		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro",e);
		}
		return "erro";
	}
	
	@RequestMapping("alterar_local")
	public String formAlterar(Model model, Local local) {
		try {
			List<Tipo> tipos = ts.listarTipos();
			model.addAttribute("tipos", tipos);
			List<Cidade> cidades = cs.listarCidades();
			model.addAttribute("cidades", cidades);
			model.addAttribute("local", ls.mostrar(local));
			return "local/localalterar";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro",e);
		}
		return "erro";
	}

	@RequestMapping("incluir_local")
	public String inclusao(@Valid Local local, BindingResult result, Model model) {
		
		try {
			if (result.hasErrors()) {
				List<Tipo> tipos = ts.listarTipos();
				model.addAttribute("tipos", tipos);
				List<Cidade> cidades = cs.listarCidades();
				model.addAttribute("cidades", cidades);
				return "local/localcriar";
			}
			ls.criar(local);
			return "redirect:listar_locais";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro",e);
		}
		return "erro";
	}
	
	@RequestMapping("listar_locais")
	public String listagem(Model model, String chave) {
		try{
			if(chave == null || chave.equals("")){
				model.addAttribute("locais", ls.listarLocais());
			} else {
				model.addAttribute("locais", ls.listarLocais(chave));
			}
			return "local/locallistar";
		} catch(IOException e){
			e.printStackTrace();
			model.addAttribute("erro",e);
		}
		return "erro";
	}
	
	@RequestMapping("limpar_locais")
	public String limparListagem(Model model) {
		model.addAttribute("locais", null);
		return "local/locallistar";
	}
	
	@RequestMapping("mostrar_local")
	public String mostrar(Local local, Model model) {
		try{
			model.addAttribute("local",ls.mostrar(local));
			return "local/localmostrar";
		} catch(IOException e){
			e.printStackTrace();
			model.addAttribute("erro",e);
		}
		return "erro";
	}
	
	@RequestMapping("remover_local")
	public String remover(Local local, Model model) {
		try{
			ls.remover(local);
			return "redirect:listar_locais";
		} catch(IOException e){
			e.printStackTrace();
			model.addAttribute("erro",e);
		}
		return "erro";
	}
	
	@RequestMapping("atualizar_local")
	public String atualizar(Local local, Model model) {
		try{
			ls.atualizar(local);
			return "redirect:listar_locais";
		} catch(IOException e){
			e.printStackTrace();
			model.addAttribute("erro",e);
		}
		return "erro";
	}
}
