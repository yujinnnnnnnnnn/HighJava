package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 프린터기능 제공 보조 스트림 예제
 * @author PC-19
 *
 */
public class T14_PrintStreamTest {

	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("d:/D_Other/print.txt");
		/*
		 	PrintStream은 모든 자료형을 출력할 수 있는 기능을 제공하는 OutputStream의 서브 클래스이다
		 	PrintStream은 IOException을 발생시키지 않는다
		 	println 및 print 등 메서드 호출시마다 autoflush 기능 제공됨
		 */
		//콘솔 출력 스트림으로 사용됨
//		PrintStream out = new PrintStream(System.out); //모든 것이 출력됨
		PrintStream out = new PrintStream(fos); // 콘솔에 뜨진 않지만 파일 경로에 저장됨
		
		out.print("안녕하세요 PrintStream입니다 .\n");
		out.print("안녕하세요 PrintStream입니다2 .\n");
		out.print("안녕하세요 PrintStream입니다3 .\n");
		out.println(out); //객체 출력 
		out.println(3.14);
		
		out.close();
		
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/print2.txt");
		// ↓보조스트림                                                               ↓보조스트림 (보조스트림 안에 보조스트림 넣어도 무방함)
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos2, "UTF-8"));
		//여러기능을 쓰고 싶을때 사용
	}
}
