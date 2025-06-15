package view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.edu.fateczl.gabriel.Lista;
import controller.InscricaoController;
import model.Inscricao;
import view.elementos.IPainelProcessosAbertos;

public class PainelProcessosAbertos extends JPanel implements IPainelProcessosAbertos {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;
	private InscricaoController inscricaoController;
	/**
	 * Create the panel.
	 */
	public PainelProcessosAbertos() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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
		tableModel = (DefaultTableModel) table.getModel();
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		scrollPane.setViewportView(table);
	}
	
	@Override
	public void atualizarTabela() throws Exception {
		
		
	}

}
