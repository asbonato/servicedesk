package br.usjt.sdesk.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.usjt.sdesk.model.entity.Fila;

public class FilaDAO {

	public ArrayList<Fila> listarFilas() throws IOException {
		String query = "select id_fila, nm_fila from fila";
		ArrayList<Fila> filas = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.obterConexao();
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();){
			
			while(rs.next()){
				Fila fila = new Fila();
				fila.setId(rs.getInt("id_fila"));
				fila.setNome(rs.getString("nm_fila"));
				filas.add(fila);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return filas;
	}
	
	public Fila carregar(int id) throws IOException {
		Fila fila = new Fila();
		fila.setId(id);
		String query = "select nm_fila from fila where id_fila=?";

		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement pst = conn.prepareStatement(query);) {
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery();) {

				if (rs.next()) {
					fila.setNome(rs.getString("nm_fila"));
				} else {
					fila.setNome(null);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return fila;
	}

}
