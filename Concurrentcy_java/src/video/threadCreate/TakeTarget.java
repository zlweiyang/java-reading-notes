package video.threadCreate;

/**
 * @author zlCalma
 * @date 2018/11/21 21:00.
 */
public class TakeTarget implements Runnable{

    private Tmall tmall;

    public TakeTarget(Tmall tmall){
        this.tmall = tmall;
    }
    @Override
    public void run() {

        while (true){
            tmall.take();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
