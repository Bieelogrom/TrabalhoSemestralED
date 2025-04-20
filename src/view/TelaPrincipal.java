package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtnomecurso;
	private JTextField txtcodigocurso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setResizable(false);
		setTitle("Tela Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(0, 0, 450, 310);
		contentPane.add(tabbedPane);
		
		JPanel Cursos = new JPanel();
		tabbedPane.addTab("Cursos", null, Cursos, null);
		Cursos.setLayout(null);
		
		JLabel lblcodigocurso = new JLabel("Código do curso");
		lblcodigocurso.setBounds(12, 12, 100, 17);
		Cursos.add(lblcodigocurso);
		
		JLabel lblnomecurso = new JLabel("Nome do curso");
		lblnomecurso.setBounds(12, 68, 94, 17);
		Cursos.add(lblnomecurso);
		
		JLabel lblareadoconhecimento = new JLabel("Área do conhecimento");
		lblareadoconhecimento.setBounds(12, 124, 133, 17);
		Cursos.add(lblareadoconhecimento);
		
		JComboBox comboxareaconhecimento = new JComboBox();
		comboxareaconhecimento.setBounds(12, 142, 324, 26);
		Cursos.add(comboxareaconhecimento);
		
		txtnomecurso = new JTextField();
		txtnomecurso.setBounds(12, 86, 324, 26);
		Cursos.add(txtnomecurso);
		txtnomecurso.setColumns(10);
		
		txtcodigocurso = new JTextField();
		txtcodigocurso.setBounds(12, 30, 324, 26);
		Cursos.add(txtcodigocurso);
		txtcodigocurso.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
	}
}
