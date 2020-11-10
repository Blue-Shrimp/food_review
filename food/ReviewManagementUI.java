package food;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ReviewManagementUI {

	MainUI ui;
	AdminPageUI ap;
	Frame f;
	JTextField jtf;
	JButton btn;
	String[] star = { "★★★★★", "★★★★", "★★★", "★★", "★" };
	Object[] columns = { "번호", "음식점명", "아이디", "리뷰내용", "평점", "날짜" };
	DefaultTableModel model = new DefaultTableModel(columns, 0) {
		public boolean isCellEditable(int i, int c) {
			return false;
		}
	};
	Object[] row;
	JTable table;
	JScrollPane scroll_pane;
	int column, row2;
	ArrayList<ReviewVO> list;
	String mid, word;

	public ReviewManagementUI(MainUI ui, AdminPageUI ap, String word) {
		this.ui = ui;
		this.ap = ap;
		this.word = word; // 검색 키워드
		reviewForm();
	}

	public void reviewForm() {

		f = new Frame(" ");
		Font font = new Font("맑은 고딕", Font.BOLD, 13);
		Font font2 = new Font("맑은 고딕", Font.BOLD, 17);
		Panel panel = new Panel();
		ImageIcon img = new ImageIcon("src/food/home.png");
		btn = new JButton(img);
		JButton join_b = new JButton("로그아웃");
		JButton search_b = new JButton("검색");
		JLabel l = new JLabel(MainUI.tf_id.getText() + "님 반갑습니다.");
		JLabel l2 = new JLabel("리뷰 관리");

		join_b.setFont(font);
		l.setFont(font);
		l2.setFont(font2);
		search_b.setFont(font);

		btn.setBackground(new Color(189, 236, 182));
		join_b.setBackground(new Color(189, 236, 182));
		search_b.setBackground(new Color(189, 236, 182));

		jtf = new JTextField(30);
		jtf.setText("찾는 음식점명 & 아이디");
		jtf.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				jtf.setText("");
			}

			public void focusLost(FocusEvent e) {

			}
		});
		jtf.setBounds(152, 122, 160, 25);
		search_b.setBounds(320, 122, 70, 25);

		btn.setBounds(30, 35, 50, 50);
		l.setBounds(608, 35, 150, 30);
		l2.setBounds(152, 90, 150, 30);
		join_b.setBounds(778, 35, 100, 30);

		if (showList() == 0) {
			scroll_pane.setBounds(150, 150, 730, 400);

			f.add(jtf);
			f.add(search_b);
			f.add(scroll_pane);
			f.add(btn);
			f.add(join_b);
			f.add(l);
			f.add(l2);
			f.add(panel);

			f.setSize(900, 600);
			f.setLocationRelativeTo(null);
			f.setResizable(false);
			f.setVisible(true);

			reviewManagementEvent mme = new reviewManagementEvent(this);
			f.addWindowListener(mme);
			btn.addActionListener(mme);
			join_b.addActionListener(mme);
			jtf.addActionListener(mme);
			search_b.addActionListener(mme);

		} else {
			JOptionPane.showMessageDialog(null, "검색된 리뷰가 없습니다");
			f.setVisible(false);
			new ReviewManagementUI(ui, ap, "");
		}
	}

	/* 리뷰 행 생성 */
	public void createRow() {
		row = new Object[30];
		table = new JTable(model);
		model.setNumRows(0);
		list = ui.fm.listReview(word);
		for (ReviewVO review : list) {
			row[0] = review.getRno();
			row[1] = review.getSname();
			row[2] = review.getMid();
			row[3] = review.getText();
			int a = review.getGrade();
			if (a == 5) {
				row[4] = star[0];
			}
			if (a == 4) {
				row[4] = star[1];
			}
			if (a == 3) {
				row[4] = star[2];
			}
			if (a == 2) {
				row[4] = star[3];
			}
			if (a == 1) {
				row[4] = star[4];
			}
			row[5] = review.getDate();

			model.addRow(row);

		}
		table.repaint();
		model.fireTableDataChanged();

	}

	/* 리뷰 리스트 테이블 생성 */
	public int showList() {

		int a = 0;

		createRow();

		if (model.getRowCount() != 0) {
			scroll_pane = new JScrollPane(table);

			DefaultTableCellRenderer cell = new DefaultTableCellRenderer();
			cell.setHorizontalAlignment(SwingConstants.CENTER);
			table.getColumnModel().getColumn(0).setPreferredWidth(1);
			table.getColumnModel().getColumn(1).setPreferredWidth(70);
			table.getColumnModel().getColumn(2).setPreferredWidth(50);
			table.getColumnModel().getColumn(3).setPreferredWidth(150);
			table.getColumnModel().getColumn(4).setPreferredWidth(25);
			table.getColumnModel().getColumn(5).setPreferredWidth(30);

			table.getColumn("번호").setCellRenderer(cell);
			table.getColumn("음식점명").setCellRenderer(cell);
			table.getColumn("아이디").setCellRenderer(cell);
			table.getColumn("리뷰내용").setCellRenderer(cell);
			table.getColumn("평점").setCellRenderer(cell);
			table.getColumn("날짜").setCellRenderer(cell);

			table.setRowHeight(table.getRowHeight() + 10);

			table.getTableHeader().setReorderingAllowed(false); // 컬럼 이동고정
			table.getTableHeader().setResizingAllowed(false); // 컬럼 사이즈 고정

			table.setShowVerticalLines(false); // 수직 선 안보이게

		} else {
			a = 1;
		}

		return a;

	}

	/* 버튼 이벤트 */
	class reviewManagementEvent extends WindowAdapter implements ActionListener {

		ReviewManagementUI rm;

		public reviewManagementEvent(ReviewManagementUI rm) {
			this.rm = rm;
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
			} else if (name == "로그아웃") {
				int count = JOptionPane.showConfirmDialog(null, "로그아웃하시겠습니까");
				if (count == 0) {
					f.setVisible(false);
					new MainUI();
				}
			} else if (name == "검색" || obj == jtf) {
				new ReviewManagementUI(ui, ap, jtf.getText());
				f.setVisible(false);
			}

		}

	}

}
