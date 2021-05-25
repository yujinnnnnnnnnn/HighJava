package kr.or.ddit.basic;

class Util2 {
	public static <T extends Number> int compare(T t1, T t2) { //extends Number : extends Number한 타입과 Number만 가능
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
		
		return Double.compare(v1, v2); // Number 타입의 파라미터를 받아서 비교 (제너릭 문법에 존재함)
		
	}
}
/**
 * 제한된 타입 파라미터 (Bound Parameter) 예제 (범위가 아닌 타입은 접근 못하도록 제약조건을 거는것)
 */
public class T04_GenericMethodTest {

	public static void main(String[] args) {
		int result1 = Util2.compare(10, 20);
		System.out.println(result1);
		
		int result2 = Util2.compare(3.14, 3);
		System.out.println(result2);
		
//		Util2.compare("자바", "홍길동"); // 타입이 맞지않아 에러뜸 
	}
}
