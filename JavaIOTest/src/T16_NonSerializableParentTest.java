package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 	부모클래스가 Serializable 인터페이스를 구현하고 있지 않을 경우 부모객체의 필드값 처리 방법 (부모가 Serializable을 하지 않은 이유 : 자식모두 상속받기 때문)
 	
 	1. 부모 클래스가 Serializable 인터페이스를 구현하도록 해야한다 (원초적인 해결방법은 아님)
 	2. 자식 클래스에 WriteObject()와 readObject()메서드를 이용하여 부모객체의 필드값을 처리할 수 있도록 직접 구현한다
 */
public class T16_NonSerializableParentTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		FileOutputStream fos = new FileOutputStream("d:/D_Other/nonSeriaizableTest.bin");
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		Child child = new Child();
		child.setParentName("부모");
		child.setChildName("자식");
		oos.writeObject(child); // 직렬화
		oos.close();

		FileInputStream fis = new FileInputStream("d:/D_Other/nonSeriaizableTest.bin");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Child child2 = (Child) ois.readObject(); // 역직렬화

		System.out.println("ParentName : " + child2.getParentName());
		System.out.println("childName : " + child2.getChildName());

		ois.close();
	}
}

/**
 * Serializable 구현하지 않은 부모 클래스
 * 
 * @author PC-19
 *
 */
class Parent {
	private String parentName;

	// 접근가능하도록 getter/setter함
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}

/**
 * Serializable을 구현한 자식 클래스
 */
class Child extends Parent implements Serializable {
	private String childName;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	/**
	 * 직렬화 될 떄 자동으로 호출됨
	 * (접근 제한자가 private이 아니면 자동 호출되지 않음)
	 * @param out
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream out) throws IOException {  // 접근제어자가 private이어야함
			out.writeUTF(getParentName());
			out.defaultWriteObject();
	}
	
	/**
	 * 역직렬화 될 때 자동으로 호출됨
	 * (접근 제한자가  private이 아니면 자동 호출되지 않음)
	 * @param in
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		setParentName(in.readUTF());
		in.defaultReadObject();
	}
	
}