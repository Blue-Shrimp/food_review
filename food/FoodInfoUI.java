package food;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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

public class FoodInfoUI {

	MainUI ui;
	LoginPageUI lp;
	AdminPageUI ap;

	Frame f, ad_f;
	String name, id;
	JLabel jl_n, jl_l, jl_f, jl_t;
	FoodVO vo;
	JTextArea jta;
	JComboBox<String> combo;
	String[] star = { "별점선택", "★★★★★", "★★★★", "★★★", "★★", "★" };
	ReviewVO re_vo;

	Object[] columns = { "번호", "아이디", "리뷰내용", "평점" };
	DefaultTableModel model = new DefaultTableModel(columns, 0) {
		public boolean isCellEditable(int i, int c) { // 셀 수정 불가
			return false;
		}
	};
	Object[] row = new Object[4];
	JTable table;
	JScrollPane scroll_pane;
	String m_name, ad_m_name;
	JButton jbn, join_b, ad_jbn, ad_join_b;

	/* 사용자 음식정보 */
	public FoodInfoUI(MainUI ui, LoginPageUI lp, String id, String name) {
		this.ui = ui;
		this.lp = lp;
		this.id = id;
		this.name = name;
		info();

	}

	/* 관리자 음식정보 */
	public FoodInfoUI(MainUI ui, AdminPageUI ap, String id, String name) {
		this.ui = ui;
		this.ap = ap;
		this.id = id;
		this.name = name;
		ad_info();

	}

	public void info() {
		m_name = ui.fm.name(MainUI.tf_id.getText());
		f = new Frame(" ");
		Font font = new Font("맑은 고딕", Font.BOLD, 13);
		Panel panel = new Panel();
		ImageIcon img = new ImageIcon("src/food/home.png");
		jbn = new JButton(img);
		JButton jbn3 = new JButton("즐겨찾기");
		join_b = new JButton("로그아웃");
		JButton jbn_r = new JButton("등록");
		JButton jbn_c = new JButton("초기화");
		JLabel l = new JLabel(m_name + "님 반갑습니다.");
		combo = new JComboBox<>(star);
		jta = new JTextArea(200, 50);
		jta.append("후기를 남겨주세요");
		jta.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				jta.setText(""); // 커서 이동시 삭제
			}

