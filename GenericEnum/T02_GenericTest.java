package kr.or.ddit.basic;

/**
 * 제너릭 클래스 만드는 방법
 * 
 * 형식 ) 
 * class 클래스명 <제너릭타입 글자>{  /<> 이안의 문자는 {}안의 타입을 의미함 
 * 		제너릭타입글자 변수명; //변수선언에 제너릭을 사용할 경우
 * 		...
 * 		제너릭타입글자 메서드명(){ //반환값이 있는 메서드에서 사용
 * 			return 값;			
 * 		}
 * 		...
 * }
 *
 * -- 제너릭 타입 글자--
 * T => Type
 * K => Key
 * V => Value
 * E => Element(자료구조에 들어가는 요소들을 나타낼 떄 사용)
 * 
 * 컴파일한 시점에 객체를 만들때 반드시 알려줘야함
 */

class NonGenericClass{
	private Object val;

	public Object getVal() {
		return val;
	}

	public void setVal(Object val) {
		this.val = val;
	}	
}
class MyGeneri<T>{  //<> 안에 타입 여러개 가능  
	private T val;

	public T getVal() {
		return val;
	}

	public void setVal(T val) {
		this.val = val;
	}
	
}
public class T02_GenericTest {

	public static void main(String[] args) {
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setVal("가나다라");
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setVal(100);

		String rtnNg1 = (String) ng1.getVal();
		System.out.println("문자열 반환값 trtnNg1 => " + rtnNg1);
		
		Integer irtnNg2 = (Integer) ng2.getVal();
		System.out.println("정수 반환값 irtnNg1 => " + irtnNg2);
		
		MyGeneri<String> mg1 = new MyGeneri<String>();  // 내가 사용한 타입을 명확히 알려줘야함
		MyGeneri<Integer> mg2 = new MyGeneri<Integer>();
		
		mg1.setVal("우리나라");
		mg2.setVal(500);
		
		rtnNg1 = mg1.getVal();
		irtnNg2 = mg2.getVal();
		
	}
}
















