package kr.or.ddit.basic;

public class T09_ThreadDaemonTest {

	public static void main(String[] args) {
		Thread autoSave = new AutoSaveThread();
		
		//데모 스레드로 설정하기(start()메서드 호출하기 전에 설정해야한다.)스타트 전에 설정해줘야 스타트때 사용가능
		autoSave.setDaemon(true);  //데몬으로 사용하고 싶을때 .setDaemon 으로 설정해주면 됨 (설정안하면 default값인 일반쓰레드임)
		
		autoSave.start(); 
		
		try {
			for (int i = 1; i <= 20; i++) {
				System.out.println("작업" + i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		System.out.println("매인 스레드 종료.."); //일반스레드 종료
	}
}

/**
 * 자동 저장 기능을 제공하는 스레드(3초에 한번씩 저장한다) (1)
 * @author PC-19
 *
 */
class AutoSaveThread extends Thread{
	public void save() {
		System.out.println("작업 내용을 저장합니다..."); //저장내용 확인 메서드
	}
	
	@Override
	public void run() {
		while(true) {  					//무한루프이지만 일반스레드가 종료되면 데몬스레드인 autoThread도 같이 종료됨
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			save(); //저장기능 호출
		}
	}
	
}