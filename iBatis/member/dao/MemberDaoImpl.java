package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil;

public class MemberDaoImpl implements IMemberDao {

	private static IMemberDao memDao; // 자기자신의 타입을 담을 변수 선언 
	
	private MemberDaoImpl() {
		
	}
	
	//외부에서 객체사용하도록
	public static IMemberDao getInstance() {
		if(memDao == null) {
			memDao = new MemberDaoImpl();
		}
		
		return memDao;
	}
	
	@Override
	public int insertMember(SqlMapClient smc, MemberVO mv) throws SQLException {
		
		int cnt = 0;
		
		Object obj =  smc.insert("member.insertMember", mv);
		
		if(obj == null) {
			cnt = 1;
		}
		return cnt;
	}

	@Override
	public boolean getMember(SqlMapClient smc, String memId) throws SQLException {
		
		boolean check = false;
		
		MemberVO resultVO =  (MemberVO)smc.queryForObject("member.getMember", memId);
		
		if(resultVO != null) {
			check = true;
		}
		return check;
	}

	@Override
	public List<MemberVO> getAllMemberList(SqlMapClient smc) throws SQLException {
		
		List<MemberVO> memList = smc.queryForList("member.getMemberAll");
		
		return memList;
	}

	@Override
	public int updateMember(SqlMapClient smc, MemberVO mv) throws SQLException {
		
		int cnt = smc.update("member.updateMember", mv);
		
		return cnt;
	}

	@Override
	public int deleteMember(SqlMapClient smc, String memId) throws SQLException {
		
		int cnt = smc.delete("member.deleteMember", memId);
		
		return cnt;
	}

	@Override
	public List<MemberVO> getSearchMember(SqlMapClient smc, MemberVO mv) throws SQLException {
		
		List<MemberVO> memList = smc.queryForList("member.getSearchMember", mv);
		
		return memList;
	}

}









