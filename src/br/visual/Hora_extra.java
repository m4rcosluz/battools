package br.visual;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import br.controle.ValidaLetras;

import java.awt.SystemColor;

public class Hora_extra {

	protected static final Object Resultado = null;
	private JFrame frmHoraExtra;
	private JTextField txt_add_noturno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hora_extra window = new Hora_extra();
					window.frmHoraExtra.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Hora_extra() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHoraExtra = new JFrame();
		frmHoraExtra.setAutoRequestFocus(false);
		try {
			String cor = br.modelo.config.define_cor(null);
			



			if (cor.equals(null)) {
				cor = "Branco";
				frmHoraExtra.getContentPane().setBackground(SystemColor.activeCaption);
			}

			if (cor.equals("Azul")) {
				frmHoraExtra.getContentPane().setBackground(Color.BLUE);
			}

			if (cor.equals("Vermelho")) {
				frmHoraExtra.getContentPane().setBackground(Color.RED);
			}

			if (cor.equals("Branco")) {
				frmHoraExtra.getContentPane().setBackground(Color.WHITE);
			}

			if (cor.equals("Rosa")) {
				frmHoraExtra.getContentPane().setBackground(Color.PINK);
			}

			if (cor.equals("Preto")) {
				frmHoraExtra.getContentPane().setBackground(Color.BLACK);
			}

		} catch (Exception e) {
			frmHoraExtra.getContentPane().setBackground(Color.WHITE);
			e.printStackTrace();
		}
		// frmHoraExtra.setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/img/46-464234_computador-da-apple-desenho-clipart-laptop-macbook-pantalla.png")));
		frmHoraExtra.setTitle("Hora Extra - 2.2| Marcos Luz - Bat tools");

		frmHoraExtra.setBounds(500, 100, 450, 300);
		frmHoraExtra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblSalrio = new JLabel("Sal\u00E1rio:");

		final JTextField txt_salario = new JTextField();
		txt_salario.setBackground(SystemColor.inactiveCaptionBorder);
		txt_salario.setText("0");
		txt_salario.setDocument(new ValidaLetras());
		String sn_salario_campo = br.modelo.config.verifica_campo_sn_salario();
		String salario_user = br.modelo.config.verifica_campo_salario();

				if(sn_salario_campo ==null) {
					sn_salario_campo = "Não";
				}
				
				if(sn_salario_campo.equals("Sim")) {
					txt_salario.setText(salario_user);
				}

		final JTextField txt_hora = new JTextField();
		txt_hora.setBackground(SystemColor.inactiveCaptionBorder);
		txt_hora.setText("0");
		txt_hora.setDocument(new ValidaLetras());

		txt_add_noturno = new JTextField();
		txt_add_noturno.setBackground(SystemColor.inactiveCaptionBorder);
		txt_add_noturno.setText("0");
		txt_add_noturno.setDocument(new ValidaLetras());

		final JRadioButton rdbtnINSS = new JRadioButton("Desconto INSS");
		rdbtnINSS.setBackground(SystemColor.activeCaption);
		final JRadioButton rdbtnDescontoVem = new JRadioButton("Desconto VEM");
		rdbtnDescontoVem.setBackground(SystemColor.activeCaption);
		rdbtnDescontoVem.setVisible(false);
		final JRadioButton rdbtnAdicionalNoturno = new JRadioButton("Adicional Noturno");
		rdbtnAdicionalNoturno.setBackground(SystemColor.activeCaption);
		JButton btnNewButton = new JButton("CALCULAR");
		// btnNewButton.setForeground(Color.GRAY);
		// btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		// btnNewButton.setIcon(new
		// ImageIcon("J:\\SERVER\\Icons\\Icon_GERENCIADOR_DIVIDAS.ico"));
		// btnNewButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		// btnNewButton.setBackground(SystemColor.info);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario_sessao = System.getProperty("user.name");
				System.out.println("Olá " + usuario_sessao);
				double resultado;
				double vem = 7;
				double vem_desc = 0;
				double valor_hora_noturna = 0;
				double vl_percentual_noturno = 0;
				// FORMULA : valor do Salario / 200 hs x 1,5 x Quantidade das horas extras

