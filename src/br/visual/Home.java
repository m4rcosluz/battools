package br.visual;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;
import br.controle.Acesso;
import br.controle.ClockLabel;
import br.modelo.gravaLog;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
public class Home {

	protected static final Object Resultado = null;
	private static final String SELECT_GERAL = null;
	private JFrame frmHome;
	private JLabel lblHora_1;

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
		String status = gravaLog.insere_log(Log);
		

		Acesso.insere_acesso(null);
		//String status = gravaLog.insere_log(null);
		String acesso_geral = Acesso.verifica_acesso_geral();
		String acesso_user = Acesso.verifica_acesso_user();
		
		
		if(acesso_geral == null) {acesso_geral = "0";}
		if(acesso_user == null) {acesso_user = "0";}
		

		
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
		
		
		//String acesso_geral = "1";
		//String acesso_user = "1";
	

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
			}
		);

		JButton btnConfigurao = new JButton("Config.");
		btnConfigurao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gravaLog Log=new gravaLog();
				Log.setFuncao("Clicou no botão Personalizacao - HOME");
				gravaLog.insere_log(Log);
				br.visual.config.main(null);
				frmHome.setVisible(false);
			}
		});
		
		JLabel lbAcesso = new JLabel("Acessos: "+acesso_user);
		lbAcesso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		
		JLabel lblSeusAcessosNull = new JLabel("Seus acessos: "+acesso_geral);
		lblSeusAcessosNull.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblStatus = new JLabel("Status: "+status);
		if(status.contentEquals("Ok")) {lblStatus.setForeground(Color.GREEN);} else {lblStatus.setForeground(Color.RED);}
		
		JLabel lblHora = new JLabel("New label");
		lblHora_1 = new ClockLabel();

		GroupLayout groupLayout = new GroupLayout(frmHome.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnCalcularHoraExtra, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnContatoDeClientes, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
									.addComponent(btnAplicativos, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)))
							.addGap(42))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblSeusAcessosNull)
									.addGap(48)
									.addComponent(lblTeste, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
								.addComponent(lbAcesso, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(33)
									.addComponent(lblStatus))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(12)
									.addComponent(lblHora_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(36))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnConfigurao)
							.addGap(30))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSeusAcessosNull, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTeste, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStatus))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lbAcesso)
						.addComponent(lblHora_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
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
