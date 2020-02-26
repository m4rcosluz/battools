package br.visual;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.AbstractButton;
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
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class VisualizaLog {

	private JFrame frmVisualizaLog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizaLog window = new VisualizaLog();
					window.frmVisualizaLog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VisualizaLog() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVisualizaLog = new JFrame();
		frmVisualizaLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVisualizaLog.getContentPane().setLayout(null);
		
		//frame = new JFrame();
		frmVisualizaLog.setAutoRequestFocus(false);
		
		gravaLog Log=new gravaLog();
		Log.setFuncao("Entrou na página visualiza Log.");
		gravaLog.insere_log(Log);
		
		String cor = br.modelo.config.define_cor(null);
		
		if(cor.equals(null)) {
			frmVisualizaLog.getContentPane().setBackground(SystemColor.activeCaption);
			}
		
		if(cor.equals("Azul")){
			frmVisualizaLog.getContentPane().setBackground(Color.BLUE);
		}
		
		if(cor.equals("Vermelho")){
			frmVisualizaLog.getContentPane().setBackground(Color.RED);
		}
		
		if(cor.equals("Branco")){
			frmVisualizaLog.getContentPane().setBackground(Color.WHITE);
		}
		
		if(cor.equals("Rosa")){
			frmVisualizaLog.getContentPane().setBackground(Color.PINK);
		}
		
		if(cor.equals("Preto")){
			frmVisualizaLog.getContentPane().setBackground(Color.BLACK);
		}
		frmVisualizaLog.getContentPane().setForeground(Color.WHITE);
		frmVisualizaLog.setTitle("Busca Contatos - 1.0 | Marcos Luz - Bat tools");

		frmVisualizaLog.setBounds(500, 100, 490, 300);
		frmVisualizaLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JTextArea textLista = new JTextArea();
		textLista.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
			}
		});
		//JScrollPane scrollPane = new JScrollPane(textLista, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		textLista.append("\n");
		textLista.setBounds(10, 83, 454, 167);
	
		
	       

		
		
		frmVisualizaLog.getContentPane().add(textLista);
		
		JButton btnNewButton = new JButton("Pesquisar/Atualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			/*	if(textCliente.getText().trim().isEmpty()){
					JOptionPane.showMessageDialog(null, "Código do cliente não pode ser vazio.", "Atenção", JOptionPane.WARNING_MESSAGE);
					return;
					} */
				
				textLista.setText(null);
				ResultSet rs = null;


				 
				String SELECT="SELECT * FROM log_battools ORDER BY DH_LOG desc";
			
				
				
				
					List<Cliente> Contatos=new ArrayList<Cliente>();
					Cliente cli=null;
					Connection conn1 = null;
					Object pstm1;
					try {
						conn1=AcessoBD.conectar();
						pstm1=conn1.prepareStatement(SELECT);
						rs=((PreparedStatement) pstm1).executeQuery();
									
						while(rs.next()){
							
							String ok1 = rs.getString(1);
							String ok2 = rs.getString(2);
							String ok3 = rs.getString(3);
							textLista.append(ok1+" "+ok2+" "+ok3+"\n");
							 }
						
					
					} catch (Exception e) {
						System.err.println("Ocorreu um erro, causa:"+e.getMessage());
						e.printStackTrace();
					}finally{
						AcessoBD.desconectar(conn1);
					}
					
					if(textLista.getText().trim().isEmpty()){
						gravaLog Log=new gravaLog();
						Log.setFuncao("Não encontrou contatos com os parametros passados. - Busca Contato");
						gravaLog.insere_log(Log);
						JOptionPane.showMessageDialog(null, "Não foram encontrados contatos deste cliente.", "Atenção", JOptionPane.WARNING_MESSAGE);
						return;
						}

			}
		});
		btnNewButton.setBounds(39, 49, 157, 23);
		frmVisualizaLog.getContentPane().add(btnNewButton);
		
		JLabel lblBuscarContatos = new JLabel("Visualiza Log");
		lblBuscarContatos.setFont(new Font("Papyrus", Font.BOLD, 15));
		lblBuscarContatos.setBounds(174, 0, 172, 38);
		frmVisualizaLog.getContentPane().add(lblBuscarContatos);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//DENTRO DO BOTÃO, FIZ CHAMAR A CLASS MAIN DA PROXIMA TELA, E OCUTEI A HOME.
				config.main(null);
		    	frmVisualizaLog.setVisible(false);
			}
		});
		btnVoltar.setBounds(246, 49, 89, 23);
		frmVisualizaLog.getContentPane().add(btnVoltar);
		
		
		
		
	}
}
