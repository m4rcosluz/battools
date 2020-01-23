package br.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import br.controle.AcessoBD;

public class config {
	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		config.cor = cor;
	}

	public String getInicia_windows() {
		return inicia_windows;
	}

	public void setInicia_windows(String inicia_windows) {
		this.inicia_windows = inicia_windows;
	}

	private static String cor;
	private String inicia_windows;

	private static Connection conn;
	private static PreparedStatement pstm;
	private static ResultSet rs;
	static String usuario_sessaob = System.getProperty("user.name");
	static String usuario_sessao = "'" + usuario_sessaob + "'";

	private final static String INSERT = " INSERT INTO dbamv.config_battols (cd_usuario, nm_cor, sn_iniciar_so, dt_gravacao)VALUES(?,?,?,sysdate)";
	private final static String UPDATE = " UPDATE config_battols set cd_usuario = ?, nm_cor = ?, sn_iniciar_so = ?, dt_gravacao = sysdate where cd_usuario ="
			+ usuario_sessao;

	public static void insere_config(config conf) {
		try {
			String usuario_sessao = System.getProperty("user.name");
			conn = AcessoBD.conectar();
			pstm = conn.prepareStatement(INSERT);
			pstm.setString(1, usuario_sessao);
			pstm.setString(2, conf.getCor());
			pstm.setString(3, conf.getInicia_windows());

			pstm.executeUpdate();
			// JOptionPane.showMessageDialog(null, "Atencão: Bem vindo ao Bat!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Atenção: Erro ao tentar salvar a configuração" + " Motivo: " + e);
			System.err.println("Ocorreu um erro, causa:" + e.getMessage());
			e.printStackTrace();
		} finally {
			AcessoBD.desconectar(conn, pstm, rs);
		}

	}

	public static void update_config(config conf) {
		try {
			String usuario_sessao = System.getProperty("user.name");
			conn = AcessoBD.conectar();
			pstm = conn.prepareStatement(UPDATE);
			pstm.setString(1, usuario_sessao);
			pstm.setString(2, conf.getCor());
			pstm.setString(3, conf.getInicia_windows());

			pstm.executeUpdate();
			JOptionPane.showMessageDialog(null, "Atencão: Registros atualizados com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Atenção: Erro ao tentar salvar a configuração" + " Motivo: " + e);
			System.err.println("Ocorreu um erro, causa:" + e.getMessage());
			e.printStackTrace();
		} finally {
			AcessoBD.desconectar(conn, pstm, rs);
		}
	}

	public static String define_cor(config conf) {
		{

			String usuario_sessaob = System.getProperty("user.name");
			String usuario_sessao = "'" + usuario_sessaob + "'";
			String SELECT_COR = "SELECT NM_COR FROM config_battols where cd_usuario = " + usuario_sessao;

			Connection conn11 = null;
			Object pstm11;
			try {
				conn11 = AcessoBD.conectar();
				pstm11 = conn11.prepareStatement(SELECT_COR);
				rs = ((PreparedStatement) pstm11).executeQuery();
				while (rs.next()) {
					return rs.getString(1);
				}

			} catch (Exception e) {
				System.err.println("Ocorreu um erro, causa:" + e.getMessage());
				e.printStackTrace();
				return null;
			} finally {
				AcessoBD.desconectar(conn11);
			}

		}
		return cor;

	}

	public static String verifica_config_user(config conf) {
		{

			String usuario_sessaob = System.getProperty("user.name");
			String usuario_sessao = "'" + usuario_sessaob + "'";
			String SELECT_CONFIG_USER = "SELECT count(cd_usuario) FROM config_battols where cd_usuario = "
					+ usuario_sessao;

			Connection conn11 = null;
			Object pstm11;
			try {
				conn11 = AcessoBD.conectar();
				pstm11 = conn11.prepareStatement(SELECT_CONFIG_USER);
				rs = ((PreparedStatement) pstm11).executeQuery();
				while (rs.next()) {
					String valida = rs.getString(1);
					return valida;
				}

			} catch (Exception e) {
				System.err.println("Ocorreu um erro, causa:" + e.getMessage());
				e.printStackTrace();
			} finally {
				AcessoBD.desconectar(conn11);
			}

		}
		return cor;

	}

}
