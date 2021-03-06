package br.visual;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import br.controle.AcessoBD;
import br.controle.ClockLabel;
import br.modelo.gravaLog;
public class Telaconfig {

	protected static final Object Rosa = null;
	protected static final String Sim = null;
	private static final String SELECT_COR = null;
	private JFrame configBat;
	private JTextField textField;
	private JTextField campoCor;
	private JTextField txtSalario;
	private JTextField textCheat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Telaconfig window = new Telaconfig();
					window.configBat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Telaconfig() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		PreparedStatement pstm;
		ResultSet rs;
		
		configBat = new JFrame();
		
		gravaLog Log=new gravaLog();
		Log.setFuncao("Entou na p�gina Config.");
		gravaLog.insere_log(Log);
	

		String cor = br.modelo.config.define_cor(null);
		
		if (cor.equals("Azul")) {
			configBat.getContentPane().setBackground(SystemColor.textHighlight);
		}

		if (cor.equals("Vermelho")) {
			configBat.getContentPane().setBackground(new Color(220, 20, 60));
		}

		if (cor.equals("Branco")) {
			configBat.getContentPane().setBackground(new Color(230, 230, 250));
		}

		if (cor.equals("Rosa")) {
			configBat.getContentPane().setBackground(new Color(238, 130, 238));
		}

		if (cor.equals("Preto")) {
			configBat.getContentPane().setBackground(UIManager.getColor("ToolBar.floatingForeground"));
		}
		
		//frame.getContentPane().setBackground(Color.WHITE);
		configBat.setBounds(500, 100, 450, 317);
		configBat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		configBat.getContentPane().setLayout(null);


		final JComboBox<Object> comboBox = new JComboBox(new Object[]{cor,"Azul","Preto","Vermelho", "Rosa", "Branco"});
		comboBox.setBounds(10, 98, 107, 20);
		configBat.getContentPane().add(comboBox);
		String sn_salario_campo = br.modelo.config.verifica_campo_sn_salario();
		String salario_user = br.modelo.config.verifica_campo_salario();
		final JComboBox<Object> comboBox_2 = new JComboBox<Object>(new Object[]{sn_salario_campo,"Sim","N�o"});
		comboBox_2.setBounds(10, 165, 56, 20);
		configBat.getContentPane().add(comboBox_2);
		
		String sn_salario = ((String) comboBox_2.getSelectedItem());
		
		final JComboBox<Object> comboBox_1 = new JComboBox<Object>(new Object[]{"Sim","N�o"});
		comboBox_1.setBounds(10, 134, 107, 20);
		configBat.getContentPane().add(comboBox_1);
		
		JLabel lblAbreAoLigar = new JLabel("Iniciar com o S.O ?");
		lblAbreAoLigar.setBounds(10, 120, 119, 14);
		configBat.getContentPane().add(lblAbreAoLigar);
		
		JLabel lblCor = new JLabel("Cor");
		lblCor.setBounds(10, 82, 65, 14);
		configBat.getContentPane().add(lblCor);
		
		txtSalario = new JTextField();
		txtSalario.setBounds(131, 165, 65, 20);
		txtSalario.setColumns(10);
		
		if(sn_salario_campo ==null) {
			sn_salario_campo = "N�o";
		}
		
		if(sn_salario_campo.equals("Sim")) {
			txtSalario.setEnabled(true);
			txtSalario.setText(salario_user);
		} else {
			txtSalario.setEnabled(false);
			} 
		
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			private ResultSet rs;
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("141");
				String cor = ((String) comboBox.getSelectedItem());
				String sn_salario = ((String) comboBox_2.getSelectedItem());
				String salario_user = txtSalario.getText();
				String inicia_windows = ((String) comboBox_1.getSelectedItem());
				String snsalario_user = ((String) comboBox_2.getSelectedItem());	
				br.modelo.config conf=new br.modelo.config();
				conf.setCor(cor);
				conf.setSnSalario(sn_salario);
				conf.setSalario(salario_user);
				conf.setInicia_windows(inicia_windows);
				String sn_salario_campo = conf.getSnSalario();
				System.out.println(sn_salario_campo);
				if(snsalario_user.equals("Sim")) {
					txtSalario.setEnabled(true);
				} else {
					txtSalario.setEnabled(false);
					} 
				System.out.println("149");
				gravaLog Log=new gravaLog();
				Log.setFuncao("Clicou no bot�o Salvar - Config.");
				gravaLog.insere_log(Log);
				System.out.println("153");
				
				String teste = br.modelo.config.define_cor(null);
				System.out.print(teste);
				
			String cheat = textCheat.getText();	
			if(cheat.contentEquals("log")) {
				
				VisualizaLog.main(null);
				configBat.setVisible(false);
				return;
			}
				
			
			
			textCheat.setText(null);
			String usuario_sessaob = System.getProperty("user.name");
				String usuario_sessao = "'"+usuario_sessaob+"'";
				String SELECT_CONFIG_USER="SELECT count(cd_usuario) FROM config_battols where cd_usuario = "+usuario_sessao;
				String SELECT_COR="SELECT NM_COR FROM config_battols where cd_usuario = "+usuario_sessao;
				
				Connection conn1 = null;
					Object pstm1;
					try {System.out.println("173");
						conn1=AcessoBD.conectar();
						pstm1=conn1.prepareStatement(SELECT_CONFIG_USER);
						rs=((PreparedStatement) pstm1).executeQuery();
						while (rs.next()) {
							System.out.println(rs.getString(1));
							textField.setText(rs.getString(1));
							} 
							
	
					} catch (Exception e) {
						System.out.println("184");
						textField.setText("1");
						//System.err.println("Ocorreu um erro, causa:"+e.getMessage());
						//e.printStackTrace();
					}finally{
						AcessoBD.desconectar(conn1);
					}
					
