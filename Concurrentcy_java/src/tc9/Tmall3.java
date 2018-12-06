package tc9;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Tmall3 implements Shop {

	public final int MAX_COUNT = 10;

	private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(MAX_COUNT);

	public void push() {
		try {
			queue.put(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void take() {
		try {
			queue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void size() {
		while (true) {
			System.out.println("��ǰ���еĳ���Ϊ��" + queue.size());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
