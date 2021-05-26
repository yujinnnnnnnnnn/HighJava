package kr.or.ddit.basic;

public class T12_ThreadYieldTest {

	/**
	  yield() 메서드에 대하여
	  
	  1. 현재 실행 대기중인 동등한 위선순의 이상의 다른 스레드에게 실행기회를 제공한다 (양보) 
	  2. 현재 실행중인 스레드의 상태를 Runnable상태로 바꾼다.(Waiting이나 Blocked 상태로 바뀌지 않는다) /Runnable(실행중) -> Runnable(실행대기)
	  3. yield()메서드를 실행한다고 해서 현재 실행중인 스레드가 곧 바로 runnable상태로 전이된다고 학신할 수 없다. (확실하게 양보가 되지않아 비추)
	 */
	public static void main(String[] args) {
		Thread th1 = new YieldThreadTestEx1();
		Thread th2 = new YieldThreadTestEx2();
		
		th1.start(); 
		th2.start();
	}
}

class YieldThreadTestEx1 extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("YieldThreadTestEx1 : " + i);
			yield(); // 양보하기
		}
	}
	
}
class YieldThreadTestEx2 extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("YieldThreadTestEx2 : " + i);
		}
	}
	
}