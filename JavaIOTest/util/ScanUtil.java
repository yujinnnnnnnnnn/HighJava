package util;

import java.util.Scanner;

public class ScanUtil {

	private static Scanner s = new Scanner(System.in);
	
	public static String nextLine(){  //객체생성없이 쓰기위해 static 사용
		return s.nextLine();
	}
	public static int nextInt(){
		return Integer.parseInt(s.nextLine());
	}
	
	
}
