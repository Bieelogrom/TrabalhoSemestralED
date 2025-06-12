package view;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import br.edu.fateczl.fila.Fila;
import controller.InscricaoController;
import model.Inscricao;
import view.elementos.IPainelInscricoes;

public class PainelInscricoes extends JPanel implements IPainelInscricoes {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtfCpfProfessor;
	private JTextField comboBoxCodigoDisciplina;
	private InscricaoController inscricaoController;

	/**
	 * Create the panel.
	 */
	public PainelInscricoes() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel_3 = new JPanel();
		add(panel_3);
		
		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2);
		
		JPanel panel = new JPanel();
		panel_2.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel lblCpfProfessor = new JLabel("CPF do professor");
		panel.add(lblCpfProfessor);
		
		txtfCpfProfessor = new JTextField();
		panel.add(txtfCpfProfessor);
		
		JPanel panel_1 = new JPanel();
		panel_2.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JLabel lblCdigoDaDisciplina = new JLabel("Código da disciplina");
		panel_1.add(lblCdigoDaDisciplina);
		
		comboBoxCodigoDisciplina = new JTextField();
		panel_1.add(comboBoxCodigoDisciplina);
		
		JButton btnInscreverProfessor = new JButton("Inscrever professor");
		panel_3.add(btnInscreverProfessor);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CPF do Professor", "Pontua\u00E7\u00E3o", "Data de inscri\u00E7\u00E3o", "C\u00F3digo da disciplina"
			}
		) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				true, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		scrollPane.setViewportView(table);
		
		this.inscricaoController = new InscricaoController(txtfCpfProfessor,comboBoxCodigoDisciplina);
		
		btnInscreverProfessor.addActionListener(inscricaoController);
	}

	
	/*
	 * Métodos de callback
	 */
	@Override
	public void atualizarCombosBox() {
		//Fila<Inscricao> filaDeInscricoes = 
		
	}

	@Override
	public void atualizarTabela() {
		// TODO Auto-generated method stub
		
	}

}
