package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// 호텔 저장시점 : 호텔 문닫을 때  <-> 호텔 시작 시점에 오브젝트 객체로 만들어야함
/**
 * 객체 입출력보조 스트림 예제 (직렬화와 역직렬화)
 * 직렬화가 필요할때 : Object의 데이터를 입출력할때 
 * @author PC-19
 *
 */

public class T15_ObjectStreamTest {

	public static void main(String[] args) {
		//Member 인스턴스 생성
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("일지매", 30, "대구");
		Member mem3 = new Member("이몽룡", 40, "광주");
		Member mem4 = new Member("성춘향", 50, "부산");
		
		try {
			//객체를 파일에 저장하기
			
			//출력용 스트림 객체 생성
			ObjectOutputStream oos = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream("d:/D_Other/memObj.bin")) );
			               //버퍼기능
			//쓰기 작업
			oos.writeObject(mem1); //직렬화  [write하고 싶은 객체를 파라미터로 넣어줌] 
			oos.writeObject(mem2); //직렬화
			oos.writeObject(mem3); //직렬화
			oos.writeObject(mem4); //직렬화
			
			System.out.println("쓰기 작업 완료");
			oos.close();
			
			//==========================================================
			
			//저장한 객체를 읽어와 출력하기
			
			//입력용 스트림 객체 생성
			ObjectInputStream ois = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream("d:/D_Other/memObj.bin")) );
			
			Object obj = null;
			
									//Object단위로 읽음 
				while((obj = ois.readObject()) != null) {
					//읽어온 데이터를 원래의 객체형으로 변환 후 사용한다
					Member mem = (Member) obj; //실제 사용할때는 해당하는 타입으로 캐스팅해줘야함
					System.out.println("이름 : " + mem.getName());
					System.out.println("나이 : " + mem.getAge());
					System.out.println("주소 : " + mem.getAddr());
					System.out.println("--------------------------");
				}
				ois.close();
				
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
			
		}catch(IOException ex) {  
			//더이상 읽어올 객체가 없으면 예외 발생함
//			ex.printStackTrace(); // 예외발생을 출력하기 위해 사용됨 
			System.out.println("출력 작업 끝");
		}
	}
}
class Member implements Serializable{   // 직렬화는 인스턴스 변수에 담긴 정보들만 직렬화가능
	/*
	 	자바는 Serializable 인터페이스를 구현한 클래스만 직렬화 할 수 있도록 제한하고 있음
	 	구현하지 않으면 직렬화 작업 시 java.io.NotSerializableException 예외 발생함
	 */
	
	/*
	 	transient => 직렬화가 되지 않을 멤버변수에 지정한다 (일시적이라는 뜻) // 변수안에 있는 정보들 중 중요한 정보들은 제외하고 IO작업을 하고 싶을때 사용
	 				(*static 필드도 직렬화 대상이 아니다.) [인스턴스 객체가 아니기 때문]
	 	직렬화가 되지 않는 멤버변수는 기본값으로 저장된다
	 	(참조형변수 : null, 숫자형 변수 : 0)
	 */
	private transient String name;
	private transient int age;  // 실행하면 0이 됨 => age의 정보를 제외하여 값자체가 없기때문에 기본값 0으로 나옴
	private String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
}
