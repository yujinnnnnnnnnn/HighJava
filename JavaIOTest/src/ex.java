package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ex {

	public static void main(String[] args) {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			fis = new FileInputStream("d:/D_Other/Tulips.jpg");
			
			bis = new BufferedInputStream(fis);
			
			fos = new FileOutputStream("d:/D_Other/복사본_Tulips.jpg");
			bos = new BufferedOutputStream(fos);
			
			int c;
			while((c = bis.read()) != -1) {
				bos.write(c);
			}
			bos.flush();
			System.out.println("작업끝");
			
			bis.close(); //보조스트림 닫으면 기반스트림도 닫힘 
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
