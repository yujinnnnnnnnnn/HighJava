package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 기본타입 입출력 보조 스트림 예제
 * @author PC-19
 *
 */
public class T13_DataIOStreamTest {

	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("d:/D_Other/test.dat"); //데이터 저장
		
		//DataOutputStream 은 출력용 데이터를 자료형에맞게 출력해준다
		DataOutputStream dos = new DataOutputStream(fos); 
		
		dos.writeUTF("홍길동");  //문자열데이터 출력
		dos.writeInt(17);			//정수형으로 데이터 출력
		dos.writeFloat(3.14f);		//실수형으로 출력 (float)
		dos.writeDouble(3.14);  	//실수형으로 출력 (double)
		dos.writeBoolean(true);    //논리형으로 출력
		
		System.out.println("출력 완료");
		
		dos.close();
		//========================================
		//출력한 자료 읽어오기  //기본데이터 타입에 맞게 저장했다가 읽을때도 저장한 위치에 맞게 읽어와야함
		FileInputStream fis = new FileInputStream("d:/D_Other/test.dat");
		DataInputStream dis = new DataInputStream(fis);
		
		System.out.println("문자열 자료 : " + dis.readUTF());
		System.out.println("정수형 자료 : " + dis.readInt());
		System.out.println("실수형 자료(Float) : " + dis.readFloat());
		System.out.println("실수형 자료(Double) : " + dis.readDouble());
		System.out.println("논리형 자료 : " + dis.readBoolean());
		
		dis.close();
	}
}
