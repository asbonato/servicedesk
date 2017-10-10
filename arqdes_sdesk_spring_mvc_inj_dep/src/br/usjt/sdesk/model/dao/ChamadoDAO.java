package br.usjt.sdesk.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.sdesk.model.entity.Chamado;
import br.usjt.sdesk.model.entity.Fila;
@Repository
public class ChamadoDAO {
	Connection conn;
	
	@Autowired
	public ChamadoDAO(DataSource ds) throws IOException{
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}
	
	public int inserirChamado(Chamado chamado) throws IOException {
		int id = -1;
		String comando = "insert into chamado (descricao, status, dt_abertura, id_fila) "
				+ "values (?,?,?,?)";
		try (PreparedStatement pst = conn.prepareStatement(comando);) {
			pst.setString(1, chamado.getDescricao());
			pst.setString(2, chamado.getStatus());
			pst.setDate(3, new java.sql.Date(chamado.getDataAbertura()
					.getTime()));
			pst.setInt(4, chamado.getFila().getId());
			pst.execute();
			// pegar o ultimo id inserido nesta sessao
			try (PreparedStatement pst1 = conn
					.prepareStatement("select LAST_INSERT_ID()");
					ResultSet rs = pst1.executeQuery();) {
				rs.next();
				id = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return id;
	}
	
	public ArrayList<Chamado> listarChamadosAbertos(Fila fila) throws IOException{
		ArrayList<Chamado> lista = new ArrayList<>();
		String query = "select c.id_chamado, c.descricao, c.dt_abertura, f.nm_fila "+
				"from chamado c, fila f where c.id_fila = f.id_fila and c.id_fila=? "+
				"and c.dt_fechamento is null";
		
		try(PreparedStatement pst = conn.prepareStatement(query);){
			pst.setInt(1, fila.getId());
			
			try(ResultSet rs = pst.executeQuery();){
				while(rs.next()){
					Chamado chamado = new Chamado();
					chamado.setNumero(rs.getInt("id_chamado"));
					chamado.setDescricao(rs.getString("descricao"));
					chamado.setDataAbertura(rs.getDate("dt_abertura"));
					fila.setNome(rs.getString("nm_fila"));
					chamado.setFila(fila);
					lista.add(chamado);
				}
			} catch(SQLException e){
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch(SQLException e){
			e.printStackTrace();
			throw new IOException(e);
		}
		return lista;
	}
	
	public ArrayList<Chamado> listarChamados(Fila fila) throws IOException{
		ArrayList<Chamado> lista = new ArrayList<>();
		String query = "select c.id_chamado, c.descricao, c.dt_abertura, f.nm_fila, "+
				"c.dt_fechamento, c.status "+ 
				"from chamado c, fila f where c.id_fila = f.id_fila and c.id_fila=?";
		
		try(PreparedStatement pst = conn.prepareStatement(query);){
			pst.setInt(1, fila.getId());
			
			try(ResultSet rs = pst.executeQuery();){
				while(rs.next()){
					Chamado chamado = new Chamado();
					chamado.setNumero(rs.getInt("id_chamado"));
					chamado.setDescricao(rs.getString("descricao"));
					chamado.setStatus(rs.getString("status"));
					chamado.setDataAbertura(rs.getDate("dt_abertura"));
					chamado.setDataFechamento(rs.getDate("dt_fechamento"));
					fila.setNome(rs.getString("nm_fila"));
					chamado.setFila(fila);
					lista.add(chamado);
				}
			} catch(SQLException e){
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch(SQLException e){
			e.printStackTrace();
			throw new IOException(e);
		}
		return lista;
	}

	public void fecharChamados(ArrayList<Integer> lista) throws IOException{
		String comando = "update chamado set dt_fechamento=?, status=? where id_chamado=?";
		
		try(PreparedStatement pst = conn.prepareStatement(comando);){
			
			for(int id:lista){
				pst.setDate(1, new java.sql.Date(new Date().getTime()));
				pst.setString(2, Chamado.FECHADO);
				pst.setInt(3, id);
				pst.execute();
			}
			
			
		} catch(SQLException e){
			e.printStackTrace();
			throw new IOException(e);
		}
		
	}


}
