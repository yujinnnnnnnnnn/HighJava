package kr.or.ddit.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {

	static { //jdbcuitl이라는 클래스가 메모리를 읽어지는 시점에  static블럭 한번만 실행됨 (로직에 한번만 실행하고싶을때 블럭안에 넣음) 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 완료");
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	/**
	 * DB연결
	 * @return
	 */
	//커넥션 객체
	public static Connection getConnection() {
		
		try {
			
			return DriverManager.getConnection(  //커넥션 맺는법
					"jdbc:oracle:thin:@localhost:1521:xe",
					"koo",
					"java");
			
		} catch (SQLException ex) {
			System.out.println("DB연결 실패");
			ex.printStackTrace();
			return null;
		}
	}
	/**
	 * 자원 반납
	 * @param conn
	 * @param stmt
	 * @param pstmt
	 * @param rs
	 */
	public static void disConnect(Connection conn, Statement stmt , PreparedStatement pstmt, ResultSet rs) {
		
		if(rs != null) try {rs.close();}catch(SQLException ex) {}
		if(stmt != null) try {stmt.close();}catch(SQLException ex) {}
		if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {}
		if(conn != null) try {conn.close();}catch(SQLException ex) {}
	}
}












