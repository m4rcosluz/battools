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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import br.controle.ValidaLetras;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Decimo_terceiro {

	protected static final Object Resultado = null;
	private JFrame frmDecimoTerceiro;
	private JTextField text1parcela;
	private JTextField text2parcela;
	private JTextField textDecimoTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Decimo_terceiro window = new Decimo_terceiro();
					window.frmDecimoTerceiro.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Decimo_terceiro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDecimoTerceiro = new JFrame();
		frmDecimoTerceiro.setAutoRequestFocus(false);
		String cor = br.modelo.config.define_cor(null);

		if (cor.equals("Azul")) {
			frmDecimoTerceiro.getContentPane().setBackground(Color.BLUE);
		}

		if (cor.equals("Vermelho")) {
			frmDecimoTerceiro.getContentPane().setBackground(Color.RED);
		}

		if (cor.equals("Branco")) {
			frmDecimoTerceiro.getContentPane().setBackground(Color.WHITE);
		}

		if (cor.equals("Rosa")) {
			frmDecimoTerceiro.getContentPane().setBackground(Color.PINK);
		}

		if (cor.equals("Preto")) {
			frmDecimoTerceiro.getContentPane().setBackground(Color.BLACK);
		}
		frmDecimoTerceiro.setTitle("Decimo Terceiro - 1.0 | Marcos Luz - Bat tools");
		frmDecimoTerceiro.setBounds(500, 100, 450, 317);
		frmDecimoTerceiro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblSalrio = new JLabel("Sal\u00E1rio:");

		final JComboBox<Object> comboBox = new JComboBox<Object>(
				new Object[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" });
		// JTextField txt_salario = new JTextField();
		final JTextField txt_salario = new JTextField();
		txt_salario.setDocument(new ValidaLetras());
		txt_salario.setBackground(SystemColor.inactiveCaptionBorder);
		txt_salario.setText("0");
		String sn_salario_campo = br.modelo.config.verifica_campo_sn_salario();
		String salario_user = br.modelo.config.verifica_campo_salario();

				if(sn_salario_campo ==null) {
					sn_salario_campo = "Não";
				}
				
				if(sn_salario_campo.equals("Sim")) {
					txt_salario.setText(salario_user);
				}
		final JLabel lblMsgMeses = new JLabel(
				"Se a quantidade de meses forem menores que 3, valores negativos podem ser apresentados.");
		lblMsgMeses.setForeground(Color.RED);
		lblMsgMeses.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblMsgMeses.setVisible(false);
		text1parcela = new JTextField();
		text1parcela.setColumns(10);
		text1parcela.setEnabled(true);
		text1parcela.setEditable(false);
		text1parcela.setText("R$ " + 0.00);
		text2parcela = new JTextField();
		text2parcela.setColumns(10);
		text2parcela.setText("R$ " + 0.00);
		text2parcela.setEnabled(true);
		text2parcela.setEditable(false);
		JButton btnNewButton = new JButton("CALCULAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario_sessao = System.getProperty("user.name");
				System.out.println("Olá " + usuario_sessao);

	
				if (txt_salario.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Atenção: Campo salário não pode ser vazio.", "Atenção",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				double meses = Double.parseDouble((String) comboBox.getSelectedItem());
				if (meses < 3) {
					lblMsgMeses.setVisible(true);
				} else {
					lblMsgMeses.setVisible(false);
				}
				double salario = Double.parseDouble(txt_salario.getText().replace(',', '.'));

				if (salario < 0) {
					JOptionPane.showMessageDialog(null, "Atenção: salário não pode ser Negativo.", "Atenção",
							JOptionPane.WARNING_MESSAGE);
					txt_salario.setText("0");
					return;
				}

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

				if (salario >= 5839) {
					inss_desc = 11;
				}

				double desconto = (salario * inss_desc) / (100);
				System.out.println("salario ponto: " + salario);
				System.out.println(meses);

				double decimo_total = (salario / 12) * meses;
				double vl_1_parcela = (decimo_total / 2);
				double vl_2_parcela = vl_1_parcela - desconto;
				double vl_total = vl_1_parcela + vl_2_parcela;

				DecimalFormat formatar = new DecimalFormat("#.##");

				String parcela1 = formatar.format(vl_1_parcela);
				String parcela2 = formatar.format(vl_2_parcela);
				String geral = formatar.format(vl_total);

				text1parcela.setText("R$ " + parcela1);

				text2parcela.setText("R$ " + parcela2);

				textDecimoTotal.setText("R$ " + geral);

				// DecimalFormat valor_1 = new DecimalFormat("#.##");
				// JOptionPane.showMessageDialog(null, "Você vai receber no total de "+"R$ " +
				// decimo_total+", 1 Parcela: R$ "+vl_1_parcela);
				System.out.println("Decimo total: " + decimo_total);

			}
		});

		JLabel lblHoras = new JLabel("Meses trabalhados:");

		JButton SAIR = new JButton("VOLTAR");
		// SAIR.setForeground(Color.GRAY);
		SAIR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// DENTRO DO BOTÃO, FIZ CHAMAR A CLASS MAIN DA PROXIMA TELA, E OCUTEI A HOME.
				Home.main(null);
				frmDecimoTerceiro.setVisible(false);
				// System.exit(0);
			}
		});
		// SAIR.setVerticalAlignment(SwingConstants.TOP);
		// SAIR.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		// SAIR.setBackground(SystemColor.info);

		textDecimoTotal = new JTextField();
		textDecimoTotal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
		textDecimoTotal.setText("R$ 0.0");
		textDecimoTotal.setEnabled(true);
		textDecimoTotal.setColumns(10);
		textDecimoTotal.setEditable(false);

		JLabel lblValorTotal = new JLabel("Valor total:");

		JLabel lblParcela = new JLabel("1\u00BA Parcela");

		JLabel lblParcela_1 = new JLabel("2\u00BA Parcela");

		JLabel lblCalculadorDe = new JLabel("Calculador de 13\u00BA");
		lblCalculadorDe.setFont(new Font("Papyrus", Font.BOLD, 15));

