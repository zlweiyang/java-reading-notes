package tc4;

/**
 * ���������� 
 * ����������
 * ������
 * @author worker
 *
 */
public class Demo {
	
	private int value;
	
	public synchronized void a() { // 1  ��ȡ��
		value ++; // 2
	} // 3 �ͷ���
	
	public synchronized void b() { // 4 ��ȡ��
		int a = value; // 5
		// ���������Ĳ��� 
	} // 6 �ͷ���
	

}
