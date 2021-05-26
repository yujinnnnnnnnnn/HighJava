package kr.or.ddit.basic;

import java.util.Arrays;

// 경마 : 10개의 말 쓰레드 돌리기
// 출력용 쓰레드 별도 만들기 
public class Thread_Game {

	public static void main(String[] args) {

		Horse[] horses = new Horse[] { new Horse("1번말"), new Horse("2번말"), new Horse("3번말"), new Horse("4번말"),
				new Horse("5번말"), new Horse("6번말"), new Horse("7번말"), new Horse("8번말"), new Horse("9번말"),
				new Horse("10번말"), };

		for (Horse h : horses) {
			h.start();
		}

		HorseState hs = new HorseState(horses);

		hs.start();

		for (Horse h : horses) {
			try {
				h.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			hs.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Arrays.sort(horses);
		
		for(Horse h : horses) {
			System.out.println(h);
		}
	}
}

// horse 클래스

class Horse extends Thread implements Comparable<Horse> {

	public static int currentRank = 0;
	private String Horsename;
	private int rank;
	private int position;

	public Horse(String Horsename) {
		super();
		this.Horsename = Horsename;

	}

	public String getHorsename() {
		return Horsename;
	}

	public void setHorsename(String horsename) {
		this.Horsename = horsename;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	
	@Override
	public String toString() {
		return "경주마 " + Horsename + "의 등수는 = " + rank + "등 입니다.";
	}

	@Override
	public int compareTo(Horse h) {
		
		return Integer.compare(rank, h.getRank());
	}

	@Override
	public void run() {
		for (int i = 1; i <= 50; i++) {
			position = i;

			try {
				Thread.sleep((int) (Math.random() * 401 + 200));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		currentRank++;
		rank = currentRank;
	}
}

class HorseState extends Thread {
	private Horse[] ho;

	public HorseState(Horse[] ho) {
		this.ho = ho;
	}

	public Horse[] getHo() {
		return ho;
	}

	public void setHo(Horse[] ho) {
		this.ho = ho;
	}

	@Override
	public void run() {
		while (true) {
			if (Horse.currentRank == ho.length) {
				break;
			}
			for (int i = 0; i < ho.length; i++) {
				System.out.print(ho[i].getHorsename() + " : ");

				for (int j = 1; j <= 50; j++) {
					if (ho[i].getPosition() == j) {
						System.out.print(">");
					} else {
						System.out.print("-");
					}
				}
				System.out.println();
		}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
