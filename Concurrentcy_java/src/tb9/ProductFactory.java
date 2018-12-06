package tb9;

import java.util.Random;

public class ProductFactory {
	
	public Future createProduct(String name) {
		Future f = new Future(); // ����һ������
		System.out.println("�µ��ɹ��������ȥ�ϰ��ˡ�����");
		// ������Ʒ
		new Thread(new Runnable() {
			@Override
			public void run() {
				Product p = new Product(new Random().nextInt(), name);
				f.setProduct(p);
			}
		}).start();
		return f;
	}

}
