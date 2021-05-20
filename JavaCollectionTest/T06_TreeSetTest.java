package kr.or.ddit.basic;

import java.util.SortedSet;
import java.util.TreeSet;

public class T06_TreeSetTest {

	public static void main(String[] args) {
		//HashHSet은 데이터에 순서가 없으나(등록되는 순서를 알지 못함) TreeSet은 자동 정렬 기능이 들어가 있다
		TreeSet<String> ts = new TreeSet<>();
		
		//영어 대문자를 문자열로 변환하여 TreeSet 에 저장하기
		for(char ch = 'Z'; ch >= 'A'; ch--) { //자동정렬을 보기 위해 반대로 저장함 
			String temp = String.valueOf(ch);
			ts.add(temp);
		}
		System.out.println("TreeSet 자료 : "+ ts);
		
		//TreeSet에 저장된 자료 중 특정한 자료보다 작은 자료를 찾아서 SortedSet으로 변환하는 메서드가 있다.
		// => headSet(기준값) 메서드(기본적으로 '기준값'은 포함시키지 않음)
		//=> headSet(기준값, 논리값) (논리값이 true이면 '기준값'을 포함함)
		SortedSet<String> ss1 = ts.headSet("K");
		System.out.println("K 이전 자료 : " + ss1); // 기준값 미포함
		System.out.println("K 이전 자료(기준값 포함) : " + ts.headSet("K", true));
		
		//'기준값'보다 큰 자료를 찾아SortedSet으로 반환하는 메서드
		//tailSet(기준값) => 기본적으로 '기준값'을 포함시킨다
		//tailSet(기준값, 논리값) => 논리값이 false이면 '기준값'을 포함시키지 않음
		SortedSet<String> ss2 = ts.tailSet("K");
		System.out.println("K 이후 자료 : "+  ss2);
		System.out.println("K 이후 자료 (기준값 미포함) : "+ ts.tailSet("K", false));
		
		//subSet(기준값 1, 기준값 2) => 기준값1~기준값2 사이의 값을 가져온다.
		//                         ('기준값1'포함, '기준갑 2' 미포함) : default
		//subSet(기준값 1, 논리값 1, 기준값2, 논리값 2) => 각 기준값을 포함할지 여부를 설정한다 (논리값이 true이면 포함 아니면 불포함)
		System.out.println("K (포함)부터 N(미포함) 까지 : " + ts.subSet("K", "N"));  //subSet : 부분집합을 뜻함
		System.out.println("K (포함)부터 N(포함) 까지 : " + ts.subSet("K", true, "N", true));
		System.out.println("K (미포함)부터 N(미포함) 까지 : " + ts.subSet("K", false, "N", false));
		System.out.println("K (미포함)부터 N(포함) 까지 : " + ts.subSet("K", false, "N", true));
		
		
		
	}

}
