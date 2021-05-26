package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/**
 * 멀티 스레드 활용한 카운트다운 처리(4)
 * @author PC-19
 *
 */
public class T06_ThreadTest {
	//입력 여부를 확인하기 위한 변수 선언
	// 모든 스레드에서 공통으로 사용할 변수 
	public static boolean inputCheck = false;  // 여러개의 쓰레드들이 사용할 수 있도록 static변수를 선언해줌 (1)
	
	public static void main(String[] args) {
		//폴스택 만들기
		Thread th1 = new DataInput(); 
		Thread th2 = new CountDown();
		
		th1.start();
		th2.start();
	} //main 쓰레드는 start시켜놓고 가장 먼저 죽음
}
/**
 * 데이터를 입력받는 스레드 (2)
 */
class DataInput extends Thread { 
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요 ."); //입력 받자마자 run이 실행되면서 inputCheck를 true로 바꿔줌
		
		//입력이 완료되면 inputCheck 변수를 true로 변경한다
		T06_ThreadTest.inputCheck = true;
		
		System.out.println("입력한 값은 " + str + "입니다");
	}
}
/**
 * 카운트다운하는 스레드(3)
 */
class CountDown extends Thread{
	@Override
	public void run() {
		for (int i = 10; i >=1; i--) {
			//입력이 완료되었는지 여부를 검사하고 입력이 완료되면  run()메서드를 종료시킨다 즉 현재 스레드를 종료한다
			if(T06_ThreadTest.inputCheck == true) {  //위에 DateInput이 잘 실행됐는지 확인
				return; // run()메서드가 종료되면 스레드도 끝난다  
			}
			
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//10초가 경과되었는데도 입력이 없으면 프로그램을 종료한다
		System.out.println("10초가 지냈습니다.프로그램을 종료합니다.");
		System.exit(0); 
	}
}
