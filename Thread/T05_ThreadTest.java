package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/**
 * 단일 스레스에서의 사용자 입력 처리 예제
 * @author PC-19
 *
 */

public class T05_ThreadTest {

	public static void main(String[] args) {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요 >>"); //윈도우창을 닫을때까지 멈춰있음
		System.out.println("입력한 값은 " + str + "입니다.");
		
		for (int i = 10; i >= 1; i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000); //1초동안 잠시 멈춘다
			} catch (InterruptedException e) {
				e.printStackTrace(); 
			}
		}
	}// 쓰레드가 끝나면 프로세스도 종료됨 (쓰레드가 여러개일경우 여러개의 쓰레드가 모두 종료될 시점에 프로세스가 종료됨)
	
}
