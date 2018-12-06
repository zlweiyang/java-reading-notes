package tc3;

public class Demo {
	
	private int a;
	private int b;
	private int c;
	
	/**
	 * 1 happens-before 2
	 * 2 happens-before 3
	 * 3 happens-before 4
	 * ...
	 */
	public void a () {
		a = 2;  // 1
		b = 10; // 2
		
		c = a + b; // 3
		
		System.out.println(c);  // 4
	}
	
	public static void main(String[] args) {
		new Demo().a();
	}

}
