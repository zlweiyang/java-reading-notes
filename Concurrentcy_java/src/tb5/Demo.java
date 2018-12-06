package tb5;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Demo {

	Random random = new Random();

	public void meeting(CyclicBarrier barrier) {
		try {
			Thread.sleep(random.nextInt(4000));
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " ��������ң��ȴ�����..");

		if(Thread.currentThread().getName().equals("Thread-7")) {
			// Thread.currentThread().interrupt();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			barrier.reset();
			
		}
		
		try {
			barrier.await();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Demo demo = new Demo();

		CyclicBarrier barrier = new CyclicBarrier(10, new Runnable() {
			@Override
			public void run() {
				System.out.println("�ã����ǿ�ʼ����...");
			}
		});

		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					demo.meeting(barrier);
				}
			}).start();
		}
		
		// ��صȴ��߳���
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("�ȴ����߳��� " + barrier.getNumberWaiting());
					System.out.println("is broken " + barrier.isBroken());
				}
			}
		}).start();
	}

}
