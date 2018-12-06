package tb1;

public class Demo {
	
	public void target (Thread joinThread) {
		
		System.out.println("target ����ִ����...");
		try {
			joinThread.start();
			joinThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("join �߳�ִ�����...");
		
	}
	
	
	public static void main(String[] args) {
		Demo d = new Demo();
		
		Thread joinThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					d.a();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				d.target(joinThread);
			}
		}).start();
	}


	protected void a() throws InterruptedException {
		System.out.println("join �߳̽���");
		Thread.sleep(1000);
	}

}
