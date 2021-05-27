package kr.or.ddit.basic;

public class LambdaTest03 {

	static int stVar = 9;
	private String name = "aaa";
	
	private void testMethod(final int temp) {
		int localVar = 50;
		int kor = 100;

		/*
		 	람다식 내부에서 사용되는 ★지역변수★는 모두 final(상수)이어야한다
		 	보통은 final을 붙이지 않으면 컴파일러가 자동으로 붙여준다
		 	단, 지역변수의 값을 변경하는 식이 있을 경우에는 자동으로 final을 붙여주지 않는다
		 */
//		temp = 500; //final 되어 있어서 값을 변할 수 없으므로 컴파일에러뜸
//		localVar = 2000;
//		kor = 400;
		
		//람다식에서 지역변수 사용하기 //final을 안붙였을 때 컴파일러가 자동으로 final을 붙여서 실행됨
		LambdaTestInterface1 lt = 
				()->{
					System.out.println("temp = " + temp);
					System.out.println("localVar = " + localVar);  //지역변수인 localVar에 final 형태의값이 아니면 컴파일 에러뜸
					System.out.println("kor = " + kor);
					System.out.println("stVar = " + stVar);
					System.out.println("name = " + this.name); //멤버변수
				};
			lt.test();
	}
	
	public static void main(String[] args) {
		new LambdaTest03().testMethod(200);
	}
}
