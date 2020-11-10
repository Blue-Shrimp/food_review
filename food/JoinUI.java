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
	String[] names = { "이름", "아이디", "비밀번호", "번호", "번호", "번호", "사는지역" };
	JComboBox<String> combo;
	String[] list = { "", "한식", "중식", "경양식", "일식", "기타" };
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
		JLabel label = new JLabel("전부 기입해 주세요");
		Panel p1 = new Panel();
		Panel p2 = new Panel();
		Panel p3 = new Panel();
		Panel p4 = new Panel();
		Panel p5 = new Panel();
		Panel p6 = new Panel();
		Panel p7 = new Panel();

		Panel main = new Panel(new GridLayout(9, 1));

		Panel cc = new Panel();

		JLabel name = new JLabel("이름           ");
		JLabel id = new JLabel("아이디   ");
		JLabel password = new JLabel("비밀번호         ");

		JLabel phnumber = new JLabel("번호       ");
		JLabel loc = new JLabel("사는지역    ");
		JLabel food = new JLabel("선호음식    ");
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

		jb_join = new JButton("회원가입");
		jb_cancel = new JButton("초기화");
		jb_idCheck = new JButton("중복확인");

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

	/* 버튼 이벤트 */
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

			if (name == "초기화") {
				for (JTextField tf : textlist) {
					tf.setText("");
				}
				combo.setSelectedIndex(0);
			} else if (name == "회원가입") {
				if (validCheck()) {
					save();
					if (idResult) {
						String num = "0" + vo.getPn1() + vo.getPn2() + vo.getPn3();
						if (num.length() == 11) { // 핸드폰 자리수 11자리 지정
							if (ui.fm.insert(vo)) {
								JOptionPane.showMessageDialog(null, "회원가입되었습니다");
								f.setVisible(false);
							} else
								JOptionPane.showMessageDialog(null, "회원가입 실패했습니다");

						} else {
							JOptionPane.showMessageDialog(null, "전화번호 11자리를 입력해주세요");
						}
					} else {
						JOptionPane.showMessageDialog(null, "아이디 중복을 확인해주세요");
					}
				}

			} else if (name == "중복확인") {
				if (validID()) {
					if (idCheck()) {
						JOptionPane.showMessageDialog(null, "아이디 사용가능합니다.");
					} else {
						JOptionPane.showMessageDialog(null, "아이디 중복입니다.");
						idResult = false;
					}
				} else {
					idResult = false;
				}
			}

		}

		// null 값 체크
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
				if (no == 7) {
					if ((String) combo.getSelectedItem() == "") {
						JOptionPane.showMessageDialog(null, "선호음식을  입력해주세요");
						result = false;
					}
				}
			}

			return result;
		}

		/* 회원가입 처리 */
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

		/* 아이디 중복 체크 */
		public boolean idCheck() {
			boolean result = false;

			String id = textlist.get(1).getText();
			result = ui.fm.check(id);
			idResult = ui.fm.check(id);

			return result;
		}

		/* 아이디 유효성 체크 */
		public boolean validID() {
			boolean result = false;
			String id = textlist.get(1).getText();

			Pattern alphabetLow = null;
			Pattern number = null;
			Matcher match;
			int charType = 0;

			alphabetLow = Pattern.compile("[a-z]"); // 소문자
			number = Pattern.compile("[0-9]"); // 숫자

			match = alphabetLow.matcher(id);
			if (match.find()) {
				charType++;
			}
			match = number.matcher(id);
			if (match.find()) {
				charType++;
			}

			if (charType == 0 || charType == 1) { // 영소문자와 숫자의 조합으로 아이디 생성
				JOptionPane.showMessageDialog(null, "영소문자와 숫자가 들어가야합니다");
			} else {
				result = true;
			}

			return result;
		}
	}
}
