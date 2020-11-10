package food;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class StoreLikeUI {

	MainUI ui;
	LoginPageUI lp;
	Frame f;
	Object[] columns = { "��ȣ", "��������", "��������", "�ּ�", "��ȭ��ȣ", "����" };
	DefaultTableModel model = new DefaultTableModel(columns, 0);
	Object[] row = new Object[7];
	JTable table;
	JScrollPane scroll_pane;

	public StoreLikeUI(MainUI ui, LoginPageUI lp) {
		this.ui = ui;
		this.lp = lp;
		showList();
		likeUI();
	}

	public void likeUI() {
		f = new Frame(" ");
		Font font = new Font("���� ����", Font.BOLD, 17);
		String m_name = ui.fm.name(MainUI.tf_id.getText());
		JLabel title = new JLabel(m_name + "���� ���ã�� ���");

		title.setFont(font);

		title.setBounds(100, 50, 250, 50);

		if (showList() == 0) {
			scroll_pane.setBounds(100, 100, 550, 300);
			f.add(title);
			f.add(scroll_pane);

			f.add(new Panel());
			f.setSize(700, 500);
			f.setLocationRelativeTo(null);
			f.setResizable(false);
			f.setVisible(true);

			storeLikeUIEvent slu = new storeLikeUIEvent(this);
			f.addWindowListener(slu);
			mainMouseEvent mme = new mainMouseEvent(this);
			table.addMouseListener(mme);

		} else {
			JOptionPane.showMessageDialog(null, "���ã�Ⱑ �����ϴ�");
			lp.setVisible(false);
			f.setVisible(false);
			new LoginPageUI(ui, "");
		}

	}

	/* ���ã�� �� ���� */
	public void createRow() {
		table = new JTable(model);
		model.setNumRows(0);
		ArrayList<FoodVO> list = ui.fm.likeList(MainUI.tf_id.getText());
		for (FoodVO food : list) {
			row[0] = food.getRno();
			row[1] = food.getName();
			row[2] = food.getKind();
			row[3] = food.getLoc();
			row[4] = food.getPhone();

			model.addRow(row);
		}

	}

	/* ���ã�� ���̺� ���� */
	public int showList() {

		int a = 0;

		createRow();
		if (model.getRowCount() != 0) {
			scroll_pane = new JScrollPane(table);

			DefaultTableCellRenderer cell = new DefaultTableCellRenderer();
			DefaultTableCellRenderer cell2 = new MyDefaultTableCellRenderer2();
			cell.setHorizontalAlignment(SwingConstants.CENTER);
			table.getColumnModel().getColumn(0).setPreferredWidth(1);
			table.getColumnModel().getColumn(1).setPreferredWidth(80);
			table.getColumnModel().getColumn(2).setPreferredWidth(20);
			table.getColumnModel().getColumn(3).setPreferredWidth(100);
			table.getColumnModel().getColumn(4).setPreferredWidth(50);
			table.getColumnModel().getColumn(5).setPreferredWidth(20);

			table.getColumn("��ȣ").setCellRenderer(cell);
			table.getColumn("��������").setCellRenderer(cell);
			table.getColumn("��������").setCellRenderer(cell);
			table.getColumn("�ּ�").setCellRenderer(cell);
			table.getColumn("��ȭ��ȣ").setCellRenderer(cell);
			table.getColumn("����").setCellRenderer(cell2);

			table.setRowHeight(table.getRowHeight() + 10);

			table.getTableHeader().setReorderingAllowed(false); // �÷� �̵�����
			table.getTableHeader().setResizingAllowed(false); // �÷� ������ ����

			table.setShowVerticalLines(false); // ���� �� �Ⱥ��̰�

		} else {
			a = 1;
		}

		return a;

	}

	/* ��ư �̺�Ʈ */
	class storeLikeUIEvent extends WindowAdapter implements ActionListener {

		StoreLikeUI slu;

		public storeLikeUIEvent(StoreLikeUI slu) {
			this.slu = slu;
		}

		public void windowClosing(WindowEvent e) {
			f.setVisible(false);
		}

		public void actionPerformed(ActionEvent e) {

		}

	}

	/* ���콺 �̺�Ʈ */
	class mainMouseEvent extends MouseAdapter {
		StoreLikeUI slu;

		public mainMouseEvent(StoreLikeUI slu) {
			this.slu = slu;
		}

		public void mouseClicked(MouseEvent e) {
			int result = e.getButton();

			if (result == 1) {
				int column = table.getSelectedColumn(); // ��
				if (column == 5) {
					ArrayList<FoodVO> list = ui.fm.likeList(MainUI.tf_id.getText());
					int row = table.getSelectedRow(); // ��
					String sname = (String) table.getValueAt(row, 1);

					for (FoodVO vo : list) {
						if (vo.getName().equals(sname)) {

							int count = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?");
							if (count == 0) {
								if (ui.fm.like_delete(MainUI.tf_id.getText(), vo.getId())) {
									JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�");
									new StoreLikeUI(ui, lp);
									f.setVisible(false);

								} else {
									JOptionPane.showMessageDialog(null, "���� �����߽��ϴ�");
								}

							}
						}
					}
				} else {
					int row = table.getSelectedRow();
					String name = (String) table.getValueAt(row, 1);
					showInfo(name);
				}
			}

		}
	}

	/* ���� ���� ��ȸ */
	public void showInfo(String name) {
		ArrayList<FoodVO> list = ui.fm.list();
		for (FoodVO vo : list) {
			if (vo.getName().equals(name)) {
				String sid = vo.getId();
				new FoodInfoUI(ui, lp, sid, name);
				f.setVisible(false);
				lp.setVisible(false);

			}
		}

	}

}