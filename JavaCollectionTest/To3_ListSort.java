package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class To3_ListSort {

	/*
	 *  정렬과 관련된 Interface 는 Comparable 과 Comparator 두가지가 있다
	 *  
	 *  -보통 객체 자체에 정렬 기능을 넣기 위해서는Comparable을 구현하고, 정렬 기준을 별도로 구현하고 싶을 때는 Comparator를 구현하여
	 *  사용하면 된다
	 *  
	 *  -Comparable 에서는 CompareTo()메서드를 구현해야하고, Comparator에서는 Compare()메서드를 구현해야한다
	 *  
	 */
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		
	System.out.println("정렬 전 : " + list);
	
	//정렬은 Collections.sort()메서드를 이용하여 정렬한다  (Collections- 클래스 / Collection - 인터페이스)
	//정렬은 기본적으로 '오름차순 정렬'을 수행한다
	// 정렬방식을 변경하려면 정렬방식을 결정하는 객체를 만들어서  Collections.sort()메서드에 인수로 넘겨주면 된다
	 Collections.sort(list);  //오름차순으로 정렬하기
	 System.out.println("정렬 후 : " + list);
	
	 Collections.shuffle(list); //데이터를 섞어준다.
	 System.out.println("자료 섞기 후 : " + list);
	 
	 //정렬 방식을 결정하는 객체를 이용하여 정렬하기
	 Collections.sort(list, new Desc());
	 System.out.println("정렬 후 : " + list);
	 
	 
	}
}

/*
 * 정렬 방식을 결정하는 class 는 Comparator라는 인터페이스를 구현해야함
 * 이 Comparator 인터페이스의 compare()라는 메서드를 재정의 하여 구현하면 된다  /compare - 파라미터 2개 필요 , compareTo - 파라미터 1개  
 */
class Desc implements Comparator<String>{

	/*
	  compare()메서드의 반환값을 결정하는 방법
	  => 이 메서드가 양수를 반환하면 두값의 순서가 바뀐다.(오름차순이 기본임)
	  
	  - 오름자순 정렬일 경우 
	  => 앞의 값이 크면 양수, 같으면 0 앞의 값이 작으면 음수를 반환하도록 한다
	  
	  - String 객체에는 정렬을 위해서 compareTo()메서드가 구현되어 있는 데 이메서드의 반환값은 오름차순에 맞게 반환되도록 구현되어 있다.
	  (Wrapper클래스와 Date, File 클래스에도 구현되어 있다.)
	 */
	@Override
	public int compare(String str1, String str2) {  //내가 만든 객체의 정렬기능 구현하고 싶을때 (클래스 자체의 정렬기능 비교시) comparable / 정렬기능 갖고있는 별도의 클래스를 구현하고 싶을 떄 (외부정렬자로써 두개의 배교대상을 구현하는것은) comparator
		
		return str1.compareTo(str2) * -1;  // 내림차순 : * -1 
	}
	
}






