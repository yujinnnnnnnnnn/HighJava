package kr.or.ddit.basic;

public class T10_ThreadStatement {
/**
 	<쓰레드의 상태>
 	
 	-NEW : 쓰레드가 생성되고 아직 start()가 호출되지 않은 상태
 	-RUNNABLE : 실행 중 또는 실행 가능한 상태
 	-BLOCKED : 동기화 블럭에 의해서 일시정지된 상태 (lock이 풀릴떄까지 기다리는 상태)
 	-WAITING/ TIMED_WAITING : 스레드의 작업이 종료되지는 않았지만 실행가능하지않은 상태(UNRUNNABLE)
 								TIMED_WAITING은 일시정지시간이 지정된 경우임
 	- TERMINATED : 스레드의 작업이 종료된 상태 // 실행중인 모든 쓰레드가 종료되야 종료되는 것임
 */
	
	public static void main(String[] args) {
		Thread th = new StatePrintThread(new TargetThread());
		
		th.start();
	}
}
//스레드의 상태를 출력하는 스레드 클래스(이 클래스도 스레드로 작성)
class StatePrintThread extends Thread {
	private Thread targetThread; //상태를 출력할 스레드가 저장될변수
	
	public StatePrintThread(Thread targetThread) {
		this.targetThread = targetThread;
	}
	@Override
	public void run() {
		while(true) {
			//Thread의 상태 구하기 (getState()메서드 이용)
			Thread.State state = targetThread.getState();  //쓰레드에서 사용하는 enum타입 사용  //.getState()로 쓰레드의 상태를 알 수있음
			System.out.println("타겟 스레드의 상태값 : " + state);
			
			//new 상태인지 검사
			if(state == Thread.State.NEW) { // StatePrintThread가 Thread가 스타트시킴 (메인쓰레드가 하지않음)
				targetThread.start(); //main, StatePrintThread, targetThread 3개의 쓰레드가 돌고 있음
			}
			
			//타겟스레드가 종료 상태인지 검사
			if(state == Thread.State.TERMINATED) {
				break;
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

// Target용 스레드 클래스
class TargetThread extends Thread{
	@Override
	public void run() {
	for (long i = 1; i <=1000000000L; i++) {} //시간 지연용
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (long i = 1; i <=1000000000L; i++) {} //시간 지연용 //getState를 했다면 : runnable
	}
}