package food;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyDefaultTableCellRenderer2 extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		JButton comp = (JButton) value;
		if (column == 5) {
			comp = new JButton("삭제");

		} else if (column == 7) {
			comp = new JButton("삭제");

		}
		comp.setBackground(new Color(189, 236, 182));
		return comp;

	}
}
