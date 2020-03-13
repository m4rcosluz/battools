package br.visual;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;

import br.controle.AcessoBD;
import br.controle.ValidaLetras;
import br.modelo.Cliente;
import br.modelo.gravaLog;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class BuscaContato {

	private JFrame frmBuscaContatos;
	private JTextField textCliente;
	private JTextField textNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscaContato window = new BuscaContato();
					window.frmBuscaContatos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BuscaContato() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBuscaContatos = new JFrame();
		frmBuscaContatos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBuscaContatos.getContentPane().setLayout(null);
		
		//frame = new JFrame();
		frmBuscaContatos.setAutoRequestFocus(false);
		
		gravaLog Log=new gravaLog();
		Log.setFuncao("Entrou na p�gina Busca Contato.");
		gravaLog.insere_log(Log);
		
		String cor = br.modelo.config.define_cor(null);
		
		if(cor.equals(null)) {
			frmBuscaContatos.getContentPane().setBackground(SystemColor.activeCaption);
			}
		
		if(cor.equals("Azul")){
			frmBuscaContatos.getContentPane().setBackground(Color.BLUE);
		}
		
		if(cor.equals("Vermelho")){
			frmBuscaContatos.getContentPane().setBackground(Color.RED);
		}
		
		if(cor.equals("Branco")){
			frmBuscaContatos.getContentPane().setBackground(Color.WHITE);
		}
		
		if(cor.equals("Rosa")){
			frmBuscaContatos.getContentPane().setBackground(Color.PINK);
		}
		
		if(cor.equals("Preto")){
			frmBuscaContatos.getContentPane().setBackground(Color.BLACK);
		}
		frmBuscaContatos.getContentPane().setForeground(Color.WHITE);
		frmBuscaContatos.setTitle("Busca Contatos - 1.0 | Marcos Luz - Bat tools");

		frmBuscaContatos.setBounds(500, 100, 490, 300);
		frmBuscaContatos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JTextArea textLista = new JTextArea();
		textLista.setLineWrap(true);
		JScrollPane barraRolagem = new JScrollPane(textLista);
		textLista.setVisible(true);
		frmBuscaContatos.getContentPane().add(barraRolagem);
		//this.getContentPane().add(barraRolagem);

		//JScrollPane scrollPane = new JScrollPane(textLista, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		textLista.append("\n");
		textLista.setBounds(10, 113, 454, 137);	
		   
		//frmBuscaContatos.getContentPane().add(textLista);
		
		textCliente = new JTextField();
		textCliente.setBounds(10, 37, 97, 20);
		frmBuscaContatos.getContentPane().add(textCliente);
		textCliente.setColumns(10);
		textCliente.setDocument(new ValidaLetras());
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			/*	if(textCliente.getText().trim().isEmpty()){
					JOptionPane.showMessageDialog(null, "C�digo do cliente n�o pode ser vazio.", "Aten��o", JOptionPane.WARNING_MESSAGE);
					return;
					} */
				
				textLista.setText(null);
				ResultSet rs = null;
				String codigo_cliente = textCliente.getText();
				String Nome = textNome.getText();
				String bkpcodigo_cliente = "'"+codigo_cliente+"'";
				String bkpNome = "'%"+Nome+"%'";

				 
				String SELECT=" SELECT * FROM cliente_conta"+ "to where cd_cliente = "+bkpcodigo_cliente.toUpperCase();
				
				
				if(textCliente.getText().trim().isEmpty()){
					SELECT="SELECT * FROM cliente_contato where Upper(nm_contato) like "+bkpNome.toUpperCase();
					gravaLog Log=new gravaLog();
					Log.setFuncao("Pesquisou pelo contato: "+bkpNome.toUpperCase()+" - Busca Contato");
					gravaLog.insere_log(Log);
				} 

				
			
				if(textNome.getText().length() > 0 && textCliente.getText().length() > 0){
					System.out.println("entrou");
					SELECT="SELECT * FROM cliente_contato where cd_cliente ="+bkpcodigo_cliente+" and Upper(nm_contato) like "+bkpNome.toUpperCase();
					gravaLog Log=new gravaLog();
					Log.setFuncao("Pesquisou pelo contato: "+bkpNome.toUpperCase() +" Cliente: "+ codigo_cliente+" - Busca Contato");
					gravaLog.insere_log(Log);
					} 
				
				if(textNome.getText().length() < 1 && textCliente.getText().length() < 1){
					JOptionPane.showMessageDialog(null, "� Necess�rio informar o c�digo do cliente e/ou o nome do contato.", "Aten��o", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(textNome.getText().length() < 1 && textCliente.getText().length() > 0){
					gravaLog Log=new gravaLog();
					Log.setFuncao("Pesquisou pelo cliente: "+codigo_cliente+" - Busca Contato");
					gravaLog.insere_log(Log);
				}
				
				
				
					List<Cliente> Contatos=new ArrayList<Cliente>();
					Cliente cli=null;
					Connection conn1 = null;
					Object pstm1;
					try {
						conn1=AcessoBD.conectar();
						pstm1=conn1.prepareStatement(SELECT);
						rs=((PreparedStatement) pstm1).executeQuery();
									
						while(rs.next()){
					
							cli=new Cliente();
							cli.setCd_contato(rs.getString("cd_contato"));
							cli.setNm_contato(rs.getString("nm_contato"));
							cli.setCd_cliente(rs.getString("cd_cliente"));
							cli.setCd_ramal(rs.getString("cd_ramal"));
							Contatos.add(cli);
							System.out.println(cli.getCd_cliente());
							textLista.append("Nome: "+cli.getNm_contato()+"   Skype: "+cli.getCd_contato()+   "    Ramal: "+cli.getCd_ramal()+"   Cliente: "+cli.getCd_cliente()+ "\n");
							 }
						
					
					} catch (Exception e) {
						System.err.println("Ocorreu um erro, causa:"+e.getMessage());
						e.printStackTrace();
					}finally{
						AcessoBD.desconectar(conn1);
					}
					
					if(textLista.getText().trim().isEmpty()){
						gravaLog Log=new gravaLog();
						Log.setFuncao("N�o encontrou contatos com os parametros passados. - Busca Contato");
						gravaLog.insere_log(Log);
						JOptionPane.showMessageDialog(null, "N�o foram encontrados contatos deste cliente.", "Aten��o", JOptionPane.WARNING_MESSAGE);
						return;
						}

			}
		});
		btnNewButton.setBounds(160, 79, 89, 23);
		frmBuscaContatos.getContentPane().add(btnNewButton);
		
		JLabel lblBuscarContatos = new JLabel("Buscar Contatos");
		lblBuscarContatos.setFont(new Font("Papyrus", Font.BOLD, 15));
		lblBuscarContatos.setBounds(133, 0, 172, 38);
		frmBuscaContatos.getContentPane().add(lblBuscarContatos);
		
		JLabel label = new JLabel("C\u00F3d Cliente:");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(10, 22, 86, 16);
		frmBuscaContatos.getContentPane().add(label);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//DENTRO DO BOT�O, FIZ CHAMAR A CLASS MAIN DA PROXIMA TELA, E OCUTEI A HOME.
		    	  ContatosCliente.main(null);
		    	  frmBuscaContatos.setVisible(false);
			}
		});
		btnVoltar.setBounds(264, 79, 89, 23);
		frmBuscaContatos.getContentPane().add(btnVoltar);
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNome.setBounds(10, 68, 117, 16);
		frmBuscaContatos.getContentPane().add(lblNome);
		
		
		textNome = new JTextField();
		textNome.setColumns(10);
		textNome.setBounds(10, 82, 97, 20);
		frmBuscaContatos.getContentPane().add(textNome);
		
		//JTextArea textLista = new JTextArea();
		textLista.setBounds(10, 113, 454, 137);
		frmBuscaContatos.getContentPane().add(textLista);
		
		
		
		
	}
}
