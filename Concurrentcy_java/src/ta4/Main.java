package ta4;

public class Main {

	private int value;
	private MyLock2 lock = new MyLock2();

	public int next() {
		lock.lock();

		try {
			Thread.sleep(300);
			return value++;
		} catch (InterruptedException e) {
			throw new RuntimeException();
		} finally {
			lock.unlock();
		}
	}
	
	public void a() {
		lock.lock();
		System.out.println("a");
		b();
		lock.unlock();
	}
	
	public void b() {
		lock.lock();
		System.out.println("b");
		lock.unlock();
	}

	public static void main(String[] args) {

		Main m = new Main();

		new Thread(new Runnable() {

			@Override
			public void run() {
				m.a();
			}
		}).start();

	}

}
