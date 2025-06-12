package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.edu.fateczl.fila.Fila;
import controller.CursoController;
import model.Curso;
import view.elementos.TableActionEvent;
import view.elementos.IPainelCursos;
import view.elementos.TableActionCellEditor;
import view.elementos.TableActionCellRender;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.text.ParseException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class PainelCursos extends JPanel implements IPainelCursos {

	private static final long serialVersionUID = 1L;
	private JFormattedTextField txtfCodigoCurso;
	private JTextField txtfNomeCurso;
	private JComboBox<String> comboxareaconhecimento; 
	private CursoController cursoController;
	private CardLayout cardLayout;
	private JPanel painelTroca;

	/**
	 * Create the panel.
	 * @throws ParseException 
	 */
	public PainelCursos() throws ParseException {
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
	
	
	private JPanel formularioCadastroCursos() throws ParseException {
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
		
		txtfCodigoCurso = new JFormattedTextField(new MaskFormatter("UUU###"));
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
		
	    this.cursoController = new CursoController(btnSalvarCurso, txtfCodigoCurso, txtfNomeCurso, comboxareaconhecimento, this);
        btnSalvarCurso.addActionListener(cursoController);
		
	    btnVisualizarCursos.addActionListener(e -> {
	    	try {
				atualizarTabelaCursos();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            cardLayout.show(painelTroca, "tabela");
        });
		
		return panel;
	}
	
	private void atualizarTabelaCursos() throws Exception {
		//Por questões de praticidade deixei o método público no controller mesmo;
		Fila<Curso> filaDeCursos = cursoController.enfileirarCursos();
		tableModel.setRowCount(0);
		while(!filaDeCursos.isEmpty()) {
			Curso curso = filaDeCursos.remove();
			tableModel.addRow(new Object[] {
					curso.getCodigoCurso(),
					curso.getNomeCurso(),
					curso.getAreaConhecimento()
			});
		}
	}

	private JTable tabelaCursos;
	private JTable table;
    private DefaultTableModel tableModel;
	
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
				"ID", "Nome do Curso", "Área do curso", "Opçoes"
			}
		) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setRowHeight(40);
		tableModel = (DefaultTableModel) table.getModel();
        
        TableActionEvent event = cursoController;
        table.getColumnModel().getColumn(3).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(3).setCellEditor(new TableActionCellEditor(cursoController));

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> cardLayout.show(painelTroca, "formulario"));
        panel.add(btnVoltar, BorderLayout.SOUTH);

        return panel;
    }


    /*
     * Métodos de callback. Ainda aprendendo implementação.
     */
	@Override
	public void atualizarTabela() {
		try {
			atualizarTabelaCursos();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}


	@Override
	public void limparTextos() {
		// TODO Auto-generated method stub
		txtfCodigoCurso.setText("");;
		txtfNomeCurso.setText("");
	}

}
