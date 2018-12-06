package ta6;

public class Demo {
	
	private volatile int signal;
	
	public void set (int value) {
		this.signal = value;
	}
	
	public int get () {
		return signal;
	}
	
	public static void main(String[] args) {
		Demo d = new Demo();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("�޸�״̬���߳�ִ��...");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				d.set(1);
				System.out.println("״ֵ̬�޸ĳɹ�������");
			}
		}).start();
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				// �ȴ�signalΪ1��ʼִ�У�������ִ��
				while(d.get() != 1) {
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				// ���ź�Ϊ1 ��ʱ��ִ�д���
				System.out.println("ģ������ִ��...");
				
				
			}
		}).start();
	}

}
