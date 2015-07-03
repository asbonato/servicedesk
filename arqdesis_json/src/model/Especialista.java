package model;

import java.util.ArrayList;
import java.util.TreeSet;

import to.CervejaTO;
import dao.CervejaDAO;

public class Especialista {
	private ArrayList<Cerveja> cervejas;

	public Especialista() {
		this.cervejas = this.cadastroDeCervejas();
	}

	public void adicionar(TreeSet<Item> carrinho, String nome, int quantidade){
		Cerveja cerveja = buscarNome(nome);
		if (cerveja != null){
			Item item = new Item(cerveja, quantidade);
			//se nao adicionar, pq ja existe, soma na quantidade
			if (!carrinho.add(item)){
				for(Item outro: carrinho){
					if (outro.equals(item)){
						outro.setQuantidade(item.getQuantidade() + outro.getQuantidade());
					}
				}
			}
		}
	}
	
	public void alterar(TreeSet<Item> carrinho, String nome, int quantidade){
		Cerveja cerveja = buscarNome(nome);
		if (cerveja != null){
			Item item = new Item(cerveja, quantidade);
			for(Item outro: carrinho){
				if (outro.equals(item)){
					if(item.getQuantidade() > 0){
						outro.setQuantidade(item.getQuantidade());
					} else {
						carrinho.remove(outro);
					}
					break;
				}
			}
		}
	}
	
	private Cerveja buscarNome(String nome) {
		for (Cerveja cerveja : cervejas) {
			if (nome.equals(cerveja.getNome())) {
				return cerveja;
			}
		}
		return null;
	}
	
	public TreeSet<Cerveja> listarMarcas(String estilo, String cor, String pais) {
		TreeSet<Cerveja> marcas = new TreeSet<Cerveja>();

		if (estilo.length() > 0 && cor.length() > 0 && pais.length() > 0) {
			marcas = buscarEstiloCorPais(estilo, cor, pais);
		} else if (estilo.length() > 0 && cor.length() > 0) {
			marcas = buscarEstiloCor(estilo, cor);
		} else if (estilo.length() > 0 && pais.length() > 0) {
			marcas = buscarEstiloPais(estilo, pais);
		} else if (cor.length() > 0 && pais.length() > 0) {
			marcas = buscarCorPais(cor, pais);
		} else if (estilo.length() > 0) {
			marcas = buscarEstilo(estilo);
		} else if (cor.length() > 0) {
			marcas = buscarCor(cor);
		} else if (pais.length() > 0) {
			marcas = buscarPais(pais);
		} else {
			marcas = todas();
		}

		return marcas;
	}

	private TreeSet<Cerveja> buscarEstilo(String estilo) {
		TreeSet<Cerveja> lista = new TreeSet<Cerveja>();
		for (Cerveja cerveja : cervejas) {
			if (estilo.equals(cerveja.getEstilo())) {
				lista.add(cerveja);
			}
		}
		return lista;
	}

	private TreeSet<Cerveja> buscarCor(String cor) {
		TreeSet<Cerveja> lista = new TreeSet<Cerveja>();
		for (Cerveja cerveja : cervejas) {
			if (cor.equals(cerveja.getCor())) {
				lista.add(cerveja);
			}
		}
		return lista;
	}

	private TreeSet<Cerveja> buscarPais(String pais) {
		TreeSet<Cerveja> lista = new TreeSet<Cerveja>();
		for (Cerveja cerveja : cervejas) {
			if (pais.equals(cerveja.getPais())) {
				lista.add(cerveja);
			}
		}
		return lista;
	}

	private TreeSet<Cerveja> buscarEstiloCor(String estilo, String cor) {
		TreeSet<Cerveja> lista = new TreeSet<Cerveja>();
		for (Cerveja cerveja : cervejas) {
			if (estilo.equals(cerveja.getEstilo())
					&& cor.equals(cerveja.getCor())) {
				lista.add(cerveja);
			}
		}
		return lista;
	}

	private TreeSet<Cerveja> buscarEstiloPais(String estilo, String pais) {
		TreeSet<Cerveja> lista = new TreeSet<Cerveja>();
		for (Cerveja cerveja : cervejas) {
			if (estilo.equals(cerveja.getEstilo())
					&& pais.equals(cerveja.getPais())) {
				lista.add(cerveja);
			}
		}
		return lista;
	}

