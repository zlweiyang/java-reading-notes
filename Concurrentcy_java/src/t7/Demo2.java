package t7;

public class Demo2 {
	
	public volatile boolean run = false;
	
	public static void main(String[] args) {
		
		Demo2 d = new Demo2();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 1;i<=10;i++) {
					System.err.println("ִ���˵� " + i + " ��");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				d.run = true;
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(!d.run) {
					// ��ִ��
				}
				System.err.println("�߳�2ִ����...");
			}
		}).start();
		
		
	}

}
