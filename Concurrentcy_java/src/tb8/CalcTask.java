package tb8;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class CalcTask extends RecursiveTask<Integer> {
	
	private int begin;
	private int end;
	
	public CalcTask(int begin, int end) {
		this.begin = begin;
		this.end = end;
	}
	
	private static final int threshold = 2;

	@Override
	protected Integer compute() {
		System.out.println(Thread.currentThread().getName() + " ���� " + begin + " " + end);
		if(end - begin <= threshold) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(Thread.currentThread().getName() + " �������..." + begin + " " + end);
			return begin + end;
		}
		
		int middle = (begin + end) / 2;
		
		CalcTask s = new CalcTask(begin, middle);
		CalcTask l = new CalcTask(middle + 1, end);
		
		s.fork();
		l.fork();
		
		
		int a = s.join();
		int b = l.join();
		System.out.println(Thread.currentThread().getName() + " join " + a + " " + b);
		
		return a + b;
	}
	
	public static void main(String[] args) throws Exception {
		ForkJoinPool pool = new ForkJoinPool(20);
		
		CalcTask c = new CalcTask(1, 8);
		Future<Integer> f = pool.submit(c);
		System.out.println(f.get());
		
	}

}
