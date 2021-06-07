package kr.or.ddit.member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil;
import kr.or.ddit.util.JDBCUtil3;
import kr.or.ddit.util.SqlMapClientFactory;

//controller -> service(model영역) -> dao

public class MemberServiceImpl implements IMemberService{  //service가 하는일 : 다오를 호출해서 데이터 관련일이 끝나면 관리자에게 끝냈다는 메일을 보내는 기능 등을 함 

	private IMemberDao memDao;
	
	private static IMemberService memService; // 자기 자신의타입을 담을 변수 선언
	
	private SqlMapClient smc;
	
	private MemberServiceImpl() { // 외부에서 사용하지 못하도록 public -> private로 바꿔줌
		memDao = MemberDaoImpl.getInstance();
		smc = SqlMapClientFactory.getInstance();
	}

	public static IMemberService getInstance() { //외부에서 객체를 사용할 수 있더록
		if(memService == null) {
			memService = new MemberServiceImpl();
		}
		return memService;
	}
	
	@Override
	public int insertMember(MemberVO mv) {
		
		
//		conn.setAutoCommit(false); // 자동커밋풀기(하나의 커넥션으로 묶고싶을때)＊
		
		int cnt = 0;
		
		try {//메서드에서 throws처리하게 만들어서 컴파일에러 남 => try/ catch문 사용
			cnt = memDao.insertMember(smc, mv);
//			conn.commit();＊
		} catch (SQLException e) {
			e.printStackTrace();
//			conn.rollback();＊
		} 
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		
		int cnt = 0;
		
		try {
			cnt = memDao.deleteMember(smc, memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO mv) {
		
		int cnt = 0;
		
		try {
			cnt = memDao.updateMember(smc, mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
	
		try {
			memList = memDao.getAllMemberList(smc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memList;
	}

	@Override
	public boolean checkMember(String memId) {
		
		boolean chk = false;
		
		try {
			chk = memDao.getMember(smc, memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chk;
	}

	@Override
	public List<MemberVO> getsearchMember(MemberVO mv) {
		//dao호출해야하기 때문에 dao부터 정리한다음 작성
		
		List<MemberVO> memList = new ArrayList<>();
		
		try {
			memList = memDao.getSearchMember(smc, mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memList;
	}

}
