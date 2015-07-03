package model;

import java.io.Serializable;

public class Cerveja implements Comparable<Cerveja>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private String estilo;
	private String cor;
	private String pais;
	private String imagem;
	private double preco;

	public Cerveja(){
		
	}
	
	public Cerveja(String nome, String estilo, String cor, String pais, String imagem, double preco) {
		this.nome = nome;
		this.estilo = estilo;
		this.cor = cor;
		this.pais = pais;
		this.imagem = imagem;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public String getEstilo() {
		return estilo;
	}

	public String getCor() {
		return cor;
	}

	public String getPais() {
		return pais;
	}

	public String getImagem() {
		return imagem;
	}
	
	public double getPreco(){
		return preco;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public void setPreco(double preco){
		this.preco = preco;
	}
	 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cerveja other = (Cerveja) obj;
		if (cor == null) {
			if (other.cor != null)
				return false;
		} else if (!cor.equals(other.cor))
			return false;
		if (estilo == null) {
			if (other.estilo != null)
				return false;
		} else if (!estilo.equals(other.estilo))
			return false;
		if (imagem == null) {
			if (other.imagem != null)
				return false;
		} else if (!imagem.equals(other.imagem))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (Double.doubleToLongBits(preco) != Double
				.doubleToLongBits(other.preco))
			return false;
		return true;
	}

	@Override
	public int compareTo(Cerveja cerveja) {
		if (this.equals(cerveja)) {
			return 0;
		}
		return nome.compareTo(cerveja.getNome());
	}

	@Override
	public String toString() {
		return "Cerveja [nome=" + nome + ", estilo=" + estilo + ", cor=" + cor
				+ ", pais=" + pais + ", imagem=" + imagem + ", preco=" + preco
				+ "]";
	}
	
}
