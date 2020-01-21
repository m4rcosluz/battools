package br.visual;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Aplicativos {

	private JFrame Aplicativos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aplicativos window = new Aplicativos();
					window.Aplicativos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Aplicativos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Aplicativos = new JFrame();
		Aplicativos.getContentPane().setLayout(null);
		Aplicativos.setAutoRequestFocus(false);
		String cor = br.modelo.config.define_cor(null);
		
		if(cor.equals("Azul")){
			Aplicativos.getContentPane().setBackground(Color.BLUE);
		}
		
		if(cor.equals("Vermelho")){
			Aplicativos.getContentPane().setBackground(Color.RED);
		}
		
		if(cor.equals("Branco")){
			Aplicativos.getContentPane().setBackground(Color.WHITE);
		}
		
		if(cor.equals("Rosa")){
			Aplicativos.getContentPane().setBackground(Color.PINK);
		}
		
		if(cor.equals("Preto")){
			Aplicativos.getContentPane().setBackground(Color.BLACK);
		}
		Aplicativos.setTitle("Aplicativos - 1.0 | Marcos Luz - Bat tools");

		Aplicativos.setBounds(500, 100, 450, 300);
		Aplicativos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnVnc = new JButton("VNC");
		btnVnc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Runtime.getRuntime().exec("C:\\Program Files\\UltraVNC\\vncviewer.exe");
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Atenção: Programa não encontrado.",  "Atenção", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		btnVnc.setBounds(36, 97, 108, 23);
		Aplicativos.getContentPane().add(btnVnc);
		
		JButton btnTs = new JButton("TS");
		btnTs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("C:\\Windows\\system32\\mstsc.exe");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Atenção: Programa não encontrado.",  "Atenção", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		btnTs.setBounds(36, 131, 108, 23);
		Aplicativos.getContentPane().add(btnTs);
		
		JLabel lblConexes = new JLabel("Conex\u00F5es");
		lblConexes.setBounds(60, 72, 84, 14);
		Aplicativos.getContentPane().add(lblConexes);
		
		JButton btnAnydesk = new JButton("AnyDesk");
		btnAnydesk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Runtime.getRuntime().exec("\\\\Mvrec_suporte\\BAT_MARCOS\\Data\\arquivos_BAT\\AnyDesk.exe");
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Atenção: Programa não encontrado.",  "Atenção", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		btnAnydesk.setBounds(36, 165, 108, 23);
		Aplicativos.getContentPane().add(btnAnydesk);
		
		JLabel lblPortaisInternos = new JLabel("Portais Internos");
		lblPortaisInternos.setBounds(161, 72, 113, 14);
		Aplicativos.getContentPane().add(lblPortaisInternos);
		
		JButton btnJira = new JButton("Jira");
		btnJira.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("\\\\Mvrec_suporte\\BAT_MARCOS\\Data\\arquivos_BAT\\Jira.bat");
				} catch (IOException jira) {
					JOptionPane.showMessageDialog(null, "Atenção: Programa não encontrado.",  "Atenção", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		btnJira.setBounds(161, 97, 113, 23);
		Aplicativos.getContentPane().add(btnJira);
		
		JButton btnMyplace = new JButton("MyPlace");
		btnMyplace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("\\\\mvrec_suporte\\BAT_MARCOS\\Data\\arquivos_BAT\\MyPlace.bat");
				} catch (IOException place) {
					JOptionPane.showMessageDialog(null, "Atenção: Programa não encontrado.",  "Atenção", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		btnMyplace.setBounds(161, 131, 113, 23);
		Aplicativos.getContentPane().add(btnMyplace);
		
		JButton btnOcomon = new JButton("Ocomon");
		btnOcomon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("\\\\mvrec_suporte\\BAT_MARCOS\\Data\\arquivos_BAT\\Ocomon.bat");
				} catch (IOException ocomon) {
					JOptionPane.showMessageDialog(null, "Atenção: Programa não encontrado.",  "Atenção", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		btnOcomon.setBounds(161, 165, 113, 23);
		Aplicativos.getContentPane().add(btnOcomon);
		
		JButton btnAutoscreenrecord = new JButton("AutoScreen");
		btnAutoscreenrecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("\\\\mvrec_suporte\\BAT_MARCOS\\Data\\arquivos_BAT\\AutoScreen\\AutoScreenRecorder.exe");
				} catch (IOException autoscrem) {
					JOptionPane.showMessageDialog(null, "Atenção: Programa não encontrado.",  "Atenção", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
			}
		});
		btnAutoscreenrecord.setBounds(288, 97, 113, 23);
		Aplicativos.getContentPane().add(btnAutoscreenrecord);
		
		JButton btnLightshot = new JButton("LightShot");
		btnLightshot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("\\\\mvrec_suporte\\BAT_MARCOS\\Data\\arquivos_BAT\\Lightshot\\Lightshot.exe");
				} catch (IOException print) {
					JOptionPane.showMessageDialog(null, "Atenção: Programa não encontrado.",  "Atenção", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		btnLightshot.setBounds(288, 131, 113, 23);
		Aplicativos.getContentPane().add(btnLightshot);
		
		JLabel lblFerramentasDe = new JLabel("Print e V\u00EDdeo");
		lblFerramentasDe.setBounds(304, 72, 84, 14);
		Aplicativos.getContentPane().add(lblFerramentasDe);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home.main(null);
				Aplicativos.setVisible(false);
				
			}
		});
		btnVoltar.setBounds(173, 219, 90, 31);
		Aplicativos.getContentPane().add(btnVoltar);
	}
}
