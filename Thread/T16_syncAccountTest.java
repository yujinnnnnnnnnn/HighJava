package kr.or.ddit.basic;

/**
 * 은행의 입출금을 스레드로 처리하는 예제 (synchronized을 이용한 동기화 처리)
 */
public class T16_syncAccountTest {
	public static void main(String[] args) {
		SynchAccount sAcc = new SynchAccount();
		sAcc.setBalance(10000); // 입금처리

		BankThread bth1 = new BankThread(sAcc);
		BankThread bth2 = new BankThread(sAcc);

		bth1.start();
		bth2.start();
	}
}

// 은행의 입출금을 관리하는 클래스 정의(공유객체)
class SynchAccount {
	private int balance; // 잔액이 저장될 변수

	synchronized public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	// 입금 처리를 수행하는 메서드
	public synchronized void deposit(int money) {
		balance += money;
	}

	// 출금을 처리하는 메서드(출금성공: true, 출금실패 : false 반환)
	// 동기화 영역에서 호출하는 메서드도 동기화처리를 해주어야 한다  
	/* synchronized */ public boolean withdraw(int money) {
		synchronized (this) {
			if (balance >= money) { // 잔액이 많을경우
				for (int i = 1; i <= 100000000; i++) {
				} // 시간때우기 // 스레드1에 대한 시간때우기 작업이 실행될때 스레드2가 들어와 둘다 ture가 됨 => 동기화처리가 필요함 (임계영역)
				balance -= money; // 잔액 - 출금하고자하는 금액
				System.out.println("메서드 안에서 balance = " + getBalance()); // getBalance()는 동기화 영역에서 호출하는 메서드이기때문에 동일하게 동기화 처리를 해주어야 한다

				return true;
			} else {
				return false;
			}
		}
	}
}

// 은행 업무를 처리하는 스레드 클래스
class BankThread extends Thread {
	private SynchAccount sAcc;

	public BankThread(SynchAccount sAcc) { // 두개의 쓰레드가 공유객체로 들어감
		this.sAcc = sAcc;
	}

	@Override
	public void run() {
		boolean result = sAcc.withdraw(6000); // 6000원 인출
		System.out.println("스레드 안에서 result =" + result + ", balance = " + sAcc.getBalance());
	}
}
