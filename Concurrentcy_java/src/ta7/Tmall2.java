package ta7;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tmall2 {

	private int count;

	private Lock lock = new ReentrantLock();
	Condition p = lock.newCondition();
	Condition t = lock.newCondition();

	public final int MAX_COUNT = 10;

	public void push() {
		lock.lock();
		while (count >= MAX_COUNT) {
			try {
				System.out.println(Thread.currentThread().getName() + " ��������ﵽ���ޣ�������ֹͣ������");
				p.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		count++;
		System.out.println(Thread.currentThread().getName() + " ��������������ǰ���Ϊ��" + count);
		t.signal();
		lock.unlock();
	}

	public void take() {
		lock.lock();
		while (count <= 0) {
			try {
				System.out.println(Thread.currentThread().getName() + " �������Ϊ�㣬�����ߵȴ���");
				t.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		count--;
		System.out.println(Thread.currentThread().getName() + " ���������ѣ���ǰ���Ϊ��" + count);
		p.signal();
		lock.unlock();
	}

}
