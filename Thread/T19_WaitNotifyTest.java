package kr.or.ddit.basic;
//동기화는 잘되어있지만 처리할 수 없는 쓰레드가 들어왔을 경우

public class T19_WaitNotifyTest {
/*
  	wait()메서드 => 동기화 영역에서 락을 풀고 Wait-Set영역(공유객체별로 존재함)으로 이동시킨다.
  	
  	notify() 또는 notifyAll()메서드 => Wait-Set영역에 있는 스레드를 깨워서 실행 될 수 있도록 한다.
  	(notify()는 하나, notifyAll()은 Wait-Set에 있는 전부를 깨운다.)
  	
  	=> wait()과 notify(), notifyAll()메서드는 동기화 영역에서만 실행 할 수 있고, Object클래스에서 제공하는 메서드이다.
 */
	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		Thread thA = new ThreadA(workObj);
		Thread thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
	}
}
//공유객체 (동기화엔 공유객체 필요)
class WorkObject {
	public synchronized void methodA() {
		System.out.println("methodA()메서드 작업중");
		
		notify();
		
		try {
			wait(); //wait set에 들어가서 락을 해제하여 methodB가 들어옴
		} catch (InterruptedException e) {
			e.printStackTrace(); 
		}
	}
	
	public synchronized void methodB() { // 마지막 methodB는 methodA가 작업을 종료하여 wait set에 있는 methodB를 깨울 수 없으므로 계속 대기중인 상태로 남음
		System.out.println("methodB()메서드 작업중");
		
		notify(); //대기중인 쓰레드 깨우고
		
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

//WorkObject의 methodA()메서드만 호출하는 스레드
class ThreadA extends Thread{
	private WorkObject workObj;

	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <=10; i++) {
			workObj.methodA();
		}
		System.out.println("ThreadA종료 ");
	}
}

//WorkObject의 methodB()메서드만 호출하는 스레드
class ThreadB extends Thread{
	private WorkObject workObj;

	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <=10; i++) {
			workObj.methodB();
		}
		System.out.println("ThreadB종료 ");
	}
}


















