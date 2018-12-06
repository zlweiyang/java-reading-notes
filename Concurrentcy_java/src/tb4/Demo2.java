package tb4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Demo2 {

	private int[] nums;

	public Demo2(int line) {
		nums = new int[line];
	}

	public void calc(String line, int index, CountDownLatch latch) {
		String[] nus = line.split(","); // �зֳ�ÿ��ֵ
		int total = 0;
		for (String num : nus) {
			total += Integer.parseInt(num);
		}
		nums[index] = total; // �Ѽ���Ľ���ŵ�������ָ����λ��
		System.out.println(Thread.currentThread().getName() + " ִ�м�������... " + line + " ���Ϊ��" + total);
		latch.countDown();
	}

	public void sum() {
		System.out.println("�����߳̿�ʼִ��... ");
		int total = 0;
		for (int i = 0; i < nums.length; i++) {
			total += nums[i];
		}
		System.out.println("���յĽ��Ϊ��" + total);
	}

	public static void main(String[] args) {

		List<String> contents = readFile();
		int lineCount = contents.size();
		
		CountDownLatch latch = new CountDownLatch(lineCount);

		Demo2 d = new Demo2(lineCount);
		for (int i = 0; i < lineCount; i++) {
			final int j = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					d.calc(contents.get(j), j, latch);
				}
			}).start();
		}

		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		d.sum();
	}

	private static List<String> readFile() {
		List<String> contents = new ArrayList<>();
		String line = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("e:\\nums.txt"));
			while ((line = br.readLine()) != null) {
				contents.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return contents;
	}

}
