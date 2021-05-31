package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class T02_FileTest {
	public static void main(String[] args) {
		File f1 = new File("d:/D_Other/sample.txt");
		File f2 = new File("d:/D_Other/test.txt");
		
		if(f1.exists()) {
			System.out.println(f1.getAbsolutePath() + "은 존재합니다.");
		}else {
			System.out.println(f1.getAbsolutePath() + "은 없는 파일 입니다.");
			try {
				if(f1.createNewFile()) { 
					System.out.println(f1.getAbsolutePath() + "파일을 새로 만들었습니다.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(f2.exists() ) {
			System.out.println(f2.getAbsolutePath() + "은 존재합니다");
		}else {
			System.out.println(f2.getAbsolutePath() + "은 없는파일 입니다");
		}
		System.out.println("----------------------------------------------------");
		
		File f3 = new File("d:/D_Other");
		File[] files = f3.listFiles(); // 해당 디렉토리 안에있는 파일객체들을 파일 배열로 받아줌 
		
		for (int i = 0; i < files.length; i++) {
			System.out.println(files[i].getName() + "=> " );
			if(files[i].isFile()) {
				System.out.println("파일");
			}else if(files[i].isDirectory()) {
				System.out.println("디렉토리");
			}
		}
		System.out.println("=======================================================");
		String[] strFiles = f3.list();  //파일 이름만 String배열로 받아줌
		for (int i = 0; i < strFiles.length; i++) {
			System.out.println(strFiles[i]);
		}
		System.out.println("--------------------------------------------------------");
		System.out.println();
		
		//-----------------------------------------------------------------------------
		// 출력할 디렉토리 정보를 갖는 File 객체 생성
		File dir = new File("d:/D_Other");
		
		displayFileList(dir);
	}
/**
 * 지정된 디렉토리(폴더)에 포함된 파일과 디렉토리 목록을 보여주는 메서드
 * @param dir 조회할 폴더 (디렉토리)
 */
	private static void displayFileList(File dir) {
		
		System.out.println("[" + dir.getAbsolutePath() + "] 디렉토리의 내용 : ");
		
		//디렉토리 안의 모든 파일 목록을 가져온다
		File[] files = dir.listFiles();
		
		//하위 디렉토리 정보를 저장할 list 객체 생성(File배열의 인덱스 저장)
		List<Integer> subDirList = new ArrayList<Integer>(); //하위 디렉토리의 목록도 조회 //현재 디렉토리만 조회 할거면 필요없음
		
		//날짜를 출력하기 위한 형식 설정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm"); // a : 오전/ 오후
		
		for (int i = 0; i < files.length; i++) {
			//파일의 속성(읽기, 쓰기 , 히든, 디렉토리 구분)
			String attr = ""; 
			
			String size = ""; // 파일 용량
			
			if(files[i].isDirectory()) {
				attr = "<DIR>";
				subDirList.add(i); //인덱스 정보를 List에 추가 //디렉토리인 경우에만 저장
			}else {
				size = files[i].length() + ""; // ""를 넣으므로써 스트링 화시킴
				attr += files[i].canRead() ? "R" : " "; // 읽을 수 있는 상태인지 확인 //  읽기만 가능할 경우 : R_ _
				attr += files[i].canWrite() ? "W" : " ";// 작성할 수 있는 권한이 있는지 확인 // _W_
				attr += files[i].isHidden()? "H" : " "; // 숨긴파일 속성이 있는지 확인             
			}
			System.out.printf("%s %5s %12s %s\n",               //  (문자열 /5글자공간확보/ 12글자공간확보 / n공간 확보)           
					sdf.format(new Date(files[i].lastModified()))  //lastModified() 이 %s로 들어감 
					,attr, size, files[i].getName()
					);
		}
		int dirCount = subDirList.size(); //하위 폴더 개수  
		int fileCount = files.length - dirCount; //파일 수
		
		System.out.println(fileCount + "개의 파일, " + dirCount + "개의 디렉토리");
		System.out.println();
		
		for (int i = 0; i < subDirList.size(); i++) {
			//하위 폴더의 내용들도 출력하기 위해 현재의 메서드를 재귀호출하여 처리한다
			displayFileList(files[subDirList.get(i)]);
		}
		
		
	}
}

























