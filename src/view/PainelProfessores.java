package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import java.text.ParseException;

import javax.swing.Box;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.edu.fateczl.fila.Fila;
import controller.ProfessorController;
import model.Curso;
import model.Professor;
import view.elementos.IPainelProfessores;
import view.elementos.TableActionCellEditor;
import view.elementos.TableActionCellRender;
import view.elementos.TableActionEvent;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

public class PainelProfessores extends JPanel implements IPainelProfessores {

	private static final long serialVersionUID = 1L;
	private JFormattedTextField txtfCpfProfessor;
	private JTextField txtfNomeProfessor;
	private JComboBox txtfAreaInteresse;
	private JTextField txtfPontuação;
	private CardLayout cardLayout;
	private JPanel painelTroca;
	private ProfessorController professorController;

	public PainelProfessores() throws Exception {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));

        cardLayout = new CardLayout();
        painelTroca = new JPanel(cardLayout);
        add(painelTroca, BorderLayout.CENTER);

        JPanel painelFormulario = formularioCadastroProfessor();
        JPanel painelTabela = tabelaVisualizacao();

        painelTroca.add(painelFormulario, "formulario");
        painelTroca.add(painelTabela, "tabela");

        cardLayout.show(painelTroca, "formulario");
	}
	
	/**
	 * Create the panel.
	 * @throws Exception 
	 */
	public JPanel formularioCadastroProfessor() throws Exception {
		JPanel panelP = new JPanel();
		panelP.setLayout(new BoxLayout(panelP, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));
		panelP.add(panel);
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
		
		professorController = new ProfessorController(txtfCpfProfessor,txtfNomeProfessor,comboxareaconhecimento,txtfPontuação, this);
		btnCadastrarProfessor.addActionListener(professorController);
		
		btnVisualizarProfessores.addActionListener(e -> {
			try {
				atualizarTabelaProfessores();
			}catch(Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			cardLayout.show(painelTroca, "tabela");
		});
		
		return panelP;
	}
	
	private void atualizarTabelaProfessores() throws Exception {
		Fila<Professor> filaDeProfessores =	professorController.enfileirarProfessores();
		tableModel.setRowCount(0);
		while(!filaDeProfessores.isEmpty()) {
			Professor novoProfessor = filaDeProfessores.remove();
			tableModel.addRow(new Object[] {
					novoProfessor.getCpf(),
					novoProfessor.getNome(),
					novoProfessor.getArea(),
					novoProfessor.getPontuacao()
			});
		}
	}
	
	private JTable tabelaCursos;
	private JTable table;
    private DefaultTableModel tableModel; 
	
	public JPanel tabelaVisualizacao() {
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
					"CPF", "Nome do professor", "Área do curso", "Pontuação", "Opçoes"
				}
			) {
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, true
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			table.setRowHeight(40);
			tableModel = (DefaultTableModel) table.getModel();
	        
	        TableActionEvent event = professorController;
	        table.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
	        table.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(professorController));

	        JButton btnVoltar = new JButton("Voltar");
	        btnVoltar.addActionListener(e -> cardLayout.show(painelTroca, "formulario"));
	        panel.add(btnVoltar, BorderLayout.SOUTH);

	        return panel;
	}

	@Override
	public void atualizarTabela() throws Exception {
		atualizarTabelaProfessores();
	}

	@Override
	public void limparTextos() {
		// TODO Auto-generated method stub
	
	}
}
