package br.usjt.arqdes16.mapeamento.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Size(max=100)
	private String username;
	@NotNull
	@Size(max=100)
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "UsuarioTO [username=" + username + ", password=" + password
				+ "]";
	}
}
