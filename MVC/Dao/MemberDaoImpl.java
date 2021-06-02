package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil;

public class MemberDaoImpl implements IMemberDao {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public int insertMember(Connection conn, MemberVO mv) throws SQLException {
		
		String sql = "insert into mymember "
				+ " (mem_id, mem_name, mem_tel, mem_addr) "
				+ " values (?, ?, ?, ?) ";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mv.getMemId());
		pstmt.setString(2, mv.getMemName());
		pstmt.setString(3, mv.getMemTel());
		pstmt.setString(4, mv.getMemAddr());
		
		int cnt = pstmt.executeUpdate();
		
		JDBCUtil.disConnect(null, stmt, pstmt, rs); 
		
		return cnt;
	}

	@Override
	public boolean getMember(Connection conn, String memId) throws SQLException {
		
		boolean check = false;
		
		String sql = "select count(*) cnt from mymember "
				+ " where mem_id = ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		
		rs = pstmt.executeQuery();
		
		//resultSet 꺼내오기
		int count = 0;
		
		if(rs.next()) {
			count = rs.getInt("cnt");
		}
		
		if(count > 0) {
			check = true;
		}
		return check;
	}

	@Override
	public List<MemberVO> getAllMemberList(Connection conn) throws SQLException {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		String sql = "select * from mymember";
		
		stmt = conn.createStatement();
		
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			MemberVO mv = new MemberVO(); //데이터 담을 객체를 만듬
			
			//getter/setter이용해서 정보를 담음
			mv.setMemId(rs.getString("mem_id")); 
			mv.setMemName(rs.getString("mem_name"));
			mv.setMemTel(rs.getString("mem_tel"));
			mv.setMemAddr(rs.getString("mem_addr"));
			
			memList.add(mv); //리스트 안에다가 while문도는 회수에 맞춰 memList에 담김
		}
		
		return memList;
	}

	@Override
	public int updateMember(Connection conn, MemberVO mv) throws SQLException {
		
		String sql = "update mymember "  
				+ " set mem_name = ? "  
				+ "    ,mem_tel = ? "  
				+ "    ,mem_addr = ? " 
				+ " where mem_id = ? ";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mv.getMemName());
		pstmt.setString(2, mv.getMemTel());
		pstmt.setString(3, mv.getMemAddr());
		pstmt.setString(4, mv.getMemId());
		
		int cnt = pstmt.executeUpdate();
		
		return cnt;
	}

	@Override
	public int deleteMember(Connection conn, String memId) throws SQLException {
		
		String sql = "delete from mymember where mem_id = ? ";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		
		int cnt = pstmt.executeUpdate();
		
		return cnt;
	}

	@Override
	public List<MemberVO> getSearchMember(Connection conn, MemberVO mv) throws SQLException {
		
		List<MemberVO> memList = new ArrayList<>();
		
		String sql = "select * from mymember where 1=1 "; //전체조회할 쿼리
		
		if(mv.getMemId() != null && !mv.getMemId().equals("")) { //id값이 null이 아니고 빈값이 아닌 = 아이디가 존재할때만 if절 실행
			sql += " and mem_id = ? ";
		}
		if(mv.getMemName() != null && !mv.getMemName().equals("")) {
			sql += " and mem_name = ? ";
		}
		if(mv.getMemTel() != null && !mv.getMemTel().equals("")) { 
			sql += " and mem_tel = ? ";
		}
		if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) { 
			sql += " and mem_addr like '%' || ? || '%' "; // 입력값이 들어가 있는 주소
		}
		
		pstmt = conn.prepareStatement(sql);
		
		int index = 1;
		
		if(mv.getMemId() != null && !mv.getMemId().equals("")) { //id값이 null이 아니고 빈값이 아닌 = 아이디가 존재할때만 if절 실행
			pstmt.setString(index++, mv.getMemId()); //(물음표 위치, 값)
		}
		if(mv.getMemName() != null && !mv.getMemName().equals("")) {
			pstmt.setString(index++, mv.getMemName());
		}
		if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
			pstmt.setString(index++, mv.getMemTel());
		}
		if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
			pstmt.setString(index++, mv.getMemAddr());
		}
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			MemberVO mv2 = new MemberVO(); //데이터 담을 객체를 만듬
			
			//getter/setter이용해서 정보를 담음
			mv2.setMemId(rs.getString("mem_id")); 
			mv2.setMemName(rs.getString("mem_name"));
			mv2.setMemTel(rs.getString("mem_tel"));
			mv2.setMemAddr(rs.getString("mem_addr"));
			
			memList.add(mv2); //리스트 안에다가 while문도는 회수에 맞춰 mv2 넣기
		}
		
		return memList;
	}

}









