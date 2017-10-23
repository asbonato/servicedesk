package br.usjt.arqdes16.mapeamento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
@Table(name="Uf")
public class Estado implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="idUf")
	@Size(max=2)
	private String id;
	@NotNull
	@Column(name="nmUf")
	@Size(max=128)
	private String nome;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return "UF [id=" + id + ", nome=" + nome + "]";
	}
}
