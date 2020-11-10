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
	Object[] columns = { "번호", "음식점", "리뷰내용", "평점", "작성일자", "수정", "삭제" };
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
	String[] star = { "별점선택", "★★★★★", "★★★★", "★★★", "★★", "★" };
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
		Font font = new Font("맑은 고딕", Font.BOLD, 13);
		Font font2 = new Font("맑은 고딕", Font.BOLD, 17);
		m_name = ui.fm.name(MainUI.tf_id.getText());
		JLabel label = new JLabel("마이리뷰");
		Panel panel = new Panel();
		ImageIcon img = new ImageIcon("src/food/home.png");
		jbn = new JButton(img);
		JButton join_b = new JButton("로그아웃");
		JLabel l = new JLabel(m_name + "님 반갑습니다.");

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
			JOptionPane.showMessageDialog(null, "작성된 리뷰가 없습니다");
			f.setVisible(false);
			new LoginPageUI(ui, "");
		}
	}

	/* 버튼 이벤트 */
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
			} else if (name == "로그아웃") {
				int count = JOptionPane.showConfirmDialog(null, "로그아웃하시겠습니까");
				if (count == 0) {
					f.setVisible(false);
					new MainUI();
				}
			}
		}
	}

	/* 내가 쓴 리뷰 행 생성 */
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

	/* 내가 쓴 리뷰 리스트 테이블 생성 */
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

			table.getColumn("번호").setCellRenderer(cell);
			table.getColumn("음식점").setCellRenderer(cell);
			table.getColumn("리뷰내용").setCellRenderer(cell);
			table.getColumn("평점").setCellRenderer(cell);
			table.getColumn("작성일자").setCellRenderer(cell);
			table.getColumn("수정").setCellRenderer(cell2);
			table.getColumn("삭제").setCellRenderer(cell2);

			table.setRowHeight(table.getRowHeight() + 10);

			table.getTableHeader().setReorderingAllowed(false); // 컬럼 이동고정
			table.getTableHeader().setResizingAllowed(false); // 컬럼 사이즈 고정

			table.setShowVerticalLines(false); // 수직 선 안보이게

		} else {
			a = 1;
		}

		return a;

	}

	/* 마우스 이벤트 */
	class myReviewMouseEvent extends MouseAdapter {
		MyReviewUI mr;

		public myReviewMouseEvent(MyReviewUI mr) {
			this.mr = mr;
		}

		public void mouseClicked(MouseEvent e) {
			column = table.getColumnModel().getColumnIndexAtX(e.getX()); // 열
			row2 = e.getY() / table.getRowHeight(); // 행

			rid = list.get(row2).getRid();

			if (column == 5) {
				updateForm(rid);
			} else if (column == 6) {
				int count = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?");
				if (count == 0) {
					if (ui.fm.reviewDelete(rid)) {
						JOptionPane.showMessageDialog(null, "삭제되었습니다.");
						new MyReviewUI(ui, lp, id);
						f.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "삭제실패했습니다.");
					}
				}
			}

		}
	}

	/* 리뷰 수정 폼 */
	public void updateForm(String rid) {
		f1 = new Frame(" ");

		content_panel = new Panel(new BorderLayout());
		Panel lable_panel = new Panel();
		Panel tf_panel = new Panel();
		Panel combo_panel = new Panel();
		Panel btn_panel = new Panel();

		Font font = new Font("맑은 고딕", Font.BOLD, 13);
		jta = new JTextArea(15, 18);
		jta.setBackground(Color.LIGHT_GRAY);
		jta.setText(list.get(row2).getText());
		Label label = new Label("수정할 리뷰 내용와 별점을 입력해주세요");
		combo = new JComboBox<>(star);
		combo.setSelectedIndex(0);
		JButton btn = new JButton("수정");
		JButton btn1 = new JButton("초기화");

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

	/* 버튼 이벤트 */
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
			if (name.equals("수정")) {
				if (re_vo != null && re_vo.getGrade() != 0) {
					int gra = re_vo.getGrade();
					if (jta.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "리뷰를 작성해주세요");
						jta.requestFocus();
					} else {
						if (ui.fm.updateReview(rid, gra, jta.getText())) {
							JOptionPane.showMessageDialog(null, "수정되었습니다");
							new MyReviewUI(ui, lp, id);
							f.setVisible(false);
							f1.setVisible(false);
						} else
							JOptionPane.showMessageDialog(null, "수정실패되었습니다");

					}

				} else {
					JOptionPane.showMessageDialog(null, "별점을 등록해주세요");
				}
			} else if (name.equals("초기화")) {
				jta.setText("");
				combo.setSelectedIndex(0);
			}

		}
	}

	/* 콤보박스 이벤트 */
	class searchEvent implements ActionListener {

		MyReviewUI mr;

		public searchEvent(MyReviewUI mr) {
			this.mr = mr;
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

}
