package tc2;

public class Demo2 {
	
	private int a;
	private boolean flag;
	
	public void writer () {
		// ����������֮��û�����������ԣ���˴�������������д������ָ��������
		a = 1;
		flag = true;
	}
	
	public void reader () {
		if(flag) {
			int b = a + 1;
			System.out.println(b);
		}
	}

}
