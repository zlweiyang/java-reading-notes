package tc9;

public class Main {
	
	public static void main(String[] args) {
		
		Shop tmall = new Tmall3();
		
		PushTarget p = new PushTarget(tmall);
		TakeTarget t = new TakeTarget(tmall);
		
		new Thread(p).start();
		new Thread(p).start();
		new Thread(p).start();
		new Thread(p).start();
		new Thread(p).start();
		new Thread(p).start();
		new Thread(p).start();
		new Thread(p).start();
		
		new Thread(t).start();
		new Thread(t).start();
		new Thread(t).start();
		new Thread(t).start();
		new Thread(t).start();
		new Thread(t).start();
		new Thread(t).start();
		new Thread(t).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				tmall.size();				
			}
		}).start();
	}
	
}
