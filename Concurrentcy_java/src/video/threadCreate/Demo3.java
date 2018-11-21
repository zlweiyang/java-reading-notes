package video.threadCreate;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author zlCalma
 * @date 2018/11/15 14:48.
 */
public class Demo3 implements Callable<Integer> {
    public static void main(String[] args)throws Exception {

        Demo3 d = new Demo3();
        FutureTask<Integer> task = new FutureTask<>(d);
        Thread t = new Thread(task);
        t.start();
        System.out.println("先做点其他事情");
        Integer res = task.get();
        System.out.println(res);
    }
    //类似于run方法
    @Override
    public Integer call() throws Exception{
        System.out.println("正在进行计算");
        Thread.sleep(3000);
        return 1;
    }
}
