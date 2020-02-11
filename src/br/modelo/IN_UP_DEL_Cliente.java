package br.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

import br.controle.AcessoBD;

public class IN_UP_DEL_Cliente {
	
	private static Connection conn;
	private static PreparedStatement pstm;
	private static ResultSet rs;
	String usuario_sessao = System.getProperty("user.name");
	private final static String INSERT=" INSERT INTO cliente_contato(cd_contato, nm_contato, cd_cliente, cd_usuario, dt_gravacao, cd_ramal)VALUES(?,?,?,?,sysdate,?)";
	
	
	
	
	public void insere_cliente(Cliente cli){
		
		
		Connection conn1 = null;
		Object pstm1;
		try {String VALIDA_CONEXAO = "select * from dbamv.cliente_contato";
			conn1=AcessoBD.conectar();
			pstm1=conn1.prepareStatement(VALIDA_CONEXAO);
			rs=((PreparedStatement) pstm1).executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(1));
				
				} 
				

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Atenção: Erro ao tentar conectar no banco de dados.",  "Atenção", JOptionPane.WARNING_MESSAGE);
			return;
		}finally{
			AcessoBD.desconectar(conn1);
		}
		
		
		
		try {
			
			
			
			
			String usuario_sessao = System.getProperty("user.name");
			conn=AcessoBD.conectar();
			pstm=conn.prepareStatement(INSERT);
			pstm.setString(1, cli.getCd_contato());
			pstm.setString(2, cli.getNm_contato());
			pstm.setString(3, cli.getCd_cliente());
			pstm.setString(4, usuario_sessao);
			pstm.setString(5, cli.getCd_ramal());
			pstm.executeUpdate();

			JOptionPane.showMessageDialog(null, "Atencão: o contato " + cli.getCd_contato()+" do cliente "+cli.getCd_cliente()+" foi cadastrado com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Atencão: o contato " + cli.getCd_contato()+" já está existe");
			System.err.println("Ocorreu um erro, causa:"+e.getMessage());
			e.printStackTrace();
		}finally{
			AcessoBD.desconectar(conn, pstm, rs);
		}
	}
	

		public static void update_nome_cx_alta(Empregado emp){
			try {
				conn=AcessoBD.conectar();
				pstm=conn.prepareStatement("UPDATE cliente_contato SET nm_contato = Upper(nm_contato)");
				pstm.executeUpdate();
			} catch (Exception e) {
				System.err.println("Ocorreu um erro, causa:"+e.getMessage());
				e.printStackTrace();
			}finally{
				AcessoBD.desconectar(conn, pstm, rs);
			}
		}
	
	
	


	

	
	
}
