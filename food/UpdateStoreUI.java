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

public class UpdateStoreUI {

	MainUI ui;
	AdminPageUI ap;
	String sid, sname;
	Frame f;
	JButton btn;
	FoodVO vo;
	JTextField jt_name, jt_loc, jt_pn1, jt_pn2, jt_pn3, jt_img;
	JComboBox<String> combo;
	String[] list = { "", "한식", "중식", "경양식", "일식", "기타" };
	ArrayList<JTextField> textlist;
	String[] names = { "음식점명", "음식점위치", "식당번호", "식당번호", "식당번호" };

	public UpdateStoreUI(MainUI ui, AdminPageUI ap, String sid, String sname) {
		this.ui = ui;
		this.ap = ap;
		this.sid = sid;
		this.sname = sname;

		updateForm();
	}

	public void updateForm() {
		f = new Frame(" ");
		Font font = new Font("맑은 고딕", Font.BOLD, 13);
		Font font2 = new Font("맑은 고딕", Font.BOLD, 17);
		Panel panel = new Panel();
		ImageIcon img = new ImageIcon("src/food/home.png");
		btn = new JButton(img);
		JButton join_b = new JButton("로그아웃");
		JButton search = new JButton("찾기");
		JButton update = new JButton("수정");
		JButton cancel = new JButton("초기화");
		JLabel l = new JLabel(MainUI.tf_id.getText() + "님 반갑습니다.");
		JLabel l2 = new JLabel("음식점 정보 수정");

		join_b.setFont(font);
		search.setFont(font);
		update.setFont(font);
		cancel.setFont(font);
		l.setFont(font);
		l2.setFont(font2);

		btn.setBackground(new Color(189, 236, 182));
		join_b.setBackground(new Color(189, 236, 182));
		search.setBackground(new Color(189, 236, 182));
		update.setBackground(new Color(189, 236, 182));
		cancel.setBackground(new Color(189, 236, 182));

		vo = ui.fm.upload(sid);

		ImageIcon image = new ImageIcon("src\\food\\" + vo.getSimg());
		JLabel jl = imageSetSize(image);

		JLabel jl_n = new JLabel("음식점명");
		JLabel jl_l = new JLabel("음식점위치");
		JLabel jl_f = new JLabel("식당번호");
		JLabel jl_t = new JLabel("음식종류");
		JLabel jl_i = new JLabel("이미지");
		JLabel jl_s = new JLabel("(100x100)");
		JLabel a1 = new JLabel("-");
		JLabel a2 = new JLabel("-");

		textlist = new ArrayList<>();
		jt_name = new JTextField(10);
		textlist.add(jt_name);
		jt_loc = new JTextField(10);
		textlist.add(jt_loc);
		jt_pn1 = new JTextField(2);
		textlist.add(jt_pn1);
		jt_pn2 = new JTextField(4);
		textlist.add(jt_pn2);
		jt_pn3 = new JTextField(4);
		textlist.add(jt_pn3);
		combo = new JComboBox<String>(list);
		jt_img = new JTextField(10);

		jt_name.setText(vo.getName());
		jt_loc.setText(vo.getLoc());
		if (vo.getPhone().length() == 11) {
			jt_pn1.setText(vo.getPhone().substring(0, 2));
			jt_pn2.setText(vo.getPhone().substring(3, 6));
			jt_pn3.setText(vo.getPhone().substring(7, 11));

		} else if (vo.getPhone().length() == 12) {
			jt_pn1.setText(vo.getPhone().substring(0, 2));
			jt_pn2.setText(vo.getPhone().substring(3, 7));
			jt_pn3.setText(vo.getPhone().substring(8, 12));

		}
		if (vo.getKind().equals("한식")) {
			combo.setSelectedIndex(1);
		} else if (vo.getKind().equals("중식")) {
			combo.setSelectedIndex(2);
		} else if (vo.getKind().equals("경양식")) {
			combo.setSelectedIndex(3);
		} else if (vo.getKind().equals("일식")) {
			combo.setSelectedIndex(4);
		} else if (vo.getKind().equals("기타")) {
			combo.setSelectedIndex(5);
		}
		jt_img.setText(vo.getSimg());
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
		a1.setBounds(472, 255, 5, 20);
		jt_pn2.setBounds(485, 255, 70, 20);
		a2.setBounds(557, 255, 5, 20);
		jt_pn3.setBounds(570, 255, 70, 20);
		combo.setBounds(430, 315, 210, 20);
		jt_img.setBounds(430, 375, 150, 20);
		search.setBounds(582, 375, 60, 20);

		update.setBounds(300, 450, 100, 30);
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

		f.add(update);
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
		update.addActionListener(use);
		cancel.addActionListener(use);
	}

	/* 버튼 이벤트 */
	class updateStoreEvent extends WindowAdapter implements ActionListener {

		UpdateStoreUI us;

		public updateStoreEvent(UpdateStoreUI us) {
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
			} else if (name == "찾기") {
				new test_Frame();
			} else if (name == "수정") {
				if (validCheck()) {
					String num = jt_pn1.getText() + jt_pn2.getText() + jt_pn3.getText();
					if (num.length() == 9 || num.length() == 10) { // 전화번호 9자리, 10자리로 지정
						updateProc();

					} else {
						JOptionPane.showMessageDialog(null, "전화번호 9자리 혹은 10자리를 입력해주세요");
					}

				}
			} else if (name == "초기화") {
				jt_name.setText("");
				jt_loc.setText("");
				combo.setSelectedIndex(0);
				jt_pn1.setText("");
				jt_pn2.setText("");
				jt_pn3.setText("");
			}

		}

	}

	/* 이미지 사이즈 지정 */
	public JLabel imageSetSize(ImageIcon image) {
		Image img = image.getImage();
		Image change = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		JLabel lbl = new JLabel(new ImageIcon(change));
		return lbl;
	}

	/* pc 경로에서 이미지 첨부 */
	class test_Frame extends JFrame implements ActionListener {

		JFileChooser jfc = new JFileChooser(" ");
		JButton jbt_save = new JButton("저장");

		public test_Frame() {

			jfc.setCurrentDirectory(new File("src/food")); // default 경로
			jfc.setFileFilter(new FileNameExtensionFilter("png & jpg", "png", "jpg")); // png, jpg 확장자
			jfc.setMultiSelectionEnabled(false); // 중복 선택 불가

			if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				jt_img.setText(jfc.getSelectedFile().getName()); // 이미지 이름 텍스트필드에 저장
			}

			jbt_save.addActionListener(this);
		}

		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == jbt_save) {
				if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
					jt_img.setText(jfc.getSelectedFile().getName());
				}
			}
		}
	}

	/* 음식점 수정 처리 */
	public void updateProc() {
		FoodVO vo = new FoodVO();

		vo.setId(sid);
		vo.setName(jt_name.getText());
		vo.setLoc(jt_loc.getText());
		vo.setKind((String) combo.getSelectedItem());
		vo.setPhone(jt_pn1.getText() + "-" + jt_pn2.getText() + "-" + jt_pn3.getText());
		vo.setSimg(jt_img.getText());

		if (ui.fm.updateStore(vo)) {
			JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");
		} else {
			JOptionPane.showMessageDialog(null, "수정이 실패되었습니다.");
		}
	}

	/* null 값 체크 */
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
			if (no == 5) {
				if ((String) combo.getSelectedItem() == "") {
					JOptionPane.showMessageDialog(null, "음식종류을(를)  입력해주세요");
					result = false;
				}

			}
		}

		return result;
	}

}
