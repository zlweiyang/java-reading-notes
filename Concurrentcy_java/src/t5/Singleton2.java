package t5;

public class Singleton2 {
	
	private Singleton2() {}
	
	private static volatile Singleton2 instance;
	
	/**
	 * ˫�ؼ�����
	 * 
	 * @return
	 */
	public static Singleton2 getInstance () {
		// ����   while(true)
		if(instance == null) {
			synchronized (Singleton2.class) {
				if(instance == null) {
					instance = new Singleton2();  // ָ��������
					
					// ����һ���ڴ�ռ�   // 1
					// �����ռ���ʵ��������  // 2
					// instance������ָ�����ռ��ַ   // 3
				}
			}
		}
		return instance;
	}
	
	// ���̵߳Ļ�����
	// �����й�����Դ
	// ����Դ���з�ԭ���Բ���

}
