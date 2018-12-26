package com.yjh.study.two;

class Runner1 extends Thread{
	@Override
	public void run() {
		for(int i = 0; i < 10 ; ++i ) {
			System.out.println("Runner1 : " + i);
	
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Runner2 extends Thread{
	@Override
	public void run() {
		for(int i = 0; i < 100 ; ++i ) {
			System.out.println("Runner2 : " + i);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
}


public class App {
	
	public static void main(String[] args) {
		Runner1 t1 = new Runner1();
		Runner2 t2 = new Runner2();
		

		t1.start();
		t2.start();
		
		try {
			// 모든 Thread에서 join을 걸어줘야한다. 
			// 만약 t1.join만 했을 시, t1 task가 종료되면 t2는 실행되고 있어도 Finised the task을 출력함 
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("Finised the task");
		
	}
}
