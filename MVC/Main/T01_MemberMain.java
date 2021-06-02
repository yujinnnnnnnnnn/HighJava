package kr.or.ddit.member.main;
import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil;


/*
 * 
 * JDBC 코딩을한다는건 인터페이스 
 * 
 * 오라클서버 접속 해서 커넥션 맺음 -> statement에 맞는 인터페이스의 객체를 얻어옴 -> 오라클 쿼리에 따라 결과값 받기 (resultSet으로 받아옴)
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력	---> select
		5. 작업 끝.
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128),    -- 주소
    CONSTRAINT MYMEMBER_PK PRIMARY KEY (mem_id)
);

*/
public class T01_MemberMain {

	private Scanner scan = new Scanner(System.in); 
	
	private IMemberService memService;//service를 담기위한 변수 선언
	
	public T01_MemberMain() {
		memService = new MemberServiceImpl();
	}

	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 자료 검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertMember();
					break;
				case 2 :  // 자료 삭제
					deleteMember();
					break;
				case 3 :  // 자료 수정
					updateMember();
					break;
				case 4 :  // 전체 자료 출력
					displayMemberAll();
					break;
				case 5 :  // 자료검색
					searchMember();
					break;
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
	}
	
	/**
	 * 회원 정보를 검색하는 메서드
	 */
	private void searchMember() {
	/**
		검색할 회원ID, 회원이름, 전화번호, 주소 등을입력하면 입력한 정보만 사용하여 검색하는 기능을 구현하시오
		주소는 입력한 값이 포함만 되어도 검색되도록 한다
		입력을 하지 않을 자료는 엔터키로 다음 입력으로 넘긴다  	
	 */
		scan.nextLine(); //입력버퍼 지우기
		System.out.println();
		System.out.println("검색할 회원 정보를 입력하세요");
		
		System.out.print("회원 ID >> ");
		String memId = scan.nextLine().trim();
		
		System.out.println("회원 이름 >> ");
		String memName = scan.nextLine().trim();
		
		System.out.println("회원 전화번호 >> ");
		String memTel = scan.nextLine().trim();
		 
		
		System.out.println("회원 주소 >> ");  //주소는 한줄이므로 nextLine()
		String memAddr = scan.nextLine().trim();
		
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		
		//입력한 정보로 검색한 정보를 출력하는 부분
		
		System.out.println();
		System.out.println("--------------------------------");
		System.out.println(" ID\t이 름 \t전화번호\t주   소");
		System.out.println("--------------------------------");
		
		List<MemberVO> memList = memService.getsearchMember(mv);
		
			if(memList.size() == 0){
				System.out.println("검색한 회원정보가 존재하지 않습니다.");
			}else {
				for(MemberVO mv2: memList) {
					System.out.println(mv2.getMemId() + "\t" + mv2.getMemName() + "\t" + mv2.getMemTel() + "\t" + mv2.getMemAddr());							
			}
			
		}
		System.out.println("------------------------------------------------------------");
		System.out.println("검색 작업 끝");
		
	}

	/**
	 * 회원 정보를 삭제하느 메서드 (이력받은 회원ID를 이용하여 삭제한다)
	 */
	private void deleteMember() {
		   boolean chk = false;
		   String memId;
		   
		   do {
		      System.out.println();
		      System.out.println("수정할 회원 정보 입력");
		      System.out.print("회원 ID >> ");
		      memId = scan.next();
		      
		      chk = checkMember(memId);
		      
		      if(chk == false) {
		         System.out.println(memId + "는 존재하지 않는 아이디입니다.");
		         System.out.println("다시 입력하세요.");
		      }
		      
		   }while(chk == false);
		   
		   int cnt = memService.deleteMember(memId);
		   
		   if(cnt > 0) {
		      System.out.println(memId + "회원을 삭제했습니다.");
		   } else {
		      System.out.println(memId + "회원을 삭제하지 못했습니다.");
		   }
		}

	/**
	 * 회원 정보 수정
	 */
	private void updateMember() {
	
		boolean chk = false;
		
		String memId;
		do {
			System.out.println();
			System.out.println("수정할 회원 정보를 입력하세요");
			System.out.print("회원 ID >> ");
			memId = scan.next();
			
			chk = checkMember(memId);
			
			if(chk == false) {
				System.out.println("회원 ID가 " + memId + "인 회원은 없는 회원입니다.");
				System.out.println("다시 입력하세요");
			}
		}while(chk == false);
		
		System.out.println("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.println("회원 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine(); //입력 버퍼 비우기 
		
		System.out.println("회원 주소 >> ");  //주소는 한줄이므로 nextLine()
		String memAddr = scan.nextLine();
		
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		
		
		int cnt = memService.updateMember(mv);
		
		if(cnt > 0) {
			System.out.println(memId + "회원의 정보를 수정했습니다");
		}else {
			System.out.println(memId + "회원의 정보 수정실패!");
		}
	}

	/**
	 * 전체 회원을 출력하는 메서드
	 */
	private void displayMemberAll() {
		System.out.println();
		System.out.println("--------------------------------");
		System.out.println(" ID\t이 름 \t전화번호\t주   소");
		System.out.println("--------------------------------");
		
		List<MemberVO> memList = memService.getAllMemberList();
		
		for(MemberVO mv: memList) {
			System.out.println(mv.getMemId() + "\t" + mv.getMemName() + "\t" + mv.getMemTel() + "\t" + mv.getMemAddr());				
		}
		System.out.println("------------------------------------------------------------");
		System.out.println("출력 작업 끝");
	}

	private void insertMember() {
		
		boolean chk = false;
		String memId;
		do {
			System.out.println();
			System.out.println("추가할 회원 정보를 입력하세요");
			System.out.print("회원 ID >> ");
			memId = scan.next();
			
			chk = checkMember(memId);
			
			if(chk == true) {
				System.out.println("회원 ID가 " + memId + "인 회원은 이미 존재합니다.");
				System.out.println("다시 입력하세요");
			}
		}while(chk == true);
		
		System.out.println("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.println("회원 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine(); //입력 버퍼 비우기 
		
		System.out.println("회원 주소 >> ");  //주소는 한줄이므로 nextLine()
		String memAddr = scan.nextLine();
		
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		
		int cnt = memService.insertMember(mv); 
		
		if(cnt > 0) { 
			System.out.println(memId + "회원 추가 작업 성공");
		}else {
			System.out.println(memId + "회원 추가 작업 실패!!!");
		}
	}
	
	/**
	 * 회원 ID를 이용하여 회원이 있는지 알려주느 메서드
	 * 
	 * @param memId 체크할 회원 ID
	 * @return 존재하면 true, 아니면 false
	 */
	private boolean checkMember(String memId) {
		
		boolean check = false;
		
		check = memService.checkMember(memId);
	
		return check;
	}

	public static void main(String[] args) {
		T01_MemberMain memObj = new T01_MemberMain();
		memObj.start();
	}

}






