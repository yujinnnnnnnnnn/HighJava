package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Score {
	
	public void rank(List<Student> stuList) {
		for(Student stu : stuList) {
			int rank = 1;
			for(Student stu2 : stuList) {
				if(stu.getSum() < stu2.getSum()) {
					rank++;
				}
			}
			stu.setRank(rank);
		}
	}
	public static void main(String[] args) {

		List<Student> stuList = new ArrayList<Student>();
		
		stuList.add(new Student("1703005", "강유진", 84, 95, 54));
		stuList.add(new Student("1703003", "김수연", 87, 94, 45));
		stuList.add(new Student("1703002", "박주경", 75, 12, 69));
		stuList.add(new Student("1703004", "고시현", 77, 64, 45));
		stuList.add(new Student("1703001", "강지수", 45, 46, 95));
		
		new Score().rank(stuList);
		System.out.println("정렬 전");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("------------------------------");
		
		Collections.sort(stuList);
		System.out.println("학번의 오름차순 정렬");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("------------------------------");
		
		Collections.sort(stuList, new SortSumDesc());
		System.out.println("총점의 내림차순, 동일 총점은 학번 내림차순으로 정렬");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("-------------------------------");
	}
}
class SortSumDesc implements Comparator<Student>{

	@Override
	public int compare(Student s1, Student s2) {
		if(s1.getSum() == s2.getSum()) {
		return new String(s1.getNum()).compareTo(s2.getNum()) * -1;
	}else {
		return new Integer(s1.getSum()).compareTo(s2.getSum()) * -1;}
	}
	
}
class Student implements Comparable<Student>{

	private String num;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int sum;
	private int rank;
	
	
	public Student(String num, String name, int kor, int eng, int math) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.sum = kor + eng + math;
		
	
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public int compareTo(Student s) {
		
		return this.getNum().compareTo(s.getNum());
	}

	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math + ", sum="
				+ sum + ", rank=" + rank + "]";
	}
	
}
