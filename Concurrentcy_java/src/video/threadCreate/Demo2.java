package video.threadCreate;

/**
 * @author zlCalma
 * @date 2018/11/15 14:36.
 */
public class Demo2 {

    public static void main(String[] args){
//        new Thread(){
//            @Override
//            public void run() {
//                System.out.println("线程执行");
//            }
//        }.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程执行");
            }
        }).start();
    }
}
