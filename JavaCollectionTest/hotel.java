package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import util.ScanUtil;

public class hotel {
	private Map<Integer, reserve> hotelMap;
	
	public hotel() {
		hotelMap = new HashMap<>();
	}

	public static void main(String[] args) {

	new hotel().start();
	}

	public void start() {
		System.out.println("********************************************");
		System.out.println("      호텔 문을 열었습니다.");
		System.out.println("********************************************");
		while (true) {
			System.out.println("********************************************");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1. 체크인    \t 2. 체크아웃 \t 3. 객실상태 \t 4. 업무종료");
			System.out.println("********************************************");

			System.out.print("메뉴 선택 => ");
			int input = ScanUtil.nextInt();

			switch (input) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				roomCondition();
				break;
			case 4:
				System.out.println("***************************************");
				System.out.println("호텔 문을 닫았습니다.");
				System.out.println("***************************************");
				System.exit(4);
			}

		}
	}
	
	private void roomCondition() {
		Set<Integer> keySet = hotelMap.keySet();
		
		if(keySet.size() == 0) {
			System.out.println("예약된 방이 없습니다");
		}else {
			Iterator<Integer> it = keySet.iterator();
			int cnt = 0;
			while(it.hasNext()) {
				cnt++;
				int roomNum = it.next();
				reserve re = hotelMap.get(roomNum);
				System.out.println("방번호 : " + re.getRoomNum() + "," +"투숙객 : " + re.getName());
			}
		}
		
	}

	private void checkOut() {
		System.out.println("어느방에 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int roomNum = ScanUtil.nextInt();
		if(hotelMap.get(roomNum) == null) {
			System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
		}else {
			System.out.println(roomNum + "방 체크아웃 되었습니다.");
			hotelMap.remove(roomNum);
		}
		
	}

	private void checkIn() {
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int roomNum = ScanUtil.nextInt();
		 System.out.print("누구를 체크인 하시겠습니까?");
		 String name = ScanUtil.nextLine();
		 
		if(hotelMap.get(roomNum) != null) {
			System.out.println(roomNum + "방에는 이미 사람이 있습니다.");
			return;
		}
		System.out.println("체크인 되었습니다.");
		hotelMap.put(roomNum, new reserve(roomNum, name));
		

	}
}

class reserve{
	private String name;
	private int roomNum;
	
	public reserve(int roomNum, String name) {
		super();
		this.name = name;
		this.roomNum = roomNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	@Override
	public String toString() {
		return "people [name=" + name + ", roomNum=" + roomNum + "]";
	}
	
	
}






















