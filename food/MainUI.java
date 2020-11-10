package food;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainUI {
	FoodMgm fm;
	Frame f;
	Panel intro_panel, menu_panel, content_panel, insert_panel, select_panel, update_panel, delete_panel, update_bottom;
	public static JTextField tf_id;
	JPasswordField tf_pass;

	public MainUI() {
		fm = new FoodMgm();
		f = new Frame(" ");
		intro_panel = new Panel(new BorderLayout());
		ImageIcon img = new ImageIcon("src/food/로고.png");
		JLabel logo = new JLabel(img);

		Font font = new Font("맑은 고딕", Font.BOLD, 13);
		Panel login_panel = new Panel();
		Panel id_panel = new Panel();
		Panel pass_panel = new Panel();
		id_panel.setLayout(new GridLayout(2, 1));
		pass_panel.setLayout(new GridLayout(2, 1));
		Panel button_panel = new Panel();
		Label label_id = new Label("아이디   ");
		Label label_pass = new Label("비밀번호");
		tf_id = new JTextField(15);
		tf_pass = new JPasswordField(15);
		JButton btn_login = new JButton("로그인");
		JButton btn_join = new JButton("회원가입");
		btn_login.setBackground(new Color(189, 236, 182));
		btn_join.setBackground(new Color(189, 236, 182));

		label_id.setFont(font);
		label_pass.setFont(font);
		btn_login.setFont(font);
		btn_join.setFont(font);

		button_panel.add(btn_login);
		button_panel.add(btn_join);

		Panel p1 = new Panel();
		p1.add(label_id);
		p1.add(tf_id);
		id_panel.add(p1);
		Panel p2 = new Panel();
		p2.add(label_pass);
		p2.add(tf_pass);
		pass_panel.add(p2);

		login_panel.setLayout(new GridLayout(2, 1));
		login_panel.add(id_panel);
		login_panel.add(pass_panel);

		intro_panel.add(BorderLayout.NORTH, logo);
		intro_panel.add(BorderLayout.CENTER, login_panel);
		intro_panel.add(BorderLayout.SOUTH, button_panel);
		f.add(intro_panel);

		f.setSize(800, 600);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setVisible(true);

		foodEvent fe = new foodEvent(this);
		f.addWindowListener(fe);

		tf_pass.addActionListener(fe);
		btn_login.addActionListener(fe);
		btn_join.addActionListener(fe);

	}

	/* 로그인 처리 */
	public void loginProc() {
		String id = tf_id.getText().trim();
		String pass = tf_pass.getText().trim();

		if (id.equals("")) {
			JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
			tf_id.requestFocus();
		} else if (pass.equals("")) {
			JOptionPane.showMessageDialog(null, "패스워드를 입력해주세요");
			tf_pass.requestFocus();
		} else if (id.equals("admin") && pass.equals("1234")) {
			JOptionPane.showMessageDialog(null, "관리자 모드입니다");
			new AdminPageUI(this, "");
			f.setVisible(false);
		} else {
			boolean result = fm.login(id, pass);
			if (result) {
				JOptionPane.showMessageDialog(null, "로그인 성공했습니다");
				new LoginPageUI(this, "");
				f.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "아이디 또는 패스워드가 일치하지 않습니다");
				tf_id.requestFocus();
			}
		}
	}

	/* 버튼 이벤트 */
	class foodEvent extends WindowAdapter implements ActionListener {
		MainUI ui;

		public foodEvent(MainUI ui) {
			this.ui = ui;
		}

		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}

		public void actionPerformed(ActionEvent e) {
			String name = e.getActionCommand().trim();
			Object obj = e.getSource();

			if (name.equals("로그인") || obj == ui.tf_pass) {
				ui.loginProc();
			} else if (name.equals("회원가입")) {
				new JoinUI(ui);
			}

		}
	}

}
