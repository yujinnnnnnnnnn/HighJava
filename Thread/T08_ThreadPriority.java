package kr.or.ddit.basic;

public class T08_ThreadPriority {
	// 쓰레드의 우선순위 (우선순위를 부여한다고해서 우선순위가 명확히 되진 않음) 우선순위 부여 : th2.setPriority(1);  
	public static void main(String[] args) {

		Thread th1 = new ThreadTest1();
		Thread th2 = new ThreadTest1();
		Thread th3 = new ThreadTest1();
		Thread th4 = new ThreadTest1();
		Thread th5 = new ThreadTest1();
		
		Thread th6 = new ThreadTest2();
		//우선순위는 start()메서드를 호출하기 전에 설정해야한다
		
		th1.setPriority(1); //Thread.MIN_PRIORITY를 써도 동일함
		th2.setPriority(1);
		th3.setPriority(1);
		th4.setPriority(1);
		th5.setPriority(1);
		th6.setPriority(10); //Thread.MAX_PRIORITY를 써도 동일함
		
		System.out.println("th의 우선순위 : "+ th1.getPriority());
		System.out.println("th의 우선순위 : "+ th2.getPriority()); 
		System.out.println("th의 우선순위 : "+ th3.getPriority()); 
		System.out.println("th의 우선순위 : "+ th4.getPriority()); 
		System.out.println("th의 우선순위 : "+ th5.getPriority()); 
		System.out.println("th의 우선순위 : "+ th6.getPriority()); 
		
		th1.start();
		th2.start();
		th3.start();
		th4.start();
		th5.start();
		th6.start();
		try {
			th1.join();
			th2.join();
			th3.join();
			th4.join();
			th5.join();
			th6.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("최대우선순위 : " + Thread.MAX_PRIORITY);
		System.out.println("최소우선순위 : " + Thread.MIN_PRIORITY);
		System.out.println("보통우선순위 : " + Thread.NORM_PRIORITY);
	}
}

// 대문자를 출력하는 메서드
class ThreadTest1 extends Thread {
	@Override
	public void run() {
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.println(ch);
		}
		// 아무것도 하지않는 반복문 (시간때우기용)
		for (long i = 1; i <= 100000000; i++) {}
//		Thread.sleep(100); => 위for문와 차이점 : for문에는 cpu가 작업을 하고 있지만 sleep은 일시정지 
	}
}

	// 소문자를 출력하는 메서드
class ThreadTest2 extends Thread {
		@Override
		public void run() {
			for (char ch = 'a'; ch <= 'z'; ch++) {
				System.out.println(ch);
			}
			// 아무것도 하지않는 반복문 (시간때우기용)
			for (long i = 1; i <= 100000000; i++) {
				
			}
		}
}
