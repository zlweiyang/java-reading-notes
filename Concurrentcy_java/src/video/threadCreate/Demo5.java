package video.threadCreate;

import java.lang.reflect.Executable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zlCalma
 * @date 2018/11/15 15:11.
 */
public class Demo5 {
    public static void main(String[] args){
        //创建带有10个线程的线程池
        //Executor threadPool = Executors.newFixedThreadPool(10);
        ExecutorService threadPool =  Executors.newCachedThreadPool();//认为够用就回收，不够用就创建线程
        //线程任务
        for(int i=0;i<100;i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
    }
}
