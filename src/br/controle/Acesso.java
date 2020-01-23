package br.controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.modelo.gravaLog;

public class Acesso {

	private int acesso_user;
	private String dt_acesso;
	
	public int getAcesso_user() {
		return acesso_user;
	}
	public void setAcesso_user(int acesso_user) {
		this.acesso_user = acesso_user;
	}
	public String getDt_acesso() {
		return dt_acesso;
	}
	public void setDt_acesso(String dt_acesso) {
		this.dt_acesso = dt_acesso;
	}
	
	public static void insere_acesso(Acesso acesso){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		final String INSERT=" INSERT INTO acesso_battools(cd_usuario, acesso_user, dt_acesso)VALUES(?,?,SYSDATE)";
		try {
			String usuario_sessao = System.getProperty("user.name");
			conn=AcessoBD.conectar();
			pstm=conn.prepareStatement(INSERT);
			((PreparedStatement) pstm).setString(1, usuario_sessao);
			((PreparedStatement) pstm).setLong(2, 1);
			((PreparedStatement) pstm).executeUpdate();

		} catch (Exception e) {
			System.err.println("Ocorreu um erro, causa:"+e.getMessage());
			e.printStackTrace();
		}finally{
			AcessoBD.desconectar(conn, pstm, rs);
		}
	}

	

}
