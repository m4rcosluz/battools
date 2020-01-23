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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.modelo.Cliente;
import br.modelo.IN_UP_DEL_Cliente;
import br.modelo.gravaLog;

import java.awt.Font;
import java.awt.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Home {

	protected static final Object Resultado = null;
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
	 */
	private void initialize() {
		
		
		gravaLog Log=new gravaLog();
		Log.setFuncao("Entrou na p�gina home.");
		gravaLog.insere_log(Log);
		
		String validacao = br.modelo.config.verifica_config_user(null);
		if (validacao.equals("0")) {
			br.modelo.config conf = new br.modelo.config();
			conf.setCor("Branco");
			conf.setInicia_windows("Sim");
			br.modelo.config.insere_config(conf);

		}
		
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
				Log.setFuncao("Clicou no bot�o D�cimo terceiro - HOME");
				gravaLog.insere_log(Log);
				// DENTRO DO BOT�O, FIZ CHAMAR A CLASS MAIN DA PROXIMA TELA, E OCUTEI A HOME.
				Decimo_terceiro.main(null);
				frmHome.setVisible(false);
			}
		});

		JButton btnCalcularHoraExtra = new JButton("Calcular Hora Extra");
		btnCalcularHoraExtra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gravaLog Log=new gravaLog();
				Log.setFuncao("Clicou no bot�o Hora Extra - HOME");
				gravaLog.insere_log(Log);
				// DENTRO DO BOT�O, FIZ CHAMAR A CLASS MAIN DA PROXIMA TELA, E OCUTEI A HOME.
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
				Log.setFuncao("Clicou no bot�o Contato de Clientes - HOME");
				gravaLog.insere_log(Log);
				try {
					String cor = br.modelo.config.define_cor(null);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,
							"Aten��o: A conex�o com o banco de dados n�o foi efetiva, essa fun��o est� temporariamente indispon�vel.",
							"Aten��o", JOptionPane.WARNING_MESSAGE);
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
				Log.setFuncao("Clicou no bot�o Aplicativos - HOME");
				gravaLog.insere_log(Log);
				Aplicativos.main(null);
				frmHome.setVisible(false);
			}
		});

		JButton btnConfigurao = new JButton("Personaliza\u00E7\u00E3o");
		btnConfigurao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gravaLog Log=new gravaLog();
				Log.setFuncao("Clicou no bot�o Personalizacao - HOME");
				gravaLog.insere_log(Log);
				br.visual.config.main(null);
				frmHome.setVisible(false);
			}
		});

		JLabel lblV = new JLabel("v20.01.23");
		lblV.setFont(new Font("Papyrus", Font.BOLD, 15));

		GroupLayout groupLayout = new GroupLayout(frmHome.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(146)
						.addComponent(lblTeste, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
						.addComponent(lblV, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnCalcularHoraExtra, GroupLayout.PREFERRED_SIZE, 165,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE).addComponent(
										btnNewButton, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addComponent(btnContatoDeClientes, GroupLayout.PREFERRED_SIZE, 165,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE).addComponent(
										btnAplicativos, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)))
						.addGap(42))
				.addGroup(groupLayout.createSequentialGroup().addGap(164).addComponent(btnConfigurao)
						.addContainerGap(167, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblTeste,
										GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblV, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addGap(5)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
								.addComponent(btnCalcularHoraExtra, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnContatoDeClientes, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAplicativos, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
						.addComponent(btnConfigurao, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		frmHome.getContentPane().setLayout(groupLayout);
	}

	public void Abri_hora_extra(boolean b) {
		frmHome.setVisible(true);
	}
}
