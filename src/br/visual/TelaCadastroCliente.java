package br.visual;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.modelo.Cliente;
import br.modelo.Empregado;
import br.modelo.EmpregadoDAO;
import br.modelo.IN_UP_DEL_Cliente;
import br.modelo.gravaLog;
import br.controle.AcessoBD;
import br.controle.ValidaLetras;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.event.ActionEvent;
public class TelaCadastroCliente {

	protected static final Object Resultado = null;
	protected static final String String = null;
	protected static final boolean True = false;
	private JFrame frmCadastroCli;
	private JTextField textId;
	private JTextField textNome;
	private JTextField textCd_cliente;
	private JTextField textRamal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroCliente window = new TelaCadastroCliente();
					window.frmCadastroCli.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCadastroCliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadastroCli = new JFrame();
		frmCadastroCli.setAutoRequestFocus(false);
		String cor = br.modelo.config.define_cor(null);
		gravaLog Log=new gravaLog();
		Log.setFuncao("Entrou na página Cadastro Cliente.");
		gravaLog.insere_log(Log);
		
		if(cor.equals(null)) {
			frmCadastroCli.getContentPane().setBackground(SystemColor.activeCaption);
			}
		
		if(cor.equals("Azul")){
			frmCadastroCli.getContentPane().setBackground(Color.BLUE);
		}
		
		if(cor.equals("Vermelho")){
			frmCadastroCli.getContentPane().setBackground(Color.RED);
		}
		
		if(cor.equals("Branco")){
			frmCadastroCli.getContentPane().setBackground(Color.WHITE);
		}
		
		if(cor.equals("Rosa")){
			frmCadastroCli.getContentPane().setBackground(Color.PINK);
		}
		
		if(cor.equals("Preto")){
			frmCadastroCli.getContentPane().setBackground(Color.BLACK);
		}
		//frmCadastroCli.getContentPane().setForeground(Color.WHITE);
		String usuario_sessao = System.getProperty("user.name");
		String usuario_dir = System.getProperty("user.dir");
		System.out.println(usuario_dir);
		frmCadastroCli.setTitle("Cadastrar Contatos - 1.0 | Marcos Luz - Bat tools");
		//frmCadastroCli.getContentPane().setBackground(SystemColor.activeCaption);
		frmCadastroCli.setBounds(500, 100, 450, 300);
		frmCadastroCli.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblTeste = new JLabel("Bem-Vindo "+usuario_sessao);
		lblTeste.setFont(new Font("Papyrus", Font.BOLD, 15));
		
		textId = new JTextField();
		textId.setColumns(10);

		textNome = new JTextField();
		textNome.setColumns(10);
		//BOTOES
		final JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setVisible(false);
		final JButton btnCadastrar = new JButton("Cadastrar");
		final JButton btnAlterao = new JButton("Altera\u00E7\u00E3o");
		final JButton btnCadastr = new JButton("Cadastro");
		btnCadastr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCadastrar.setText("Cadastrar");
				btnCadastr.setEnabled(false);
				btnAlterao.setEnabled(true);
				btnNewButton.setVisible(false);
				textCd_cliente.setEnabled(true);
				textNome.setEnabled(true);
				textRamal.setEnabled(true);
			}
		});
		
		//final JButton btnAlterao = new JButton("Altera\u00E7\u00E3o");
		btnAlterao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCadastrar.setText("Alterar");
				btnAlterao.setEnabled(false);
				btnCadastr.setEnabled(true);
				btnNewButton.setVisible(true);
				textCd_cliente.setEnabled(false);
				textNome.setEnabled(false);
				textRamal.setEnabled(false);
			}
		});
		
		btnCadastr.setEnabled(false);
		
		textCd_cliente = new JTextField();
		textCd_cliente.setColumns(10);
		textCd_cliente.setDocument(new ValidaLetras());
		
		//JButton btnCadastrar = new JButton("Salvar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textCd_cliente.getText().trim().isEmpty()){
					JOptionPane.showMessageDialog(null, "Atenção: Campo Cliente não pode ser vazio.",  "Atenção", JOptionPane.WARNING_MESSAGE);
					return;
					}
				
				if(textNome.getText().trim().isEmpty()){
					JOptionPane.showMessageDialog(null, "Atenção: Campo Nome não pode ser vazio.",  "Atenção", JOptionPane.WARNING_MESSAGE);
					return;
					}
				
				if(textId.getText().trim().isEmpty()){
					JOptionPane.showMessageDialog(null, "Atenção: Campo Skype não pode ser vazio.",  "Atenção", JOptionPane.WARNING_MESSAGE);
					return;
					}
				
				
				if(textRamal.getText().trim().isEmpty()){
					textRamal.setText("0");
					}

				IN_UP_DEL_Cliente cli_IN_UP_DEL=new IN_UP_DEL_Cliente();
				Cliente cli=new Cliente();
				 
				String id = textId.getText();
				String nome = textNome.getText();
				String cliente = textCd_cliente.getText();
				String ramal = textRamal.getText();
				
				cli.setCd_contato(id);
				cli.setNm_contato(nome);
				cli.setCd_cliente(cliente);
				cli.setCd_ramal(ramal);
				cli_IN_UP_DEL.insere_cliente(cli);
				IN_UP_DEL_Cliente.update_nome_cx_alta(null);
				
				gravaLog Log=new gravaLog();
				Log.setFuncao("Cadastrou o contato : "+id+" Cliente : "+cliente);
				gravaLog.insere_log(Log);
				
			}
		});
		
		JLabel lblSkype = new JLabel("Skype :");
		lblSkype.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNome = new JLabel("Nome :");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblCdCliente = new JLabel("C\u00F3d Cliente:");
		lblCdCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	  ContatosCliente.main(null);
		    	  frmCadastroCli.setVisible(false);
			}
		});
		
		textRamal = new JTextField();
		textRamal.setColumns(10);
		textRamal.setText("0");
		textRamal.setDocument(new ValidaLetras());
		
		JLabel lblRamal = new JLabel("Ramal :");
		lblRamal.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		

		GroupLayout groupLayout = new GroupLayout(frmCadastroCli.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(112)
									.addComponent(btnCadastrar)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addContainerGap()
											.addComponent(textId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(textNome, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(34)
											.addComponent(lblSkype)
											.addGap(64)
											.addComponent(lblNome)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCdCliente, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
										.addComponent(textCd_cliente, 0, 0, Short.MAX_VALUE))))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textRamal, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(38)
									.addComponent(lblRamal, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(110)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnCadastr, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAlterao, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblTeste, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(162)
							.addComponent(btnNewButton)))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblTeste, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastr)
						.addComponent(btnAlterao))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSkype)
						.addComponent(lblCdCliente)
						.addComponent(lblRamal, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNome))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textCd_cliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textRamal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton)
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastrar)
						.addComponent(btnVoltar))
					.addGap(50))
		);
		frmCadastroCli.getContentPane().setLayout(groupLayout);
	}
}

