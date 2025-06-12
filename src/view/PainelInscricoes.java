package view;

import javax.swing.JPanel;

import java.text.ParseException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.edu.fateczl.fila.Fila;
import br.edu.fateczl.gabriel.Lista;
import controller.DisciplinaController;
import controller.InscricaoController;
import controller.ProfessorController;
import model.Disciplina;
import model.Inscricao;
import model.Professor;
import view.elementos.IPainelInscricoes;
import view.elementos.TableActionCellEditor;
import view.elementos.TableActionCellRender;
import view.elementos.TableActionEvent;
import java.awt.Component;
import javax.swing.Box;

public class PainelInscricoes extends JPanel implements IPainelInscricoes {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JComboBox<String> txtfCpfProfessor;
	private JComboBox<String> comboBoxCodigoDisciplina;
	private JFormattedTextField textField;
	private InscricaoController inscricaoController;
	private DefaultTableModel tableModel;
	private ProfessorController professorController;
	private DisciplinaController disciplinaController;

	/**
	 * Create the panel.
	 * @throws ParseException 
	 */
	public PainelInscricoes() throws ParseException {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel_3 = new JPanel();
		add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		panel_3.add(horizontalStrut_3);
		
		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel_2.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel lblCpfProfessor = new JLabel("CPF do professor");
		panel.add(lblCpfProfessor);
		
		txtfCpfProfessor = new JComboBox();
		panel.add(txtfCpfProfessor);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel_2.add(horizontalStrut_2);
		
		JPanel panel_1 = new JPanel();
		panel_2.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JLabel lblCdigoDaDisciplina = new JLabel("Código da disciplina");
		panel_1.add(lblCdigoDaDisciplina);
		
		/*
		 * Colocar máscaras em inputs no Java é muito mais fácil que um html com javascript.
		 */
		comboBoxCodigoDisciplina = new JComboBox();
		panel_1.add(comboBoxCodigoDisciplina);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel_3.add(horizontalStrut_1);
		
		JButton btnInscreverProfessor = new JButton("Inscrever professor");
		panel_3.add(btnInscreverProfessor);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Disciplina", "Professor", "Op\u00E7\u00F5es"
			}
		));
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.setRowHeight(40);
		tableModel = (DefaultTableModel) table.getModel();
		TableActionEvent event = inscricaoController;

		scrollPane.setViewportView(table);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_2.add(horizontalStrut);
	
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JLabel lblCdigo = new JLabel("Código do processo");
		panel_4.add(lblCdigo);
		
		textField = new JFormattedTextField(new MaskFormatter("COD###"));
		panel_4.add(textField);
		textField.setColumns(5);
		
		this.disciplinaController = new DisciplinaController();
		this.professorController = new ProfessorController();
		this.inscricaoController = new InscricaoController(txtfCpfProfessor,comboBoxCodigoDisciplina,textField, this);
		
	    table.getColumnModel().getColumn(3).setCellRenderer(new TableActionCellRender());
	    table.getColumnModel().getColumn(3).setCellEditor(new TableActionCellEditor(inscricaoController));
		
		btnInscreverProfessor.addActionListener(inscricaoController);
	}

	
	/*
	 * Métodos de callback
	 */
	@Override
	public void atualizarCombosBox() {
		try {
			comboBoxCodigoDisciplina.removeAllItems();
			txtfCpfProfessor.removeAllItems();
			Lista<Professor> listaDeProfessores = professorController.listarProfessor();
			Lista<Disciplina> listaDeDisciplinas = disciplinaController.listarDisciplinas();
			for(int i = 0; i < listaDeProfessores.size(); i++) {
				txtfCpfProfessor.addItem(listaDeProfessores.get(i).getCpf());
			}
			for(int i = 0; i < listaDeDisciplinas.size(); i++) {
				comboBoxCodigoDisciplina.addItem(listaDeDisciplinas.get(i).getCodigoDisciplina());
			}
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	@Override
	public void atualizarTabela() throws Exception {
		Fila<Inscricao> filaDeInscricoes = inscricaoController.enfileirarInscricoes();
		tableModel.setRowCount(0);
		while(!filaDeInscricoes.isEmpty()) {
			Inscricao inscricao = filaDeInscricoes.remove();
			tableModel.addRow(new Object[] {
					inscricao.getCodigoProcesso(),
					inscricao.getCodigoDisciplina(),
					inscricao.getCpfProfessor()
			});
		}
	}

}
