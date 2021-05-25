package kr.or.ddit.basic;

import java.util.Arrays;

public class T06_WildCardTest {

	/**
	 * 모든 과정 수강정보 조회
	 * @param course 모든과정
	 */
	public static void listCourseInfo(Course<?> course) { //Course<? extent Object> 생략된 것 
		System.out.println(course.getName() + " 수강생 : " + Arrays.toString(course.getStudents()));
	}
	
	/**
	 * 학생과정 수강정보 조회
	 * @param course
	 */
	public static void listStudentCourseInfo(Course<? extends Student> course) {
		System.out.println(course.getName() + " 수강생 : " + Arrays.toString(course.getStudents()));		
	}
	/**
	 * 직장인과정 수강 정보 조회
	 * @param course 직장인과 일반인
	 */
	public static void listWorkerCourseInfo(Course<? super Worker> course) { //super Worker: Worker도 포함(Worker까지)
		System.out.println(course.getName() + " 수강생 : " + Arrays.toString(course.getStudents()));
	}
	
	public static void main(String[] args) {
		// extents와 super사용하는 예제임
		
		Course<Person> personCourse = new Course("일반인 과정", 5);
		personCourse.add(new Person("일반인1"));
		personCourse.add(new Worker("직장인1"));
		personCourse.add(new Student("학생1"));
		personCourse.add(new HighStudent("고등학생1"));
		
		Course<Worker> workerCourse = new Course<>("직장인과정", 5);
		workerCourse.add(new Worker("직장인1"));
		
		Course<Student> studentCourse = new Course<>("학생과정", 5);
		studentCourse.add(new Student("학생1"));
		studentCourse.add(new HighStudent("고등학생1"));
		
		Course<HighStudent> highStudentCourse = new Course<>("고등학생과정", 5);
		highStudentCourse.add(new HighStudent("고등학생1"));
		
		listCourseInfo(personCourse);
		listCourseInfo(workerCourse);
		listCourseInfo(studentCourse);
		listCourseInfo(highStudentCourse);
		System.out.println("---------------------------------------------------");
		
//		listStudentCourseInfo(personCourse); // Student의 extents만 가능하므로 에러뜸 
//		listStudentCourseInfo(workerCourse);
		listStudentCourseInfo(studentCourse);
		listStudentCourseInfo(highStudentCourse);
		System.out.println("---------------------------------------------------");
		
		listWorkerCourseInfo(personCourse);
		listWorkerCourseInfo(workerCourse);
//		listWorkerCourseInfo(studentCourse);  // Worker의 extents만 가능하므로 에러뜸
//		listWorkerCourseInfo(highStudentCourse);


		
		
	}
}

class Person{
	String name;
	public Person (String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
}
class Worker extends Person{
	public Worker (String name) {
		super(name);
	}
}
class Student extends Person{
	public Student (String name) {
		super(name);
	}
}
class HighStudent extends Student{
	public HighStudent(String name) {
		super(name);
	}
}
/**
 * 수강과정
 * @author PC-19
 *
 * @param <T>
 */
class Course<T> { 
	private String name; // 과정명
	private T[] students;// 수강생 (제너릭 배열)
	
	public Course(String name, int capacity) {  //capacity = 몇명들어가는지 확인
		this.name = name;
		//타입 파라미터로 배열을 생성시 오브젝트 배열을 생성 후 ,타입 파라미터 배열로 캐스팅 처리해야함
		
		//제너릭 배열 생성 실패 (new 연산자는 생성할 객체의 태입이 정확히 정의되어야 사용 가능)
//		students = new T[capacity];  //객체생성 할때(new) 객체정보는 명확히 알려줘야함 
		students = (T[])(new Object[capacity]); //t타입이 뭔지 모르지만 객체생성 할때는 명확히 알려줘야하기때문에 Object로 받아주고 t타입으로 캐스팅함
	}

	public String getName() {
		return name;
	}

	public T[] getStudents() {
		return students;
	}
	/**
	 * 수강생 등록
	 * @param t
	 */
	public void add(T t) {
		for (int i = 0; i < students.length; i++) {
			if(students[i] == null) { //아직 등록되지 않은 (빈) 자리인지 확인
				students[i] = t;
				break;
			}
		}
	}
}

























