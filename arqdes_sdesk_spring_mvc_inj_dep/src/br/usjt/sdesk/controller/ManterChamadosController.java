package br.usjt.sdesk.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.usjt.sdesk.model.entity.Chamado;
import br.usjt.sdesk.model.entity.Fila;
import br.usjt.sdesk.model.service.ChamadoService;
import br.usjt.sdesk.model.service.FilaService;

@Controller
public class ManterChamadosController {
	private FilaService filaService;
	private ChamadoService chamadoService;
	
	@Autowired
	public ManterChamadosController(FilaService fs, ChamadoService cs){
		filaService = fs;
		chamadoService = cs;
	}

	@RequestMapping("index")
	public String inicio() {
		return "index";
	}

	private ArrayList<Fila> carregarFilas() throws IOException {
		return filaService.listarFilas();
	}

	@RequestMapping("/listar_filas")
	public String listarFilas(Model model) {
		try {
			model.addAttribute("lista", carregarFilas());
			return "NovoChamado";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/criar_chamado")
	public String criarChamado(@Valid Chamado chamado, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			try {
				model.addAttribute("lista", carregarFilas());
				return "NovoChamado";
			} catch (IOException e) {
				e.printStackTrace();
				return "Erro";
			}
		} else {

			try {
				int idChamado = chamadoService.novoChamado(chamado);
				chamado.setNumero(idChamado);
				return "NumeroChamado";
			} catch (IOException e) {
				e.printStackTrace();
				return "Erro";
			}
		}
	}

	@RequestMapping("/listar_filas_fechar")
	public String listarFilasFechar(Model model) {
		try {
			ArrayList<Fila> filas = filaService.listarFilas();
			model.addAttribute("filas", filas);
			return "ChamadoFechar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/listar_chamados_abertos")
	public String listarChamadosAbertos(Fila fila, Model model) {
		try {
			fila = filaService.carregar(fila.getId());
			model.addAttribute("fila", fila);

			ArrayList<Chamado> chamados = chamadoService.listarChamadosAbertos(fila);
			model.addAttribute("chamados", chamados);
			return "ChamadoFecharSelecionar";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/fechar_chamados")
	public String fecharChamados(
			@RequestParam Map<String, String> allRequestParams) {
		try {
			Set<String> chaves = allRequestParams.keySet();
			Iterator<String> i = chaves.iterator();
			ArrayList<Integer> lista = new ArrayList<>();
			while (i.hasNext()) {
				String chave = i.next();
				String valor = (String) allRequestParams.get(chave);
				if (valor.equals("on")) {
					lista.add(Integer.parseInt(chave));
				}
			}
			if (lista.size() > 0) {
				chamadoService.fecharChamados(lista);
			}
			return "ChamadoFechado";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/listar_filas_exibir")
	public String listarFilasExibir(Model model) {
		try {
			ArrayList<Fila> filas = filaService.listarFilas();
			model.addAttribute("filas", filas);
			return "ChamadoListar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/listar_chamados_exibir")
	public String listarChamadosExibir(Fila fila, Model model) {
		try {
			fila = filaService.carregar(fila.getId());
			model.addAttribute("fila", fila);

			ArrayList<Chamado> chamados = chamadoService.listarChamados(fila);
			model.addAttribute("chamados", chamados);

			return "ChamadoListarExibir";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
}
