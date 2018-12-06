package ta7;

public class Tmall {
	
	private int count;
	
	public final int MAX_COUNT = 10;
	
	public synchronized void push () {
		while(count >= MAX_COUNT) {
			try {
				System.out.println(Thread.currentThread().getName() + " ��������ﵽ���ޣ�������ֹͣ������");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		count ++;
		System.out.println(Thread.currentThread().getName() + " ��������������ǰ���Ϊ��" + count);
		notifyAll();
	}
	
	public synchronized void take () {
		
		while(count <= 0) {
			try {
				System.out.println(Thread.currentThread().getName() + " �������Ϊ�㣬�����ߵȴ���");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		count --;
		System.out.println(Thread.currentThread().getName() + " ���������ѣ���ǰ���Ϊ��" + count);
		notifyAll();
	}

}
