package tc6;

public class Demo {
	
	private int a;
	private static final int b ;
	
	static  {
		b = 10;
	}
	
	public Demo() { // 1
//		b = 20; // 2
		a = 10; // 3
	} // 4
	
	private Demo demo;
	
	public void w() { // 5
		demo = new Demo(); // 6
	} // 
	
	public void r () {
		Demo d = demo; // 7
		int temp1 = d.a; // 8
		int temp2 = d.b; // 9
	}
	
	
}
