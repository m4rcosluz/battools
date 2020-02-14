package br.modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.controle.AcessoBD;
public class gravaLog {
public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public String getDh_log() {
		return dh_log;
	}
	public void setDh_log(String dh_log) {
		this.dh_log = dh_log;
	}
private String usuario;
private String funcao;
private String dh_log;


public static void insere_log(gravaLog log){
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	final String INSERT=" INSERT INTO log_battools(cd_usuario, funcao, dh_log)VALUES(?,?,SYSDATE)";
	try {
		String usuario_sessao = System.getProperty("user.name");
		conn=AcessoBD.conectar();
		pstm=conn.prepareStatement(INSERT);
		((PreparedStatement) pstm).setString(1, usuario_sessao);
		((PreparedStatement) pstm).setString(2, log.getFuncao());
		((PreparedStatement) pstm).executeUpdate();

	} catch (Exception e) {
		//JOptionPane.showMessageDialog(null, "Atenção: A aplicação pode não funcionar por completa, não",  "Atenção", JOptionPane.WARNING_MESSAGE);
	}finally{
		AcessoBD.desconectar(conn, pstm, rs);
	}
}

}
