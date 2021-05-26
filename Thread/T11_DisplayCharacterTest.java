package kr.or.ddit.basic;

/**
 * 3개(명)의 스레드가 각각 알파벳 대문자를 출력하는데 출력을 끝낸 순서대로 결과를 나타내는 프로그램을 작성하기
 * 경마 : 10개의 말 쓰레드 돌리기
 * 출력용 쓰레드 별도 만들기 
 * @author PC-19
 *
 */
public class T11_DisplayCharacterTest {

	static String strRank = ""; // 순위 저장용 변수
	
	public static void main(String[] args) {
		Thread[] disChars = new DisplayCharacter[] {
				new DisplayCharacter("홍길동"),
				new DisplayCharacter("일지매"),
				new DisplayCharacter("변학도")
		}; //3개의 쓰레드 객체 생성
		
		for(Thread th : disChars) {
			th.start();
		}
		for(Thread th : disChars) {
			try {
				th.join(); //main스레드가 기다림
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("경기 끝");
		System.out.println("=============================");
		System.out.println();
		System.out.println("경기  결과");
		System.out.println("순위 : "+ strRank);
	}
}

//영어 대문자를 출력하는 스레드 클래스
class DisplayCharacter extends Thread {
	private String name;

	//생성자
	public DisplayCharacter(String name) {
		this.name = name;
	}
	@Override
	public void run() {
		for (char ch = 'A'; ch <='Z'; ch++) {
			System.out.println(name + "의 출력 문자 : " + ch);
			
			try {
				//sleep()메서드의 값을 200~500사이의 난수로 한다
				Thread.sleep((int)(Math.random() * 301 + 200)); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name + "츨력 끝");
		
		T11_DisplayCharacterTest.strRank += name + " "; //출력된 name을 strRank에 누적함
	}
	
}