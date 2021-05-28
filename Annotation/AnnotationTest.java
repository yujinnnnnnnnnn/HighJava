package kr.or.ddit.basic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationTest {
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println(PrintAnnotation.id); // 어노테이션 아이디 접근
		
		//reflection 기능을 이용한 메서드 실행하기
		//선언된 메서드 목록 가져오기
		Method[] declaredMethods = Service.class.getDeclaredMethods();
		
		for(Method m : declaredMethods) {
			System.out.println(m.getName()); //메서드명
			
			PrintAnnotation printAnno = m.getDeclaredAnnotation(PrintAnnotation.class);
			
			for (int i = 0; i < printAnno.count(); i++) {
				System.out.print(printAnno.value());
			}
			System.out.println(); //줄바꿈 처리
			
			//m.invoke(new Service()); (메서드를 직접 실행하는 메서드로 객체생성 방법1)
			//객체생성 방법 2
			Class<Service> clazz = Service.class;
			
			Service service;
			try {
				service = (Service) clazz.newInstance();  //객체 생성
				m.invoke(service);  // 메서드 실행
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		}
	}
}
