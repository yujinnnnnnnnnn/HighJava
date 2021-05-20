package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.ScanUtil;

public class BaseBall {

	public static void main(String[] args) {
		
		Set<Integer> intRnd = new HashSet<>();
		
//		Iterator it = intRnd.iterator();
		while(intRnd.size() < 3) {
			int num = (int)(Math.random() * 9 +1);
			intRnd.add(num);
		}

		int n = 0;
		int n2 = 0;
		int n3 = 0;
		System.out.println(intRnd);
		List<Integer> intRndList = new ArrayList<>(intRnd);

		int st = 0;
		int ball = 0;
		int out = 0;
	
		do{System.out.println("숫자 입력 >");
		n = ScanUtil.nextInt();
		System.out.println("숫자 입력 >");
		n2 = ScanUtil.nextInt();
		System.out.println("숫자 입력 >");
		n3 = ScanUtil.nextInt();
		
		if(n == intRndList.get(0)) {
			st++;
		}else if (n == intRndList.get(1) || n == intRndList.get(2)) {
			ball++;
		}else {
			out++;
		}
		if(n2 == intRndList.get(1)) {
			st++;
		}else if (n2 == intRndList.get(0) || n2 == intRndList.get(2)) {
			ball++;
		}else {
			out++;
		}
		if(n3 == intRndList.get(2)) {
			st++;
		}else if (n3 == intRndList.get(0) || n3 == intRndList.get(1)) {
			ball++;
		}else {
			out++;
		}		
		System.out.println(st + "S " + ball + "B " + out + "O" );
		
		st = 0;
		ball = 0;
		out = 0;
		
		}while(n != intRndList.get(0) || n2 != intRndList.get(1) || n3 != intRndList.get(2));
		System.out.println("정답입니다.");
	}
}
