package br.visual;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.controle.Acesso;
import br.controle.AcessoBD;
import br.modelo.Cliente;
import br.modelo.IN_UP_DEL_Cliente;
import br.modelo.gravaLog;

import java.awt.Font;
import java.awt.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Home {

	protected static final Object Resultado = null;
	private static final String SELECT_GERAL = null;
	private JFrame frmHome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frmHome.setVisible(true);
 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	


	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @return 
	 */
	private Object initialize() {
		
		String validacao = br.modelo.config.verifica_config_user(null);
		if (validacao.equals("0")) {
			br.modelo.config conf = new br.modelo.config();
			conf.setCor("Branco");
			conf.setInicia_windows("Sim");
			br.modelo.config.insere_config(conf);
		}
		
		gravaLog Log=new gravaLog();
		Log.setFuncao("Entrou na página home.");
		gravaLog.insere_log(Log);
		
		Acesso.insere_acesso(null);
	
		String acesso_geral = Acesso.verifica_acesso_geral();
		String acesso_user = Acesso.verifica_acesso_user();
		System.out.print(acesso_geral);
		
		frmHome = new JFrame();
		frmHome.setAutoRequestFocus(false);
		

		
		String cor = br.modelo.config.define_cor(null);


		if (cor.equals("Azul")) {
			frmHome.getContentPane().setBackground(Color.BLUE);
		}

		if (cor.equals("Vermelho")) {
			frmHome.getContentPane().setBackground(Color.RED);
		}

		if (cor.equals("Branco")) {
			frmHome.getContentPane().setBackground(Color.WHITE);
		}

		if (cor.equals("Rosa")) {
			frmHome.getContentPane().setBackground(Color.PINK);
		}

		if (cor.equals("Preto")) {
			frmHome.getContentPane().setBackground(Color.BLACK);
		}
		



			
	

		String usuario_dir = System.getProperty("user.dir");
		System.out.println(usuario_dir);

		frmHome.setTitle("Tela Inicial - 1.0 | Marcos Luz - Bat tools");

		frmHome.setBounds(500, 100, 450, 300);
		frmHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btnNewButton = new JButton("Calcular Decimo 13\u00BA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				gravaLog Log=new gravaLog();
				Log.setFuncao("Clicou no botão Décimo terceiro - HOME");
				gravaLog.insere_log(Log);
				// DENTRO DO BOTÃO, FIZ CHAMAR A CLASS MAIN DA PROXIMA TELA, E OCUTEI A HOME.
				Decimo_terceiro.main(null);
				frmHome.setVisible(false);
			}
		});

		JButton btnCalcularHoraExtra = new JButton("Calcular Hora Extra");
		btnCalcularHoraExtra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gravaLog Log=new gravaLog();
				Log.setFuncao("Clicou no botão Hora Extra - HOME");
				gravaLog.insere_log(Log);
				// DENTRO DO BOTÃO, FIZ CHAMAR A CLASS MAIN DA PROXIMA TELA, E OCUTEI A HOME.
				Hora_extra.main(null);
				frmHome.setVisible(false);

			}
		});

		JLabel lblTeste = new JLabel("Bat Tools");
		lblTeste.setFont(new Font("Papyrus", Font.BOLD, 15));

		JButton btnContatoDeClientes = new JButton("Contato de Clientes");
		btnContatoDeClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gravaLog Log=new gravaLog();
				Log.setFuncao("Clicou no botão Contato de Clientes - HOME");
				gravaLog.insere_log(Log);
				try {
					String cor = br.modelo.config.define_cor(null);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,
							"Atenção: A conexão com o banco de dados não foi efetiva, essa função está temporariamente indisponível.",
							"Atenção", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
					return;
				}
				ContatosCliente.main(null);
				frmHome.setVisible(false);
			}
		});

		JButton btnAplicativos = new JButton("Aplicativos");
		btnAplicativos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gravaLog Log=new gravaLog();
				Log.setFuncao("Clicou no botão Aplicativos - HOME");
				gravaLog.insere_log(Log);
				Aplicativos.main(null);
				frmHome.setVisible(false);
			}
		});

		JButton btnConfigurao = new JButton("Personaliza\u00E7\u00E3o");
		btnConfigurao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gravaLog Log=new gravaLog();
				Log.setFuncao("Clicou no botão Personalizacao - HOME");
				gravaLog.insere_log(Log);
				br.visual.config.main(null);
				frmHome.setVisible(false);
			}
		});

		JLabel lblV = new JLabel("v20.01.23");
		lblV.setFont(new Font("Papyrus", Font.BOLD, 15));
		
		JLabel lbAcesso = new JLabel("Acessos: "+acesso_user);
		lbAcesso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblSeusAcessosNull = new JLabel("Seus acessos: "+acesso_geral);
		lblSeusAcessosNull.setFont(new Font("Tahoma", Font.PLAIN, 12));

		GroupLayout groupLayout = new GroupLayout(frmHome.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(164)
					.addComponent(btnConfigurao)
					.addContainerGap(167, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnCalcularHoraExtra, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnContatoDeClientes, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
							.addComponent(btnAplicativos, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)))
					.addGap(42))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSeusAcessosNull)
						.addComponent(lbAcesso, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addComponent(lblTeste, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
					.addComponent(lblV, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblV, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblSeusAcessosNull, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lbAcesso))
								.addComponent(lblTeste, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnCalcularHoraExtra, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnContatoDeClientes, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAplicativos, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnConfigurao, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		frmHome.getContentPane().setLayout(groupLayout);
		return groupLayout;
	}

	public void Abri_hora_extra(boolean b) {
		frmHome.setVisible(true);
	}
}
