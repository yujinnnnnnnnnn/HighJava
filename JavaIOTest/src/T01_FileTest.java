package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

public class T01_FileTest {
	public static void main(String[] args) throws IOException {
		// File 객체 만들기 연습
		
		//1.new File(String 파일 또는 경로명)
		//=> 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분 문자는 '\'를 사용하거니 '/'를 사용할 수 있다
		File file = new File("d:\\D_Other\\test.txt");  //파일객체를 가지고 파일 핸들링 하려고 생성자에 경로를 알려줌
		System.out.println("파일명 : " + file.getName()); //파일 이름 갖고옴
		System.out.println("파일 여부 : " + file.isFile() ); //파일 여부 확인 (폴더도 파일클래스로 핸들링 가능) // boolean타입으로 리턴됨 // 파일 존재하지 않으면 false
		System.out.println("디렉토리(폴더 ) 여부 : "+ file.isDirectory()); // 폴더를 확인 하는 메서드 // // boolean타입으로 리턴됨 // 폴더 경로가 존재하지 않으면 false
		System.out.println("============================================");
		
		File file2 = new File("d:\\D_Other");
		System.out.print(file2.getName() + "은 ");
		if(file2.isFile()) {
			System.out.println("파일");
		}else {
			System.out.println("디렉토리(폴더)");
			
		}
		System.out.println("----------------------------------------------");
		
		//2. new File (File parent, String child)
		//=> 'parent'디렉토리 안에 있는 'child'파일 또는 디렉토리를 갖는다
		File file3 = new File(file2, "test.txt");  //파라미터만 다른 메서드는 동일나 오버로드
		System.out.println(file3.getName() + " 의 용량 크기 : " + file3.length() + "bytes"); //byte 단위의 길이가 나옴
		
		//3 new File(String parent, String child)
		File file4 = new File("/D_Other/test/..","test.txt"); 
		System.out.println("절대 경로 : " + file4.getAbsolutePath()); //: 시작부터 전체적인 부분까지 알려줌 장점 : 명확한 경로를 알 수 있음 (가독성) <-> 상대경로 : 현재위치부터 경로설정 
		System.out.println("경로 : " + file4.getPath()); //내가 설정한 경로 그대로를 갖고오고싶을때
		System.out.println("표준 경로 : " + file4.getCanonicalPath());  // 실제경로를 계산하여 경로 알려줌 (계산된 결과)
		
		/**
		 	디렉토리 (폴더) 만들기
		 	1. mkdir() => File객체의 경로 중 마지막 위치의 디렉토리를 만든다. 중간의 경로가 모두 미리 만들어져 있어야한다(없는 경로면 만들어지지 않음)
		 	
		 	2. mkdirs() => 중간의 경로가 없으면 중간의 경로도 새롭게 만든 후 마짐가 위치의 디렉토리르 만들어 준다 
		 				=> 위 두 메서드 모두 만들기를 성공하면 true, 실패하면 false 반환
		 	※  정상정 동작을 하기 위해선 온전하게 권한이 있을때 
		 */
		File file5 = new File("d:/D_Other/연습용");
		if(file5.mkdir()) {
			System.out.println(file5.getName() + " 만들기 성공");
		}else {
			System.out.println(file5.getName() + " 만들기 실패");
		}
		System.out.println();
		
		File file6 = new File("d:/D_Other/test/java/src");
		if(file6.mkdirs()) {
			System.out.println(file6.getName() + " 만들기 성공");
		}else {
			System.out.println(file6.getName() + " 만들기 실패");  //src 앞에 경로들이 만들어져 있지 않아서 만들기 실패뜸 (mkdir)
		}
	}
}


















