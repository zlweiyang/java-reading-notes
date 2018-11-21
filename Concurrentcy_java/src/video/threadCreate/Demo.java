package video.threadCreate;

/**
 * @author zlCalma
 * @date 2018/11/15 14:29.
 */

//runable只是作为线程任务传给Thread。
public class Demo implements Runnable {

    @Override
    public void run() {
        while(true){
            System.out.println("线程执行");
        }
    }

    public static void main(String[] args){
        Thread thread = new Thread(new Demo());
        thread.start();
    }
}
