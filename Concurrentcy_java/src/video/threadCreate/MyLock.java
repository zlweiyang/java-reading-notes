package video.threadCreate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author zlCalma
 * @date 2018/11/19 21:26.
 */
public class MyLock implements Lock {
    private boolean isLocked = false;

    Thread lockBy = null;
    int lockCount = 0;


    //加锁
    @Override
    public synchronized void  lock() {

        Thread currentThread = Thread.currentThread();

        while (isLocked && currentThread != lockBy) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        isLocked = true;
            lockBy = currentThread;
            lockCount++;
        }
    }


    @Override
    public   void lockInterruptibly() throws InterruptedException {


    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }
    //释放锁
    @Override
    public synchronized void  unlock() {
        if(lockBy == Thread.currentThread()){
            lockCount --;
            if (lockCount == 0){
                isLocked = false;
                notify();//需要加synchronized
            }
        }

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
