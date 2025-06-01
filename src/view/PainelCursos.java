package view;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;

public class PainelCursos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtfCodigoCurso;
	private JTextField txtfNomeCurso;

	/**
	 * Create the panel.
	 */
	public PainelCursos() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		add(verticalStrut_1);
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(0, 20, 0, 20));
		
		JLabel lblCodigoCurso = new JLabel("Código do Curso");
		panel.add(lblCodigoCurso);
		
		txtfCodigoCurso = new JTextField();
		panel.add(txtfCodigoCurso);
		txtfCodigoCurso.setColumns(10);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		add(verticalStrut);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JLabel lblNomeCurso = new JLabel("Nome do curso");
		panel_1.add(lblNomeCurso);
		
		txtfNomeCurso = new JTextField();
		panel_1.add(txtfNomeCurso);
		txtfNomeCurso.setColumns(10);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		add(verticalStrut_2);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JLabel lblAreaConhecimento = new JLabel("Área do conhecimento");
		panel_2.add(lblAreaConhecimento);
		
		String[] opcoes = {"Ciências Agrárias","Ciências Biológicas","Ciências da Saúde","Ciências Exatas e da Terra","Ciências Sociais Aplicadas","Engenharias","Ciências Humanas","Linguística, Letras e Artes"}; 
		JComboBox<String> comboxareaconhecimento = new JComboBox<>(opcoes);
		panel_2.add(comboxareaconhecimento);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		add(verticalStrut_3);
		
		JPanel panel_3 = new JPanel();
		add(panel_3);
		
		JButton btnSalvarCurso = new JButton("Salvar curso");
		panel_3.add(btnSalvarCurso);
		
		JButton btnVisualizarCursos = new JButton("Visualizar cursos");
		panel_3.add(btnVisualizarCursos);

		
		
	}

}
