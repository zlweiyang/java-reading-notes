package tb2;

public class Demo {

	public void a(Thread joinThread) {

		System.out.println("����aִ����...");
		joinThread.start();
		try {
			joinThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("a����ִ�����...");

	}

	public void b() {
		System.out.println("�����߳̿�ʼִ��....");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("�����߳�ִ�����...");
	}

	public static void main(String[] args) {
		Demo demo = new Demo();
		Thread joinThread = new Thread(new Runnable() {

			@Override
			public void run() {
				demo.b();
			}
		});

		new Thread(new Runnable() {

			@Override
			public void run() {
				demo.a(joinThread);
			}
		}).start();
	}

}
