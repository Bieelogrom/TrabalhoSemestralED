package view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.edu.fateczl.fila.Fila;
import br.edu.fateczl.gabriel.Lista;
import controller.DisciplinaController;
import controller.InscricaoController;
import model.Dicionario;
import model.Disciplina;
import model.Inscricao;
import view.elementos.IPainelProcessosAbertos;

public class PainelProcessosAbertos extends JPanel implements IPainelProcessosAbertos {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;
	private InscricaoController inscricaoController;
	private DisciplinaController disciplinaController;
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
				"Curso", "C\u00F3digo da disciplina", "Disciplina", "Dia da semana", "Carga hor\u00E1ria", "Horas aula", "Indice hash"
			}
		));
		tableModel = (DefaultTableModel) table.getModel();
		scrollPane.setViewportView(table);
	}
	
	@Override
	public void atualizarTabela() throws Exception {
		tableModel.setRowCount(0);

	    Fila<Inscricao> filaInscricoes = new InscricaoController().enfileirarInscricoes();
	    Lista<String> codigosDisciplinasAtivas = new Lista<>();

	    while (!filaInscricoes.isEmpty()) {
	        Inscricao ins = filaInscricoes.remove();
	        String cod = ins.getCodigoDisciplina();

	        boolean jaTem = false;
	        for (int i = 0; i < codigosDisciplinasAtivas.size(); i++) {
	            if (codigosDisciplinasAtivas.get(i).equals(cod)) {
	                jaTem = true;
	                break;
	            }
	        }
	        if (!jaTem) codigosDisciplinasAtivas.addLast(cod);
	    }

	    Fila<Disciplina> filaDisciplinas = new DisciplinaController().enfileirarDisciplinas();
	    Dicionario dicionario = new Dicionario();

	    while (!filaDisciplinas.isEmpty()) {
	        Disciplina d = filaDisciplinas.remove();

	        for (int i = 0; i < codigosDisciplinasAtivas.size(); i++) {
	            if (d.getCodigoDisciplina().equals(codigosDisciplinasAtivas.get(i))) {
	                dicionario.adicionarDisciplina(d);
	                break;
	            }
	        }
	    }

	    // Etapa 3: varrer a tabelaHashDicionario
	    Lista[] tabela = dicionario.getTabelaHashDicionario();
	    for (int i = 0; i < tabela.length; i++) {
	        Lista<Disciplina> lista = tabela[i];
	        for (int j = 0; j < lista.size(); j++) {
	            Disciplina d = lista.get(j);
	            tableModel.addRow(new Object[] {
	                d.getCurso(),
	                d.getCodigoDisciplina(),
	                d.getNomeDisciplina(),
	                d.getDiaDaSemanaDisciplina(),
	                d.getHorarioDisciplina(),
	                d.getQuantidadeHorasDisciplina(),
	                i 
	            });
	        }
	    }
	}

}
