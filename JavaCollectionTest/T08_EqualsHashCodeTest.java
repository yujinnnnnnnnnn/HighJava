package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Set;

public class T08_EqualsHashCodeTest {

	/**
	 * 해시함수는 임의의 길이의 데이터를 고정된 길이의 데이터로 매핑하는 함수이다
	 * 해시함수에 의해 얻어지는 값은 해시값, 해시코드, 해시 체크섬 또는 간단하게 해시라고 한다
	 * 
	 * HashSet, HashMap, Hashtable과 같은 객체들을 사용할 경우
	 * 객체가 서로 같은지를 비교하기 위해 equals() 메서드와 hashCode()메서드를 호출한다
	 * 그래서 객체가 서로 같은지 여부를 결정하려면 두 메서드를 재정의 해야한다.
	 * 
	 * - equals()메서드는 두객체의 내용(값)이 같은지 비교하는 메서드이고,
	 * - hashCode() 메서드는 두객체가 같은 객체인지 비교하는 메서드로 사용된다.
	 *
	 * -equals()메서드와 hashCode()메서드에 관련된 규칙
	 * 1. 두 객체가 같으면 반드시 같은 hashCode를 가져야한다  (hashCode는 속도가 빠름)
	 * 2. 두 객체가 같으면 equals()메서드를 호출했을때 true를 반환해야한다
	 *    즉, 객체 a, b가 같다면 a.equals(b)와 b.equals(a) 둘다 true이여야한다
	 * 3. 두 객체의 hashCode가 같다고 해서 두 객체가 반드시 같은 객체는 아니다 
	 *    하지만, 두 객체가 같으면 반드시 hashCode가 같아야한다
	 * 4.equals()메서드를 override하면 반드시 hashCode()메서드도 override해야한다.
	 * 5. hashCode()는 기본적으로 Heap메모리에 있는 각 객체에 대한 메모리 주소 매핑 정보를 기반으로 한 정수 값을 반환한다
	 *    그러므로 클래스에서 hashCode메서드를 override하지않으면 절대로 두 객체가 같은 것으로 간주될 수 없다.
	 *    
	 * hashCode()메서드에 사용하는 '해싱 알로리즘'에서 서로 다른 객체에 대하여 같은 hashCode값을 만들어 낼 수 있다.
	 * 그래서 객체가 같지않더라도 hashCode가 같을 수 있다
	 * 
	 * hashCode 값이  다르면 두 객체는 서로 다른 객체
	 * 서로다른 두객체가 같은 hashCode 값을 가지고 있을경우를 판단하기 위해 equals()메서드를 사용
	 * 1) hashCode 체크
	 * 2) equals()메서드 호출 
	 */
	
	public static void main(String[] args) {
		Person p1 = new Person(1,"홍길동");
		Person p2 = new Person(1,"홍길동");
		Person p3 = new Person(1,"이순신");
		
		System.out.println("p1.equals(p2) : " + p1.equals(p2)); //p1과 p2다름 필드값만 같은 것임
		System.out.println("p1 == p2 : " + (p1 == p2)); //메모리 위치가 달라서 오버라이드해도 false나옴
		
		Set<Person> set =new HashSet<>();
		set.add(p1);
		set.add(p2);
		
		System.out.println("p1, p2 등록 후 데이터");
		for(Person p : set) {  //향상된 포문을 사용한 경우 iterator사용 안해도됨
			System.out.println(p.getId() + " : " + p.getName()); // hashCode를 override하지 않아서 다른 객체로 인식
			
		}
		System.out.println("add(p3) 성공여부 : " + set.add(p3));
		
		System.out.println("add(p3) 후 데이터");
		for(Person p : set) {
			System.out.println(p.getId() + " : " + p.getName());
			}
		
		System.out.println("remove(p2) 성공여부 : " + set.remove(p2));
		
		System.out.println("remove(p2) 후 데이터");
		for(Person p : set) {
			System.out.println(p.getId() + " : " + p.getName());
			}
	
	}
}

class Person{
	private int id;
	private String name;
	
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) { 
		if (this == obj)  //obj의 값이 p1과 같을때를 뜻함 
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass()) //getClass : this.getClass [현재갖고있는 객체(p1)의 클래스의 정보]
			return false;  // 다른 클래스의 객체들 비교
		Person other = (Person) obj; //other : p2
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


}

















