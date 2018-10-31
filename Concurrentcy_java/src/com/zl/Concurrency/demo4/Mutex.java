package com.zl.Concurrency.demo4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public abstract class Mutex implements Lock {

    private static class Sync extends AbstractQueuedSynchronizer{
        @Override
        protected boolean isHeldExclusively(){
            return getState() == 1;
        }
        @Override
        public boolean tryAcquire(int acquires){
            if(compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int releases){
            if(getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        Condition newCondition(){
                return new ConditionObject();

        }

        private final Sync sync = new Sync();
        public void lock(){
            sync.acquire(1);
        }
        public boolean tryLock(){
            return sync.tryAcquire(1);
        }
        public void unlock(){
            sync.release(1);
        }
        public Condition newConnection(){
            return sync.newConnection();
        }
        public boolean isLocked(){
            return sync.isHeldExclusively();
        }
        public boolean hasQueueThreads(){
            return sync.hasQueuedThreads();
        }

        public void lockInterruptibly()throws InterruptedException{
            sync.acquireInterruptibly(1);
        }
        public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
            return sync.tryAcquireNanos(1,unit.toNanos(timeout));
        }
    }
}
