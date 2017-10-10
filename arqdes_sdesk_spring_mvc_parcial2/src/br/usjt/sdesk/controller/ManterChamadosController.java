package br.usjt.sdesk.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import br.usjt.sdesk.model.dao.ChamadoDAO;
import br.usjt.sdesk.model.entity.Chamado;
import br.usjt.sdesk.model.entity.Fila;
import br.usjt.sdesk.model.service.ChamadoService;
import br.usjt.sdesk.model.service.FilaService;

@Controller
public class ManterChamadosController {
	ChamadoDAO dao = new ChamadoDAO();

	@RequestMapping("index")
	public String inicio() {
		return "index";
	}
	
	private ArrayList<Fila> carregarFilas() throws IOException{
		FilaService service = new FilaService();
		return service.listarFilas();
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
		}else{
			ChamadoService service = new ChamadoService();

			try {
				int idChamado = service.novoChamado(chamado);
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
			model.addAttribute("lista", carregarFilas());
			return "ListarFilasFechar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@RequestMapping("/listar_chamados_fechar")
	public String listarChamadosFechar(Fila fila, Model model) {
		try {
			FilaService fService = new FilaService();
			fila = fService.carregar(fila.getId());
			model.addAttribute("fila", fila);
			
			ChamadoService service = new ChamadoService();
			ArrayList<Chamado> chamados = service.listarChamadosAbertos(fila);
			model.addAttribute("chamados", chamados);

			return "ListarChamadosFechar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@RequestMapping("/fechar_chamados")
	public String fecharChamados(@RequestParam Map<String, String> allRequestParams) {
		try {
			Set<String> chaves = allRequestParams.keySet();
			Iterator<String> i = chaves.iterator();
			ArrayList<Integer> lista = new ArrayList<>();
			while (i.hasNext()) {
				String chave = i.next();
				String valor =  allRequestParams.get(chave);
				if (valor.equals("on")) {
					lista.add(Integer.parseInt(chave));
				}
			}
			if (lista.size() > 0) {
				ChamadoService service = new ChamadoService();
				service.fecharChamados(lista);
			}
			return "ChamadoFechado";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@RequestMapping("/listar_filas_consultar")
	public String listarFilasConsultar(Model model) {
		try {
			model.addAttribute("lista", carregarFilas());
			return "ListarFilasConsultar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@RequestMapping("/listar_chamados_consultar")
	public String listarChamadosConsultar(Fila fila, Model model) {
		try {
			FilaService fService = new FilaService();
			fila = fService.carregar(fila.getId());
			model.addAttribute("fila", fila);
			
			ChamadoService service = new ChamadoService();
			ArrayList<Chamado> chamados = service.listarChamados(fila);
			model.addAttribute("chamados", chamados);

			return "ListarChamadosConsultar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
}
