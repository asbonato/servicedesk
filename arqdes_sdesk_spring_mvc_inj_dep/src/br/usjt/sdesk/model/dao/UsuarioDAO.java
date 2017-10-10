package br.usjt.sdesk.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.sdesk.model.entity.Usuario;

import java.sql.PreparedStatement;

@Repository
public class UsuarioDAO {
	Connection conn;
	
	@Autowired
	public UsuarioDAO(DataSource ds) throws IOException{
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}
	
	public boolean validarUsuario(Usuario usuario) throws IOException{
		String query = "select nome, senha from usuario "+
				"where nome=? and senha=?";
		try(PreparedStatement pst = conn.prepareStatement(query)){
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getSenha());
			try(ResultSet rs = pst.executeQuery();){
				if(rs.next()){
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		} 
		return false;
	}
	
	
}







