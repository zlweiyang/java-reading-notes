package ta4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock2 implements Lock {

	private Helper helper = new Helper();

	private class Helper extends AbstractQueuedSynchronizer {

		@Override
		protected boolean tryAcquire(int arg) {

			// �����һ���߳̽����������õ�����������ǿ��Է���true

			// ����ڶ����߳̽��������ò�����������false�����������������ǰ�������̺߳͵�ǰ������߳���ͬһ���̣߳�������õ����������д��ۣ�Ҫ����״ֵ̬

			// ����ж��ǵ�һ���߳̽������������߳̽�����
			int state = getState();
			Thread t = Thread.currentThread();

			if (state == 0) {
				if (compareAndSetState(0, arg)) {
					setExclusiveOwnerThread(t);
					return true;
				}
			} else if (getExclusiveOwnerThread() == t) {
				setState(state + 1);
				return true;
			}
			return false;
		}

		@Override
		protected boolean tryRelease(int arg) {

			// ���Ļ�ȡ���ͷſ϶���һһ��Ӧ�ģ���ô���ô˷������߳�һ���ǵ�ǰ�߳�

			if (Thread.currentThread() != getExclusiveOwnerThread()) {
				throw new RuntimeException();
			}

			int state = getState() - arg;

			boolean flag = false;

			if (state == 0) {
				setExclusiveOwnerThread(null);
				flag = true;
			}

			setState(state);

			return flag;
		}

		Condition newCondition() {
			return new ConditionObject();
		}

	}

	@Override
	public void lock() {
		helper.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		helper.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		return helper.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return helper.tryAcquireNanos(1, unit.toNanos(time));
	}

	@Override
	public void unlock() {
		helper.release(1);
	}

	@Override
	public Condition newCondition() {
		return helper.newCondition();
	}

}
