package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Array;
import java.util.Arrays;

public class T03_ByteArrayIOTest {

	public static void main(String[] args) throws IOException {
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		
		//스트림선언 및 객체 생성 (입 출력 클래스가 따로 제공되고 있음) 자바프로그램기준으로 입출력을 판단해야함
		ByteArrayInputStream bais = null; //스트림 선언
		bais = new ByteArrayInputStream(inSrc); // 객체 생성 // 읽을 소스 정보를 ()에 넣어줌
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); //출력용 stream클래스 객체 생성
		
		int data; //읽어온 자료를 저장할 변수
		
		//read() 메서드 => byte단위로 자료를 읽어와 int형으로 반환한다  // Stream클래스는 read가 반드시 존재
//					  => 더이상 읽어올 자료가 없으면  -1을 반환한다
		while((data=bais.read()) != -1) { //read()한번 호출하면 1byte씩 읽음 // 1byte -> int값(4byte)으로 리턴함  // -1 : 더이상 읽을게 없다는 뜻
			baos.write(data); //출력하기 // 자기자신한테 write함(자기자신 객체에 저장) 
		}  //-1나오면 while문 끝남
		
		//출력된 스트림 값들을 배열로 변환해서 반환하는 메서드
		outSrc = baos.toByteArray(); // 저장해논 byte단위를 byteArray로 반환해줌 
		
		System.out.println("inSrc => " + Arrays.toString(inSrc));
		System.out.println("inSrc => " + Arrays.toString(outSrc));
		
		//스트림 객체 닫기
		bais.close();
		baos.close();
	}
}
