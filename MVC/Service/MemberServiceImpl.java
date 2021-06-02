package kr.or.ddit.member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil;
import kr.or.ddit.util.JDBCUtil3;

//controller -> service(model영역) -> dao

public class MemberServiceImpl implements IMemberService{  //service가 하는일 : 다오를 호출해서 데이터 관련일이 끝나면 관리자에게 끝냈다는 메일을 보내는 기능 등을 함 

	private IMemberDao memDao;
	
	public MemberServiceImpl() {
		memDao = new MemberDaoImpl();
	}

	@Override
	public int insertMember(MemberVO mv) {
		
		Connection conn = JDBCUtil3.getConnection(); //트랜젝션을 만났을때 커넥션이 있는것이 좋음
//		conn.setAutoCommit(false); // 자동커밋풀기(하나의 커넥션으로 묶고싶을때)＊
		
		int cnt = 0;
		
		try {//메서드에서 throws처리하게 만들어서 컴파일에러 남 => try/ catch문 사용
			cnt = memDao.insertMember(conn, mv);
//			conn.commit();＊
		} catch (SQLException e) {
			e.printStackTrace();
//			conn.rollback();＊
		}finally { //커넥션 반납
			JDBCUtil.disConnect(conn, null, null, null);
		}  
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		
		int cnt = 0;
		
		Connection conn = JDBCUtil3.getConnection();
		
		try {
			cnt = memDao.deleteMember(conn, memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, null, null, null); //static 메서드이므로 JDBCUtil 써도 무방
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO mv) {
		
		int cnt = 0;
		
		Connection conn = JDBCUtil3.getConnection();
		
		try {
			cnt = memDao.updateMember(conn, mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, null, null, null);
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		Connection conn = JDBCUtil3.getConnection();
		
		try {
			memList = memDao.getAllMemberList(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, null, null, null);
		}
		return memList;
	}

	@Override
	public boolean checkMember(String memId) {
		
		boolean chk = false;
		
		Connection conn = JDBCUtil3.getConnection();
		
		try {
			chk = memDao.getMember(conn, memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, null, null, null);
		}
		
		return chk;
	}

	@Override
	public List<MemberVO> getsearchMember(MemberVO mv) {
		//dao호출해야하기 때문에 dao부터 정리한다음 작성
		
		List<MemberVO> memList = new ArrayList<>();
		
		Connection conn = JDBCUtil.getConnection();
		
		try {
			memList = memDao.getSearchMember(conn, mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, null, null, null);
		}
		
		return memList;
	}

}
