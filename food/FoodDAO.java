package food;

import java.util.ArrayList;

import db.conn.DBConn;

public class FoodDAO extends DBConn {

	public boolean insert(JoinVO vo) {
		boolean result = false;
		try {
			String sql = "insert into food_member values(?,?,?,?,?,?,sysdate)";
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, "0" + vo.getPn1() + "-" + vo.getPn2() + "-" + vo.getPn3());
			pstmt.setString(5, vo.getFood());
			pstmt.setString(6, vo.getAddr());

			int count = pstmt.executeUpdate();
			if (count != 0)
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean update(JoinVO vo) {
		boolean result = false;
		try {
			String sql = "update food_member set mpass = ?, mname = ?, mphone = ?, mwant = ?, marea = ? where mid = ?";
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getPass());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, "0" + vo.getPn1() + "-" + vo.getPn2() + "-" + vo.getPn3());
			pstmt.setString(4, vo.getFood());
			pstmt.setString(5, vo.getAddr());
			pstmt.setString(6, vo.getId());

			int count = pstmt.executeUpdate();
			if (count != 0)
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public JoinVO getData(String id) {
		JoinVO vo = new JoinVO();
		try {
			String sql = "select * from food_member where mid= ?";
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				vo.setId(rs.getString(1));
				vo.setPass(rs.getString(2));
				vo.setName(rs.getString(3));
				String number = rs.getString(4);
				vo.setPn1(Integer.parseInt(number.substring(0, 2)));
				vo.setPn2(Integer.parseInt(number.substring(4, 8)));
				vo.setPn3(Integer.parseInt(number.substring(9, 13)));
				vo.setFood(rs.getString(5));
				vo.setAddr(rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;
	}

	public boolean login(String id, String pass) {
		boolean result = false;
		try {
			String sql = " select count(*) from food_member where mid =? and mpass=?";
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				if (count != 0)
					result = true;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean check(String id) {
		boolean result = false;
		try {
			String sql = "select count(*) from food_member where mid = ?";
			getPreparedStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next())
				if (rs.getInt(1) != 1) {
					result = true;
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String name(String id) {
		String name = "";
		try {
			String sql = "select mname from food_member where mid =?";
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				name = rs.getString(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return name;
	}

	public ArrayList<FoodVO> list() {
		ArrayList<FoodVO> list = new ArrayList<>();
		try {
			String sql = "select rownum rno , sid, sname, skind, slocation, sphone, score, simg\r\n"
					+ "from (select rownum rno , f.sid, sname, skind, slocation, sphone,  score, simg from food_store f, (select sid, round(avg(rservice),1) score from food_review group by sid order by score desc) r\r\n"
					+ "where f.sid = r.sid(+) \r\n"
					+ "group by rownum, f.sid, sname, score, skind, slocation, sphone, simg\r\n" + "order by sname)";
			getStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				FoodVO vo = new FoodVO();
				vo.setRno(rs.getInt(1));
				vo.setId(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setKind(rs.getString(4));
				vo.setLoc(rs.getString(5));
				vo.setPhone(rs.getString(6));
				vo.setScore(rs.getInt(7));
				vo.setSimg(rs.getString(8));
				list.add(vo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<FoodVO> listHigh() {
		ArrayList<FoodVO> list = new ArrayList<>();
		try {
			String sql = "select rownum rno, sname,skind, slocation, sphone,simg, score from (select sname,skind, slocation, sphone,simg,round(nvl(score,0),0) score from food_sr order by score desc)";
			getStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				FoodVO vo = new FoodVO();
				vo.setRno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setKind(rs.getString(3));
				vo.setLoc(rs.getString(4));
				vo.setPhone(rs.getString(5));
				vo.setSimg(rs.getString(6));
				vo.setScore(rs.getInt(7));
				list.add(vo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<FoodVO> listChange(String type) {

		ArrayList<FoodVO> list = new ArrayList<>();
		try {
			String sql = "select rownum rno, sname ,sid,skind,slocation, sphone, simg, score from (select rownum rno , sname ,sid,skind,slocation, sphone, simg, round(nvl(score,0),0) score from food_sr where "
					+ " skind like '%" + type + "%' or slocation like '%" + type + "%' or sphone like '%" + type
					+ "%' or sname like '%" + type + "%' order by sname) ";

			getPreparedStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				FoodVO vo = new FoodVO();

				vo.setRno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setId(rs.getString(3));
				vo.setKind(rs.getString(4));
				vo.setLoc(rs.getString(5));
				vo.setPhone(rs.getString(6));
				vo.setSimg(rs.getString(7));
				vo.setScore(rs.getInt(8));
				list.add(vo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public FoodVO upload(String id) {
		FoodVO vo = new FoodVO();
		try {
			String sql = "select sid,sname, skind, slocation, sphone, simg from food_store where sid =?";
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo.setId(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setKind(rs.getString(3));
				vo.setLoc(rs.getString(4));
				vo.setPhone(rs.getString(5));
				vo.setSimg(rs.getString(6));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	public boolean setReview(String text, int gra, String id, String name) {
		boolean result = false;
		try {
			String sql = "insert into food_review(rid,mid,sid, rservice, rcontent,rdate) values('R_'||SEQ_F_REVIEW.nextval,?,?,?,?,sysdate)";
			getPreparedStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setInt(3, gra);
			pstmt.setString(4, text);

			int count = pstmt.executeUpdate();

			if (count != 0)
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<ReviewVO> reviewlist(String id) {
		ArrayList<ReviewVO> list = new ArrayList<>();
		try {
			String sql = "select rownum rno, mid, rcontent ,rservice from (select mid, rcontent ,rservice from food_review where sid = ? order by rdate desc) ";
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReviewVO vo = new ReviewVO();
				vo.setRno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setText(rs.getString(3));
				vo.setGrade(rs.getInt(4));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<ReviewVO> reviewMylist(String id) {
		ArrayList<ReviewVO> list = new ArrayList<>();
		try {
			String sql = "select rid, rownum rno, sname, rcontent ,rservice, TO_CHAR(rdate, 'YY/MM/DD')\r\n"
					+ "from (select rid, sname, rcontent ,rservice, rdate\r\n"
					+ "      from food_review r, food_store s\r\n"
					+ "      where r.sid = s.sid and mid = ? order by rdate desc) ";
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReviewVO vo = new ReviewVO();
				vo.setRid(rs.getString(1));
				vo.setRno(rs.getInt(2));
				vo.setSname(rs.getString(3));
				vo.setText(rs.getString(4));
				vo.setGrade(rs.getInt(5));
				vo.setDate(rs.getString(6));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	public ArrayList<FoodVO> likeList(String mid) {
		ArrayList<FoodVO> list = new ArrayList<>();
		try {
			String sql = "select rownum rno, sid,sname, skind, slocation, sphone "
					+ "from (select s.sid, sname, skind, slocation, sphone, mid from food_store s ,food_like l where s.sid =l.sid ) where mid=? ";
			getPreparedStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FoodVO vo = new FoodVO();
				vo.setRno(rs.getInt(1));
				vo.setId(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setKind(rs.getString(4));
				vo.setLoc(rs.getString(5));
				vo.setPhone(rs.getString(6));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public boolean like_delete(String mid, String sid) {
		boolean result = false;
		try {
			String sql = "delete from food_like where mid=? and sid =?";
			getPreparedStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setString(2, sid);

			int count = pstmt.executeUpdate();
			if (count != 0)
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean s_like(String mid, String sid) {
		boolean result = false;
		try {
			String sql = "insert into food_like values(?,?,sysdate)";
			getPreparedStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setString(2, sid);
			int count = pstmt.executeUpdate();
			if (count != 0)
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	public boolean like_check(String mid, String sid) {
		boolean result = false;
		try {
			String sql = "select count(*) from food_like where mid=? and sid=?";
			getPreparedStatement(sql);

			pstmt.setString(1, mid);
			pstmt.setString(2, sid);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				if (rs.getInt(1) != 0)
					result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int count(String type) {
		int count = 0;
		try {
			String sql = "select count(*) from food_sr where skind like '%" + type + "%' or slocation like '%" + type
					+ "%' or sphone like '%" + type + "%' or sname like '%" + type + "%' ";
			;
			getPreparedStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public boolean reviewDelete(String rid) {
		boolean result = false;
		try {
			String sql = "delete from food_review where rid = ?";
			getPreparedStatement(sql);
			pstmt.setString(1, rid);

			int count = pstmt.executeUpdate();
			if (count != 0)
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean updateReview(String rid, int gra, String text) {
		boolean result = false;
		try {
			String sql = "update food_review set rservice = ?, rcontent = ? where rid = ?";
			getPreparedStatement(sql);
			pstmt.setInt(1, gra);
			pstmt.setString(2, text);
			pstmt.setString(3, rid);

			int count = pstmt.executeUpdate();
			if (count != 0)
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean deleteMember(String id) {
		boolean result = false;
		try {
			String sql = "delete from food_member where mid = ?";
			getPreparedStatement(sql);
			pstmt.setString(1, id);

			int count = pstmt.executeUpdate();
			if (count != 0)
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean deleteStore(String sid) {
		boolean result = false;
		try {
			String sql = "delete from food_store where sid = ?";
			getPreparedStatement(sql);
			pstmt.setString(1, sid);

			int count = pstmt.executeUpdate();
			if (count != 0)
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean updateStore(FoodVO vo) {
		boolean result = false;
		try {
			String sql = "update food_store set sname = ?, skind = ?, slocation = ?, sphone = ? , simg = ? where sid = ?";
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getKind());
			pstmt.setString(3, vo.getLoc());
			pstmt.setString(4, vo.getPhone());
			pstmt.setString(5, vo.getSimg());
			pstmt.setString(6, vo.getId());

			int count = pstmt.executeUpdate();
			if (count != 0)
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean insertStore(FoodVO vo) {
		boolean result = false;
		try {
			String sql = "insert into food_store values('Y_' || SEQ_F_STORE.NEXTVAL, ?, ?, ?, ?, ?)";
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getKind());
			pstmt.setString(3, vo.getLoc());
			pstmt.setString(4, vo.getPhone());
			pstmt.setString(5, vo.getSimg());

			int count = pstmt.executeUpdate();
			if (count != 0)
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<MemberVO> listMember() {
		ArrayList<MemberVO> list = new ArrayList<>();
		try {
			String sql = " select rownum rno, mid, mname, mphone, mwant, marea, TO_CHAR(mdate, 'YY/MM/DD')\r\n"
					+ " from (select mid, mname, mphone, mwant, marea, mdate\r\n"
					+ " from food_member order by mdate desc)";
			getStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setRno(rs.getInt(1));
				vo.setId(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setPhone(rs.getString(4));
				vo.setKind(rs.getString(5));
				vo.setLoc(rs.getString(6));
				vo.setDate(rs.getString(7));
				list.add(vo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<ReviewVO> listReview(String word) {
		ArrayList<ReviewVO> list = new ArrayList<>();
		try {
			String sql = " select rownum rno, sname, mid, rcontent ,rservice, TO_CHAR(rdate, 'YY/MM/DD')\r\n"
					+ " from (select rid, sname, mid, rcontent ,rservice, rdate\r\n"
					+ " from food_review r, food_store s\r\n" + " where r.sid = s.sid and (sname like '%" + word
					+ "%' or mid like '%" + word + "%') order by rdate desc)";
			getStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ReviewVO vo = new ReviewVO();
				vo.setRno(rs.getInt(1));
				vo.setSname(rs.getString(2));
				vo.setMid(rs.getString(3));
				vo.setText(rs.getString(4));
				vo.setGrade(rs.getInt(5));
				vo.setDate(rs.getString(6));
				list.add(vo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}