					try {
						System.out.println("207");
						conn1=AcessoBD.conectar();
						pstm1=conn1.prepareStatement(SELECT_COR);
						rs=((PreparedStatement) pstm1).executeQuery();
						while (rs.next()) {
							System.out.println(rs.getString(1));
							campoCor.setText(rs.getString(1));
							} 
							
	
					} catch (Exception e) {
						System.out.println("218");
					}finally{
						AcessoBD.desconectar(conn1);
					}
				
					String result = textField.getText();
					double valida = Double.parseDouble(result);
					if(valida < 1){
						Log.setFuncao("Inseriu configura��o - Config");
						gravaLog.insere_log(Log);
						System.out.print("insert");
						System.out.println("231");
						//String cor = ((String) comboBox.getSelectedItem());
						//String inicia_windows = ((String) comboBox_1.getSelectedItem());
						//IN_UP_DEL_Cliente cli_IN_UP_DEL=new IN_UP_DEL_Cliente();

						conf.setCor(cor);
						conf.setInicia_windows(inicia_windows);
						br.modelo.config.insere_config(conf);
						
					} else {
						System.out.println("241");
						Log.setFuncao("Atualizou configura��o - Config");
						gravaLog.insere_log(Log);
						System.out.print("update");

						//IN_UP_DEL_Cliente cli_IN_UP_DEL=new IN_UP_DEL_Cliente();
						
						br.modelo.config.update_config(conf);
						
						}
					
				String cor1 = ((String) comboBox.getSelectedItem());	
				//String inicia_windows = (String) comboBox_1.getSelectedItem(); 
				System.out.println("254");
				
				String usuario_dir = System.getProperty("user.dir")+"\\Services";
				System.out.println(usuario_dir+"\\instala_inicializar.bat");
				
				if(inicia_windows.contentEquals("Sim")){
					try {
						Runtime.getRuntime().exec(usuario_dir+"\\instala_inicializar.bat");
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Aten��o: Erro ao tentar mover o bat para a inicializa��o do S.O.",  "Aten��o", JOptionPane.WARNING_MESSAGE);
						
					}
					
				} else {
					try {
						Runtime.getRuntime().exec(usuario_dir+"\\apaga_inicializar.bat");
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Aten��o: Erro ao tentar remover o bat para a inicializa��o do S.O.",  "Aten��o", JOptionPane.WARNING_MESSAGE);
						
					}
				}
				
				if (cor.equals("Azul")) {
					configBat.getContentPane().setBackground(SystemColor.textHighlight);
				}

				if (cor.equals("Vermelho")) {
					configBat.getContentPane().setBackground(new Color(220, 20, 60));
				}

				if (cor.equals("Branco")) {
					configBat.getContentPane().setBackground(new Color(230, 230, 250));
				}

				if (cor.equals("Rosa")) {
					configBat.getContentPane().setBackground(new Color(238, 130, 238));
				}

				if (cor.equals("Preto")) {
					configBat.getContentPane().setBackground(UIManager.getColor("ToolBar.floatingForeground"));
				}
			}
			
		});

		btnSalvar.setBounds(273, 225, 97, 25);
		configBat.getContentPane().add(btnSalvar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				br.visual.Home.main(null);
				configBat.setVisible(false);
	
			}
		});
		//btnVoltar.setVerticalAlignment(SwingConstants.TOP);
		//btnVoltar.setForeground(Color.GRAY);
		//btnVoltar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		//btnVoltar.setBackground(SystemColor.info);
		btnVoltar.setBounds(99, 227, 97, 25);
		configBat.getContentPane().add(btnVoltar);
		
		textField = new JTextField();
		textField.setVisible(false);
		textField.setEnabled(false);
		textField.setBackground(SystemColor.inactiveCaptionBorder);
		textField.setBounds(0, 0, 107, 20);
		configBat.getContentPane().add(textField);
		
		campoCor = new JTextField();
		campoCor.setEnabled(false);
		campoCor.setColumns(10);
		campoCor.setVisible(false);
		campoCor.setBounds(10, 11, 97, 20);
		configBat.getContentPane().add(campoCor);
		
		JLabel lblSalarioAutExtra = new JLabel("Salario Aut. Extra e Decimo?");
		lblSalarioAutExtra.setBounds(10, 152, 169, 14);
		configBat.getContentPane().add(lblSalarioAutExtra);
		

		
		JLabel lblSalrio = new JLabel("Sal\u00E1rio:");
		lblSalrio.setBounds(76, 168, 45, 14);
		configBat.getContentPane().add(lblSalrio);
		
		configBat.getContentPane().add(txtSalario);
		
		textCheat = new JTextField();
		textCheat.setBounds(10, 58, 86, 20);
		configBat.getContentPane().add(textCheat);
		textCheat.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Cheat's");
		lblNewLabel.setBounds(29, 42, 46, 14);
		configBat.getContentPane().add(lblNewLabel);
		


	}
		

	protected char[] getCor() {
		// TODO Auto-generated method stub
		return null;
	}

	protected void setInicia_windows(String string) {
		// TODO Auto-generated method stub
		
	}

	protected void setCor(String string) {
		// TODO Auto-generated method stub
		
	}
}