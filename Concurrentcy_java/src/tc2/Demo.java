package tc2;

public class Demo {
	
	private int a ;
	private int b;
	private int c;
	
	public void a () {
		
		// д���
		// ����д
		// д��д
		
		b = 2;
		
		a = 1; // д����
		c = a; // ������
		b = c + a;
		
		System.out.println(b);
	}
	
	public static void main(String[] args) {
		new Demo().a();
	}

}
