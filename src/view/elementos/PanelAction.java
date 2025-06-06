package view.elementos;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;


public class PanelAction extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelAction() {
		initComponents();
	}
	
	public void initEvent(TableActionEvent event, int row) {
		cmdEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				event.onEdit(row);	
			}
		});
		cmdDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				event.onDelete(row);	
			}
		});
	}
	
	@SuppressWarnings("unused")
	private void initComponents() {
		cmdEdit = new view.elementos.ActionButton();
		cmdDelete = new view.elementos.ActionButton();		
		ImageIcon editIcon = new ImageIcon(PanelAction.class.getResource("/img/edit.png"));
		ImageIcon deleteIcon = new ImageIcon(PanelAction.class.getResource("/img/delete.png"));
		
		Image imagemRedimensionada = editIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
	    ImageIcon iconRedimensionado = new ImageIcon(imagemRedimensionada);
	    Image imagemRedimensionada2 = deleteIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
	    ImageIcon iconRedimensionado2 = new ImageIcon(imagemRedimensionada2);
	     
	    cmdEdit.setIcon(iconRedimensionado);
		cmdDelete.setIcon(iconRedimensionado2);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		add(cmdEdit);
		add(cmdDelete);
	}

	
	private ActionButton cmdDelete;
	private ActionButton cmdEdit;
}