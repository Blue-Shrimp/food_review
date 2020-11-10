package food;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MyPageUI {

	MainUI ui;

	String[] names = { "이름", "아이디", "비밀번호", "번호", "번호", "번호", "선호음식", "사는지역" };
	Frame f;
	Panel main_p, center_p;
	JTextField jt_name, jt_id, jt_pass, jt_pn1, jt_pn2, jt_pn3, jt_f, jt_loc;
	JButton jb_join, jb_cancel, jb_login, jb_idCheck, btn, jb_delete;
	ArrayList<JTextField> textlist;

	public MyPageUI(MainUI ui) {
		this.ui = ui;

		myPa();
	}

	public void myPa() {
		f = new Frame(" ");
		JLabel label = new JLabel("마이페이지");
		Panel p1 = new Panel();
		Panel p2 = new Panel();
		Panel p3 = new Panel();
		Panel p4 = new Panel();
		Panel p5 = new Panel();
		Panel p6 = new Panel();
		Panel p7 = new Panel();
		Panel main = new Panel(new GridLayout(9, 1));

		Panel cc = new Panel();

		JLabel name = new JLabel("이름                 ");
		JLabel id = new JLabel("아이디             ");
		JLabel password = new JLabel("비밀번호         ");
		JLabel phnumber = new JLabel("번호       ");
		JLabel food = new JLabel("선호음식    ");
		JLabel loc = new JLabel("사는지역    ");
		JLabel a1 = new JLabel("-");
		JLabel a2 = new JLabel("-");

		textlist = new ArrayList<JTextField>();
		jt_name = new JTextField(14);
		textlist.add(jt_name);
		jt_id = new JTextField(14);
		textlist.add(jt_id);
		jt_pass = new JTextField(14);
		textlist.add(jt_pass);
		jt_pn1 = new JTextField(3);
		textlist.add(jt_pn1);
		jt_pn2 = new JTextField(5);
		textlist.add(jt_pn2);
		jt_pn3 = new JTextField(5);
		textlist.add(jt_pn3);
		jt_f = new JTextField(15);
		textlist.add(jt_f);
		jt_loc = new JTextField(15);
		textlist.add(jt_loc);

		jb_join = new JButton("수정");
		jb_cancel = new JButton("초기화");
		ImageIcon img = new ImageIcon("src/food/home.png");
		btn = new JButton(img);
		jb_delete = new JButton("회원탈퇴");

		btn.setBackground(new Color(189, 236, 182));
		jb_join.setBackground(new Color(189, 236, 182));
		jb_cancel.setBackground(new Color(189, 236, 182));
		jb_delete.setBackground(new Color(189, 236, 182));

		getData();
		p1.add(name);
		p1.add(jt_name);
		p2.add(id);
		p2.add(jt_id);
		p3.add(password);
		p3.add(jt_pass);
		p4.add(phnumber);
		p4.add(jt_pn1);
		p4.add(a1);
		p4.add(jt_pn2);
		p4.add(a2);
		p4.add(jt_pn3);
		p5.add(food);
		p5.add(jt_f);
		p6.add(loc);
		p6.add(jt_loc);
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

		btn.setBounds(30, 35, 50, 50);
		jb_delete.setBounds(650, 35, 100, 30);
		f.add(btn);
		f.add(jb_delete);

		f.add(cc);
		f.setSize(800, 600);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setVisible(true);

		myPageEvent mpe = new myPageEvent(this);
		f.addWindowListener(mpe);
		btn.addActionListener(mpe);
		jb_join.addActionListener(mpe);
		jb_cancel.addActionListener(mpe);
		jb_delete.addActionListener(mpe);

	}

	/* 버튼 이벤트 */
	class myPageEvent extends WindowAdapter implements ActionListener {

		MyPageUI mp;

		public myPageEvent(MyPageUI mp) {
			this.mp = mp;
		}

		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}

		public void actionPerformed(ActionEvent e) {
			String name = e.getActionCommand();
			Object obj = e.getSource();
			if (obj == btn) {
				new LoginPageUI(ui, "");
				f.setVisible(false);
			} else if (name == "수정") {
				if (validCheck()) {
					String num = jt_pn1.getText() + jt_pn2.getText() + jt_pn3.getText();
					if (num.length() == 11) {
						updateProc();
					} else {
						JOptionPane.showMessageDialog(null, "전화번호 11자리를 입력해주세요");
					}
				}
			} else if (name == "초기화") {
				jt_name.setText("");
				jt_pass.setText("");
				jt_pn1.setText("");
				jt_pn2.setText("");
				jt_pn3.setText("");
				jt_f.setText("");
				jt_loc.setText("");
			} else if (name == "회원탈퇴") {
				int count = JOptionPane.showConfirmDialog(null, "정말로 탈퇴하시겠습니까?");
				if (count == 0) {
					if (ui.fm.deleteMember(MainUI.tf_id.getText())) {
						JOptionPane.showMessageDialog(null, "탈퇴 완료했습니다.");
						f.setVisible(false);
						new MainUI();
					} else {
						JOptionPane.showMessageDialog(null, "탈퇴 실패했습니다.");
					}
				}
			}
		}
	}

	/* 사용자 정보 받아오기 */
	public void getData() {
		JoinVO vo = ui.fm.getData(MainUI.tf_id.getText());
		jt_name.setText(vo.getName());
		jt_id.setText(vo.getId());
		jt_id.setEditable(false);
		jt_pass.setText(vo.getPass());
		jt_pn1.setText("0" + String.valueOf((vo.getPn1())) + "0");
		jt_pn2.setText(String.valueOf(vo.getPn2()));
		jt_pn3.setText(String.valueOf(vo.getPn3()));
		jt_f.setText(vo.getFood());
		jt_loc.setText(vo.getAddr());
	}

	/* 사용자 수정 처리 */
	public void updateProc() {
		JoinVO vo = new JoinVO();

		vo.setId(jt_id.getText());
		vo.setName(jt_name.getText());
		vo.setPass(jt_pass.getText());
		vo.setPn1(Integer.parseInt(jt_pn1.getText()));
		vo.setPn2(Integer.parseInt(jt_pn2.getText()));
		vo.setPn3(Integer.parseInt(jt_pn3.getText()));
		vo.setFood(jt_f.getText());
		vo.setAddr(jt_loc.getText());

		if (ui.fm.update(vo)) {
			JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");
		} else {
			JOptionPane.showMessageDialog(null, "수정이 실패되었습니다.");
		}
	}

	/* null값 체크 */
	public boolean validCheck() {
		boolean result = true;
		int no = 0;
		for (JTextField tf : textlist) {
			if (tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, names[no].trim() + "을(를)  입력해주세요");
				tf.requestFocus();
				result = false;
				break;
			}
			no++;
		}

		return result;
	}

}
