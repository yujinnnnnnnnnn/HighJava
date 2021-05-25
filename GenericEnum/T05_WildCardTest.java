package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T05_WildCardTest {

	/*
	   	와일드 카드(<?>) 선언 방법  => 제너릭한타입이 어떤 타입인지 모를때 사용 (제너릭 선언을 안했을때 제너릭 객체를 사용할때 필요) ※제너릭일떄만 와일드카드 사용가능
	 	<? extends T> : 와일드카드의 상한 제한. T와 그 자손들만 가능
	 	<? super T>   : 와일드카드의 하한 제한. T와 그 조상들만 가능
	 	<?>			  : 모든타입이 가능 <? extends Object> 와 동일 
	 	사용할 시기 : 변수를 선언할때 (제너릭한 타입)/
	 */
	
	public static void main(String[] args) {
		
		FruitBox<Fruit> fruitBox = new FruitBox<>(); //과일상자
		FruitBox<Apple> appleBox = new FruitBox<>(); //과일상자
		FruitBox<? extends Fruit> fruitBox2 = new FruitBox<Fruit>(); //과일상자
		
		fruitBox.add(new Apple());
		fruitBox.add(new Grape());
		
		appleBox.add(new Apple());
//		appleBox.add(new Grape());  // 타입이 맞지않아 못담음
		
		Juicer.makeJuice(fruitBox);
		Juicer.makeJuice(appleBox);  
		//타입이 맞지않음 FruitBox만 와야함 => 그래서 Juicer클래스의 FruitBox<Fruit>를 <T>로 바꿔줌 => <T>로 바꿔주면 아무 타입이 다 들어오므로<T extends Fruit>로 Fruit타입들만 올 수 있도록 함 
	}
}

class Juicer {
//	static void makeJuice(FruitBox<Fruit> box) {
//	static <T extends Fruit> void makeJuice(FruitBox<T> box) {
	static void makeJuice(FruitBox<? extends Fruit> box) {
		String fruitListStr = ""; // 과일 목록
		
		int cnt = 0;
		for(Fruit f : box.getFruitList()) { //와일드 카드의 extends한 Fruit가 옴
			if(cnt == 0) {
				fruitListStr += f;
			}else {
				fruitListStr += "," + f;
			}
			cnt++;
		}
		System.out.println(fruitListStr + " => 쥬스 완성 !");
	}
}
/**
 * 과일 (최상위)
 */
class Fruit {
	private String name; // 과일이름

	public Fruit(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "과일 (" + name + ")";
	}
	
}

class Apple extends Fruit{
	public Apple() {
		super("사과");
	}
}
class Grape extends Fruit{
	public  Grape() {
		super("포도");
	}
}
//
class FruitBox<T>{
	private List<T> fruitList;

	public FruitBox() {
		fruitList = new ArrayList<T>();
	}
	
	public List<T> getFruitList() {
		return fruitList;
	}

	public void setFruitList(List<T> fruitList) {
		this.fruitList = fruitList;
	}

	public void add(T fruit) {
		fruitList.add(fruit);
	}
	
	
}