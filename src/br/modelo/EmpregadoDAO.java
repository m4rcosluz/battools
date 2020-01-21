package br.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.controle.AcessoBD;

/**
 * classe faz a persistencia com a entidade EMPREGADOS do ORACLE
 * @author Roberto Silva
 * criado em: 04/07/2013
 */
public class EmpregadoDAO {
    
	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	private final String SELECT=" SELECT * FROM EMPREGADO "; 
	private final String INSERT=" INSERT INTO EMPREGADO(ID,NOME,DAT_NASC,SALARIO)VALUES(?,?,?,?) ";
	private final String UPDATE=" UPDATE EMPREGADO SET NOME=?,DAT_NASC=?,SALARIO=? WHERE ID=? ";
	private final String DELETE=" DELETE FROM EMPREGADO WHERE ID=? ";
	
	/**
	 * metodo seleciona registros de EMPREGADO
	 * @param nome
	 * @return
	 * @throws Exception
	 */
	public List<Empregado> selecionar(){
		List<Empregado> empregados=new ArrayList<Empregado>();
		Empregado emp=null;
		try {
			conn=AcessoBD.conectar();
			pstm=conn.prepareStatement(SELECT);
			rs=pstm.executeQuery();
						
			while(rs.next()){
				emp=new Empregado();
				emp.setId(rs.getInt("id"));
				emp.setNome(rs.getString("nome"));
				emp.setDat_nasc(rs.getDate("dat_nasc"));
				emp.setSalario(rs.getDouble("salario"));
				
				empregados.add(emp);
			}
		
		} catch (Exception e) {
			System.err.println("Ocorreu um erro, causa:"+e.getMessage());
			e.printStackTrace();
		}finally{
			AcessoBD.desconectar(conn, pstm, rs);
		}
		
		return empregados;
	}
	
	/**
	 * faz a inserção de EMPREGADO
	 * @param empregado
	 * @throws Exception
	 */
	
	public void insere_cliente(Cliente cli){
		try {
			conn=AcessoBD.conectar();
			pstm=conn.prepareStatement(INSERT);
			cli.setCd_contato(rs.getString("codigo"));
			cli.setNm_contato(rs.getString("nome"));
			cli.setCd_cliente(rs.getString("cliente"));
			
			pstm.executeUpdate();
		} catch (Exception e) {
			System.err.println("Ocorreu um erro, causa:"+e.getMessage());
			e.printStackTrace();
		}finally{
			AcessoBD.desconectar(conn, pstm, rs);
		}
	}
	
	
	public void inserir(Empregado emp){
		try {
			conn=AcessoBD.conectar();
			pstm=conn.prepareStatement(INSERT);
			pstm.setInt(1, emp.getId());
			pstm.setString(2, emp.getNome());
			pstm.setDate(3, emp.getDat_nasc());
			pstm.setDouble(4, emp.getSalario());
			
			pstm.executeUpdate();
		} catch (Exception e) {
			System.err.println("Ocorreu um erro, causa:"+e.getMessage());
			e.printStackTrace();
		}finally{
			AcessoBD.desconectar(conn, pstm, rs);
		}
	}

	/**
	 * faz a atualização de EMPREGADO
	 * @param empregado
	 * @throws Exception
	 */
	public void atualizar(Empregado emp){
		try {
			conn=AcessoBD.conectar();
			pstm=conn.prepareStatement(UPDATE);
			pstm.setString(1, emp.getNome());
			pstm.setDate(2, emp.getDat_nasc());
			pstm.setDouble(3, emp.getSalario());
			pstm.setInt(4, emp.getId());
			
			pstm.executeUpdate();
		} catch (Exception e) {
			System.err.println("Ocorreu um erro, causa:"+e.getMessage());
			e.printStackTrace();
		}finally{
			AcessoBD.desconectar(conn, pstm, rs);
		}
	}

	/**
	 * faz a exclusão de Empregado
	 * @param id
	 * @throws Exception
	 */
	public void excluir(int id){
		try {
			conn=AcessoBD.conectar();
			pstm=conn.prepareStatement(DELETE);
			pstm.setInt(1, id);
			
			pstm.executeUpdate();
		} catch (Exception e) {
			System.err.println("Ocorreu um erro, causa:"+e.getMessage());
			e.printStackTrace();
		}finally{
			AcessoBD.desconectar(conn, pstm, rs);
		}
	}
	
}
