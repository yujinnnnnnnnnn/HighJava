package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class T18_SyncCollectionTest {
	/**
	 * Vector, Hashtable 등의 예전부터 존재하던 Collection들은 내부에 동기화 처리가 되어있다 하지만, 최근에 새로 구성된
	 * Collection들은 동기화 처리가 되어있지 않다. 그래서 동기화 처리가 필요한 프로그램에서 이런 Collection들을 사용하려면
	 * 동기화 처리를 한 후에 사용해야한다 // 동기화 처리를 하게되면 속도가 매우 느리기때문에 데이터가 많은 collection은 동기화 처리를
	 * 안해주고 필요시 동기화 처리해주게 함
	 */
	// 동기화 처리를 하지 않을 경우
	private static List<Integer> list1 = new ArrayList<Integer>();
	
	//동기화 처리를 하는 경우
	//Collections의 정적 메서드 중에서 SynChronized로 시작하는 메서드 사용
	private static List<Integer> list2 = Collections.synchronizedList(new ArrayList<>()); // arraylist가 동기화 처리됨

	public static void main(String[] args) {
		// 익멱클래스로 스레드 구현
		Runnable r = new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 10000; i++) {
//					list1.add(i); // 동기화 처리 하지 않은 리스트
					list2.add(i);
				}
			}
		};
		
		Thread[] ths = new Thread[] { new Thread(r), new Thread(r), new Thread(r), new Thread(r), new Thread(r) };
		
		for (Thread th : ths) {
			th.start();
		}
		for (Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
//		System.out.println("list의 개수 : " + list1.size());
		System.out.println("list2의 개수 : " + list2.size());
	}
}
