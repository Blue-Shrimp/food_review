package food;

import java.awt.Color;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MemberManagementUI {

	MainUI ui;
	AdminPageUI ap;
	Frame f;
	JButton btn;
	Object[] columns = { "��ȣ", "���̵�", "�̸�", "��ȭ��ȣ", "��ȣ����", "����", "��������", "����" };
	DefaultTableModel model = new DefaultTableModel(columns, 0) {
		public boolean isCellEditable(int i, int c) {
			return false;
		}
	};
	Object[] row;
	JTable table;
	JScrollPane scroll_pane;
	int column, row2;
	ArrayList<MemberVO> list;
	String mid;

	public MemberManagementUI(MainUI ui, AdminPageUI ap) {
		this.ui = ui;
		this.ap = ap;
		managementForm();
	}

	public void managementForm() {

		f = new Frame(" ");
		Font font = new Font("���� ����", Font.BOLD, 13);
		Font font2 = new Font("���� ����", Font.BOLD, 17);
		Panel panel = new Panel();
		ImageIcon img = new ImageIcon("src/food/home.png");
		btn = new JButton(img);
		JButton join_b = new JButton("�α׾ƿ�");
		JLabel l = new JLabel(MainUI.tf_id.getText() + "�� �ݰ����ϴ�.");
		JLabel l2 = new JLabel("ȸ�� ����");

		join_b.setFont(font);
		l.setFont(font);
		l2.setFont(font2);

		btn.setBackground(new Color(189, 236, 182));
		join_b.setBackground(new Color(189, 236, 182));

		btn.setBounds(30, 35, 50, 50);
		l.setBounds(608, 35, 150, 30);
		l2.setBounds(150, 100, 150, 30);
		join_b.setBounds(778, 35, 100, 30);

		if (showList() == 0) {
			scroll_pane.setBounds(150, 150, 730, 400);

			f.add(scroll_pane);
			f.add(btn);
			f.add(join_b);
			f.add(l);
			f.add(l2);
			f.add(panel);

			f.setSize(900, 600);
			f.setLocationRelativeTo(null);
			f.setResizable(false);
			f.setVisible(true);

			memberManagementEvent mme = new memberManagementEvent(this);
			f.addWindowListener(mme);
			btn.addActionListener(mme);
			join_b.addActionListener(mme);
			memberManagementMouseEvent mmm = new memberManagementMouseEvent(this);
			table.addMouseListener(mmm);

		} else {
			JOptionPane.showMessageDialog(null, "��ϵ� ����ڰ� �����ϴ�");
			f.setVisible(false);
			new AdminPageUI(ui, "");
		}
	}

	/* ����� �� ���� */
	public void createRow() {
		row = new Object[30];
		table = new JTable(model);
		model.setNumRows(0);
		list = ui.fm.listMember();
		for (MemberVO member : list) {
			row[0] = member.getRno();
			row[1] = member.getId();
			row[2] = member.getName();
			row[3] = member.getPhone();
			row[4] = member.getKind();
			row[5] = member.getLoc();
			row[6] = member.getDate();

			model.addRow(row);

		}
		table.repaint();
		model.fireTableDataChanged();

	}

	/* ����� ����Ʈ ���̺� ���� */
	public int showList() {

		int a = 0;

		createRow();

		if (model.getRowCount() != 0) {
			scroll_pane = new JScrollPane(table);

			DefaultTableCellRenderer cell = new DefaultTableCellRenderer();
			DefaultTableCellRenderer cell2 = new MyDefaultTableCellRenderer2();
			cell.setHorizontalAlignment(SwingConstants.CENTER);
			table.getColumnModel().getColumn(0).setPreferredWidth(1);
			table.getColumnModel().getColumn(1).setPreferredWidth(70);
			table.getColumnModel().getColumn(2).setPreferredWidth(50);
			table.getColumnModel().getColumn(3).setPreferredWidth(80);
			table.getColumnModel().getColumn(4).setPreferredWidth(25);
			table.getColumnModel().getColumn(5).setPreferredWidth(30);
			table.getColumnModel().getColumn(6).setPreferredWidth(30);
			table.getColumnModel().getColumn(7).setPreferredWidth(30);

			table.getColumn("��ȣ").setCellRenderer(cell);
			table.getColumn("���̵�").setCellRenderer(cell);
			table.getColumn("�̸�").setCellRenderer(cell);
			table.getColumn("��ȭ��ȣ").setCellRenderer(cell);
			table.getColumn("��ȣ����").setCellRenderer(cell);
			table.getColumn("����").setCellRenderer(cell);
			table.getColumn("��������").setCellRenderer(cell);
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
	class memberManagementEvent extends WindowAdapter implements ActionListener {

		MemberManagementUI mm;

		public memberManagementEvent(MemberManagementUI mm) {
			this.mm = mm;
		}

		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}

		public void actionPerformed(ActionEvent e) {
			String name = e.getActionCommand();
			Object obj = e.getSource();
			if (obj == btn) {
				new AdminPageUI(ui, "");
				f.setVisible(false);
			} else if (name == "�α׾ƿ�") {
				int count = JOptionPane.showConfirmDialog(null, "�α׾ƿ��Ͻðڽ��ϱ�");
				if (count == 0) {
					f.setVisible(false);
					new MainUI();
				}
			}

		}

	}

	/* ���콺 �̺�Ʈ */
	class memberManagementMouseEvent extends MouseAdapter {
		MemberManagementUI mm;

		public memberManagementMouseEvent(MemberManagementUI mm) {
			this.mm = mm;
		}

		public void mouseClicked(MouseEvent e) {
			column = table.getColumnModel().getColumnIndexAtX(e.getX()); // ��
			row2 = e.getY() / table.getRowHeight(); // ��

			mid = list.get(row2).getId();

			if (column == 7) {
				int count = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?");
				if (count == 0) {
					if (ui.fm.deleteMember(mid)) {
						JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");
						new MemberManagementUI(ui, ap);
						f.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "���������߽��ϴ�.");
					}
				}
			}

		}
	}

}