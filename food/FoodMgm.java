package food;

import java.util.ArrayList;

public class FoodMgm {

	FoodDAO dao = new FoodDAO();

	/* 회원가입 */
	public boolean insert(JoinVO vo) {
		return dao.insert(vo);

	}

	/* 회원정보 수정 */ 
	public boolean update(JoinVO vo) {
		return dao.update(vo);

	}

	/* 회원정보 조회 */
	public JoinVO getData(String id) {
		return dao.getData(id);
	}

	/* 로그인 */
	public boolean login(String id, String pass) {
		return dao.login(id, pass);
	}

	/* 아이디 중복 확인 */
	public boolean check(String id) {
		return dao.check(id);
	}

	/* 사용자 이름 조회 */
	public String name(String id) {
		return dao.name(id);
	}

	/* 음식점 리스트(이름순) */
	public ArrayList<FoodVO> list() {
		return dao.list();
	}

	/* 음식점 리스트(평점순) */
	public ArrayList<FoodVO> listHigh() {
		return dao.listHigh();
	}

	/* 음식점 리스트(카테고리별) */
	public ArrayList<FoodVO> listChange(String type) {
		return dao.listChange(type);
	}

	/* 음식점 정보 조회 */
	public FoodVO upload(String id) {
		return dao.upload(id);
	}

	/* 리뷰 등록 */
	public boolean setReview(String text, int gra, String id, String name) {
		return dao.setReview(text, gra, id, name);
	}

	/* 리뷰 리스트 */
	public ArrayList<ReviewVO> reviewlist(String id) {
		return dao.reviewlist(id);
	}

	/* 자신이 쓴 리뷰 리스트 */
	public ArrayList<ReviewVO> reviewMylist(String id) {
		return dao.reviewMylist(id);
	}

	/* 즐겨찾기 리스트 */
	public ArrayList<FoodVO> likeList(String mid) {
		return dao.likeList(mid);
	}

	/* 즐겨찾기 삭제 */
	public boolean like_delete(String mid, String sid) {
		return dao.like_delete(mid, sid);
	}

	/* 즐겨찾기 등록 */
	public boolean s_like(String mid, String sid) {
		return dao.s_like(mid, sid);
	}

	/* 즐겨찾기 중복 확인 */
	public boolean like_check(String mid, String sid) {
		return dao.like_check(mid, sid);
	}

	/* 검색시 리스트 갯수 확인 */
	public int count(String type) {
		return dao.count(type);
	}

	/* 리뷰 삭제 */
	public boolean reviewDelete(String rid) {
		return dao.reviewDelete(rid);
	}

	/* 리뷰 수정 */
	public boolean updateReview(String rid, int gra, String text) {
		return dao.updateReview(rid, gra, text);
	}

	/* 사용자 삭제 */
	public boolean deleteMember(String id) {
		return dao.deleteMember(id);
	}

	/* 음식점 삭제 */
	public boolean deleteStore(String sid) {
		return dao.deleteStore(sid);
	}

	/* 음식점 수정 */
	public boolean updateStore(FoodVO vo) {
		return dao.updateStore(vo);
	}

	/* 음식점 등록 */
	public boolean insertStore(FoodVO vo) {
		return dao.insertStore(vo);
	}

	/* 사용자 리스트 */
	public ArrayList<MemberVO> listMember() {
		return dao.listMember();
	}

	/* 리뷰 검색 리스트 */
	public ArrayList<ReviewVO> listReview(String word) {
		return dao.listReview(word);
	}
}
