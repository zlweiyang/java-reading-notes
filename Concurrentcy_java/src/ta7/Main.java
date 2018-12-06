package ta7;

public class Main {
	
	public static void main(String[] args) {
		
		Tmall tmall = new Tmall();
		
		PushTarget p = new PushTarget(tmall);
		TakeTarget t = new TakeTarget(tmall);
		
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
		
		
	}
	
}
