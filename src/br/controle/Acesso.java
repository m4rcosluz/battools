package br.controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.modelo.config;
import br.modelo.gravaLog;

public class Acesso {

	private int acesso_user;
	private static String dt_acesso;
	
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
	
	
	public static String verifica_acesso_geral() {
		{
			String usuario_sessao = System.getProperty("user.name");
			String SELECT_ACESSO = "SELECT sum(acesso_user) FROM acesso_battools where cd_usuario = "+"'"+usuario_sessao+"'";

			Connection conn11 = null;
			Object pstm11;
			try {
				conn11 = AcessoBD.conectar();
				pstm11 = conn11.prepareStatement(SELECT_ACESSO);
				ResultSet rs = ((PreparedStatement) pstm11).executeQuery();
				while (rs.next()) {
					String acesso = rs.getString(1);
					return acesso;
				}

			} catch (Exception e) {
				System.err.println("Ocorreu um erro, causa:" + e.getMessage());
				e.printStackTrace();
			} finally {
				AcessoBD.desconectar(conn11);
			}

		}
		return dt_acesso;
	}
		
		
		public static String verifica_acesso_user() {
			{

				String SELECT_ACESSO_user = "SELECT sum(acesso_user) FROM acesso_battools";

				Connection conn11 = null;
				Object pstm11;
				try {
					conn11 = AcessoBD.conectar();
					pstm11 = conn11.prepareStatement(SELECT_ACESSO_user);
					ResultSet rs = ((PreparedStatement) pstm11).executeQuery();
					while (rs.next()) {
						String acesso = rs.getString(1);
						return acesso;
					}

				} catch (Exception e) {
					System.err.println("Ocorreu um erro, causa:" + e.getMessage());
					e.printStackTrace();
				} finally {
					AcessoBD.desconectar(conn11);
				}

			}
		return dt_acesso;
	}


}
