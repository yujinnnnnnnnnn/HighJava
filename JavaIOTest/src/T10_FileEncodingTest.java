package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class T10_FileEncodingTest {
/*
  	OutputStreamWriter => OutputStream(바이트기반의 출력용 객체)을 Writer(문자기반의 출력용 객체)로 변환하는 객체
  						=> 이 객체도 출력할떄 '인코딩 방식'을 지정해서 출력할 수 있다
 */
	public static void main(String[] args) throws IOException {
		//키보드로 입력한 내용을 파일로저장하는데, out_utf8.txt파일은 'utf-8'인코딩 방식으로
								//   out_anis.txt파일은 'ms949'인코딩 방식으로 저장한다
		InputStreamReader isr = new InputStreamReader(System.in);
		
		//파일 출력용(파일(File)을 저장(Output)하고 싶을때 )
		FileOutputStream fos1 = new FileOutputStream("d:/D_Other/out_utf8.txt"); 
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/out_ansi.txt");
		
		//FileOutputStream은 인코딩 처리를 하지못함  // 디스크에서 디스크로 복사할때는 인코딩 상관없이 가능[바이트단위로 읽어서 복사하면 됨] (인코딩은 복사된 파일을 저장하는 시점에 인코딩 방식을 생각해야함)
		//OutputStreamWriter 은 바이트 출력 스트림에 연결되어 문자 출력 스트림인 Writer로 변환시키는 보조 스트림
		
		OutputStreamWriter osw1 = new OutputStreamWriter(fos1, "utf-8"); // utf-8 = default값 //write하는 시점에 utf-8로 문자를 저장됨
		OutputStreamWriter osw2 = new OutputStreamWriter(fos2, "ms949");
		
		int c;
		System.out.println("아무거나 입력하세요");
		
		while((c = isr.read()) != -1) {
			osw1.write(c);
			osw2.write(c);
		}
		System.out.println("작업완료");
		
		isr.close();
		osw1.close();
		osw2.close();
	}
}