				// VALIDA CAMPO VAZIO
				if (txt_salario.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Atenção: Campo salário não pode ser vazio.", "Atenção",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				// VALIDA CAMPO VAZIO
				if (txt_hora.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Atenção: Campo hora não pode ser vazio.", "Atenção",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				// VALIDA CAMPO VAZIO
				if (txt_add_noturno.getText().trim().isEmpty()) {
					txt_add_noturno.setText("0");
					// return;
				} else {
					vl_percentual_noturno = 20;
				}
				// CODIGOS SECRETOS
				String secreto = (txt_salario.getText());

				if (secreto.contentEquals("vem")) {
					rdbtnDescontoVem.setVisible(true);
					txt_salario.setText("0");
				}

				if (secreto.contentEquals("vemd")) {
					rdbtnDescontoVem.setVisible(false);
					txt_salario.setText("0");
				}

				// CAMPO SALARIO
				String salario_string = (txt_hora.getText().replace(",", "."));
				System.out.println(salario_string + " Informação do salário");

				// CAMPO HORA
				String hora_string = (txt_hora.getText().replace(",", "."));
				System.out.println(hora_string + " Informação da hora");

				// CAMPO HORA AD. NORTUNO
				String hora_norturno_string = (txt_add_noturno.getText().replace(",", "."));
				System.out.println(txt_add_noturno + " Informação da hora noturno");

				double salario1 = Double.parseDouble(salario_string);
				System.out.println("salario virgula: " + salario1);
				double qt_hora_noturna = Double.parseDouble(hora_norturno_string);

				double salario = Double.parseDouble(txt_salario.getText().replace(',', '.'));
				double hora = Double.parseDouble(hora_string);
				System.out.println("salario ponto: " + salario);

				if (rdbtnAdicionalNoturno.isSelected()) {
					// calcula hora
					double valor_hora = (salario / 200) * 1.5;
					double valor_percentual = (vl_percentual_noturno * valor_hora / 100);
					double valor_hora_noturna1 = valor_percentual + valor_hora;
					valor_hora_noturna = valor_hora_noturna1 * qt_hora_noturna;
					System.out.println(valor_percentual + " " + valor_hora + " " + qt_hora_noturna
							+ " Valor da hora noturna " + valor_hora_noturna + " Valor do percentual "
							+ valor_percentual + " Valor da normal " + valor_hora);
				} else {
					txt_add_noturno.setText("0");
					valor_hora_noturna = 0;

				}

				if (rdbtnDescontoVem.isSelected()) {
					vem_desc = (salario / 100) * vem;
				}
				// boolean inss = rdbtnINSS;
				// valor do Salario / 200 hs x 1,5 x Quantidade das horas extras
				resultado = (salario / 200) * 1.5 * hora - vem_desc + valor_hora_noturna;
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

					double desconto = (resultado * inss_desc) / (100);
					double resultado_final;
					resultado_final = resultado - desconto;

					double d = resultado_final;
					DecimalFormat df = new DecimalFormat("#.##");
					JOptionPane.showMessageDialog(null, "Você vai receber: " + "R$ " + df.format(d) + " ");

					// JOptionPane.showMessageDialog(null, "Você vai receber: "+" "+
					// (resultado_final)+" "+"R$");
					// JOptionPane.showMessageDialog(null, "ok");
				} else {
					double d = resultado;
					DecimalFormat df = new DecimalFormat("#.##");
					JOptionPane.showMessageDialog(null, "Você vai receber: " + "R$ " + df.format(d));
					// JOptionPane.showMessageDialog(null, "Você vai receber: "+" "+
					// Double.toString(resultado));
				}

			}
		});

		JLabel lblHoras = new JLabel("Horas normais:");

		// JRadioButton rdbtnAdicionalNoturno = new JRadioButton("Adicional Noturno");

		JLabel lblParametros = new JLabel("Parametros");

		JLabel lblHorasAdNoturno = new JLabel("Horas Ad. Noturno:");

		JButton btnVoltar = new JButton("VOLTAR");
		// btnVoltar.setForeground(Color.GRAY);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// DENTRO DO BOTÃO, FIZ CHAMAR A CLASS MAIN DA PROXIMA TELA, E OCUTEI A HOME.
				Home.main(null);
				frmHoraExtra.setVisible(false);

				// System.exit(0);
			}
		});
		// btnVoltar.setVerticalAlignment(SwingConstants.TOP);
		// btnVoltar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		// btnVoltar.setBackground(SystemColor.info);

		JLabel lblCalculadorDeHora = new JLabel("Calculador de Hora Extra");
		lblCalculadorDeHora.setFont(new Font("Papyrus", Font.BOLD, 15));

//		textField = new JTextField();
//		textField.setColumns(10);

		// JRadioButton rdbtnDescontoVem = new JRadioButton("Desconto VEM");

		GroupLayout groupLayout = new GroupLayout(frmHoraExtra.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(107).addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblHoras, GroupLayout.PREFERRED_SIZE, 89,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblSalrio).addComponent(lblHorasAdNoturno))
										.addGap(10)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(txt_salario).addComponent(txt_hora, Alignment.LEADING)
												.addComponent(txt_add_noturno, Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)))
								.addComponent(lblCalculadorDeHora, GroupLayout.PREFERRED_SIZE, 258,
										GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup().addGap(29).addComponent(lblParametros))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(rdbtnINSS, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(rdbtnAdicionalNoturno, Alignment.LEADING,
												GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(rdbtnDescontoVem, GroupLayout.PREFERRED_SIZE, 126,
										GroupLayout.PREFERRED_SIZE))
								.addGap(15)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnVoltar, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
										.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 183,
												Short.MAX_VALUE))))
				.addContainerGap(69, GroupLayout.PREFERRED_SIZE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(lblCalculadorDeHora, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addGap(13)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txt_salario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSalrio))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txt_hora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHoras))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txt_add_noturno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHorasAdNoturno))
						.addGap(2)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup().addGap(22).addComponent(lblParametros)
										.addGap(12).addComponent(rdbtnAdicionalNoturno).addGap(6)
										.addComponent(rdbtnINSS).addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(rdbtnDescontoVem).addContainerGap(10, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup().addComponent(btnNewButton)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnVoltar,
												GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addGap(24)))));
		frmHoraExtra.getContentPane().setLayout(groupLayout);
	}

	public void Abri_hora_extra(boolean b) {
		frmHoraExtra.setVisible(true);
	}
}
