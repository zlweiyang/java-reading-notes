package tc5;

public class Demo {
	
	private int a;
	private volatile boolean flag;
	
	public void writer () {
		a = 1; // 1
		flag = true; // 2   ��дһ��volatile����ʱ��Java�ڴ�ģ�ͻ�Ѹ��̶߳�Ӧ�ı����ڴ��еĹ������ֵˢ�µ����ڴ���
	}
	
	public void reader () {
		if(flag) { // 3 ����һ��volatile����ʱ��Java�ڴ�ģ�ͻ�ѵ�ǰ�̶߳�Ӧ�ı����ڴ��еĹ��������Ϊ��Ч��Ȼ������ڴ��ж�ȡ���������
			int b = a + 1; // 4
			System.out.println(b); // 5
		}
	}
	
}
