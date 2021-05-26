package kr.or.ddit.basic;

public class T01_ThreadTest {
//프로세스에서 실행하는 최소 단위  = Thread
	public static void main(String[] args) { //쓰레드 하나가 메인메서드를 찾아서 위에서 아래방향으로 실행시켜줌 -> 순서대로 실행해서 * 다음 $임
		//싱글 스레드 프로그램
		for (int i = 0; i <= 200; i++) {
			System.out.print("*");
		}
		
		System.out.println();
		
		for (int i = 0; i <=200; i++) {
			System.out.print("$");
		}
	} 
	
}
