package food;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class MyTableModel2 extends AbstractTableModel {
	public static int i;

	String[] columns = { "번호", "이미지", "음식점명", "음식종류", "위치", "전화번호", "평점", "수정", "삭제" };
	Object[][] data;
	LoginPageUI lp;
	AdminPageUI ap;
	ArrayList<FoodVO> list;

	public MyTableModel2(String type, AdminPageUI ap) {

		int i = 0;
		String a = null;

		if (type.equals("평점순")) {
			list = ap.ui.fm.listHigh();
		} else if (type.equals("이름순")) {
			list = ap.ui.fm.list();

		} else {
			list = ap.ui.fm.listChange(type);
			int num = ap.ui.fm.count(type); // 음식점 갯수
			MyTableModel2.i = num;
		}
		data = new Object[list.size()][];
		ImageIcon[] icon = new ImageIcon[list.size()];

		for (FoodVO vo : list) {

			int num = vo.getScore();

			if (num == 5) {
				a = "★★★★★";
			} else if (num == 4) {
				a = "★★★★";
			} else if (num == 3) {
				a = "★★★";
			} else if (num == 2) {
				a = "★★";
			} else if (num == 1) {
				a = "★";
			} else
				a = "";

			icon[i] = new ImageIcon("src/food/" + vo.getSimg());

			JButton jbn = new JButton("수정");
			JButton jbn2 = new JButton("삭제");

			data[i] = new Object[] { vo.getRno(), icon[i], vo.getName(), vo.getKind(), vo.getLoc(), vo.getPhone(), a,
					jbn, jbn2 };

			i++;

		}
		MyTableModel2.i = list.size();

	}

	public int getColumnCount() {
		return columns.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int col) {
		return columns[col];
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];

	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	public boolean isCellEditable(int row, int col) {

		return col == 1 || col == 6 ? true : false;

	}
}