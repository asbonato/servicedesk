package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Cerveja;
import to.CervejaTO;

public class CervejaDAO {

	public CervejaTO selecionarTodas(){
		String query = "select c.nome, c.preco, c.imagem, e.nome, r.nome, "+
				"p.nome from cerveja c, estilo e, pais p, cor r "+
				"where c.idcor = r.idcor and c.idestilo = e.idestilo and "+
				"c.idpais = p.idpais";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		CervejaTO to = new CervejaTO();
		try {
			conn = ConexaoBD.conectar();
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			while(rs.next()){
				Cerveja cerveja = new Cerveja();
				cerveja.setNome(rs.getString(1));
				cerveja.setPreco(rs.getDouble(2));
				cerveja.setImagem(rs.getString(3));
				cerveja.setEstilo(rs.getString(4));
				cerveja.setCor(rs.getString(5));
				cerveja.setPais(rs.getString(6));
				to.add(cerveja);
			}
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			if(rs != null){
				try{
					rs.close();
				} catch(SQLException sqe){
					sqe.printStackTrace();
				}
			}
			if(pst != null){
				try{
					pst.close();
				} catch(SQLException sqe){
					sqe.printStackTrace();
				}
			}
			if(conn != null){
				try{
					ConexaoBD.desconectar(conn);
				} catch(SQLException sqe){
					sqe.printStackTrace();
				}
			}
		}
		
		return to;
	}
}
