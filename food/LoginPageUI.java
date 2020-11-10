package food;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class LoginPageUI extends Frame {
	Panel main_p, center_p, side_p;
	JTextField jtf;
	JComboBox<String> combo;
	String[] list = { "�̸���", "������", "�ѽ�", "�߽�", "����", "�Ͻ�", "��Ÿ" };

	Object[] columns = { "��ȣ", "�̹���", "��������", "��������", "��ȭ��ȣ", "����" };
	MyTableModel model;

	JTable table, table2;
	JScrollPane pane, pane2;
	MainUI ui;
	String sid, type;
	JButton jbn;

	public LoginPageUI(MainUI ui, String type) {
		this.type = type;
		this.ui = ui;
		startPage();
	}

	public void startPage() {
		main_p = new Panel(new BorderLayout());
		center_p = new Panel();
		side_p = new Panel();
		Font font = new Font("���� ����", Font.BOLD, 13);

		JButton jbn1 = new JButton("����������");
		JButton jbn2 = new JButton("���̸���");
		JButton jbn3 = new JButton("���ã��");
		ImageIcon img = new ImageIcon("src/food/home.png");
		jbn = new JButton(img);
		JButton join_b = new JButton("�α׾ƿ�");
		JButton search_b = new JButton("�˻�");
		JLabel l = new JLabel(ui.fm.name(MainUI.tf_id.getText()) + "�� �ݰ����ϴ�.");

		ImageIcon image = new ImageIcon("src/food/�ΰ�2.png");
		JLabel jl = imageSetSize(image);

		jbn1.setFont(font);
		jbn2.setFont(font);
		jbn3.setFont(font);
		join_b.setFont(font);
		search_b.setFont(font);
		l.setFont(font);

		jbn.setBackground(new Color(189, 236, 182));
		jbn1.setBackground(new Color(189, 236, 182));
		jbn2.setBackground(new Color(189, 236, 182));
		jbn3.setBackground(new Color(189, 236, 182));
		join_b.setBackground(new Color(189, 236, 182));
		search_b.setBackground(new Color(189, 236, 182));

		jl.setBounds(152, 25, 300, 80);
		jbn.setBounds(30, 35, 50, 50);
		jbn1.setBounds(30, 110, 100, 30);
		jbn2.setBounds(30, 150, 100, 30);
		jbn3.setBounds(30, 190, 100, 30);
		l.setBounds(500, 35, 150, 30);
		join_b.setBounds(670, 35, 100, 30);

		jtf = new JTextField(30);
		jtf.setText("ã�� ��������");
		jtf.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				jtf.setText("");
			}

			public void focusLost(FocusEvent e) {

			}
		});
		jtf.setBounds(152, 122, 160, 25);
		search_b.setBounds(320, 122, 70, 25);

		combo = new JComboBox<String>(list);
		if (type.equals("")) {
			combo.setSelectedIndex(0);
		} else if (type.equals("������")) {
			combo.setSelectedIndex(1);
		} else if (type.equals("�ѽ�")) {
			combo.setSelectedIndex(2);
		} else if (type.equals("�߽�")) {
			combo.setSelectedIndex(3);
		} else if (type.equals("����")) {
			combo.setSelectedIndex(4);
		} else if (type.equals("�Ͻ�")) {
			combo.setSelectedIndex(5);
		} else if (type.equals("��Ÿ")) {
			combo.setSelectedIndex(6);
		}
		combo.setBounds(700, 122, 70, 25);

		if (showList(type) == 0) {
			pane.setBounds(150, 150, 620, 400);

			add(jl);
			add(jbn);
			add(jbn1);
			add(jbn2);
			add(jbn3);
			add(l);
			add(join_b);

			add(jtf);
			add(search_b);
			add(combo);
			add(pane);

			add(main_p);

			setSize(800, 600);
			setLocationRelativeTo(null);
			setResizable(false);
			setVisible(true);

			loginPageEvent mue = new loginPageEvent(this);
			addWindowListener(mue);
			jbn1.addActionListener(mue);
			jbn2.addActionListener(mue);
			jbn3.addActionListener(mue);
			jbn.addActionListener(mue);
			jtf.addActionListener(mue);

			search_b.addActionListener(mue);
			join_b.addActionListener(mue);
			lpsearchEvent se = new lpsearchEvent(this);
			combo.addActionListener(se);

			mainMouseEvent mme = new mainMouseEvent(this);
			table.addMouseListener(mme);

		} else {
			JOptionPane.showMessageDialog(null, "�˻��� ����� �����ϴ�");
			setVisible(false);
			new LoginPageUI(ui, "");
		}
	}

	/* ������ ����Ʈ */
	public int showList(String type) {

		int a = 0;

		model = new MyTableModel(type, this);
		if (model.getRowCount() != 0) {
			table = new JTable(model);

			pane = new JScrollPane(table);

			DefaultTableCellRenderer cell = new DefaultTableCellRenderer();
			cell.setHorizontalAlignment(SwingConstants.CENTER);
			table.getColumnModel().getColumn(0).setPreferredWidth(1);
			table.getColumnModel().getColumn(1).setPreferredWidth(70);
			table.getColumnModel().getColumn(2).setPreferredWidth(70);
			table.getColumnModel().getColumn(3).setPreferredWidth(1);
			table.getColumnModel().getColumn(4).setPreferredWidth(55);
			table.getColumnModel().getColumn(5).setPreferredWidth(55);
			table.getColumnModel().getColumn(6).setPreferredWidth(20);

			table.getColumn("��ȣ").setCellRenderer(cell);
			table.getColumn("��������").setCellRenderer(cell);
			table.getColumn("��������").setCellRenderer(cell);
			table.getColumn("��ġ").setCellRenderer(cell);
			table.getColumn("��ȭ��ȣ").setCellRenderer(cell);
			table.getColumn("����").setCellRenderer(cell);

			table.setRowHeight(table.getRowHeight() + 70);

			table.getTableHeader().setReorderingAllowed(false); // �÷� �̵�����
			table.getTableHeader().setResizingAllowed(false); // �÷� ������ ����

			table.setShowVerticalLines(false); // ���� �� �Ⱥ��̰�

		} else {
			a = 1;
		}

		return a;

	}

	/* ��ư �̺�Ʈ */
	class loginPageEvent extends WindowAdapter implements ActionListener {
		LoginPageUI lp;

		public loginPageEvent(LoginPageUI lp) {
			this.lp = lp;
		}

		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}

		public void actionPerformed(ActionEvent e) {
			String name = e.getActionCommand();
			Object obj = e.getSource();
			if (obj == jbn) {
				new LoginPageUI(ui, "");
				setVisible(false);

			} else if (name == "�α׾ƿ�") {
				int count = JOptionPane.showConfirmDialog(null, "�α׾ƿ��Ͻðڽ��ϱ�");
				if (count == 0) {
					setVisible(false);
					new MainUI();
				}
			} else if (name == "����������") {
				new MyPageUI(ui);
				setVisible(false);
			} else if (name == "�˻�" || obj == jtf) {

				new LoginPageUI(ui, jtf.getText());
				setVisible(false);
			} else if (name == "���̸���") {
				new MyReviewUI(ui, lp, MainUI.tf_id.getText());
				setVisible(false);
			} else if (name == "���ã��") {
				new StoreLikeUI(ui, lp);
			}
		}
	}

	/* ���콺 �̺�Ʈ */
	class mainMouseEvent extends MouseAdapter {
		LoginPageUI lp;

		public mainMouseEvent(LoginPageUI lp) {
			this.lp = lp;
		}

		public void mouseClicked(MouseEvent e) {
			int result = e.getButton();

			if (result == 1) {
				int row = table.getSelectedRow();
				String name = (String) table.getValueAt(row, 2);

				showInfo(name);

				setVisible(false);

			}

		}
	}

	/* �޺��ڽ� �̺�Ʈ */
	class lpsearchEvent implements ActionListener {
		LoginPageUI lp;

		public lpsearchEvent(LoginPageUI lp) {
			this.lp = lp;
		}

		public void actionPerformed(ActionEvent e) {

			JComboBox cd = (JComboBox) e.getSource();
			String list = (String) cd.getSelectedItem();

			if (list.equals("�ѽ�")) {
				new LoginPageUI(lp.ui, list);
				lp.setVisible(false);
			} else if (list.equals("�Ͻ�")) {
				new LoginPageUI(lp.ui, list);
				lp.setVisible(false);
			} else if (list.equals("�߽�")) {
				new LoginPageUI(lp.ui, list);
				lp.setVisible(false);
			} else if (list.equals("����")) {
				new LoginPageUI(lp.ui, list);
				lp.setVisible(false);
			} else if (list.equals("��Ÿ")) {
				new LoginPageUI(lp.ui, list);
				lp.setVisible(false);
			} else if (list.equals("������")) {
				new LoginPageUI(lp.ui, list);
				lp.setVisible(false);
			} else if (list.equals("�̸���")) {
				new LoginPageUI(lp.ui, list);
				lp.setVisible(false);
			}

		}
	}

	/* �������� ��ȸ */
	public void showInfo(String name) {

		ArrayList<FoodVO> list = ui.fm.list();
		for (FoodVO vo : list) {
			if (vo.getName().equals(name)) {
				String sid = vo.getId();
				new FoodInfoUI(ui, this, sid, name);

			}
		}

	}

	/* �̹��� ������ ���� */
	public JLabel imageSetSize(ImageIcon image) {
		Image img = image.getImage();
		Image change = img.getScaledInstance(300, 80, Image.SCALE_SMOOTH);
		JLabel lbl = new JLabel(new ImageIcon(change));
		return lbl;
	}
}