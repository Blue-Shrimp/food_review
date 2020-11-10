package food;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class JoinUI {

	MainUI ui;
	String[] names = { "�̸�", "���̵�", "��й�ȣ", "��ȣ", "��ȣ", "��ȣ", "�������" };
	JComboBox<String> combo;
	String[] list = { "", "�ѽ�", "�߽�", "����", "�Ͻ�", "��Ÿ" };
	JTextField jt_name, jt_pn1, jt_pn2, jt_pn3, jt_loc, jt_passCheck;
	JButton jb_join, jb_cancel, jb_login, jb_idCheck;;
	Panel center_p, main_p;
	ArrayList<JTextField> textlist;
	JTextField jt_id, jt_pass;
	JoinVO vo;
	Frame f;
	boolean idResult;

	public JoinUI(MainUI ui) {
		this.ui = ui;
		makeID();
	}

	public void makeID() {
		f = new Frame(" ");
		JLabel label = new JLabel("���� ������ �ּ���");
		Panel p1 = new Panel();
		Panel p2 = new Panel();
		Panel p3 = new Panel();
		Panel p4 = new Panel();
		Panel p5 = new Panel();
		Panel p6 = new Panel();
		Panel p7 = new Panel();

		Panel main = new Panel(new GridLayout(9, 1));

		Panel cc = new Panel();

		JLabel name = new JLabel("�̸�           ");
		JLabel id = new JLabel("���̵�   ");
		JLabel password = new JLabel("��й�ȣ         ");

		JLabel phnumber = new JLabel("��ȣ       ");
		JLabel loc = new JLabel("�������    ");
		JLabel food = new JLabel("��ȣ����    ");
		JLabel a1 = new JLabel("-");
		JLabel a2 = new JLabel("-");

		textlist = new ArrayList<>();
		jt_name = new JTextField(16);
		textlist.add(jt_name);
		jt_id = new JTextField(9);
		textlist.add(jt_id);
		jt_pass = new JTextField(14);
		textlist.add(jt_pass);
		jt_pn1 = new JTextField(3);
		textlist.add(jt_pn1);
		jt_pn2 = new JTextField(5);
		textlist.add(jt_pn2);
		jt_pn3 = new JTextField(5);
		textlist.add(jt_pn3);
		jt_loc = new JTextField(15);
		textlist.add(jt_loc);
		combo = new JComboBox<String>(list);
		combo.setPreferredSize(new Dimension(155, 20));

		jb_join = new JButton("ȸ������");
		jb_cancel = new JButton("�ʱ�ȭ");
		jb_idCheck = new JButton("�ߺ�Ȯ��");

		jb_join.setBackground(new Color(189, 236, 182));
		jb_cancel.setBackground(new Color(189, 236, 182));
		jb_idCheck.setBackground(new Color(189, 236, 182));

		p1.add(name);
		p1.add(jt_name);
		p2.add(id);
		p2.add(jt_id);
		p2.add(jb_idCheck);
		p3.add(password);
		p3.add(jt_pass);

		p4.add(phnumber);
		p4.add(jt_pn1);
		p4.add(a1);
		p4.add(jt_pn2);
		p4.add(a2);
		p4.add(jt_pn3);
		p5.add(loc);
		p5.add(jt_loc);
		p6.add(food);
		p6.add(combo);
		p7.add(jb_join);
		p7.add(jb_cancel);

		main.add(label);
		main.add(p1);
		main.add(p2);
		main.add(p3);
		main.add(p4);
		main.add(p5);
		main.add(p6);
		main.add(p7);
		cc.add(main);

		f.add(cc);
		f.setSize(300, 400);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setVisible(true);

		joinUIEvent jue = new joinUIEvent(this, ui);
		f.addWindowListener(jue);
		jb_join.addActionListener(jue);
		jb_cancel.addActionListener(jue);
		jb_idCheck.addActionListener(jue);

	}

	/* ��ư �̺�Ʈ */
	class joinUIEvent extends WindowAdapter implements ActionListener {
		JoinUI jui;
		MainUI ui;

		public joinUIEvent(JoinUI jui, MainUI ui) {
			this.jui = jui;
			this.ui = ui;
		}

		public void windowClosing(WindowEvent e) {
			f.setVisible(false);
		}

		public void actionPerformed(ActionEvent e) {
			String name = e.getActionCommand();
			Object obj = e.getSource();

			if (name == "�ʱ�ȭ") {
				for (JTextField tf : textlist) {
					tf.setText("");
				}
				combo.setSelectedIndex(0);
			} else if (name == "ȸ������") {
				if (validCheck()) {
					save();
					if (idResult) {
						String num = "0" + vo.getPn1() + vo.getPn2() + vo.getPn3();
						if (num.length() == 11) { // �ڵ��� �ڸ��� 11�ڸ� ����
							if (ui.fm.insert(vo)) {
								JOptionPane.showMessageDialog(null, "ȸ�����ԵǾ����ϴ�");
								f.setVisible(false);
							} else
								JOptionPane.showMessageDialog(null, "ȸ������ �����߽��ϴ�");

						} else {
							JOptionPane.showMessageDialog(null, "��ȭ��ȣ 11�ڸ��� �Է����ּ���");
						}
					} else {
						JOptionPane.showMessageDialog(null, "���̵� �ߺ��� Ȯ�����ּ���");
					}
				}

			} else if (name == "�ߺ�Ȯ��") {
				if (validID()) {
					if (idCheck()) {
						JOptionPane.showMessageDialog(null, "���̵� ��밡���մϴ�.");
					} else {
						JOptionPane.showMessageDialog(null, "���̵� �ߺ��Դϴ�.");
						idResult = false;
					}
				} else {
					idResult = false;
				}
			}

		}

		// null �� üũ
		public boolean validCheck() {
			boolean result = true;
			int no = 0;
			for (JTextField tf : textlist) {
				if (tf.getText().equals("")) {
					JOptionPane.showMessageDialog(null, names[no].trim() + "��(��)  �Է����ּ���");
					tf.requestFocus();
					result = false;
					break;
				}
				no++;
				if (no == 7) {
					if ((String) combo.getSelectedItem() == "") {
						JOptionPane.showMessageDialog(null, "��ȣ������  �Է����ּ���");
						result = false;
					}
				}
			}

			return result;
		}

		/* ȸ������ ó�� */
		public void save() {
			ArrayList<String> list = new ArrayList<>();
			vo = new JoinVO();
			for (JTextField tf : textlist) {
				list.add(tf.getText());
			}
			vo.setName(list.get(0));
			vo.setId(list.get(1));
			vo.setPass(list.get(2));
			vo.setPn1(Integer.parseInt(list.get(3)));
			vo.setPn2(Integer.parseInt(list.get(4)));
			vo.setPn3(Integer.parseInt(list.get(5)));
			vo.setAddr(list.get(6));
			vo.setFood((String) combo.getSelectedItem());

		}

		/* ���̵� �ߺ� üũ */
		public boolean idCheck() {
			boolean result = false;

			String id = textlist.get(1).getText();
			result = ui.fm.check(id);
			idResult = ui.fm.check(id);

			return result;
		}

		/* ���̵� ��ȿ�� üũ */
		public boolean validID() {
			boolean result = false;
			String id = textlist.get(1).getText();

			Pattern alphabetLow = null;
			Pattern number = null;
			Matcher match;
			int charType = 0;

			alphabetLow = Pattern.compile("[a-z]"); // �ҹ���
			number = Pattern.compile("[0-9]"); // ����

			match = alphabetLow.matcher(id);
			if (match.find()) {
				charType++;
			}
			match = number.matcher(id);
			if (match.find()) {
				charType++;
			}

			if (charType == 0 || charType == 1) { // ���ҹ��ڿ� ������ �������� ���̵� ����
				JOptionPane.showMessageDialog(null, "���ҹ��ڿ� ���ڰ� �����մϴ�");
			} else {
				result = true;
			}

			return result;
		}
	}
}