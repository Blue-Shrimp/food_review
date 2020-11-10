package food;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class StoreLikeUI {

	MainUI ui;
	LoginPageUI lp;
	Frame f;
	Object[] columns = { "번호", "음식점명", "음식종류", "주소", "전화번호", "삭제" };
	DefaultTableModel model = new DefaultTableModel(columns, 0);
	Object[] row = new Object[7];
	JTable table;
	JScrollPane scroll_pane;

	public StoreLikeUI(MainUI ui, LoginPageUI lp) {
		this.ui = ui;
		this.lp = lp;
		showList();
		likeUI();
	}

	public void likeUI() {
		f = new Frame(" ");
		Font font = new Font("맑은 고딕", Font.BOLD, 17);
		String m_name = ui.fm.name(MainUI.tf_id.getText());
		JLabel title = new JLabel(m_name + "님의 즐겨찾기 목록");

		title.setFont(font);

		title.setBounds(100, 50, 250, 50);

		if (showList() == 0) {
			scroll_pane.setBounds(100, 100, 550, 300);
			f.add(title);
			f.add(scroll_pane);

			f.add(new Panel());
			f.setSize(700, 500);
			f.setLocationRelativeTo(null);
			f.setResizable(false);
			f.setVisible(true);

			storeLikeUIEvent slu = new storeLikeUIEvent(this);
			f.addWindowListener(slu);
			mainMouseEvent mme = new mainMouseEvent(this);
			table.addMouseListener(mme);

		} else {
			JOptionPane.showMessageDialog(null, "즐겨찾기가 없습니다");
			lp.setVisible(false);
			f.setVisible(false);
			new LoginPageUI(ui, "");
		}

	}

	/* 즐겨찾기 행 생성 */
	public void createRow() {
		table = new JTable(model);
		model.setNumRows(0);
		ArrayList<FoodVO> list = ui.fm.likeList(MainUI.tf_id.getText());
		for (FoodVO food : list) {
			row[0] = food.getRno();
			row[1] = food.getName();
			row[2] = food.getKind();
			row[3] = food.getLoc();
			row[4] = food.getPhone();

			model.addRow(row);
		}

	}

	/* 즐겨찾기 테이블 생성 */
	public int showList() {

		int a = 0;

		createRow();
		if (model.getRowCount() != 0) {
			scroll_pane = new JScrollPane(table);

			DefaultTableCellRenderer cell = new DefaultTableCellRenderer();
			DefaultTableCellRenderer cell2 = new MyDefaultTableCellRenderer2();
			cell.setHorizontalAlignment(SwingConstants.CENTER);
			table.getColumnModel().getColumn(0).setPreferredWidth(1);
			table.getColumnModel().getColumn(1).setPreferredWidth(80);
			table.getColumnModel().getColumn(2).setPreferredWidth(20);
			table.getColumnModel().getColumn(3).setPreferredWidth(100);
			table.getColumnModel().getColumn(4).setPreferredWidth(50);
			table.getColumnModel().getColumn(5).setPreferredWidth(20);

			table.getColumn("번호").setCellRenderer(cell);
			table.getColumn("음식점명").setCellRenderer(cell);
			table.getColumn("음식종류").setCellRenderer(cell);
			table.getColumn("주소").setCellRenderer(cell);
			table.getColumn("전화번호").setCellRenderer(cell);
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

	/* 버튼 이벤트 */
	class storeLikeUIEvent extends WindowAdapter implements ActionListener {

		StoreLikeUI slu;

		public storeLikeUIEvent(StoreLikeUI slu) {
			this.slu = slu;
		}

		public void windowClosing(WindowEvent e) {
			f.setVisible(false);
		}

		public void actionPerformed(ActionEvent e) {

		}

	}

	/* 마우스 이벤트 */
	class mainMouseEvent extends MouseAdapter {
		StoreLikeUI slu;

		public mainMouseEvent(StoreLikeUI slu) {
			this.slu = slu;
		}

		public void mouseClicked(MouseEvent e) {
			int result = e.getButton();

			if (result == 1) {
				int column = table.getSelectedColumn(); // 열
				if (column == 5) {
					ArrayList<FoodVO> list = ui.fm.likeList(MainUI.tf_id.getText());
					int row = table.getSelectedRow(); // 행
					String sname = (String) table.getValueAt(row, 1);

					for (FoodVO vo : list) {
						if (vo.getName().equals(sname)) {

							int count = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?");
							if (count == 0) {
								if (ui.fm.like_delete(MainUI.tf_id.getText(), vo.getId())) {
									JOptionPane.showMessageDialog(null, "삭제되었습니다");
									new StoreLikeUI(ui, lp);
									f.setVisible(false);

								} else {
									JOptionPane.showMessageDialog(null, "삭제 실패했습니다");
								}

							}
						}
					}
				} else {
					int row = table.getSelectedRow();
					String name = (String) table.getValueAt(row, 1);
					showInfo(name);
				}
			}

		}
	}

	/* 음식 정보 조회 */
	public void showInfo(String name) {
		ArrayList<FoodVO> list = ui.fm.list();
		for (FoodVO vo : list) {
			if (vo.getName().equals(name)) {
				String sid = vo.getId();
				new FoodInfoUI(ui, lp, sid, name);
				f.setVisible(false);
				lp.setVisible(false);

			}
		}

	}

}
