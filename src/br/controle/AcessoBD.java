package br.controle;
 
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.swing.JOptionPane;

import br.modelo.gravaLog;

public class AcessoBD{
 
    public static Connection conectar() throws Exception {
    	
    	Connection connection = null;
        String driver = "";
        String usuario = "";
        String senha = "";
        String url = "";
        String banco = "";
 
        //so renomear se precisar do outro properties
        
        String usuario_dir = System.getProperty("user.dir")+"\\jdbc.properties";
        File prop_file = new File(usuario_dir);
 
        if (!prop_file.isFile()) {
            throw new Exception("Não achou a arquivo de propriedade, caminho:" + prop_file.getAbsolutePath() + " , errado.");
        } 
 
        Properties properties = new Properties();
        //Setamos o arquivo que será lido
        FileInputStream fis = new FileInputStream(prop_file.getAbsolutePath());
 
        //método load faz a leitura através do objeto fis
        properties.load(fis);
 
        //Captura o valor da propriedade, através do nome da propriedade(Key)
        usuario = properties.getProperty("jdbc.user");
        senha = properties.getProperty("jdbc.pass");
        driver = properties.getProperty("jdbc.driver");
        url = properties.getProperty("jdbc.url");
        banco = properties.getProperty("jdbc.dataBase");
 
        Class.forName(driver);
 
        connection = DriverManager.getConnection(url + banco, usuario, senha);

        return connection;
        
        
    }
 
    //sobrecarga dos metodo desconectar,
    //na sobrecarga muda somente a assinatura dos metodos ou seja os parâmetros
    public static void desconectar(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Exceção, causa desconectar1:" + e.getClass());
            e.printStackTrace();
        }
    }
 
    public static void desconectar(Connection conn, PreparedStatement pstm) {
        try {
            if (pstm != null) {
                pstm.close();
            }
 
            if (conn != null) {
                conn.close();
            }
 
        } catch (Exception e) {
            System.out.println("Exceção, causa: desconectar2" + e.getClass());
            e.printStackTrace();
        }
    }
 
    public static void desconectar(Connection conn, PreparedStatement pstm, ResultSet rs) {
        try {
 
            if (rs != null) {
                rs.close();
            }
 
            if (pstm != null) {
                pstm.close();
            }
 
            if (conn != null) {
                conn.close();
            }
 
        } catch (Exception e) {
            System.out.println("Exceção, causa: desconectar3" + e.getClass());
            e.printStackTrace();
 
        }
    }
    
    public static String verifica_conexao(gravaLog log){
    	Connection conn = null;
    	PreparedStatement pstm = null;
    	ResultSet rs = null;
    	final String VERIF="select * from config_battols";
    	try {
    		conn=AcessoBD.conectar();
    		pstm=conn.prepareStatement(VERIF);
    		((PreparedStatement) pstm).executeUpdate();
    		return "Ok";

    	} catch (Exception e) {
    		return "Erro";
    		//JOptionPane.showMessageDialog(null, "Atenção: A aplicação pode não funcionar por completa, não",  "Atenção", JOptionPane.WARNING_MESSAGE);
    	}finally{
    		AcessoBD.desconectar(conn, pstm, rs);
    	}
    }
}