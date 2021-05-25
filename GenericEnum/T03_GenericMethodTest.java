package kr.or.ddit.basic;


class Util{
	
	/**
	 * 제너릭 메서드 <T,R> R method(T t)
	 * => 파라미터 타입과 리턴타입으로 타입파라미터를 가지는 메서드
	 * 	선언방법 : ★리턴타입 앞에 <>기호를 추가★하고 타입 파라미터를 기술 후 사용한다.
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) { //Pair 도 제너릭임
		
		boolean keyCompare = p1.getKey().equals(p2.getKey());
		boolean valueCompare = p1.getValue().equals(p2.getValue());
		
		return keyCompare && valueCompare; // true 가나올경우 두 객체는 동일 객체임을 알 수 있음
	}
}

/**
 * 멀티타입 <K,V> 를 가지는 제너릭 클래스
 *
 */
class Pair<K, V> {  //전역변수
	private K key;
	private V value;
	
	public Pair(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	
	// 키와 값을 출력하기
	public <K, V> void displayAll(K key, V val) {  //<> 안의 타입은 {}안에서만 사용해햐하는데  위에 Pair<> 타입과 동일한 형태지만 다른 타입임 (displayAll메서드의 타입임) //지역변수
		System.out.println(key.toString() + " : " + val.toString());
	}
}
public class T03_GenericMethodTest {

	public static void main(String[] args) {

		Pair<Integer, String> p1 = new Pair<Integer, String>(1, "홍길동");
		Pair<Integer, String> p2 = new Pair<Integer, String>(1, "홍길동");
		
		//구체적 ㅇ타입을 명시적으로 지정 (생략가능)
		boolean result1 = Util.<Integer, String>compare(p1,p2); //compare 은 제너릭 메서드임
		
		if(result1) {
			System.out.println("논리(의미)적으로 동일한 객체임");
		}else {
			System.out.println("논리(의미)적으로 동일한 객체가 아님");
		}
		Pair<String, String> p3 = new Pair<String, String>("001","홍길동");
		Pair<String, String> p4 = new Pair<String, String>("002","홍길동");
		
		boolean result2 = Util.compare(p3,p4);
		if(result2) {
			System.out.println("논리(의미)적으로 동일한 객체임");
		}else {
			System.out.println("논리(의미)적으로 동일한 객체가 아님");
		}
		
		//제너릭 메서드 테스트
		p1.<String, Integer> displayAll("키", 180); // 타입을 바꿔도 잘 동작함 (제너릭이 있을경우) / 제너릭이 없으면 에러뜸
	}
}























