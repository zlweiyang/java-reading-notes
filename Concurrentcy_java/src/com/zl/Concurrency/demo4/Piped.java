package com.zl.Concurrency.demo4;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * 管道输入/输出流和普通文件输入/输出流或者网络输入/输出流的不同在于，
 * 它主要用于线程之间的数据传输，传输的媒介为内存
 *
 * PipedOutputStream和PipedInputStream面向字节流的
 * PipedReader和PipedWriter面向字符
 */

public class Piped {
    public static void main(String[] args) throws Exception {
        PipedWriter out = new PipedWriter();//新建字符输出流
        PipedReader in = new PipedReader();//新建字符输入流

        out.connect(in);//将字符输入流与字符输出流进行连接
        Thread printThread = new Thread(new Print(in),"PrintThread");//新建PirntThead线程
        printThread.start();
        int receive = 0;
        try {
            while((receive = System.in.read())!= -1){
                out.write(receive);
            }
        }finally {
            out.close();
        }
    }
    //实现Runable接口新建线程
    static class Print implements Runnable{
        private PipedReader in;
        //线程类构造函数
        public Print(PipedReader in){
            this.in = in;
        }

        @Override
        public void run() {
            int receive = 0;
            try{
                while ((receive = in.read()) != -1){
                    System.out.print((char) receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
