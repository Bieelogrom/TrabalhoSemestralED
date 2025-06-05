package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.CursoController;

import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class PainelCursos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtfCodigoCurso;
	private JTextField txtfNomeCurso;
	private JComboBox<String> comboxareaconhecimento; 
	private CursoController cursoController;
	private CardLayout cardLayout;
	private JPanel painelTroca;

	/**
	 * Create the panel.
	 */
	public PainelCursos() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));

        cardLayout = new CardLayout();
        painelTroca = new JPanel(cardLayout);
        add(painelTroca, BorderLayout.CENTER);

        JPanel painelFormulario = formularioCadastroCursos();
        JPanel painelTabela = tabelaVisualizacao();

        painelTroca.add(painelFormulario, "formulario");
        painelTroca.add(painelTabela, "tabela");

        cardLayout.show(painelTroca, "formulario");
	}
	
	
	private JPanel formularioCadastroCursos() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel.add(verticalStrut_1);
		
		JPanel panel_t = new JPanel();
		panel.add(panel_t);
		panel_t.setLayout(new BoxLayout(panel_t, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(0,20,0,20));
		
		JLabel lblCodigoCurso = new JLabel("Código do Curso");
		panel_t.add(lblCodigoCurso);
		
		txtfCodigoCurso = new JTextField();
		txtfCodigoCurso.setToolTipText("Digite algo tipo: MAT132 ou AOC110");
		panel_t.add(txtfCodigoCurso);
		txtfCodigoCurso.setColumns(10);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel.add(verticalStrut);
		
		JPanel panel_y = new JPanel();
		panel.add(panel_y);
		panel_y.setLayout(new BoxLayout(panel_y, BoxLayout.Y_AXIS));
		
		JLabel lblNomeCurso = new JLabel("Nome do curso");
		panel_y.add(lblNomeCurso);
		
		txtfNomeCurso = new JTextField();
		panel_y.add(txtfNomeCurso);
		txtfNomeCurso.setColumns(10);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		panel.add(verticalStrut_2);
		
		JPanel panel_u = new JPanel();
		panel.add(panel_u);
		panel_u.setLayout(new BoxLayout(panel_u, BoxLayout.Y_AXIS));
		
		JLabel lblAreaConhecimento = new JLabel("Área do conhecimento");
		panel_u.add(lblAreaConhecimento);
		
		String[] opcoes = {"Ciências Agrárias","Ciências Biológicas","Ciências da Saúde","Ciências Exatas e da Terra","Ciências Sociais Aplicadas","Engenharias","Ciências Humanas","Linguística, Letras e Artes"}; 
		JComboBox<String> comboxareaconhecimento = new JComboBox<>(opcoes);
		panel_u.add(comboxareaconhecimento);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		panel.add(verticalStrut_3);
		
		JPanel panel_i = new JPanel();
		panel.add(panel_i);
		
		JButton btnSalvarCurso = new JButton("Cadastrar curso");
		panel_i.add(btnSalvarCurso);
		
		JButton btnVisualizarCursos = new JButton("Visualizar cursos");
		panel_i.add(btnVisualizarCursos);
		
	    btnVisualizarCursos.addActionListener(e -> {
            cardLayout.show(painelTroca, "tabela");
        });
		
		return panel;
	}
	
	private JTable tabelaCursos;
    private DefaultTableModel tableModel;
	
    private JPanel tabelaVisualizacao() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] colunas = {"Código", "Nome", "Área"};
        tableModel = new DefaultTableModel(colunas, 0);
        tabelaCursos = new JTable(tableModel);
        tabelaCursos.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(tabelaCursos);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> cardLayout.show(painelTroca, "formulario"));
        panel.add(btnVoltar, BorderLayout.SOUTH);

        return panel;
    }

}
