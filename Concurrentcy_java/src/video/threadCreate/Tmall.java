package video.threadCreate;

/**
 * @author zlCalma
 * @date 2018/11/21 20:55.
 */
public class Tmall {

    private int count;
    public final int MAX_COUNT = 10;

    public synchronized void push(){
        while (count >= MAX_COUNT){
            try {
                System.out.println(Thread.currentThread().getName() + "库存数量达到上限，生产者停止生产");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;
        System.out.println(Thread.currentThread().getName() + "生产者生产，当前库存为" +count);
        notifyAll();
    }

    public synchronized void take(){

        while (count <=0){
            System.out.println(Thread.currentThread().getName() + "库存数量为0，消费者等待");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        System.out.println(Thread.currentThread().getName() + "消费者继续消费" + count);
        notifyAll();
    }
}
