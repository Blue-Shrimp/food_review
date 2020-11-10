package food;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class InsertStoreUI {

	MainUI ui;
	AdminPageUI ap;
	Frame f;
	JButton btn;
	FoodVO vo;
	JTextField jt_name, jt_loc, jt_pn1, jt_pn2, jt_pn3, jt_img;
	JComboBox<String> combo;
	String[] list = { "", "�ѽ�", "�߽�", "����", "�Ͻ�", "��Ÿ" };
	ArrayList<JTextField> textlist;
	String[] names = { "��������", "��������ġ", "��ȭ��ȣ", "��ȭ��ȣ", "��ȭ��ȣ", "�̹���" };
	String name, loc, pn1, pn2, pn3, kind, simg;
	JLabel jl;

	public InsertStoreUI(MainUI ui, AdminPageUI ap, String name, String loc, String pn1, String pn2, String pn3,
			String kind, String simg) {
		this.ui = ui;
		this.ap = ap;
		this.name = name;
		this.loc = loc;
		this.pn1 = pn1;
		this.pn2 = pn2;
		this.pn3 = pn3;
		this.kind = kind;
		this.simg = simg;
		insertForm();
	}

	public void insertForm() {

		f = new Frame(" ");
		Font font = new Font("���� ����", Font.BOLD, 13);
		Font font2 = new Font("���� ����", Font.BOLD, 17);
		Panel panel = new Panel();
		ImageIcon img = new ImageIcon("src/food/home.png");
		btn = new JButton(img);
		JButton join_b = new JButton("�α׾ƿ�");
		JButton search = new JButton("ã��");
		JButton insert = new JButton("���");
		JButton cancel = new JButton("�ʱ�ȭ");
		JLabel l = new JLabel(MainUI.tf_id.getText() + "�� �ݰ����ϴ�.");
		JLabel l2 = new JLabel("������ ���� ���");

		join_b.setFont(font);
		search.setFont(font);
		insert.setFont(font);
		cancel.setFont(font);
		l.setFont(font);
		l2.setFont(font2);

		btn.setBackground(new Color(189, 236, 182));
		join_b.setBackground(new Color(189, 236, 182));
		search.setBackground(new Color(189, 236, 182));
		insert.setBackground(new Color(189, 236, 182));
		cancel.setBackground(new Color(189, 236, 182));

		if (simg.equals("")) {
			ImageIcon image = new ImageIcon("src\\food\\sumnail.png");
			jl = imageSetSize(image);

		} else {
			ImageIcon image = new ImageIcon("src\\food\\" + simg); // �̹��� ÷�ν� ÷�ε� �̹��� �����ֱ�
			jl = imageSetSize(image);

		}

		JLabel jl_n = new JLabel("��������");
		JLabel jl_l = new JLabel("��������ġ");
		JLabel jl_f = new JLabel("��ȭ��ȣ");
		JLabel jl_t = new JLabel("��������");
		JLabel jl_i = new JLabel("�̹���");
		JLabel jl_s = new JLabel("(100x100)");
		JLabel a1 = new JLabel("-");
		JLabel a2 = new JLabel("-");

		textlist = new ArrayList<>();
		jt_name = new JTextField(10);
		textlist.add(jt_name);
		jt_name.setText(name);
		jt_loc = new JTextField(10);
		textlist.add(jt_loc);
		jt_loc.setText(loc);
		jt_pn1 = new JTextField(2);
		textlist.add(jt_pn1);
		jt_pn1.setText(pn1);
		jt_pn2 = new JTextField(4);
		textlist.add(jt_pn2);
		jt_pn2.setText(pn2);
		jt_pn3 = new JTextField(4);
		textlist.add(jt_pn3);
		jt_pn3.setText(pn3);
		combo = new JComboBox<String>(list);
		if (kind.equals("")) {
			combo.setSelectedIndex(0);
		} else if (kind.equals("�ѽ�")) {
			combo.setSelectedIndex(1);
		} else if (kind.equals("�߽�")) {
			combo.setSelectedIndex(2);
		} else if (kind.equals("����")) {
			combo.setSelectedIndex(3);
		} else if (kind.equals("�Ͻ�")) {
			combo.setSelectedIndex(4);
		} else if (kind.equals("��Ÿ")) {
			combo.setSelectedIndex(5);
		}

		jt_img = new JTextField(10);
		textlist.add(jt_img);
		jt_img.setText(simg);
		jt_img.setEditable(false);

		jl_n.setFont(font);
		jl_l.setFont(font);
		jl_t.setFont(font);
		jl_f.setFont(font);
		jl_i.setFont(font);

		btn.setBounds(30, 35, 50, 50);
		l.setBounds(500, 35, 150, 30);
		join_b.setBounds(650, 35, 100, 30);
		jl.setBounds(150, 140, 200, 200);
		l2.setBounds(330, 90, 150, 30);

		jl_n.setBounds(370, 130, 60, 30);
		jl_l.setBounds(370, 190, 65, 30);
		jl_f.setBounds(370, 250, 60, 30);
		jl_t.setBounds(370, 310, 60, 30);
		jl_i.setBounds(370, 370, 60, 30);
		jl_s.setBounds(365, 390, 60, 30);

		jt_name.setBounds(430, 135, 210, 20);
		jt_loc.setBounds(440, 195, 200, 20);
		jt_pn1.setBounds(430, 255, 40, 20);
		a1.setBounds(475, 255, 5, 20);
		jt_pn2.setBounds(492, 255, 62, 20);
		a2.setBounds(557, 255, 5, 20);
		jt_pn3.setBounds(575, 255, 65, 20);
		combo.setBounds(430, 315, 210, 20);
		jt_img.setBounds(430, 375, 150, 20);
		search.setBounds(582, 375, 60, 20);

		insert.setBounds(300, 450, 100, 30);
		cancel.setBounds(410, 450, 100, 30);

		f.add(jl_n);
		f.add(jl_l);
		f.add(jl_t);
		f.add(jl_f);
		f.add(jl_i);
		f.add(jl_s);

		f.add(jt_name);
		f.add(jt_loc);
		f.add(combo);
		f.add(jt_pn1);
		f.add(a1);
		f.add(jt_pn2);
		f.add(a2);
		f.add(jt_pn3);
		f.add(jt_img);

		f.add(insert);
		f.add(cancel);
		f.add(search);
		f.add(btn);
		f.add(jl);
		f.add(join_b);
		f.add(l);
		f.add(l2);
		f.add(panel);

		f.setSize(800, 600);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setVisible(true);

		updateStoreEvent use = new updateStoreEvent(this);
		f.addWindowListener(use);
		btn.addActionListener(use);
		search.addActionListener(use);
		insert.addActionListener(use);
		cancel.addActionListener(use);
	}

	/* ��ư �̺�Ʈ */
	class updateStoreEvent extends WindowAdapter implements ActionListener {

		InsertStoreUI us;

		public updateStoreEvent(InsertStoreUI us) {
			this.us = us;
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
			} else if (name == "ã��") {
				new test_Frame();
			} else if (name == "���") {
				if (validCheck()) {
					String num = jt_pn1.getText() + jt_pn2.getText() + jt_pn3.getText();
					if (num.length() == 9 || num.length() == 10) { // ��ȭ��ȣ 9�ڸ�, 10�ڸ��� ����
						insertProc();
						new AdminPageUI(ui, "");
						f.setVisible(false);

					} else {
						JOptionPane.showMessageDialog(null, "��ȭ��ȣ 9�ڸ� Ȥ�� 10�ڸ��� �Է����ּ���");
					}
				}
			} else if (name == "�ʱ�ȭ") {
				jt_name.setText("");
				jt_loc.setText("");
				combo.setSelectedIndex(0);
				jt_pn1.setText("");
				jt_pn2.setText("");
				jt_pn3.setText("");
			}

		}

	}

	/* �̹��� ������ ���� */
	public JLabel imageSetSize(ImageIcon image) {
		Image img = image.getImage();
		Image change = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		JLabel lbl = new JLabel(new ImageIcon(change));
		return lbl;
	}

	/* pc ��ο��� ���� ÷�� */
	class test_Frame extends JFrame implements ActionListener {

		JFileChooser jfc = new JFileChooser(" ");
		JButton jbt_save = new JButton("����");

		public test_Frame() {

			jfc.setCurrentDirectory(new File("src/food")); // default ���
			jfc.setFileFilter(new FileNameExtensionFilter("png & jpg", "png", "jpg")); // png, jpg Ȯ����
			jfc.setMultiSelectionEnabled(false); // �ߺ� ���� �Ұ�

			if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				jt_img.setText(jfc.getSelectedFile().getName()); // �̹��� �̸� �ؽ�Ʈ�ʵ忡 ����
				new InsertStoreUI(ui, ap, jt_name.getText(), jt_loc.getText(), jt_pn1.getText(), jt_pn2.getText(),
						jt_pn3.getText(), (String) combo.getSelectedItem(), jfc.getSelectedFile().getName());
				f.setVisible(false);
			}

			jbt_save.addActionListener(this);
		}

		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == jbt_save) {
				if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
					jt_img.setText(jfc.getSelectedFile().getName());
					new InsertStoreUI(ui, ap, jt_name.getText(), jt_loc.getText(), jt_pn1.getText(), jt_pn2.getText(),
							jt_pn3.getText(), (String) combo.getSelectedItem(), jfc.getSelectedFile().getName());
					f.setVisible(false);
				}
			}
		}
	}

	/* ������ ��� ó�� */
	public void insertProc() {
		FoodVO vo = new FoodVO();

		vo.setName(jt_name.getText());
		vo.setLoc(jt_loc.getText());
		vo.setKind((String) combo.getSelectedItem());
		vo.setPhone(jt_pn1.getText() + "-" + jt_pn2.getText() + "-" + jt_pn3.getText());
		vo.setSimg(jt_img.getText());

		if (ui.fm.insertStore(vo)) {
			JOptionPane.showMessageDialog(null, "����� �Ϸ�Ǿ����ϴ�.");
		} else {
			JOptionPane.showMessageDialog(null, "����� ���еǾ����ϴ�.");
		}
	}

	/* null �� üũ */
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
			if (no == 6) {
				if ((String) combo.getSelectedItem() == "") {
					JOptionPane.showMessageDialog(null, "����������(��)  �Է����ּ���");
					result = false;
				}

			}
		}

		return result;
	}

}