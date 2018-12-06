package tc9;

public class TakeTarget implements Runnable {
	
	private Shop tmall;
	
	public TakeTarget(Shop tmall) {
		this.tmall = tmall;
	}

	@Override
	public void run() {
		while(true) {
			tmall.take();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
