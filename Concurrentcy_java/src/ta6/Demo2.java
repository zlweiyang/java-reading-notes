package ta6;


public class Demo2 {
	
	private volatile int signal;
	
	public void set (int value) {
		this.signal = value;
	}
	
	public int get () {
		return signal;
	}
	
	public static void main(String[] args) {
		Demo2 d = new Demo2();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized (d) {
					System.out.println("�޸�״̬���߳�ִ��...");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					d.set(1);
					System.out.println("״ֵ̬�޸ĳɹ�������");
					d.notify();
				}
			}
		}).start();
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized (d) {
					// �ȴ�signalΪ1��ʼִ�У�������ִ��
					while(d.get() != 1) {
						try {
							d.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					// ���ź�Ϊ1 ��ʱ��ִ�д���
					System.out.println("ģ������ִ��...");
				}
			}
		}).start();
	}

}
