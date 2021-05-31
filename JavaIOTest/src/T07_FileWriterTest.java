package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class T07_FileWriterTest {

	public static void main(String[] args) {
		/*
		 	사용자가 입력한 내용을 그대로 파일로 저장하기
		 	
		 	콘솔(표준 입출력장치)과 연결된 입력용 문자 스트림 생성
		 	InputStreamReader => 바이트기반 스트림을 문자기반 스트림으로 변환해주는 보조 스트림이다.
		 	
		 */
		InputStreamReader isr = new InputStreamReader(System.in); //(System in[기본스트림] : byte기반)을 문자기반으로 처리해줌 // 처리과정 : 기본스트림에서 읽은 1byte를 모아서 2byte가 되면 char로 변환해줌  
		
		FileWriter fw = null;
		
		try {
			//파일 출력용 문자 스트림 객체 생성
			fw = new FileWriter("d:/D_Other/testChar.txt");
			
			int c;
			
			System.out.println("아무거나 입력하세요");
			
			// 콘솔에서 입력할 때 입력의 끝 표시는 Crtl + z 키를 누른다
			while((c = isr.read()) != -1) { 
				fw.write(c); // 콘솔에서 입력받은 값을파일에 출력하기
			}
			
			System.out.println("작업 끝");
			
			isr.close();
			fw.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
