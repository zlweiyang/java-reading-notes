package video.threadCreate;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author zlCalma
 * @date 2018/11/15 15:05.
 */
public class Demo4 {
    public static void main(String[] args){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("timetask is run");
            }
        },0,1000);
    }
}