	private TreeSet<Cerveja> buscarCorPais(String cor, String pais) {
		TreeSet<Cerveja> lista = new TreeSet<Cerveja>();
		for (Cerveja cerveja : cervejas) {
			if (cor.equals(cerveja.getCor()) && pais.equals(cerveja.getPais())) {
				lista.add(cerveja);
			}
		}
		return lista;
	}

	private TreeSet<Cerveja> buscarEstiloCorPais(String estilo, String cor,
			String pais) {
		TreeSet<Cerveja> lista = new TreeSet<Cerveja>();
		for (Cerveja cerveja : cervejas) {
			if (estilo.equals(cerveja.getEstilo())
					&& cor.equals(cerveja.getCor())
					&& pais.equals(cerveja.getPais())) {
				lista.add(cerveja);
			}
		}
		return lista;
	}
	
	private TreeSet<Cerveja> todas() {
		TreeSet<Cerveja> lista = new TreeSet<Cerveja>();
		for (Cerveja cerveja : cervejas) {
			lista.add(cerveja);
		}
		return lista;
	}
	
	private ArrayList<Cerveja> cadastroDeCervejas() {
		CervejaDAO dao = new CervejaDAO();
		CervejaTO to = dao.selecionarTodas();
		return to.getLista();
	}

/*	private ArrayList<Cerveja> cadastroDeCervejas() {
		ArrayList<Cerveja> cervejas = new ArrayList<Cerveja>();
		cervejas.add(new Cerveja("Brewdog Punk IPA", "india pale ale",
				"dourada", "Escócia", "cerveja001", 22.99));
		cervejas.add(new Cerveja("Schornstein IPA", "india pale ale", "âmbar",
				"Brasil", "cerveja002", 16.90));
		cervejas.add(new Cerveja("Speakeasy Big Daddy IPA", "india pale ale",
				"âmbar", "Estados Unidos", "cerveja003", 21.90));
		cervejas.add(new Cerveja("Mikkeller Simcoe Single Hop",
				"india pale ale", "âmbar", "Dinamarca", "cerveja004", 27.90));
		cervejas.add(new Cerveja("Dortmund The White IPA", "india pale ale",
				"amarela-palha", "Brasil", "cerveja005", 19.90));
		cervejas.add(new Cerveja("Bear Beer Premium Lager", "lager", "amarela",
				"Dinamarca", "cerveja006", 11.99));
		cervejas.add(new Cerveja("Fürstenberg Premium Lager", "lager",
				"dourada", "Alemanha", "cerveja007", 10.99));
		cervejas.add(new Cerveja("Insana Gold", "lager", "dourada", "Brasil",
				"cerveja008", 14.90));
		cervejas.add(new Cerveja("Norteña", "lager", "amarela", "Uruguai",
				"cerveja009", 11.90));
		cervejas.add(new Cerveja("Rasen Bier Pilsen", "lager", "amarela",
				"Brasil", "cerveja010", 8.99));
		cervejas.add(new Cerveja("Alhambra 1925 Reserva", "pilsner", "dourada",
				"Espanha", "cerveja011", 9.90));
		cervejas.add(new Cerveja("Bamberg Natural Pilsen", "pilsner",
				"amarela", "Brasil", "cerveja012", 11.99));
		cervejas.add(new Cerveja("Colorado Cauim", "pilsner", "dourada",
				"Brasil", "cerveja013", 14.99));
		cervejas.add(new Cerveja("Invicta German Pilsener", "pilsner",
				"dourada", "Brasil", "cerveja014", 11.40));
		cervejas.add(new Cerveja("Dama Pilsen", "pilsner", "amarela-palha",
				"Brasil", "cerveja015", 9.90));
		cervejas.add(new Cerveja("Colorado Demoiselle", "porter", "preta",
				"Brasil", "cerveja016", 12.99));
		cervejas.add(new Cerveja("Três Lobos Bravo", "porter", "preta",
				"Brasil", "cerveja017", 13.90));
		cervejas.add(new Cerveja("Insana Chocolate Porter", "porter",
				"marrom-escura", "Brasil", "cerveja018", 14.90));
		cervejas.add(new Cerveja("Rogue Mocha Porter", "porter",
				"marrom-clara", "Estados Unidos", "cerveja019", 23.30));
		cervejas.add(new Cerveja("Eel River Organic Porter", "porter",
				"marrom", "Estados Unidos", "cerveja020", 18.90));
		cervejas.add(new Cerveja("Guayacán Stout", "stout", "preta", "Chile",
				"cerveja021", 9.90));
		cervejas.add(new Cerveja("Schornstein Imperial Stout", "stout",
				"preta-opaca", "Brasil", "cerveja022", 25.38));
		cervejas.add(new Cerveja("Guinness", "stout", "preta", "Irlanda",
				"cerveja023", 16.20));
		cervejas.add(new Cerveja("Dortmund Nostradamus Stout", "stout",
				"preta", "Brasil", "cerveja024", 15.99));
		cervejas.add(new Cerveja("Shepherd Neame Double Stout", "stout",
				"preta-opaca", "Inglaterra", "cerveja025", 27.90));
		cervejas.add(new Cerveja("Chimay Blue Cap", "trapistas", "marrom",
				"Bélgica", "cerveja026", 22.99));
		cervejas.add(new Cerveja("Westmalle Dubbel", "trapistas",
				"marrom-escura", "Bélgica", "cerveja027", 19.99));
		cervejas.add(new Cerveja("Engelszell Gregorius",
				"trapistas", "marrom-escura", "Áustria", "cerveja028", 44.99));
		cervejas.add(new Cerveja("Trappistes Rochefort 10", "trapistas",
				"marrom-escura", "Bélgica", "cerveja029", 29.90));
		cervejas.add(new Cerveja("Achel Trappist Brune", "trapistas",
				"cobre-claro", "Bélgica", "cerveja030", 18.99));
		cervejas.add(new Cerveja("Brooklyner Wheat Beer", "trigo/weiss",
				"dourada", "Estados Unidos", "cerveja031", 12.90));
		cervejas.add(new Cerveja("Franziskaner Weissbier Dunkel",
				"trigo/weiss", "marrom", "Alemanha", "cerveja032", 13.99));
		cervejas.add(new Cerveja("Backer Trigo", "trigo/weiss", "âmbar",
				"Brasil", "cerveja033", 14.90));
		cervejas.add(new Cerveja("Oettinger Hefeweizen", "trigo/weiss",
				"dourada", "Alemanha", "cerveja034", 13.99));
		cervejas.add(new Cerveja("Erdinger Weissbier Kristall", "trigo/weiss",
				"dourada", "Alemanha", "cerveja035", 17.90));
		cervejas.add(new Cerveja("Tripel Karmeliet", "tripel", "dourada",
				"Bélgica", "cerveja036", 21.63));
		cervejas.add(new Cerveja("Beatus Tripel", "tripel", "dourada",
				"Brasil", "cerveja037", 29.13));
		cervejas.add(new Cerveja("La Corne Triple 10", "tripel", "dourada",
				"Bélgica", "cerveja038", 22.99));
		cervejas.add(new Cerveja("Maredsous Tripel 10", "tripel",
				"marrom-clara", "Bélgica", "cerveja039", 20.90));
		cervejas.add(new Cerveja("Wäls Trippel", "tripel", "amarela", "Brasil",
				"cerveja040", 26.90));
		cervejas.add(new Cerveja("Duff Beer", "lager", "amarela-palha",
				"Brasil", "cerveja041", 9.90));
		cervejas.add(new Cerveja("Fuller's India Pale Ale", "india pale ale",
				"âmbar", "Inglaterra", "cerveja042", 25.90));
		cervejas.add(new Cerveja("Carlsberg", "pilsner", "amarela-palha",
				"Dinamarca", "cerveja043", 7.90));
		cervejas.add(new Cerveja("Quilmes", "lager", "amarela", "Argentina",
				"cerveja044", 12.99));
		cervejas.add(new Cerveja("Foster Lager", "lager", "dourada",
				"Austrália", "cerveja045", 17.90));
		cervejas.add(new Cerveja("Budweiser Alumínio", "lager", "amarela",
				"Estados Unidos", "cerveja046", 17.90));
		cervejas.add(new Cerveja("Stella Artois", "lager", "amarela-palha",
				"Bélgica", "cerveja047", 2.99));
		cervejas.add(new Cerveja("Corona", "lager", "amarela-palha", "México",
				"cerveja048", 9.90));

		return cervejas;
	}*/
}