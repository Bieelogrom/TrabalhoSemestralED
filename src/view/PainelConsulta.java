package view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.edu.fateczl.gabriel.Lista;
import controller.ConsultaController;
import controller.DisciplinaController;
import model.Disciplina;
import view.elementos.IPainelConsulta;

public class PainelConsulta extends JPanel implements IPainelConsulta {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JComboBox<String> comboBoxDisciplina;
	private DisciplinaController disciplinaController;
	private ConsultaController consultarController;
	private DefaultTableModel tableModel;
	/**
	 * Create the panel.
	 */
	public PainelConsulta() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel_3 = new JPanel();
		add(panel_3);
		
		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2);
		
		JPanel panel_1 = new JPanel();
		panel_2.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JLabel lblCdigoDaDisciplina = new JLabel("Código da disciplina");
		panel_1.add(lblCdigoDaDisciplina);
		
		comboBoxDisciplina = new JComboBox<>();
		panel_1.add(comboBoxDisciplina);
		
		JButton btnConsultar = new JButton("Consultar");
		panel_3.add(btnConsultar);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Professor", "CPF", "\u00C1rea", "Pontua\u00E7\u00E3o", "C\u00F3digo do processo", "Op\u00E7\u00F5es"
			}
		));
		table.setRowHeight(40);
		tableModel = (DefaultTableModel) table.getModel(); 
		scrollPane.setViewportView(table);
		
		this.disciplinaController = new DisciplinaController();
		this.consultarController = new ConsultaController(tableModel);
		
		btnConsultar.addActionListener(consultarController);
	}
	
	@Override
	public void carregarComboBox() {
		try {
			comboBoxDisciplina.removeAllItems();
			Lista<Disciplina> listaDeDisciplinas = disciplinaController.listarDisciplinas();
			for(int i = 0; i < listaDeDisciplinas.size(); i++) {
				comboBoxDisciplina.addItem(listaDeDisciplinas.get(i).getCodigoDisciplina());
			}
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	@Override
	public void atualizarTabela() {
		// TODO Auto-generated method stub
		
	}

}
