package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04_ByteArrayIOTest {
	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4]; // 자료 읽을떄 사용할 배열 // temp를 사용하지 않으면  자료를 읽을떄 10번을 읽어야함 그러한 번거로움을 없애기 위해 temp를 사용하여 4byte씩 읽음
		
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			// available() => 읽어 올 수 있는 byte수를 반환
			while(bais.available() > 0) {
//				bais.read(temp); // temp 배열 크기만큼 읽어와 temp배열에 저장한다 //read할때마다 4byte씩 읽음
//				baos.write(temp); // temp배열의 내용을 출력한다 
//			
				//실제 읽어온 byte수를 반환한다  => 남아있던 쓰레기값을 제거하기 위한 로직
				int len = bais.read(temp); // read한 temp의 byte수를 반환
				System.out.println(len);
				//temp배열의 내용 중에서 0번째부터 len개수만큼 출력한다
				baos.write(temp, 0, len);
				               
				System.out.println("temp : " + Arrays.toString(temp)); // 6,7 은 남아있던 쓰레기값 
			}
			
			System.out.println();
			outSrc = baos.toByteArray(); // baos객체안의 바이트들을 나열함 
			System.out.println("inSrc => " + Arrays.toString(inSrc));
			System.out.println("outSrc => " + Arrays.toString(outSrc)); //남아있던 쓰레기값 6,7까지 들어감
			
			bais.close();
			baos.close();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
