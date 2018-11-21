package video.threadCreate;

/**
 * @author zlCalma
 * @date 2018/11/19 21:41.
 */
public class Demo7 {

    MyLock lock = new MyLock();

    public void a(){
        lock.lock();
        System.out.println("a");
        b();
        lock.unlock();

    }
    public void b(){
        lock.lock();
        System.out.println("b");
        c();
        lock.unlock();
    }

    public void c(){
        lock.lock();
        System.out.println("c");
        lock.unlock();
    }

    public static void main(String[] args) {
        Demo7 d = new Demo7();

        new Thread(new Runnable() {
            @Override
            public void run() {
                d.a();
            }
        }).start();
    }
}
