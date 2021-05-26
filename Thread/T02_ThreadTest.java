package kr.or.ddit.basic;

public class T02_ThreadTest {

	public static void main(String[] args) { // run역할을 함  // 메인과 방법1, 방법2 총 3개의 쓰레드가 동시작업함
		
		//멀티 스레드 프로그램 방식// (여러개의 쓰레드로 동시작업을 할때 멀티쓰레드해야함)
		
		// 쓰레드 객체생성방법1) Thread클래스를 상속한 class의 인스턴스를 생성한 후 이 인스턴스의 start()메서드를 호출한다. //run메서드 오버라이드 //쓰는 경우 : 다중상속(여러개의 클래스상속 불가능)이 불가능 
		
		MyThread1 th1 = new MyThread1(); //Thread 객체 생성 됨
		th1.start();//Thread가 종료되면 콘솔창에 terminated뜸  
		
		//.start()실행하지 않고 .run()을 쓰면 동시에 실행하지 않고 차례대로 실행됨
		
		//방법 2) Runnable 인터페이스를 구현한 class의 인스턴스를 생성한 후 이 인스턴스를 Thread객체의 인스턴스를 생성할때 생성자의 매개변수로 넘겨준다. //쓰레드만들시기에 파라미터로 넣어줌 //쓰는 경우 : 다중상속일 때
		//		이떄  생성된 Thread객체의 인스턴스의 start()메서드도 호출한다.
		
		MyThread2 r1 = new MyThread2();//runnable 객체 생성
		Thread th2 = new Thread(r1); 
		th2.start();  //별도의 풀스택이 만들어지면서 동시에 실행이됨 (run사용시 동시 실행 불가능)
		
		//방법3) 익명클래스를 이용하는 방법  (별도 클래스 없이 객체생성가능) ※쓰는경우 : 한번하고 말때 
		//		Runnable인터페이스를 구현한 익명클래스를 Thread 인스턴스를 생성할 때 매개변수로 넘겨준다.
		
		Thread th3 = new Thread(new Runnable() {
			
			@Override
			public void run() { //리턴타입 void/ 파라미터 없는 run
				for (int i = 0; i <=200; i++) {
					System.out.print("@");
					try {
						//Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다
						//시간은 밀리세컨드 단위를 사용한다
						//즉, 1000은 1초를 의미한다.
						Thread.sleep(100); 
					}catch(InterruptedException ex) {
						ex.printStackTrace();
					}
				}
				
			}
		});
		th3.start();
	} 
}
//방법1
class MyThread1 extends Thread{
	
	@Override
	public void run() {  //run 이중요함(업무분단을 해주는 역할을 함) 리턴타입,파라미터 없어야함
		for (int i = 0; i <=200; i++) {
			System.out.print("*");
			try {
				//Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다
				//시간은 밀리세컨드 단위를 사용한다
				//즉, 1000은 1초를 의미한다.
				Thread.sleep(100); 
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}
//방법2
class MyThread2 implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i <=200; i++) {
			System.out.print("$");
			try {
				//Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다
				//시간은 밀리세컨드 단위를 사용한다
				//즉, 1000은 1초를 의미한다.
				Thread.sleep(100); 
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
}











