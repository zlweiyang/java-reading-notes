package t7;

/**
 * ��֤�ɼ��Ե�ǰ��
 * 
 * ����߳��õ�����ͬһ�����������Ǳ�֤���˵ġ�
 * 
 * @author worker
 *
 */
public class Demo {

	public volatile int a = 1;

	public synchronized int getA() {
		return a++;
	}

	public synchronized void setA(int a) {
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.a = a;
	}

	public static void main(String[] args) {

		Demo demo = new Demo();

		demo.a = 10;

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(demo.a);
			}
		}).start();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("���յĽ��Ϊ��" + demo.getA());

	}

}
