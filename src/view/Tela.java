package view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

public class Tela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public Tela() throws Exception {
		setTitle("SIGA");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 12, 628, 351);
		contentPane.add(tabbedPane);
		
		PainelDisciplinas painelDisciplinas = new PainelDisciplinas();
		PainelCursos painelCursos = new PainelCursos();
		PainelInscricoes painelInscricoes = new PainelInscricoes();
		PainelConsulta painelConsulta = new PainelConsulta();

		tabbedPane.addTab("Cursos", null, painelCursos, null);
		tabbedPane.addTab("Disciplinas", null, painelDisciplinas, null);
		tabbedPane.addTab("Professores", null, new PainelProfessores(), null);
		tabbedPane.addTab("Inscrições", null, painelInscricoes, null);
		tabbedPane.addTab("Consulta", null, painelConsulta, null);
		tabbedPane.addTab("Processos abertos",null, new PainelProcessosAbertos(),null);
		
		
		/*
		 * Tentando ao máximo não juntar lógica de view com a de controller
		 */
		tabbedPane.addChangeListener(e -> {
			int selectedIndex = tabbedPane.getSelectedIndex();
			String tabTitle = tabbedPane.getTitleAt(selectedIndex);
			if ("Disciplinas".equals(tabTitle)) {
				//Quando o usuário abrir a aba de disciplinas vai rodar o método abaixo
				painelDisciplinas.atualizarComboBoxCursos(); 
			}
			if("Cursos".equals(tabTitle)) {
				try {
					//Quando o usuário apagar o curso então atualizada os itens de disciplina
					painelDisciplinas.atualizarTabela();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			if("Inscrições".equals(tabTitle)) {
				try {
					painelInscricoes.atualizarTabela();
					painelInscricoes.atualizarCombosBox();
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			if("Consulta".equals(tabTitle)) {
				try {
					painelConsulta.carregarComboBox();
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
	}

}
