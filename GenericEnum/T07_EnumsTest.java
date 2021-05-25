package kr.or.ddit.basic;

public class T07_EnumsTest {

	/**
	 열거형 => 상수값들을 선언하는 방법
	 
	 static final int A = 0;
	 static final int B = 1;
	 static final int C = 2;
	 static final int D = 3;
	 
	 enum Data(A, B, C ,D);
	 
	 열거형 선연하는 방법
	 enum 열거형 이름 {상수값1, 상수값2,..., 상수값 n }
	 */
	
	//City 열거형 객체 선언(기본값을 이용하는 열거형 )
	public enum City { 서울, 부산, 대구, 광주, 대전 }; // 상수를 정의해도 City타입이라는 enum타입으로 객체가 만들어짐
	public enum City2 { 서울, 부산, 대구, 광주, 대전 };
	
	//데이터 값을 임의로 지정한 열거형 객체선언
	//데이터값을 정해 줄 경우에는 생성자를 만들어서 괄호속의 값이 변수에 저장되도록 한다
	public enum Season{     
		봄("3월부터 5월까지"), 여름 ("6월부터 8월까지"), 가을("9월부터 11월까지"), 겨울("12월부터 2월까지");
		
		//괄호속의 값이 저장될 변수선언
		private String str; //멤버변수
		
		//생성자 만들기 (열거형의 생성자는 제어자가 묵시적으로 'private'이다.) //이늄상수를 사용할 시점에 private만들어짐
		Season(String data) { //private Season(String data)
			this.str = data;
		}
		
		//값을 반환하는 메서드
		public String getStr() {
			return str;
		}
	} 
	public static void main(String[] args) {
		/**
		 열거형에서 사용되는 메서드
		 1. name() => 열거형 상수의 이름을 문자열로 반환
		 2. ordinal() => 열거형 상수가 정의된 순서값을 반환(기본적으로 0부터 시작)
		 3. valueOf("열거형상수이름") => 지정된 열거형에서 '열거형 상수이름'과 일치하는 열거형 상수를 반환한다 (enum에서 )
		 */
		City myCity1; //열거형 객체변수 선언
		City myCity2;
		
		//열거형 객체변수에 값 저장하기
		myCity2 = City.서울; //서울 객체가 넘어옴 
		myCity1 = City.valueOf("서울"); //City enum에서 '서울'데이터(열거형상수객체) 가져오기 / 리턴타입 : 객체  // 위에와 동일함
		
		System.out.println("myCity1 : " + myCity1.name()); // 리턴타입 : 객체가 가지고 있는 string값
		System.out.println("myCity1의 ordinal : " + myCity1.ordinal());  //enum에서 나열한 순서가 필요할때 순서를 가지고 오는메서드 // 서울이 몇번쨰인지 알수 있음
		System.out.println();
		
		System.out.println("myCity2 : " + myCity2.name());
		System.out.println("myCity2의 ordinal : " + myCity2.ordinal());
		System.out.println("---------------------------------------------");
		
		Season ss = Season.valueOf("여름");
		System.out.println("name => " + ss.name());
		System.out.println("ordinal =>" + ss.ordinal());
		System.out.println("get메서드 => " + ss.getStr()); //여름(여기 부분의 str이 리턴됨)
		System.out.println("---------------------------------------------");
		
		//열거형이름.values() => 데이터를 배열로 가져오기
		Season[] enumArr = Season.values(); //배열로 향상된 포문가능
		for (int i = 0; i < enumArr.length; i++) {
			System.out.println(enumArr[i].name() + " : " + enumArr[i].getStr());
		}
		System.out.println();
		
		for(City city : City.values()) {
			System.out.println(city + " : " + city.ordinal());
		}
		
		City city = City.대구; //city = 대구
//		City city = City2.대구; // 타입이 달라서 컴파일에러뜸 => (★타입안전코드로 코딩가능★) 
		System.out.println(city == City.대전); //대구 == 대전
		System.out.println(city == City.대구); //대구 == 대구
		
		System.out.println("대구 => " + city.compareTo(City.대구));  //이늄상수는 compareTo를 가지고 있음(크기비교 가능) / ordinal 값을 비교하고 있음
		System.out.println("서울 => " + city.compareTo(City.서울));
		System.out.println("대전 => " + city.compareTo(City.대전));

	}
}















