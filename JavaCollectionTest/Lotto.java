package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.ScanUtil;

public class Lotto {

	// 사용자는 로또를 구매할 때 구매할 금액을 입력하고
	// 입력한 금액에 맞게 로또번호를 출력한다.
	// (단, 로또 한장의 금액은 1000원이고 거스름돈도 계산하여
	// 출력한다.)
	//
	// ==========================
	// Lotto 프로그램
	// --------------------------
	// 1. Lotto 구입
	// 2. 프로그램 종료
	// ==========================
	// 메뉴선택 : 1 <-- 입력
	//
	// Lotto 구입 시작
	//
	// (1000원에 로또번호 하나입니다.)
	// 금액 입력 : 2500 <-- 입력
	//
	// 행운의 로또번호는 아래와 같습니다.
	// 로또번호1 : 2,3,4,5,6,7
	// 로또번호2 : 20,21,22,23,24,25
	//
	// 받은 금액은 2500원이고 거스름돈은 500원입니다.
	//
	// ==========================
	// Lotto 프로그램
	// --------------------------
	// 1. Lotto 구입
	// 2. 프로그램 종료
	// ==========================
	// 메뉴선택 : 2 <-- 입력
	//
	// 감사합니다

	public static void main(String[] args) {
		while (true) {
			System.out.println("===========================");
			System.out.println("      Lotto 프로그램	       ");
			System.out.println("---------------------------");
			System.out.println("1. Lotto 구입                                   ");
			System.out.println("2. 프로그램 종료                                      ");
			System.out.println("===========================");

			System.out.print("메뉴 선택 : ");
			int input = ScanUtil.nextInt();
			switch (input) {
			case 1:
				buy();
				break;
			case 2:
				System.exit(2);
				break;
			}
		}
	}

	private static void buy() {
		System.out.println("Lotto 구입  시작");

		System.out.print("금액 입력 : ");
		int input = ScanUtil.nextInt();
		int a = input / 1000;
		int change = input % 1000;
		
		System.out.println("행운의 로또번호는 아래와 같습니다.");
		
		for (int i = 0; i < a; i++) {
			Set<Integer> intRnd = new HashSet<>();
			while (intRnd.size() < 6) {
				int ran = (int) (Math.random() * 45 + 1);
				intRnd.add(ran);
			}
			List<Integer> intRndList = new ArrayList<>(intRnd);
			
			System.out.print("로또번호" + (i+1) + " : " + intRndList.get(0));

			
			for (int j = 1; j < intRndList.size(); j++) {
				System.out.print("," + intRndList.get(j));
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("받은 금액은 " + input + "이고 거스름돈은 " + change + "원 입니다. ");

	}
}
