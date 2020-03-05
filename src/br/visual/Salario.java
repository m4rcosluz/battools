package br.visual;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;

import br.controle.ValidaLetras;
import br.modelo.gravaLog;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class Salario {

	private JFrame frmSalario;
	private JTextField textSalario;
	private JTextField textIdade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Salario window = new Salario();
					window.frmSalario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Salario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
	


		frmSalario = new JFrame();
		//frmSalario.setBounds(100, 100, 450, 300);
		//frmSalario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSalario.getContentPane().setLayout(null);
		
		String cor = br.modelo.config.define_cor(null);

		gravaLog Log=new gravaLog();
		Log.setFuncao("Entrou na página Descontos salário.");
		gravaLog.insere_log(Log);
		
		if (cor.equals("Azul")) {
			frmSalario.getContentPane().setBackground(SystemColor.textHighlight);
		}

		if (cor.equals("Vermelho")) {
			frmSalario.getContentPane().setBackground(new Color(220, 20, 60));
		}

		if (cor.equals("Branco")) {
			frmSalario.getContentPane().setBackground(new Color(230, 230, 250));
		}

		if (cor.equals("Rosa")) {
			frmSalario.getContentPane().setBackground(new Color(238, 130, 238));
		}

		if (cor.equals("Preto")) {
			frmSalario.getContentPane().setBackground(UIManager.getColor("ToolBar.floatingForeground"));
		}
		
		String sn_salario_campo = br.modelo.config.verifica_campo_sn_salario();
		String salario_user = br.modelo.config.verifica_campo_salario();

		
		frmSalario.setTitle("Desconto Salário - 1.0 | Marcos Luz - Bat tools");
		frmSalario.setBounds(500, 100, 450, 317);
		
		JLabel lblNewLabel_2 = new JLabel("Tp. Plano:");
		lblNewLabel_2.setBounds(90, 93, 67, 14);
		lblNewLabel_2.setVisible(false);

		JLabel lblNewLabel_1 = new JLabel("Idade:");
		lblNewLabel_1.setBounds(249, 93, 34, 14);
		lblNewLabel_1.setVisible(false);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 164, 414, 86);
		
		textSalario = new JTextField();
		textSalario.setBounds(166, 25, 86, 20);
		frmSalario.getContentPane().add(textSalario);
		textSalario.setColumns(10);
		textSalario.setDocument(new ValidaLetras());
		
		final JComboBox<Object> cbTpPlano = new JComboBox<Object>(
				new Object[] { "Infermaria", "Apartamento"});
		cbTpPlano.setBounds(153, 90, 86, 20);
		frmSalario.getContentPane().add(cbTpPlano);
		
		textIdade = new JTextField();
		textIdade.setBounds(291, 90, 34, 20);
		frmSalario.getContentPane().add(textIdade);
		textIdade.setDocument(new ValidaLetras());
		textIdade.setColumns(10);
		
		cbTpPlano.setVisible(false);
		textIdade.setVisible(false);
		
		JRadioButton rdbtnINSS = new JRadioButton("INSS");
		rdbtnINSS.setBounds(10, 62, 109, 23);
		frmSalario.getContentPane().add(rdbtnINSS);
			
		JRadioButton rdbtnVlTransporte = new JRadioButton("Vl. Transporte");
		rdbtnVlTransporte.setBounds(143, 62, 109, 23);
		frmSalario.getContentPane().add(rdbtnVlTransporte);
		
		JRadioButton rdbtnPlSaude = new JRadioButton("Plano de Sa\u00FAde");
		rdbtnPlSaude.setBounds(304, 62, 124, 23);
		frmSalario.getContentPane().add(rdbtnPlSaude);
		
		JLabel lblNewLabel = new JLabel("Sal\u00E1rio Bruto");
		lblNewLabel.setBounds(166, 11, 86, 14);
		

				if(sn_salario_campo.contentEquals("Não")) {
					System.out.println("Entrou");
					br.modelo.config conf=new br.modelo.config();
					sn_salario_campo = conf.getSnSalario();
					System.out.println(sn_salario_campo);
					if(sn_salario_campo ==null) {
					sn_salario_campo = "Não";
					}
				}
				
				if(sn_salario_campo.contentEquals("Sim")) {
					System.out.println("Entrou2");
					if(salario_user.contentEquals("")) {
						br.modelo.config conf=new br.modelo.config();
						salario_user = conf.getSalario();
					} 
					textSalario.setText(salario_user);
				}


		rdbtnPlSaude.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String valida;
				
				if (rdbtnPlSaude.isSelected()) {
					cbTpPlano.setVisible(true);
					textIdade.setVisible(true);
					lblNewLabel_1.setVisible(true);
					lblNewLabel_2.setVisible(true);
					textIdade.setText("18");
					
				} else {
					cbTpPlano.setVisible(false);
					textIdade.setVisible(false);
					lblNewLabel_1.setVisible(false);
					lblNewLabel_2.setVisible(false);
					
				}
				
			
				}
			}
		);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if (textSalario.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Atenção: Campo salário não pode ser vazio.", "Atenção",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (textIdade.getText().trim().isEmpty()) {
				textIdade.setText("18");	
				}
				
			double vl_desc_inss = 0;
			double vl_desc_transporte = 0;
			double vl_plano = 0;
			double resultado_final = 0;
			double salario = Double.parseDouble(textSalario.getText().replace(',', '.'));
			
				if (rdbtnINSS.isSelected()) {
					
					
					
					double inss_desc = 0;
					if (salario <= 1751) {
						inss_desc = 8;
					}
					if (salario <= 2919) {
						inss_desc = 9;
					}
					if (salario <= 5839) {
						inss_desc = 11;
					}

					vl_desc_inss = (double) ((salario * inss_desc) / (100));
				}
				
				if (rdbtnVlTransporte.isSelected()) {
					vl_desc_transporte = (double) ((salario * 7) / (100));
				}

				if (rdbtnPlSaude.isSelected()) {
					double idade = Double.parseDouble(textIdade.getText().replace(',', '.'));
					Object tp_plano = cbTpPlano.getSelectedItem();
					
					
					if(tp_plano.equals("Infermaria")) {
						System.out.println("In");
						

						if(idade >= 99) {vl_plano = 525.55;}
						if(idade <= 58) {vl_plano = 426.27;}
						if(idade <= 53) {vl_plano = 327.95;}
						if(idade <= 48) {vl_plano = 227.78;}
						if(idade <= 43) {vl_plano = 173.14;}
						if(idade <= 38) {vl_plano = 148.86;}
						if(idade <= 33) {vl_plano = 49.83;}
						if(idade <= 28) {vl_plano = 47.75;}
						if(idade <= 23) {vl_plano = 40.71;}
						System.out.println(vl_plano);
						}
					
					if(tp_plano.equals("Apartamento")) {
						System.out.println("Ap");
						

						if(idade >= 99) {vl_plano = 688.97;}
						if(idade <= 58) {vl_plano = 566.21;}
						if(idade <= 53) {vl_plano = 443.42;}
						if(idade <= 48) {vl_plano = 320.57;}
						if(idade <= 43) {vl_plano = 254.77;}
						if(idade <= 38) {vl_plano = 214.92;}
						if(idade <= 33) {vl_plano = 100.38;}
						if(idade <= 28) {vl_plano = 91.46;}
						if(idade <= 23) {vl_plano = 73.66;}
						System.out.println(vl_plano);
						}
					
				}
				
				resultado_final = salario - vl_desc_inss - vl_desc_transporte - vl_plano;
				double soma_des = vl_desc_inss + vl_desc_transporte + vl_plano;
				
				gravaLog Log=new gravaLog();
				Log.setFuncao("Calculou descontos do salário."+salario);
				gravaLog.insere_log(Log);
				
				textArea.setText("Salario Normal: "+salario+"\n"+"Salário com Descontos: "+resultado_final+"\n"+
						"Total dos descontos: "+soma_des+"\n"+ "Descontos:"+"\n"+
						"INSS: "+vl_desc_inss+
						" | Vl. Transporte: "+vl_desc_transporte+
						" | Plano de Saúde: "+vl_plano
						);
			}
		});
		btnCalcular.setBounds(100, 130, 89, 23);
		frmSalario.getContentPane().add(btnCalcular);
		
		
		frmSalario.getContentPane().add(lblNewLabel);
		
		
		frmSalario.getContentPane().add(lblNewLabel_1);
		
		frmSalario.getContentPane().add(lblNewLabel_2);
		
		frmSalario.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//DENTRO DO BOTÃO, FIZ CHAMAR A CLASS MAIN DA PROXIMA TELA, E OCUTEI A HOME.
		    	  Home.main(null);
		    	  frmSalario.setVisible(false);
			}
		});
		btnNewButton.setBounds(199, 130, 89, 23);
		frmSalario.getContentPane().add(btnNewButton);
	}
}
