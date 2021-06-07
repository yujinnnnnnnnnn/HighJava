package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.member.vo.MemberVO;

public class MemberIbatisTest {

	public static void main(String[] args) {
		//iBatis를 이용하여 DB자료를 처리하는 작업순서
		// 1. iBatis의 환경설정 파일을 읽어와 실행시킨다
		
		try {
			//1-1. xml문서 읽어오기
			Charset charset = Charset.forName("UTF-8");  // 설정파일의 인코딩 설정
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");  //파라미터를 요구하기때문에 리더를 만들어놈
			
			//1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);  // 위에는 이것을 만들기 위한 준비작업임
			
			rd.close(); //Reader 객체 닫기
			
			//2. 실행할  SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.
			
			//2-1. insert작업 연습
			System.out.println("insert 작업 시작");
			
			//1) 저장할 데이터를 VO에 담는다.
			MemberVO mv = new MemberVO();
			mv.setMemId("d001");  //인서트할 필요데이터 세팅
			mv.setMemName("강감찬");
			mv.setMemTel("010-1234-1234");
			mv.setMemAddr("대전 대전인재개발원");
			
			// 2) SqlMapClient 객체 변수를 이용하여 해당 쿼리문을 실행한다
			// 형식) smc.insert("namespace값.id값", 파라미터 클래스);
			//	  반환값 : 성공하면 null이 반환됨
//			Object obj = smc.insert("memberTest.insertMember", mv); // namespace.실제ID => namespace를 사용하면 insert의 아이디를 구별하지 않아도됨
//															  // 단 useStatementNamespaces="true" 로 되어있어야함
//			if(obj == null) {
//				System.out.println("insert 작업 성공");
//			}else {
//				System.out.println("insert 작업 실패 !!");
//			}
//			System.out.println("----------------------------------");
//			
			//2-2 update 작업 연습
			System.out.println("update 작업 시작");
			
			MemberVO mv2 = new MemberVO();
			mv2.setMemId("d001");
			mv2.setMemName("이순신");
			mv2.setMemTel("010-1111-1111");
			mv2.setMemAddr("대전 대덕구");
			
			// update()메서드의 반환값은 성공한 레코드 수이다
			
			int cnt = smc.update("memberTest.updateMember", mv2);
			
			if(cnt > 0 ) {
				System.out.println("update 작업 성공!!");
			}else {
				System.out.println("update 작업 실패...");
			}
			System.out.println("------------------------------------------");
			
			//2-3 delete 작업 연습
			System.out.println("delete 작업 시작");
			
			//delete 메서드의 반환값 : 성공한 레코드 수
			
			int  cnt2 = smc.delete("memberTest.deleteMember", "d001");
			
			if(cnt2 > 0 ) {
				System.out.println("delete 작업 성공!!");
			}else {
				System.out.println("delete 작업 실패...");
			}
			System.out.println("------------------------------------------");
//			
//			//2-4 select 작업 연습
//			//1) 응답의 결과가 여러개일 경우
//			
			System.out.println("select 작업 시작(결과가 여려개일 경우)");
						
			//응답 결과가 여러개 일 겨우에는 queryForList메서드를 사용한다
			//이 메서드는 여러개의 레코드를 VO에 담은 후 이 VO데이터를 List에 추가해 주는 작업을 자동으로 수행한다
			
//			List<MemberVO> memList = smc.queryForList("memberTest.getMemberAll");  //전체조회를 갖고오는 경우여서 파라미터가 필요없음
//			
//			for(MemberVO vo : memList) {
//				System.out.println("ID : " + vo.getMemId());
//				System.out.println("이름 : " + vo.getMemName());
//				System.out.println("전화 : " + vo.getMemTel());
//				System.out.println("주소 : " + vo.getMemAddr());
//				System.out.println("===============================");
//			}
//			System.out.println("출력끝!!!");
			
			// 2) 응답이 1개일 경우
			System.out.println("select 연습 시작 (결과가 1개일 경우)...");
			
			//응답 결과가 1개가 확실할 경우 queryForObject메서드를 사용한다
			MemberVO mv3 = (MemberVO)smc.queryForObject("memberTest.getMember", "a003");
			
			System.out.println("ID : " + mv3.getMemId());
			System.out.println("이름 : " + mv3.getMemName());
			System.out.println("전화 : " + mv3.getMemTel());
			System.out.println("주소 : " + mv3.getMemAddr());
			System.out.println("===============================");
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
	
		
	}
}