//		textField = new JTextField();
//		textField.setColumns(10);

		// JRadioButton rdbtnDescontoVem = new JRadioButton("Desconto VEM");

		GroupLayout groupLayout = new GroupLayout(frmDecimoTerceiro.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
						.createSequentialGroup().addContainerGap(107, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup().addComponent(lblSalrio).addGap(61))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblHoras, GroupLayout.PREFERRED_SIZE, 112,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox, 0, 107, Short.MAX_VALUE)
								.addComponent(txt_salario, Alignment.TRAILING, 107, 107, 107)))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap(133, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(lblParcela_1, GroupLayout.PREFERRED_SIZE, 65,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblParcela, GroupLayout.PREFERRED_SIZE, 65,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblValorTotal, GroupLayout.PREFERRED_SIZE, 65,
																GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(text1parcela, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(text2parcela, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(textDecimoTotal, GroupLayout.PREFERRED_SIZE, 113,
																GroupLayout.PREFERRED_SIZE))
												.addGap(15))
										.addComponent(SAIR, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))))
				.addContainerGap(104, GroupLayout.PREFERRED_SIZE))
				.addGroup(
						groupLayout.createSequentialGroup().addContainerGap(140, Short.MAX_VALUE)
								.addComponent(lblCalculadorDe, GroupLayout.PREFERRED_SIZE, 258,
										GroupLayout.PREFERRED_SIZE)
								.addGap(36))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(lblMsgMeses, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addComponent(lblCalculadorDe, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txt_salario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSalrio))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHoras))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblValorTotal).addComponent(
						textDecimoTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(text1parcela, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblParcela))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(text2parcela, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblParcela_1))
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNewButton)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(SAIR, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblMsgMeses).addGap(46)));
		frmDecimoTerceiro.getContentPane().setLayout(groupLayout);
	}

}
