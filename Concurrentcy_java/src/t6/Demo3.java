package t6;

public class Demo3 {
	
	private Object obj1 = new Object();
	private Object obj2 = new Object();
	
	
	public void a () {
		synchronized (obj1) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (obj2) {
				System.out.println("a");
			}
		}
	}
	
	public void b () {
		synchronized (obj2) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (obj1) {
				System.out.println("b");
			}
		}
	}
	
	public static void main(String[] args) {
		
		Demo3 d = new Demo3();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				d.a();
			}
		}).start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				d.b();
			}
		}).start();
	}

}
