package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import java.text.ParseException;

import javax.swing.Box;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

public class PainelProfessores extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFormattedTextField txtfCpfProfessor;
	private JTextField txtfNomeProfessor;
	private JComboBox txtfAreaInteresse;
	private JTextField txtfPontuação;

	/**
	 * Create the panel.
	 * @throws Exception 
	 */
	public PainelProfessores() throws Exception {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("CPF do professor");
		panel_2.add(lblNewLabel);
		
		txtfCpfProfessor = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		panel_2.add(txtfCpfProfessor);
		txtfCpfProfessor.setColumns(10);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		panel_2.add(verticalStrut_2);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_1 = new JLabel("Nome do professor");
		panel_3.add(lblNewLabel_1);
		
		txtfNomeProfessor = new JTextField();
		panel_3.add(txtfNomeProfessor);
		txtfNomeProfessor.setColumns(10);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_3.add(verticalStrut_1);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_2 = new JLabel("Área de interesse");
		panel_4.add(lblNewLabel_2);
		
		String[] opcoes = {"Ciências Agrárias","Ciências Biológicas","Ciências da Saúde","Ciências Exatas e da Terra","Ciências Sociais Aplicadas","Engenharias","Ciências Humanas","Linguística, Letras e Artes"}; 
		JComboBox<String> comboxareaconhecimento = new JComboBox<>(opcoes);
		panel_4.add(comboxareaconhecimento);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel_4.add(verticalStrut);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		JLabel lblPontuao = new JLabel("Pontuação");
		panel_5.add(lblPontuao);
		
		txtfPontuação = new JTextField();
		panel_5.add(txtfPontuação);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		panel_5.add(verticalStrut_3);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JButton btnCadastrarProfessor = new JButton("Cadastrar professor");
		panel_1.add(btnCadastrarProfessor);
		
		JButton btnVisualizarProfessores = new JButton("Visualizar professores");
		panel_1.add(btnVisualizarProfessores);
		
	}
}
