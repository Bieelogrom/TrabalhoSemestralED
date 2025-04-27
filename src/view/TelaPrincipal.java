package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.CursoController;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtnomecurso;
	private JTextField txtcodigocurso;
	private JTable table;

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
		setTitle("Sistema de Gestão Acadêmica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		contentPane.add(tabbedPane, "CursosTela");
		
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
		
		//Parte modificada para reduzir código.
		String[] opcoes = {"Ciências Agrárias","Ciências Biológicas","Ciências da Saúde","Ciências Exatas e da Terra","Ciências Sociais Aplicadas","Engenharias","Ciências Humanas","Lingüística, Letras e Artes"}; 
		JComboBox<String> comboxareaconhecimento = new JComboBox<>(opcoes);
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
		
		JButton btnAdicionarCurso = new JButton("Adicionar curso");
		btnAdicionarCurso.setBounds(12, 180, 155, 27);
		Cursos.add(btnAdicionarCurso);
		
		//Modificado para melhor compreensão.
		JPanel VisualizacaoCursos = new JPanel();
		contentPane.add(VisualizacaoCursos, "VisualizaçãoCursos");
		VisualizacaoCursos.setLayout(null);
		
		JButton btnVisualizarCurso = new JButton("Visualizar cursos");
		btnVisualizarCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (contentPane.getLayout());
				cl.show(contentPane, "VisualizaçãoCursos");
			}
		});
		btnVisualizarCurso.setBounds(179, 180, 157, 27);
		Cursos.add(btnVisualizarCurso);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 51, 416, 249);
		VisualizacaoCursos.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome do Curso", "\u00C1rea do Conhecimento"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(87);
		table.getColumnModel().getColumn(1).setPreferredWidth(290);
		table.getColumnModel().getColumn(2).setPreferredWidth(228);
		
		JButton btnVoltarCurso = new JButton("Voltar");
		btnVoltarCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (contentPane.getLayout());
				cl.show(contentPane, "CursosTela");
			}
		});
		btnVoltarCurso.setBounds(12, 12, 105, 27);
		VisualizacaoCursos.add(btnVoltarCurso);
		
		CursoController CC = new CursoController(txtcodigocurso, txtcodigocurso, comboxareaconhecimento);
		
		
		btnAdicionarCurso.addActionListener(CC);
	}
}
