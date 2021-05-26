package kr.or.ddit.basic;

public class T15_SyncThreadTest {

	public static void main(String[] args) {
		
		ShareObject sObj = new ShareObject();
		
		WorkerThread th1 = new WorkerThread("1번 스레드", sObj);
		WorkerThread th2 = new WorkerThread("2번 스레드", sObj);
		
		th1.start();
		th2.start();
	}
}
//공통으로 사용할 객체
class ShareObject {
	private int sum = 0;
	
	//동기화 하는 방법1 : 메서드 자체에 동기화 설정하기 (= synchronized)
//	synchronized public void add() { //synchronized 동기화 처리함 => 여기에 접근하는 쓰레드는 동시에 접근 불가능 [순차적으로 출력됨] //동기화를 하면 공유객체에 하나의 쓰레드씩 들어갈 수 있음(공유객체에 하나의 쓰레드만 들어올 수 있음)  // 동기화처리하면 속도가 줄어듬 
	public void add() {
	//동기화 하는 방법2 : 동기화 블럭으로 설정하기
		synchronized (this) { //전체영역을 동기화 처리한것  = 메서드 자체에 동기화  // synchronized는 동기화 영역이 메서드 전체이지만 동기화 블럭은 범위지정가능 // 가능하면 동기화는 최소화가 좋음 (속도가 영향을 많이 받지 않기 때문) 
			
			for (int i = 0; i < 1000000000; i++) {} // 동기화처리전까지 시간벌기용 // 먼저들어왔다고 먼저 나가진 않음 따라서 synchronized로 제한을 걸어두는 것 //  for문을 넣어주므로써 시간을 넣어주어 랜덤하게 들어올 수 있도록 만듬
		
			int n = sum;
			n += 10; //10 증가
			sum= n;
		
			System.out.println(Thread.currentThread().getName() + "합계 : " + sum);
		}
	}
	
	public int getSum() {
		return sum;

	}
}

//작업을 수행하는 스레드
class WorkerThread extends Thread {
	ShareObject sObj;
	public WorkerThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	}
	@Override
	public void run() {
		for (int i = 1; i <=10; i++) {
			sObj.add();
		}
	}
}