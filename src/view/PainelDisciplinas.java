package view;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JButton;

public class PainelDisciplinas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtfQuantidadeHoras;

	/**
	 * Create the panel.
	 */
	public PainelDisciplinas() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Component verticalStrut = Box.createVerticalStrut(20);
		add(verticalStrut);
		
		JPanel panel_5 = new JPanel();
		add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(0, 20, 0, 20));
		
		JPanel panel_3 = new JPanel();
		panel_5.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JLabel lblCodigoDisciplina = new JLabel("Código da disciplina");
		panel_3.add(lblCodigoDisciplina);
		
		textField = new JTextField();
		panel_3.add(textField);
		textField.setColumns(10);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_3.add(verticalStrut_1);
		
		JPanel panel_4 = new JPanel();
		panel_5.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JLabel lblNomeDisciplina = new JLabel("Nome da disciplina");
		panel_4.add(lblNomeDisciplina);
		
		textField_1 = new JTextField();
		panel_4.add(textField_1);
		textField_1.setColumns(10);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		panel_4.add(verticalStrut_2);
		
		JPanel panel = new JPanel();
		panel_5.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JLabel lblDiaDaSemana = new JLabel("Dia da semana");
		panel_1.add(lblDiaDaSemana);
		
		
		String[] opcoes = {"Segunda-feira","Terça-feira","Quarta-feira","Quinta-feira","Sexta-feira","Sábado"}; 
		JComboBox<String> comboboxdiasemana = new JComboBox<>(opcoes);
		panel_1.add(comboboxdiasemana);
		
		Component horizontalStrut = Box.createHorizontalStrut(120);
		panel.add(horizontalStrut);
		
		JPanel panel_2 = new JPanel();
		panel_2.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JLabel lblQuantidadeHoras = new JLabel("Quantidade de horas");
		panel_2.add(lblQuantidadeHoras);
		
		txtfQuantidadeHoras = new JTextField();
		panel_2.add(txtfQuantidadeHoras);
		txtfQuantidadeHoras.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		panel_6.add(verticalStrut_3);
		
		JLabel lblCurso = new JLabel("Curso");
		panel_6.add(lblCurso);
		
		JComboBox comboBox_1 = new JComboBox();
		panel_6.add(comboBox_1);
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		panel_6.add(verticalStrut_4);
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7);
		
		JButton btnCadastrarNovaDisciplina = new JButton("Cadastrar nova disciplina");
		panel_7.add(btnCadastrarNovaDisciplina);
		
		JButton btnVisualizarDisciplinas = new JButton("Visualizar disciplinas");
		panel_7.add(btnVisualizarDisciplinas);
		
	}

}
