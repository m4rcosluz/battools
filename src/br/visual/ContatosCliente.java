package br.visual;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.SwingConstants;

import br.controle.AcessoBD;

import java.awt.SystemColor;
import javax.swing.JLabel;
public class ContatosCliente {

	protected static final Object Resultado = null;
	private JFrame frmContatosCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContatosCliente window = new ContatosCliente();
					window.frmContatosCliente.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ContatosCliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmContatosCliente = new JFrame();
		frmContatosCliente.setAutoRequestFocus(false);
		String cor = br.modelo.config.define_cor(null);
		
		if(cor.equals(null)) {
			frmContatosCliente.getContentPane().setBackground(SystemColor.activeCaption);
			}
		
		if(cor.equals("Azul")){
			frmContatosCliente.getContentPane().setBackground(Color.BLUE);
		}
		
		if(cor.equals("Vermelho")){
			frmContatosCliente.getContentPane().setBackground(Color.RED);
		}
		
		if(cor.equals("Branco")){
			frmContatosCliente.getContentPane().setBackground(Color.WHITE);
		}
		
		if(cor.equals("Rosa")){
			frmContatosCliente.getContentPane().setBackground(Color.PINK);
		}
		
		if(cor.equals("Preto")){
			frmContatosCliente.getContentPane().setBackground(Color.BLACK);
		}
		String usuario_sessao = System.getProperty("user.name");
		String usuario_dir = System.getProperty("user.dir");
		System.out.println(usuario_dir);
		//frmHome.setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/img/46-464234_computador-da-apple-desenho-clipart-laptop-macbook-pantalla.png")));
		frmContatosCliente.setTitle("Contatos Cliente - 1.0 | Marcos Luz - Bat tools");

		frmContatosCliente.setBounds(500, 100, 450, 300);
		frmContatosCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton btnNewButton = new JButton("Buscar Contatos");
		//btnNewButton.setForeground(Color.GRAY);
		//btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		//btnNewButton.setIcon(new ImageIcon("J:\\SERVER\\Icons\\Icon_GERENCIADOR_DIVIDAS.ico"));
		//btnNewButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		//btnNewButton.setBackground(SystemColor.info);
		btnNewButton.addActionListener(  
				   new ActionListener(){  
					      public void actionPerformed(ActionEvent evento){  
					    	  //DENTRO DO BOTÃO, FIZ CHAMAR A CLASS MAIN DA PROXIMA TELA, E OCUTEI A HOME.
					    	  BuscaContato.main(null);
					    	  frmContatosCliente.setVisible(false);
					      }  
					   }  
					);
		
		JButton btnCalcularHoraExtra = new JButton("Cadastrar Contatos");
		btnCalcularHoraExtra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 //DENTRO DO BOTÃO, FIZ CHAMAR A CLASS MAIN DA PROXIMA TELA, E OCUTEI A HOME.
		    	  TelaCadastroCliente.main(null);
		    	  frmContatosCliente.setVisible(false);
		        
			}
		});
		//btnCalcularHoraExtra.setVerticalAlignment(SwingConstants.TOP);
		//btnCalcularHoraExtra.setForeground(Color.GRAY);
		//btnCalcularHoraExtra.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		//btnCalcularHoraExtra.setBackground(SystemColor.info);
		
		JLabel lblTeste = new JLabel("Bem-Vindo "+usuario_sessao);
		lblTeste.setFont(new Font("Papyrus", Font.BOLD, 15));
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home.main(null);
				frmContatosCliente.setVisible(false);
			}
		});
		//btnVoltar.setVerticalAlignment(SwingConstants.TOP);
		//btnVoltar.setForeground(Color.GRAY);
		//btnVoltar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		//btnVoltar.setBackground(SystemColor.info);
		

		
		
		
		
		
		
//		textField = new JTextField();
//		textField.setColumns(10);
		
		//JRadioButton rdbtnDescontoVem = new JRadioButton("Desconto VEM");
		

		GroupLayout groupLayout = new GroupLayout(frmContatosCliente.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCalcularHoraExtra, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(259, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(110, Short.MAX_VALUE)
					.addComponent(lblTeste, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
					.addGap(66))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(171, Short.MAX_VALUE)
					.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(133))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblTeste, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCalcularHoraExtra, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
					.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		frmContatosCliente.getContentPane().setLayout(groupLayout);
	}

	public void Abri_hora_extra(boolean b) {
		frmContatosCliente.setVisible(true);
	}
}

