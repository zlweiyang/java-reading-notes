package tb9;

public class Demo {
	
	public static void main(String[] args) {
		
		ProductFactory pf = new ProductFactory();
		
		// �µ�����Ǯ
		Future f = pf.createProduct("����");
		
		System.out.println("��ȥ�ϰ��ˣ����˰�����ȡ����...");
		
		// ���Ŷ�����ȡ��Ʒ
		System.out.println("�����ŵ���ؼ�." + f.get());
	}

}
