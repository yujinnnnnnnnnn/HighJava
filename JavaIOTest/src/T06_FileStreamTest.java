package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 파일출력 예제
 * @author PC-19
 *
 */
public class T06_FileStreamTest {
	public static void main(String[] args) {
		//파일 출력하기
		FileOutputStream fos = null;
		
		try {
			//출력용 OutputStream객체 생성(파일에 저장할때)
			fos = new FileOutputStream("d:/D_Other/out.txt"); //출력할 경로
			
			for(char ch='a'; ch<='z'; ch ++) {
				fos.write(ch);
			}
			System.out.println("파일에 쓰기 작업 완료");
			
			//쓰기 작업 후 스트림 닫기
			fos.close();
			
			//========================================
			//저장된 파일의 내용을 읽어와 화면에 출력하기
			FileInputStream fis = new FileInputStream("d:/D_Other/out.txt");
			
			int c;
			
			while((c=fis.read()) != -1) {
				System.out.print((char) c);
			}
			System.out.println();
			System.out.println("출력 끝");
			
			fis.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
