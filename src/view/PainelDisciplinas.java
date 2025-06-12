package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.text.ParseException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.edu.fateczl.fila.Fila;
import br.edu.fateczl.gabriel.Lista;
import controller.CursoController;
import controller.DisciplinaController;
import model.Curso;
import model.Disciplina;
import view.elementos.IPainelDisciplinas;
import view.elementos.TableActionCellEditor;
import view.elementos.TableActionCellRender;
import view.elementos.TableActionEvent;

import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class PainelDisciplinas extends JPanel implements IPainelDisciplinas {

	private static final long serialVersionUID = 1L;
	private JFormattedTextField textField;
	private JTextField textField_1;
	private JTextField txtfQuantidadeHoras;
	private JComboBox<String> comboboxCursos;
	private DisciplinaController disciplinaController;
	private CardLayout cardLayout;
	private JPanel painelTroca;
	private JFormattedTextField formattedTextField;
	/**
	 * Create the panel.
	 * @throws ParseException 
	 */
	
	public PainelDisciplinas() throws ParseException {
	      setLayout(new BorderLayout());
	       setBorder(new EmptyBorder(10, 10, 10, 10));

	       cardLayout = new CardLayout();
	       painelTroca = new JPanel(cardLayout);
	       add(painelTroca, BorderLayout.CENTER);

	       JPanel painelFormulario = formularioCadastroDisciplinas();
	       JPanel painelTabela = tabelaVisualizacao();

	       painelTroca.add(painelFormulario, "formulario");
	       painelTroca.add(painelTabela, "tabela");

	       cardLayout.show(painelTroca, "formulario");
	}
	
	
	public JPanel formularioCadastroDisciplinas() throws ParseException {
		JPanel panelP = new JPanel();
		panelP.setLayout(new BoxLayout(panelP, BoxLayout.Y_AXIS));
		Component verticalStrut = Box.createVerticalStrut(20);
		panelP.add(verticalStrut);
		
		JPanel panel_5 = new JPanel();
		panelP.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(0, 20, 0, 20));
		
		JPanel panel_3 = new JPanel();
		panel_5.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		JPanel panel_9 = new JPanel();
		panel_3.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));
		
		JLabel lblCodigoDisciplina = new JLabel("Código da disciplina");
		panel_9.add(lblCodigoDisciplina);
		
		textField = new JFormattedTextField(new MaskFormatter("UUU###"));
		panel_9.add(textField);
		textField.setColumns(10);
		
		JPanel panel_10 = new JPanel();
		panel_3.add(panel_10);
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.Y_AXIS));
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_5.add(verticalStrut_1);
		
		JPanel panel_4 = new JPanel();
		panel_5.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JLabel lblNomeDisciplina = new JLabel("Nome da disciplina");
		panel_4.add(lblNomeDisciplina);
		
		textField_1 = new JTextField();
		panel_4.add(textField_1);
		textField_1.setColumns(10);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		panel_5.add(verticalStrut_2);
		
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
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JLabel lblQuantidadeHoras = new JLabel("Quantidade de horas");
		panel_2.add(lblQuantidadeHoras);
		
		txtfQuantidadeHoras = new JTextField();
		panel_2.add(txtfQuantidadeHoras);
		txtfQuantidadeHoras.setColumns(10);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		panel_5.add(verticalStrut_3);
		
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));
		
		JLabel lblCurso = new JLabel("Curso");
		panel_6.add(lblCurso);
		
		comboboxCursos = new JComboBox<>();
		panel_6.add(comboboxCursos);
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		panel_6.add(verticalStrut_4);
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7);
		
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut);
		
		JPanel panel_8 = new JPanel();
		panel.add(panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));
		
		JLabel lblHorrioDaDisciplina = new JLabel("Horário da disciplina");
		panel_8.add(lblHorrioDaDisciplina);
		
		formattedTextField = new JFormattedTextField(new MaskFormatter("##:##/##:##"));
		panel_8.add(formattedTextField);
		
		this.disciplinaController = new DisciplinaController(this, textField, textField_1, comboboxdiasemana, txtfQuantidadeHoras, comboboxCursos, formattedTextField);
		
		JButton btnCadastrarNovaDisciplina = new JButton("Cadastrar nova disciplina");
		panel_7.add(btnCadastrarNovaDisciplina);
		
		JButton btnVisualizarDisciplinas = new JButton("Visualizar disciplinas");
		panel_7.add(btnVisualizarDisciplinas);
		
		btnVisualizarDisciplinas.addActionListener(e -> {
			try {
				atualizarTabela();
			}catch(Exception e1) {
				System.err.println(e1.getMessage());
			}
			cardLayout.show(painelTroca, "tabela");
		});
		
		btnCadastrarNovaDisciplina.addActionListener(disciplinaController);
		
		return panelP;
	}
	
	private JTable tabelaCursos;
	private JTable table;
    private DefaultTableModel tableModel;
    private JTextField textField_2;

	private JPanel tabelaVisualizacao() {
		JPanel panel = new JPanel(new BorderLayout());
	        
	    JScrollPane scrollPane = new JScrollPane(tabelaCursos);
	    scrollPane.setBounds(12, 51, 496, 288);
	    panel.add(scrollPane, BorderLayout.CENTER);
	        
	    table = new JTable();
		scrollPane.setViewportView(table);
			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Nome da disciplina","Dia de semana","Horário","Carga horária","Curso da disciplina", "Opçoes"
				}
			) {
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, true
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			table.setRowHeight(40);
			tableModel = (DefaultTableModel) table.getModel();
	        
	        TableActionEvent event = disciplinaController;
	        table.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
	        table.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor(disciplinaController));

	        JButton btnVoltar = new JButton("Voltar");
	        btnVoltar.addActionListener(e -> cardLayout.show(painelTroca, "formulario"));
	        panel.add(btnVoltar, BorderLayout.SOUTH);

	        return panel;
	}


	/*
	 * Métodos de callback
	 */
	@Override
	public void atualizarTabela() throws Exception {
		Fila<Disciplina> filaDeDisciplinas = disciplinaController.enfileirarDisciplinas();
		tableModel.setRowCount(0);
		while(!filaDeDisciplinas.isEmpty()) {
			Disciplina disciplina = filaDeDisciplinas.remove();
			tableModel.addRow(new Object[] {
				disciplina.getCodigoDisciplina(),
				disciplina.getNomeDisciplina(),
				disciplina.getDiaDaSemanaDisciplina(),
				disciplina.getHorarioDisciplina(),
				disciplina.getQuantidadeHorasDisciplina(),
				disciplina.getCurso(),
			});
		}
	}


	@Override
	public void limparTextos() {
		textField.setText("");
		textField_1.setText("");
		txtfQuantidadeHoras.setText("");
		formattedTextField.setText("");
	}

	@Override
	public void atualizarComboBoxCursos() {
		try {
			comboboxCursos.removeAllItems(); 
			Lista<Curso> listaDeCursos = disciplinaController.listarCursos();
			for(int i = 0; i < listaDeCursos.size(); i++) {
				comboboxCursos.addItem(listaDeCursos.get(i).getNomeCurso());
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
