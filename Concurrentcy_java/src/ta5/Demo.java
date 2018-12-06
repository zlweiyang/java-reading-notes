package ta5;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.imageio.spi.IIOServiceProvider;

public class Demo {

	private Map<String, Object> map = new HashMap<>();

	private ReadWriteLock rwl = new ReentrantReadWriteLock();

	private Lock r = rwl.readLock();
	private Lock w = rwl.writeLock();

	private volatile boolean isUpdate;

	public void readWrite() {
		r.lock(); // Ϊ�˱�֤isUpdate�ܹ��õ����µ�ֵ
		if (isUpdate) {
			r.unlock();
			w.lock();
			map.put("xxx", "xxx");
			r.lock();
			w.unlock();
		}

		Object obj = map.get("xxx");

		System.out.println(obj);
		r.unlock();

	}

	public Object get(String key) {
		r.lock();
		System.out.println(Thread.currentThread().getName() + " ��������ִ��..");
		try {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return map.get(key);
		} finally {
			r.unlock();
			System.out.println(Thread.currentThread().getName() + " ����ִ�����..");
		}
	}

	public void put(String key, Object value) {
		w.lock();
		System.out.println(Thread.currentThread().getName() + " д������ִ��..");
		try {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			map.put(key, value);
		} finally {
			w.unlock();
			System.out.println(Thread.currentThread().getName() + " д����ִ�����..");
		}
	}

}
