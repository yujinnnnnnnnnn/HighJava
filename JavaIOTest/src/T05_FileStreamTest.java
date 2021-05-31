package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 파일 읽기 예제
 * @author PC-19
 * 입력 / 출력 기준 : 자바 프로그램 코드 
 *
 */
public class T05_FileStreamTest {

	public static void main(String[] args) {
		//FileInputStream객체를 이용한 파일 내용 읽기 (byte 기반 : InputStream, outputStream // 문자열 기반 :  reader, writer)
		FileInputStream fis = null; //선언  
		
		try {
			//방법 1 (파일 정보를 문자열로 지정하기)(파일 읽어올때)
			fis = new FileInputStream("d:/D_Other/test2.txt");
			
			//방법 2 (파일 정보를 File 객체를 이용하여 지정하기)
//			File file = new File("d:/D_Other/test2.txt");
//			fis = new FileInputStream(file);
			
			int c; // 읽어온 데이터를 저장할 변수
			
			//읽어온 값이 -1이면 파일의 끝까지 읽었다는 의미이다
			while((c = fis.read()) != -1) {
				//읽어온 자료 출력하기
				System.out.print((char) c); //문자로 보여줄려고 char로 캐스팅함 
				//영문은 표현되나 아스키 코드가 아닌 한글은 char타입이기때문에 2byte이상 있어야함
			}
			
			fis.close();//작업 완료 후 스트림 닫기
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
