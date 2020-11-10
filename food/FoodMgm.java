package food;

import java.util.ArrayList;

public class FoodMgm {

	FoodDAO dao = new FoodDAO();

	/* ȸ������ */
	public boolean insert(JoinVO vo) {
		return dao.insert(vo);

	}

	/* ȸ������ ���� */ 
	public boolean update(JoinVO vo) {
		return dao.update(vo);

	}

	/* ȸ������ ��ȸ */
	public JoinVO getData(String id) {
		return dao.getData(id);
	}

	/* �α��� */
	public boolean login(String id, String pass) {
		return dao.login(id, pass);
	}

	/* ���̵� �ߺ� Ȯ�� */
	public boolean check(String id) {
		return dao.check(id);
	}

	/* ����� �̸� ��ȸ */
	public String name(String id) {
		return dao.name(id);
	}

	/* ������ ����Ʈ(�̸���) */
	public ArrayList<FoodVO> list() {
		return dao.list();
	}

	/* ������ ����Ʈ(������) */
	public ArrayList<FoodVO> listHigh() {
		return dao.listHigh();
	}

	/* ������ ����Ʈ(ī�װ�����) */
	public ArrayList<FoodVO> listChange(String type) {
		return dao.listChange(type);
	}

	/* ������ ���� ��ȸ */
	public FoodVO upload(String id) {
		return dao.upload(id);
	}

	/* ���� ��� */
	public boolean setReview(String text, int gra, String id, String name) {
		return dao.setReview(text, gra, id, name);
	}

	/* ���� ����Ʈ */
	public ArrayList<ReviewVO> reviewlist(String id) {
		return dao.reviewlist(id);
	}

	/* �ڽ��� �� ���� ����Ʈ */
	public ArrayList<ReviewVO> reviewMylist(String id) {
		return dao.reviewMylist(id);
	}

	/* ���ã�� ����Ʈ */
	public ArrayList<FoodVO> likeList(String mid) {
		return dao.likeList(mid);
	}

	/* ���ã�� ���� */
	public boolean like_delete(String mid, String sid) {
		return dao.like_delete(mid, sid);
	}

	/* ���ã�� ��� */
	public boolean s_like(String mid, String sid) {
		return dao.s_like(mid, sid);
	}

	/* ���ã�� �ߺ� Ȯ�� */
	public boolean like_check(String mid, String sid) {
		return dao.like_check(mid, sid);
	}

	/* �˻��� ����Ʈ ���� Ȯ�� */
	public int count(String type) {
		return dao.count(type);
	}

	/* ���� ���� */
	public boolean reviewDelete(String rid) {
		return dao.reviewDelete(rid);
	}

	/* ���� ���� */
	public boolean updateReview(String rid, int gra, String text) {
		return dao.updateReview(rid, gra, text);
	}

	/* ����� ���� */
	public boolean deleteMember(String id) {
		return dao.deleteMember(id);
	}

	/* ������ ���� */
	public boolean deleteStore(String sid) {
		return dao.deleteStore(sid);
	}

	/* ������ ���� */
	public boolean updateStore(FoodVO vo) {
		return dao.updateStore(vo);
	}

	/* ������ ��� */
	public boolean insertStore(FoodVO vo) {
		return dao.insertStore(vo);
	}

	/* ����� ����Ʈ */
	public ArrayList<MemberVO> listMember() {
		return dao.listMember();
	}

	/* ���� �˻� ����Ʈ */
	public ArrayList<ReviewVO> listReview(String word) {
		return dao.listReview(word);
	}
}