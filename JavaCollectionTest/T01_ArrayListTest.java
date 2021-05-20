package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class T01_ArrayListTest {

	public static void main(String[] args) {
		//Default capacity = 10
		List list1 = new ArrayList<>();  //ArrayList 와 Vector도 사용 가능, Vector 기본적으로 동기화 되어있음
		
		//add()메서드를 사용해서 데이터를 추가한다
		list1.add("aaa");
		list1.add("bbb");
		list1.add(111);  //	<- 내부적으로 오토박싱됨	new Integer(111); 
		list1.add('k');
		list1.add(true);
		list1.add(12.34);
		
		//size 
		System.out.println("size => " + list1.size());
		System.out.println("list1 = > " + list1);

		//get으로 데이터 꺼내오기
		System.out.println("1번쨰 데이터 자료 : " + list1.get(1));
		
		//데이터 끼워 넣기도 같다
		list1.add(0, "zzz");
		System.out.println("list1 => " + list1);
		
		//데이터 변경 (set 메서드)
		String temp = (String)list1.set(0, "YYY");
		System.out.println("temp => " + temp);
		System.out.println("list1 => " + list1);
		
		//삭제
		list1.remove(0);
		System.out.println("삭제후 : " + list1);
		
		list1.remove(new Integer (111));   // 정수는 인덱스로 인식되기 때문에 객체로 넣어야함
		System.out.println("111삭제후 : " + list1);
		list1.remove("bbb");    
		System.out.println("bbb삭제후 :" + list1);
		System.out.println("===========================");
		
		//제너릭을 지정하여 선언 할 수 있다   -> 캐스팅할 필요 x <String>선언한 타입만 가능 
		List<String> list2 = new ArrayList<>();
		list2.add("AAA");
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");
		
		for (int i = 0; i < list2.size(); i++) {
			System.out.println(i + " : " +  list2.get(i));
		}		
		System.out.println();
		
		for(String s : list2) {  //인덱스에 대한 로직을 돌릴 수 없음(인덱스 사용 x)
			System.out.println(s);
		}
		System.out.println("----------------------------");
		
		
		// contains(비교객체); => 리스트에 '비교객체'가있으면 true, 없으면 false가 리턴됨
		System.out.println(list2.contains("DDD"));
		System.out.println(list2.contains("ZZZ"));
		
		//indexOf (비교객체) => 리스트에서 '비교객체'를 찾아 '비교객체'가있는 index값을 반환한다. 없으면 -1 반환함
		System.out.println("DDD의 index값 : " + list2.indexOf("DDD"));
		System.out.println("ZZZ의 index값 : " + list2.indexOf("ZZZ"));
		System.out.println("---------------------------------------");
		
		//toArray() => 리스트 안의 데이터들을 배열로 변환하여 반환한다. 기본적으로 Object배열 반환함
		Object[] strArr = list2.toArray();   //사용이유 : 배열이 필요할때
		System.out.println("배열의 개수 : "+ strArr.length);
		
		//리스트의 제너릭 타입에 맞는 자료형의 배열로 변환하는 방법
		// 제너릭 타입의 0개짜리 배열을 생성해서 매개변수로 넣어준다.
		//=> 배열의 크기가 리스트 크기보다 작으면 리스트의 크기에 맞는 배열을 생성한다.
		String[] strArr2 = list2.toArray(new String[0]);  //캐스팅할 수 없음 => 해당 타입의 배열을 파라미터로 받아줌
		System.out.println("strArr2의 개수 : " + strArr2.length);
		
		for (int i = 0; i < strArr.length; i++) {
			System.out.println(strArr[i]);
		}

		for (int i = 0; i < list2.size(); i++) {
			list2.remove(i);
		}
		System.out.println(list2.size());  //하나씩 지워지면서 인덱스도 재정렬되어 다 안지워짐 => 뒤에서 부터 지워야함
		
//		stack & Queue => 자료구조
		
		
		
		
		
		
		
	}
}
