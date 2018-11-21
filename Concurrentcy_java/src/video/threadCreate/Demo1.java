package video.threadCreate;

public class Demo1 extends Thread{
    @Override
    public void run() {
        while (!interrupted()) {//中断标志
            System.out.println(getName() + "线程执行");
        }
    }
    public static void main(String[] args){
        Demo1 d1 = new Demo1();
        Demo1 d2 = new Demo1();
        //Daemon线程使程序退出执行
//        d1.setDaemon(true);//守护线程或者叫支持性线程，
//        d2.setDaemon(true);

        d1.start();
        d2.start();

        d1.interrupt();//仅仅调用interrupt不能实现线程终止
        //d1.stop();//无限期终止
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
