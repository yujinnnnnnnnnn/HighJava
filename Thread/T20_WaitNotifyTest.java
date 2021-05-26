package kr.or.ddit.basic;

public class T20_WaitNotifyTest {

	public static void main(String[] args) {
		DataBox dataBox = new DataBox();
		
		ProducerThread pth = new ProducerThread(dataBox);
		ConsumerThread cth = new ConsumerThread(dataBox);
		
		pth.start();
		cth.start();
	}
}
//데이터를 공통으로 사용하는 클래스
class DataBox {
	private String data;
	
	//data가 null이 아닐떄 data값을 반환하는메서드
	public synchronized String getData() {
		if(data == null) {
			try {
				wait(); //데이터가 없으면 아무런 의미가 없어서 wait set으로 넘어가서 대기상태를 가짐
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String returnData = data;
		System.out.println("읽어온 데이터 : "+ returnData);
		data = null; //데이터를 가져왔기때문에 데이터가 없는 것을 의미
		System.out.println(Thread.currentThread().getName() + " notify()호출"); //대기실에 있는 스레드 호출
		notify();
		
		return returnData;
	}
	
	//data가 null일 경우에만 자료를 세팅하는 메서드
	public synchronized void setData(String data) {
		if(this.data != null) {
			try {
				wait(); //이미 값이 있기때문에 세팅하지못해서 할일이 없음 => wait set 에서 대기상태를 가짐
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		this.data = data; //멤버변수에 값을 넣어 세팅함
		System.out.println("세팅한 데이터 : " + this.data);
		System.out.println(Thread.currentThread().getName() + " notify() 호출");
		notify(); //값을 가져가라고 깨움 
	}
}

//데이터를 세팅만 하는 스레드
class ProducerThread extends Thread {
	private DataBox dataBox; //공유객체 생성
	
	public ProducerThread(DataBox dataBox) {
		super("ProducerThread"); //String 생성자 넣어줌 => 쓰레드 네이밍에 세팅됨[이름을 명시적으로 세팅]
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <=10; i++) {
			String data = "Data-" + i;
			System.out.println("dataBox.setData(" + data + ") 호출");
			dataBox.setData(data);
		}
	}
}

//데이터를 읽어만 오는 스레드
class ConsumerThread extends Thread {
	private DataBox dataBox; // 공유객체 생성
	
	public ConsumerThread(DataBox dataBox) {
		super("ConsumerThread");
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <=10; i++) {
			String data = dataBox.getData();
			System.out.println("data.Box.getData() : " + data);
		}
	}
}























