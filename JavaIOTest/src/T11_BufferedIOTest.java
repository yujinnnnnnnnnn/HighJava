package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class T11_BufferedIOTest {
// IO작업은 cpu보다 느림 (write/ read) => 느린작업(IO)들은 한번에 처리 것이 더 효과적
	public static void main(String[] args) {
		
		//입출력의 성능 향상을 위해서 버퍼를 이용하는 보조 스트림 (버퍼를 이용하여 write할 데이터들을 모아서 한번에 처리함)
		FileOutputStream fos = null;  
		BufferedOutputStream bos = null; 
		
		try {
			fos = new FileOutputStream("d:/D_Other/bufferTest.txt");
			
			//버퍼의 크기를 지정하지 않으면 기본적으로 버퍼의 크기가 8192byte(8kb)로 설정된다
			
			//버퍼 크기가 5인 스트림 생성
			bos = new BufferedOutputStream(fos, 5); //버퍼크기가 크면 램을 많이 사용하기때문에 최소한의 적당한 크기가 좋음 //5byte가 들어가는 크기로 생성
			
			for (int i = '1'; i <='9'; i++) { //1,과 9라는 코드값을 저장함
				System.out.println(i);
				bos.write(i); //버퍼에 저장하고 버퍼 사이즈가 다 찼을 때(버퍼크기: 5) 기반스트림이 write해주고 버퍼를 비워서 나머지 데이터를 버퍼에 저장함 => 결과물은 5개만 나옴 (파일이 깨짐) why=> (나머지 데이터들이 사이즈에 맞지않아서 write를 해주지 못함) 
			}
			bos.flush();// 작업을 종료하기 전에 버퍼에 남아있는 데이터를 모두 출력시킨다 (close메소드 호출 시 자동으로 호출됨) 
			System.out.println("작업끝");
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
