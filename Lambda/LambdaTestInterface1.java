package kr.or.ddit.basic;

@FunctionalInterface // 어노테이션을 붙여주는 순간 함수적 인터페이스가 됨 => 메서드 2개 사용 못함
public interface LambdaTestInterface1 {
	//반환값이 없고 매개변수도 없는 추상메서드 선언
	public void test();
}

@FunctionalInterface
interface LambdaTestInterface2{
	//반환값이 없고 매개변수가 있는 추상 메서드 선언
	public void test(int a);
}

@FunctionalInterface
interface LambdaTestInterface3{
	//반환값이 있고 매개변수도 있는 추상 메서드 선언
	public int test(int a, int b);
}
