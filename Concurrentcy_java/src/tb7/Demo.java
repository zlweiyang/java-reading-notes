package tb7;

import java.util.concurrent.Exchanger;

public class Demo {
	
	public void a (Exchanger<String> exch) {
		
		System.out.println("a ����ִ��...");
		
		try {
			System.out.println("a �߳�����ץȡ����...");
			Thread.sleep(2000);
			System.out.println("a �߳�ץȡ������...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String res = "12345";
		
		try {
			System.out.println("a �ȴ��ԱȽ��...");
			exch.exchange(res);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void b (Exchanger<String> exch) {
		System.out.println("b ������ʼִ��...");
		try {
			System.out.println("b ������ʼץȡ����...");
			Thread.sleep(4000);
			System.out.println("b ����ץȡ���ݽ���...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String res = "12345";
		
		try {
			String value = exch.exchange(res);
			System.out.println("��ʼ���бȶ�...");
			System.out.println("�ȶԽ��Ϊ��" + value.equals(res));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		Demo d = new Demo();
		Exchanger<String> exch = new Exchanger<>();
		new Thread(new Runnable() {
			@Override
			public void run() {
				d.a(exch);
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				d.b(exch);
			}
		}).start();
		
	}

}