			public void focusLost(FocusEvent e) {

			}
		});

		jbn3.setFont(font);
		join_b.setFont(font);
		jbn_r.setFont(font);
		join_b.setFont(font);
		jbn_c.setFont(font);
		l.setFont(font);

		jbn.setBackground(new Color(189, 236, 182));
		jbn3.setBackground(new Color(189, 236, 182));
		join_b.setBackground(new Color(189, 236, 182));
		jbn_r.setBackground(new Color(189, 236, 182));
		jbn_c.setBackground(new Color(189, 236, 182));

		vo = ui.fm.upload(id);

		ImageIcon image = new ImageIcon("src\\food\\" + vo.getSimg());
		JLabel jl = imageSetSize(image);

		jl_n = new JLabel("식당이름 :  " + vo.getName());
		jl_l = new JLabel("위치이름 :  " + vo.getLoc());
		jl_t = new JLabel("음식종류 :  " + vo.getKind());
		jl_f = new JLabel("식당번호 :  " + vo.getPhone());

		jl_n.setFont(font);
		jl_l.setFont(font);
		jl_t.setFont(font);
		jl_f.setFont(font);

		jbn.setBounds(30, 35, 50, 50);
		jbn3.setBounds(650, 90, 100, 30);
		l.setBounds(500, 35, 150, 30);
		join_b.setBounds(650, 35, 100, 30);
		jl.setBounds(200, 90, 200, 200);
		jbn_r.setBounds(598, 440, 72, 30);
		jbn_c.setBounds(678, 440, 72, 30);

		jl_n.setBounds(430, 85, 200, 30);
		jl_l.setBounds(430, 125, 250, 30);
		jl_t.setBounds(430, 165, 150, 30);
		jl_f.setBounds(430, 205, 200, 30);

		combo.setBounds(650, 300, 100, 30);
		jta.setBounds(200, 350, 550, 80);
		jta.setBackground(Color.LIGHT_GRAY);

		showList();
		scroll_pane.setBounds(200, 480, 550, 100);

		f.add(scroll_pane);

		f.add(jbn_r);
		f.add(jbn_c);

		f.add(jl_n);
		f.add(jl_l);
		f.add(jl_t);
		f.add(jl_f);

		f.add(combo);
		f.add(jta);

		f.add(jbn);
		f.add(jl);
		f.add(jbn3);
		f.add(join_b);
		f.add(l);
		f.add(panel);

		f.setSize(800, 600);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setVisible(true);

		foodInfoEvent fie = new foodInfoEvent(this);
		f.addWindowListener(fie);
		jbn.addActionListener(fie);
		jbn3.addActionListener(fie);
		jbn_r.addActionListener(fie);
		jbn_c.addActionListener(fie);
		join_b.addActionListener(fie);
		searchEvent se = new searchEvent(this);
		combo.addActionListener(se);

	}

	public void ad_info() {
		ad_m_name = ui.fm.name(MainUI.tf_id.getText());
		ad_f = new Frame(" ");
		Font font = new Font("맑은 고딕", Font.BOLD, 13);
		Panel panel = new Panel();
		ImageIcon img = new ImageIcon("src/food/home.png");
		ad_jbn = new JButton(img);
		ad_join_b = new JButton("로그아웃");
		JLabel l = new JLabel(MainUI.tf_id.getText() + "님 반갑습니다.");

		ad_join_b.setFont(font);
		l.setFont(font);

		ad_jbn.setBackground(new Color(189, 236, 182));
		ad_join_b.setBackground(new Color(189, 236, 182));

		vo = ui.fm.upload(id);

		ImageIcon image = new ImageIcon("src\\food\\" + vo.getSimg());
		JLabel jl = imageSetSize(image);

		jl_n = new JLabel("식당이름 :  " + vo.getName());
		jl_l = new JLabel("위치이름 :  " + vo.getLoc());
		jl_t = new JLabel("음식종류 :  " + vo.getKind());
		jl_f = new JLabel("식당번호 :  " + vo.getPhone());

		jl_n.setFont(font);
		jl_l.setFont(font);
		jl_t.setFont(font);
		jl_f.setFont(font);

		ad_jbn.setBounds(30, 35, 50, 50);
		l.setBounds(500, 35, 150, 30);
		ad_join_b.setBounds(650, 35, 100, 30);
		jl.setBounds(200, 110, 200, 200);

		jl_n.setBounds(430, 100, 200, 30);
		jl_l.setBounds(430, 140, 250, 30);
		jl_t.setBounds(430, 180, 150, 30);
		jl_f.setBounds(430, 220, 200, 30);

		showList();
		scroll_pane.setBounds(200, 370, 550, 200);

		ad_f.add(scroll_pane);

		ad_f.add(jl_n);
		ad_f.add(jl_l);
		ad_f.add(jl_t);
		ad_f.add(jl_f);

		ad_f.add(ad_jbn);
		ad_f.add(jl);
		ad_f.add(ad_join_b);
		ad_f.add(l);
		ad_f.add(panel);

		ad_f.setSize(800, 600);
		ad_f.setLocationRelativeTo(null);
		ad_f.setResizable(false);
		ad_f.setVisible(true);

		foodInfoEvent fie = new foodInfoEvent(this);
		ad_f.addWindowListener(fie);
		ad_jbn.addActionListener(fie);
		ad_join_b.addActionListener(fie);

	}

	/* 작성된 리뷰 행 생성 */
	public void createRow() {
		table = new JTable(model);
		model.setNumRows(0);
		ArrayList<ReviewVO> list = ui.fm.reviewlist(id);
		for (ReviewVO review : list) {

			row[0] = review.getRno();
			row[1] = review.getName();
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

			model.addRow(row);

		}

	}

	/* 작성된 리뷰 테이블 생성 */
	public void showList() {

		createRow();

		scroll_pane = new JScrollPane(table);

		DefaultTableCellRenderer cell = new DefaultTableCellRenderer();
		cell.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setPreferredWidth(1);
		table.getColumnModel().getColumn(1).setPreferredWidth(10);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(20);

		table.getColumn("번호").setCellRenderer(cell);
		table.getColumn("아이디").setCellRenderer(cell);
		table.getColumn("리뷰내용").setCellRenderer(cell);
		table.getColumn("평점").setCellRenderer(cell);

		table.setRowHeight(table.getRowHeight() + 10);

		table.getTableHeader().setReorderingAllowed(false); // 컬럼 이동고정
		table.getTableHeader().setResizingAllowed(false); // 컬럼 사이즈 고정

		table.setShowVerticalLines(false); // 수직 선 안보이게

	}

	/* 버튼 이벤트 */
	class foodInfoEvent extends WindowAdapter implements ActionListener {

		FoodInfoUI fi;

		public foodInfoEvent(FoodInfoUI fi) {
			this.fi = fi;
		}

		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}

		public void actionPerformed(ActionEvent e) {
			String name2 = e.getActionCommand();
			Object obj = e.getSource();

			if (name2 == "등록") {
				if (re_vo != null && re_vo.getGrade() != 0) {
					int aa = re_vo.getGrade();
					if (jta.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "리뷰를 작성해주세요");
						jta.requestFocus();
					} else {
						if (ui.fm.setReview(jta.getText(), aa, id, MainUI.tf_id.getText())) {
							JOptionPane.showMessageDialog(null, "등록되었습니다");
							jta.setText("후기를 남겨주세요");
							combo.setSelectedIndex(0);
							new FoodInfoUI(ui, lp, id, name);
							f.setVisible(false);
						} else
							JOptionPane.showMessageDialog(null, "등록실패");

					}

				} else {
					JOptionPane.showMessageDialog(null, "별점을 등록해주세요");
				}

			} else if (name2 == "초기화") {
				jta.setText("");
				combo.setSelectedIndex(0);
			} else if (obj == jbn) {
				new LoginPageUI(ui, "");
				f.setVisible(false);
			} else if (obj == join_b) {
				int count = JOptionPane.showConfirmDialog(null, "로그아웃하시겠습니까");
				if (count == 0) {
					f.setVisible(false);
					new MainUI();
				}
			} else if (name2 == "즐겨찾기") {
				if (ui.fm.like_check(MainUI.tf_id.getText(), vo.getId())) {
					JOptionPane.showMessageDialog(null, "즐겨찾기 목록에 있는 식당입니다.");
				} else {
					if (ui.fm.s_like(MainUI.tf_id.getText(), vo.getId())) {
						JOptionPane.showMessageDialog(null, "즐겨찾기에 등록되었습니다.");
						new FoodInfoUI(ui, lp, id, name);
						f.setVisible(false);
					}
				}
			} else if (obj == ad_jbn) {
				new AdminPageUI(ui, "");
				ad_f.setVisible(false);
			} else if (obj == ad_join_b) {
				int count = JOptionPane.showConfirmDialog(null, "로그아웃하시겠습니까");
				if (count == 0) {
					ad_f.setVisible(false);
					new MainUI();
				}
			}
		}
	}

	/* 콤보박스 이벤트 */
	class searchEvent implements ActionListener {

		FoodInfoUI fi;

		public searchEvent(FoodInfoUI fi) {
			this.fi = fi;
		}

		public void actionPerformed(ActionEvent e) {
			re_vo = new ReviewVO();
			JComboBox cd = (JComboBox) e.getSource();
			String list = (String) cd.getSelectedItem();

			if (list.equals("★★★★★")) {
				re_vo.setGrade(5);
			} else if (list.equals("★★★★")) {
				re_vo.setGrade(4);
			} else if (list.equals("★★★")) {
				re_vo.setGrade(3);
			} else if (list.equals("★★")) {
				re_vo.setGrade(2);
			} else if (list.equals("★")) {
				re_vo.setGrade(1);
			}

		}
	}

	/* 이미지 크기 지정 */
	public JLabel imageSetSize(ImageIcon image) {
		Image img = image.getImage();
		Image change = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		JLabel lbl = new JLabel(new ImageIcon(change));
		return lbl;
	}

}
