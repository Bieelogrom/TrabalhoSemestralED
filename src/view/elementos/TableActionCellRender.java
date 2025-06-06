package view.elementos;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableActionCellRender extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		PanelAction action = new PanelAction();
		if(isSelected == false && row % 2 == 0) {
			action.setBackground(Color.WHITE);
		}else {
			action.setBackground(com.getBackground());
		}
		action.setBackground(com.getBackground());
		return action;
	}
	
	
	
}