package t4;

public class Demo {
	
	public static void main(String[] args) {
		
		
		Thread t1 =  new Thread(new Target());
		Thread t2 =  new Thread(new Target());
		
		
		t1.setPriority(1);
		t2.setPriority(Thread.MIN_PRIORITY);
		
		t1.start();
		t2.start();
		
	}

}
