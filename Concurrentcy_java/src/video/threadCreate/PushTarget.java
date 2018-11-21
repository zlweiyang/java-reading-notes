package video.threadCreate;

/**
 * @author zlCalma
 * @date 2018/11/21 20:58.
 */
public class PushTarget implements Runnable {

    private Tmall tmall;

    public PushTarget(Tmall tmall){
        this.tmall = tmall;
    }

    @Override
    public void run() {
        while (true) {
            tmall.push();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
