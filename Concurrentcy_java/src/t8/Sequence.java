package t8;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

public class Sequence {
	
	private AtomicInteger value  = new AtomicInteger(0);
	
	private int [] s = {2,1,4,6};
	
	AtomicIntegerArray a = new AtomicIntegerArray(s);
	
	
	AtomicReference<User> user = new AtomicReference<>();
	
	AtomicIntegerFieldUpdater<User> old =  AtomicIntegerFieldUpdater.newUpdater(User.class, "old");
	
	/**
	 * @return
	 */
	public  int getNext() {
		
		User user = new User();
		System.out.println(old.getAndIncrement(user));
		System.out.println(old.getAndIncrement(user));
		System.out.println(old.getAndIncrement(user));
		
		
		
		a.getAndIncrement(2);
		a.getAndAdd(2, 10);
		return value.getAndIncrement();
	}
	
	public static void main(String[] args) {
		
		Sequence s = new Sequence();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
//				while(true) {
					System.out.println(Thread.currentThread().getName() + " " + s.getNext());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
//				}
			}
		}).start();
		
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				while(true) {
//					System.out.println(Thread.currentThread().getName() + " " + s.getNext());
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}).start();
//		
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				while(true) {
//					System.out.println(Thread.currentThread().getName() + " " + s.getNext());
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}).start();
		
	}

}
