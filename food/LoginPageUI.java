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
	String[] list = { "이름순", "평점순", "한식", "중식", "경양식", "일식", "기타" };

	Object[] columns = { "번호", "이미지", "음식점명", "음식종류", "전화번호", "평점" };
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
		Font font = new Font("맑은 고딕", Font.BOLD, 13);

		JButton jbn1 = new JButton("마이페이지");
		JButton jbn2 = new JButton("마이리뷰");
		JButton jbn3 = new JButton("즐겨찾기");
		ImageIcon img = new ImageIcon("src/food/home.png");
		jbn = new JButton(img);
		JButton join_b = new JButton("로그아웃");
		JButton search_b = new JButton("검색");
		JLabel l = new JLabel(ui.fm.name(MainUI.tf_id.getText()) + "님 반갑습니다.");

		ImageIcon image = new ImageIcon("src/food/로고2.png");
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
		jtf.setText("찾는 음식점명");
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
		} else if (type.equals("평점순")) {
			combo.setSelectedIndex(1);
		} else if (type.equals("한식")) {
			combo.setSelectedIndex(2);
		} else if (type.equals("중식")) {
			combo.setSelectedIndex(3);
		} else if (type.equals("경양식")) {
			combo.setSelectedIndex(4);
		} else if (type.equals("일식")) {
			combo.setSelectedIndex(5);
		} else if (type.equals("기타")) {
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
			JOptionPane.showMessageDialog(null, "검색된 결과가 없습니다");
			setVisible(false);
			new LoginPageUI(ui, "");
		}
	}

	/* 음식점 리스트 */
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

			table.getColumn("번호").setCellRenderer(cell);
			table.getColumn("음식점명").setCellRenderer(cell);
			table.getColumn("음식종류").setCellRenderer(cell);
			table.getColumn("위치").setCellRenderer(cell);
			table.getColumn("전화번호").setCellRenderer(cell);
			table.getColumn("평점").setCellRenderer(cell);

			table.setRowHeight(table.getRowHeight() + 70);

			table.getTableHeader().setReorderingAllowed(false); // 컬럼 이동고정
			table.getTableHeader().setResizingAllowed(false); // 컬럼 사이즈 고정

			table.setShowVerticalLines(false); // 수직 선 안보이게

		} else {
			a = 1;
		}

		return a;

	}

	/* 버튼 이벤트 */
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

			} else if (name == "로그아웃") {
				int count = JOptionPane.showConfirmDialog(null, "로그아웃하시겠습니까");
				if (count == 0) {
					setVisible(false);
					new MainUI();
				}
			} else if (name == "마이페이지") {
				new MyPageUI(ui);
				setVisible(false);
			} else if (name == "검색" || obj == jtf) {

				new LoginPageUI(ui, jtf.getText());
				setVisible(false);
			} else if (name == "마이리뷰") {
				new MyReviewUI(ui, lp, MainUI.tf_id.getText());
				setVisible(false);
			} else if (name == "즐겨찾기") {
				new StoreLikeUI(ui, lp);
			}
		}
	}

	/* 마우스 이벤트 */
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

	/* 콤보박스 이벤트 */
	class lpsearchEvent implements ActionListener {
		LoginPageUI lp;

		public lpsearchEvent(LoginPageUI lp) {
			this.lp = lp;
		}

		public void actionPerformed(ActionEvent e) {

			JComboBox cd = (JComboBox) e.getSource();
			String list = (String) cd.getSelectedItem();

			if (list.equals("한식")) {
				new LoginPageUI(lp.ui, list);
				lp.setVisible(false);
			} else if (list.equals("일식")) {
				new LoginPageUI(lp.ui, list);
				lp.setVisible(false);
			} else if (list.equals("중식")) {
				new LoginPageUI(lp.ui, list);
				lp.setVisible(false);
			} else if (list.equals("경양식")) {
				new LoginPageUI(lp.ui, list);
				lp.setVisible(false);
			} else if (list.equals("기타")) {
				new LoginPageUI(lp.ui, list);
				lp.setVisible(false);
			} else if (list.equals("평점순")) {
				new LoginPageUI(lp.ui, list);
				lp.setVisible(false);
			} else if (list.equals("이름순")) {
				new LoginPageUI(lp.ui, list);
				lp.setVisible(false);
			}

		}
	}

	/* 음시정보 조회 */
	public void showInfo(String name) {

		ArrayList<FoodVO> list = ui.fm.list();
		for (FoodVO vo : list) {
			if (vo.getName().equals(name)) {
				String sid = vo.getId();
				new FoodInfoUI(ui, this, sid, name);

			}
		}

	}

	/* 이미지 사이즈 지정 */
	public JLabel imageSetSize(ImageIcon image) {
		Image img = image.getImage();
		Image change = img.getScaledInstance(300, 80, Image.SCALE_SMOOTH);
		JLabel lbl = new JLabel(new ImageIcon(change));
		return lbl;
	}
}
