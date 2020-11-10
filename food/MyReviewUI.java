package food;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MyReviewUI {

	MainUI ui;
	ReviewVO re_vo;
	LoginPageUI lp;
	Frame f, f1;
	String m_name, id, rid;
	Object[] columns = { "��ȣ", "������", "���䳻��", "����", "�ۼ�����", "����", "����" };
	DefaultTableModel model = new DefaultTableModel(columns, 0) {
		public boolean isCellEditable(int i, int c) {
			return false;
		}
	};
	Object[] row = new Object[7];
	JTable table;
	JScrollPane scroll_pane;

	ArrayList<ReviewVO> list;
	JTextArea jta;
	Panel content_panel;
	JComboBox<String> combo;
	String[] star = { "��������", "�ڡڡڡڡ�", "�ڡڡڡ�", "�ڡڡ�", "�ڡ�", "��" };
	int column, row2;
	JButton jbn;

	public MyReviewUI(MainUI ui, LoginPageUI lp, String id) {
		this.ui = ui;
		this.lp = lp;
		this.id = id;
		myR();
	}

	public void myR() {
		f = new Frame(" ");
		Font font = new Font("���� ����", Font.BOLD, 13);
		Font font2 = new Font("���� ����", Font.BOLD, 17);
		m_name = ui.fm.name(MainUI.tf_id.getText());
		JLabel label = new JLabel("���̸���");
		Panel panel = new Panel();
		ImageIcon img = new ImageIcon("src/food/home.png");
		jbn = new JButton(img);
		JButton join_b = new JButton("�α׾ƿ�");
		JLabel l = new JLabel(m_name + "�� �ݰ����ϴ�.");

		label.setFont(font2);
		join_b.setFont(font);
		l.setFont(font);

		jbn.setBackground(new Color(189, 236, 182));
		join_b.setBackground(new Color(189, 236, 182));

		label.setBounds(120, 100, 100, 30);
		jbn.setBounds(30, 35, 50, 50);
		l.setBounds(500, 35, 150, 30);
		join_b.setBounds(670, 35, 100, 30);

		if (showList() == 0) {
			scroll_pane.setBounds(120, 150, 650, 400);

			f.add(scroll_pane);
			f.add(label);
			f.add(jbn);
			f.add(join_b);
			f.add(l);
			f.add(panel);

			f.setSize(800, 600);
			f.setLocationRelativeTo(null);
			f.setResizable(false);
			f.setVisible(true);

			myReivewEvent mre = new myReivewEvent(this);
			f.addWindowListener(mre);
			jbn.addActionListener(mre);
			join_b.addActionListener(mre);

			myReviewMouseEvent mrm = new myReviewMouseEvent(this);
			table.addMouseListener(mrm);

		} else {
			JOptionPane.showMessageDialog(null, "�ۼ��� ���䰡 �����ϴ�");
			f.setVisible(false);
			new LoginPageUI(ui, "");
		}
	}

	/* ��ư �̺�Ʈ */
	class myReivewEvent extends WindowAdapter implements ActionListener {

		MyReviewUI mr;

		public myReivewEvent(MyReviewUI mr) {
			this.mr = mr;
		}

		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}

		public void actionPerformed(ActionEvent e) {
			String name = e.getActionCommand();
			Object obj = e.getSource();
			if (obj == jbn) {
				new LoginPageUI(ui, "");
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

	/* ���� �� ���� �� ���� */
	public void createRow() {
		table = new JTable(model);
		model.setNumRows(0);
		list = ui.fm.reviewMylist(id);
		for (ReviewVO review : list) {

			row[0] = review.getRno();
			row[1] = review.getSname();
			row[2] = review.getText();
			int a = review.getGrade();

			if (a == 5) {
				row[3] = star[1];
			}
			if (a == 4) {
				row[3] = star[2];
			}
			if (a == 3) {
				row[3] = star[3];
			}
			if (a == 2) {
				row[3] = star[4];
			}
			if (a == 1) {
				row[3] = star[5];
			}
			row[4] = review.getDate();

			model.addRow(row);
		}

	}

	/* ���� �� ���� ����Ʈ ���̺� ���� */
	public int showList() {

		int a = 0;

		createRow();
		if (model.getRowCount() != 0) {
			table = new JTable(model);

			scroll_pane = new JScrollPane(table);

			DefaultTableCellRenderer cell = new DefaultTableCellRenderer();
			DefaultTableCellRenderer cell2 = new MyDefaultTableCellRenderer();
			cell.setHorizontalAlignment(SwingConstants.CENTER);
			table.getColumnModel().getColumn(0).setPreferredWidth(1);
			table.getColumnModel().getColumn(1).setPreferredWidth(70);
			table.getColumnModel().getColumn(2).setPreferredWidth(140);
			table.getColumnModel().getColumn(3).setPreferredWidth(1);
			table.getColumnModel().getColumn(4).setPreferredWidth(40);
			table.getColumnModel().getColumn(5).setPreferredWidth(10);
			table.getColumnModel().getColumn(6).setPreferredWidth(10);

			table.getColumn("��ȣ").setCellRenderer(cell);
			table.getColumn("������").setCellRenderer(cell);
			table.getColumn("���䳻��").setCellRenderer(cell);
			table.getColumn("����").setCellRenderer(cell);
			table.getColumn("�ۼ�����").setCellRenderer(cell);
			table.getColumn("����").setCellRenderer(cell2);
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

	/* ���콺 �̺�Ʈ */
	class myReviewMouseEvent extends MouseAdapter {
		MyReviewUI mr;

		public myReviewMouseEvent(MyReviewUI mr) {
			this.mr = mr;
		}

		public void mouseClicked(MouseEvent e) {
			column = table.getColumnModel().getColumnIndexAtX(e.getX()); // ��
			row2 = e.getY() / table.getRowHeight(); // ��

			rid = list.get(row2).getRid();

			if (column == 5) {
				updateForm(rid);
			} else if (column == 6) {
				int count = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?");
				if (count == 0) {
					if (ui.fm.reviewDelete(rid)) {
						JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");
						new MyReviewUI(ui, lp, id);
						f.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "���������߽��ϴ�.");
					}
				}
			}

		}
	}

	/* ���� ���� �� */
	public void updateForm(String rid) {
		f1 = new Frame(" ");

		content_panel = new Panel(new BorderLayout());
		Panel lable_panel = new Panel();
		Panel tf_panel = new Panel();
		Panel combo_panel = new Panel();
		Panel btn_panel = new Panel();

		Font font = new Font("���� ����", Font.BOLD, 13);
		jta = new JTextArea(15, 18);
		jta.setBackground(Color.LIGHT_GRAY);
		jta.setText(list.get(row2).getText());
		Label label = new Label("������ ���� ����� ������ �Է����ּ���");
		combo = new JComboBox<>(star);
		combo.setSelectedIndex(0);
		JButton btn = new JButton("����");
		JButton btn1 = new JButton("�ʱ�ȭ");

		label.setFont(font);
		btn.setFont(font);
		btn1.setFont(font);

		btn.setBackground(new Color(189, 236, 182));
		btn1.setBackground(new Color(189, 236, 182));

		lable_panel.add(label);
		tf_panel.add(jta);
		combo_panel.add(combo);
		btn_panel.add(btn);
		btn_panel.add(btn1);

		content_panel.add(lable_panel, BorderLayout.NORTH);
		content_panel.add(tf_panel, BorderLayout.CENTER);
		content_panel.add(combo_panel, BorderLayout.EAST);
		content_panel.add(btn_panel, BorderLayout.SOUTH);

		f1.add(content_panel);

		f1.setSize(300, 200);
		f1.setLocationRelativeTo(null);
		f1.setVisible(true);

		myReivewEvent2 mre2 = new myReivewEvent2(this);
		f1.addWindowListener(mre2);
		btn.addActionListener(mre2);
		btn1.addActionListener(mre2);
		searchEvent se = new searchEvent(this);
		combo.addActionListener(se);
	}

	/* ��ư �̺�Ʈ */
	class myReivewEvent2 extends WindowAdapter implements ActionListener {

		MyReviewUI mr;

		public myReivewEvent2(MyReviewUI mr) {
			this.mr = mr;
		}

		public void windowClosing(WindowEvent e) {
			f1.setVisible(false);
		}

		public void actionPerformed(ActionEvent e) {
			String name = e.getActionCommand();
			Object obj = e.getSource();
			if (name.equals("����")) {
				if (re_vo != null && re_vo.getGrade() != 0) {
					int gra = re_vo.getGrade();
					if (jta.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "���並 �ۼ����ּ���");
						jta.requestFocus();
					} else {
						if (ui.fm.updateReview(rid, gra, jta.getText())) {
							JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�");
							new MyReviewUI(ui, lp, id);
							f.setVisible(false);
							f1.setVisible(false);
						} else
							JOptionPane.showMessageDialog(null, "�������еǾ����ϴ�");

					}

				} else {
					JOptionPane.showMessageDialog(null, "������ ������ּ���");
				}
			} else if (name.equals("�ʱ�ȭ")) {
				jta.setText("");
				combo.setSelectedIndex(0);
			}

		}
	}

	/* �޺��ڽ� �̺�Ʈ */
	class searchEvent implements ActionListener {

		MyReviewUI mr;

		public searchEvent(MyReviewUI mr) {
			this.mr = mr;
		}

		public void actionPerformed(ActionEvent e) {
			re_vo = new ReviewVO();
			JComboBox cd = (JComboBox) e.getSource();
			String list = (String) cd.getSelectedItem();

			if (list.equals("�ڡڡڡڡ�")) {
				re_vo.setGrade(5);
			} else if (list.equals("�ڡڡڡ�")) {
				re_vo.setGrade(4);
			} else if (list.equals("�ڡڡ�")) {
				re_vo.setGrade(3);
			} else if (list.equals("�ڡ�")) {
				re_vo.setGrade(2);
			} else if (list.equals("��")) {
				re_vo.setGrade(1);
			}

		}
	}

}