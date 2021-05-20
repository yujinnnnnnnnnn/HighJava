package kr.or.ddit.basic;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


/*
문제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
	  컴퓨터의 숫자는 난수를 이용하여 구한다.
	  (스트라이크는 'S', 볼은 'B'로 출력한다.)

	컴퓨터의 난수가 9 5 7 일때 실행 예시)
		숫자입력 => 3 5 6
		3 5 6 ==> 1S 0B
		숫자입력 => 7 8 9
		7 8 9 ==> 0S 2B

		...
		숫자입력 => 9 5 7
		9 5 7 ==> 3S 0B

		5번째 만에 맞췄군요.
*/

public class T07_BaseBallTest {
	int[] num = new int[3]; // 난수가 저장되 배열
	int[] user;				// 사용자가 입력한 값을 저장할 배열

	int strike;	// 스트라이크 개수
	int ball;	// 볼 개수

	Scanner scan = new Scanner(System.in);

	/**
	 * 1 ~ 9 사이의 서로 다른 난수 3개를 만들어 배열에 저장하는 메서드 (Set이용)
	 */
	public void getRndNum(){

		Set<Integer> bbNumSet = new HashSet<Integer>();

		// Set을 이용한 3개의 난수 만들기(1~9)
		while(bbNumSet.size()<3){
			bbNumSet.add( (int)(Math.random() * 9 + 1) );
		}

		//num = bbNumSet.stream().mapToInt(i->i).toArray();

		// Set의 자료를 배열에 저장하기
		Iterator<Integer> it = bbNumSet.iterator();

		int i = 0; // 배열의 첨자 역할
		while(it.hasNext()){
			num[i++] = it.next().intValue();
		}


		// 데이터 섞기 (0번째 자료와 난수번째 자료를 교환하는 방법으로 데이터를 섞는다.)
		for(int j=1; j<=100; j++){  // 작업 회수
			int rnd = (int)(Math.random() * num.length);  // 0 ~ 2 사이의 난수
			int temp = num[0];
			num[0] = num[rnd];
			num[rnd] = temp;
		}

		/*
		// shuffle을 이용한 데이터 섞기
		List<Integer> bbNumList = new ArrayList<>(bbNumSet);
		Collections.shuffle(bbNumList); // 데이터 섞기
		int i = 0; // 배열의 첨자 역할
		for(int bbNum : bbNumList) {
			num[i++] = bbNum;
		}

		*/
	}

	/**
	 * 사용자로 부터 3개의 정수를 입력받아 배열에 저장하는 메서드
	 * (입력한 정수들을 서로 중복되지 않게 처리 한다.)
	 */
	public void inputNum(){
		int n1, n2, n3;  // 입력한 값을 저장할 변수

		do{
			System.out.print("중복되지 않는 정수 3개 입력 => ");
			n1 = scan.nextInt();
			n2 = scan.nextInt();
			n3 = scan.nextInt();
			if(n1==n2 || n1==n3 || n2==n3){
				System.out.println("중복되는 숫자는 입력할 수 없습니다. 다시입력하세요");
			}
		}while(n1==n2 || n1==n3 || n2==n3);

		user = new int[]{n1, n2, n3};  // 입력한 값들을 배열에 저장
	}

	// 스트라이크와 볼 판정 및 출력하는 메서드
	public void ballCount(){
		strike = 0;
		ball = 0;   // 스트라이크 개수와 볼 개수 초기화

		for(int i=0; i<num.length; i++){
			for(int j=0; j<user.length; j++){
				if(num[i] == user[j]){  // 값이 같은지 비교
					if(i==j){   // 값이 같고 첨자가 같으면 스트라이크
						strike++;
					}else{		// 값은 같은데 첨자가 다르면 볼
						ball++;
					}
				}
			}
		}

		System.out.println(user[0] + " " + user[1] + " " + user[2] + " ==> " + strike + "S " + ball + "B");

	}


	// 게임을 시작하는 메서드
	public void gameStart(){
		// 난수를 만드는 메서드 호출
		getRndNum();

		// 확인용
		System.out.println("난수값 => " + num[0] + " " + num[1] + " " + num[2]);

		int cnt = 0; // 몇번만에 맞췄는지를 저장하는 변수

		do{
			cnt++;
			inputNum(); // 사용자 입력 메서드 호출
			ballCount();  // 볼카운트하는 메서드 호출
		}while(strike!=3);  // 3 스트라이크가 될 때까지 반복

		System.out.println(cnt + "번째만에 맞췄군요.");
	}

	public static void main(String[] args) {
		T07_BaseBallTest baseBall = new T07_BaseBallTest();
		baseBall.gameStart();
	}

}
