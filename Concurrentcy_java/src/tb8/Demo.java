package tb8;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Demo {

	
	
	/**
	 * Callalbe��Runnable������
	 * 
	 * Runnable run�����Ǳ��̵߳��õģ���run�������첽ִ�е�
	 * 
	 * Callable��call�����������첽ִ�еģ�����Future��run�������õ�
	 * 
	 * 
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		Callable<Integer> call = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				System.out.println("���ڼ�����...");
				Thread.sleep(3000);
				return 1;
			}
		};

		FutureTask<Integer> task = new FutureTask<>(call);

		Thread thread = new Thread(task);
		thread.start();

		// do something
		System.out.println(" �ɵ���...");

		Integer result = task.get();

		System.out.println("�õ��Ľ��Ϊ��" + result);

	}

}
