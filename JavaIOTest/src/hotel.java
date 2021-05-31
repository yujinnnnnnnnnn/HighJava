package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class hotel{
	private Map<Integer, reserve> hotelMap;
	Scanner scan = new Scanner(System.in);
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

		input();
		while (true) {
			System.out.println("********************************************");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1. 체크인    \t 2. 체크아웃 \t 3. 객실상태 \t 4. 업무종료");
			System.out.println("********************************************");

			System.out.print("메뉴 선택 => ");
			int input = scan.nextInt();

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
				save();
				System.out.println("***************************************");
				System.out.println("호텔 문을 닫았습니다.");
				System.out.println("***************************************");
				System.exit(0);
			}

		}
	}

	private void input() {
		ObjectInputStream ois;
		Object obj = null;

		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("d:/D_Other/hotelMap.bin")));

			try {
				while ((obj = ois.readObject()) != null) {

					 hotelMap = (HashMap) obj;  // 위에 있는 hotelMap안에 obj 넣어줌

					Set<Integer> keySet = hotelMap.keySet();

						Iterator<Integer> it = keySet.iterator();
						int cnt = 0;
						while (it.hasNext()) {
							cnt++;
							int roomNum = it.next();
							reserve re = hotelMap.get(roomNum);
							System.out.println(re);
						}
				}
				ois.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			System.out.println("데이터 출력 완료");
		}
	}

	private void save() {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream("d:/D_Other/hotelMap.bin")));
			oos.writeObject(hotelMap);

		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}

	}

	private void roomCondition() {
		Set<Integer> keySet = hotelMap.keySet();

		if (keySet.size() == 0) {
			System.out.println("예약된 방이 없습니다");
		} else {
			Iterator<Integer> it = keySet.iterator();
			while (it.hasNext()) {
				int roomNum = it.next();
				reserve re = hotelMap.get(roomNum);
				System.out.println(re);
				
			}
		}

	}

	private void checkOut() {
		System.out.println("어느방에 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int roomNum = scan.nextInt();
		if (hotelMap.get(roomNum) == null) {
			System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
		} else {
			System.out.println(roomNum + "방 체크아웃 되었습니다.");
			hotelMap.remove(roomNum);
		}

	}

	private void checkIn() {
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int roomNum = scan.nextInt();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		scan.nextLine();
		String name = scan.nextLine();
		if (hotelMap.get(roomNum) != null) {
			System.out.println(roomNum + "방에는 이미 사람이 있습니다.");
			return;
		}
		System.out.println("체크인 되었습니다.");
		hotelMap.put(roomNum, new reserve(roomNum, name));

	}
}

class reserve implements Serializable {
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
		return "투숙객 [name=" + name + ", roomNum=" + roomNum + "]";
	}

}
