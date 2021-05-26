package kr.or.ddit.basic;

public class T13_ThreadStopTest {
/**
 * Thread의 stop()메서드를 호출하면 스레드가 바로 멈춘다
 * 이때 사용하던 자원을 정리하지 못하고 프로그램이 종료되어서 나중에 실행되는 프로그램에 영향을 줄 수 있다.
 * 그래서 현재는 stop()메서드는 비추천으로 되어 있다.
 * 남아있는 쓰레드를 원할때 종료시킬때 사용
 */
	public static void main(String[] args) {
		/*ThreadStopEx1 th = new ThreadStopEx1();
		th.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		th.stop(); //강제종료 (처리중만 출력되다 종료됨)
		th.setStop(true);
		*/
		
		//interrupt()메서드를 이용한 스레드 멈추기
		ThreadStopEx2 th2 = new ThreadStopEx2(); //(interrupt이용한 첫번째 방법)
		th2.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		th2.interrupt(); // th2에 인터럽트 걸기 (정지시키는 역할) /해당 쓰레드가 interrupt가 걸림 -> 무한루프 빠져나옴(try/catch)
	}
}

class ThreadStopEx1 extends Thread {
	private boolean stop;
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}


	@Override
	public void run() {
		while(!stop) {
			System.out.println("스레드 처리 중...");
		}
		System.out.println("자원 정리 중.."); 
		System.out.println("실행종료");
	}
	
}


//interrupt()메서드를 이용하여 스레드를 멈추는 방법 
class ThreadStopEx2 extends Thread {
	@Override
	public void run() {
	/*
		방법 1) ★ sleep()메서드나 join()메서드 등을 사용했을 때 interrupt()메서드를 호출하면 InterruptedException이 발생한다.
		 
	 
		try {        
			while(true) {
				System.out.println("쓰레드 처리중 ...");
				Thread.sleep(1); //sleep을 하는동안 interrupt를 걸어서 예외를 발생함 (예외발생상황 만들어주기 위해 sleep사용) 
			}
		} catch (InterruptedException e) {} //예외만 잡고 아무것도 안함 => 예외가 발생하면 while문을 빠져나가는 용도
		*/
		
		/*
		 방법 2)  interrupt()메서드가 호출되었는지 검사하기
		 */
		while(true) {
			System.out.println("스레드 처리중...");
			
			//검사방법1 => 스레드의 인스턴스 객체용 메서드를 이용하는 방법 [★ this.isInterrupted()] (interrupt걸렸는지 안걸렸는지 확인)
//			if(this.isInterrupted()) { // interrupt()메서드 호출되면 true /this = Thread객체
//				System.out.println("인스턴스용 inInerrupted()");
//				break;
//			}
			
			//검사방법2 => 스레드의 정적 메서드를 이용하는 방법 [★  Thread.interrupted()(static메서드)]
			if(Thread.interrupted()) { //interrupt()메서드가호출되면 true /static 방식의 메서드는 호출하고나면 원래값으로 돌아옴 (false로 돌아옴) / 한번만 사용해야됨  ex) 두번 호출하면 한번은 true/ 두번은 false로 돌아옴
				System.out.println("정적 메서드 interrupted()");
				break;
			}
		}
		
		System.out.println("자원 정리 중...");
		System.out.println("실행종료.");
	}
}








