**Java并发编程艺术知识点**

## 1.Volatile ##
轻量级synchronized，保证共享变量的可见性，与synchronized相比执行成本低，不会引起上下文切换和调度。

可见性和原子性。


## 第四章：Java并发编程基础 ##

    package com.zl.Concurrency.demo4;
    
    import java.lang.management.ManagementFactory;
    import java.lang.management.ThreadInfo;
    import java.lang.management.ThreadMXBean;
    
    public class MultiThread {
    public static void main(String[] args){
        
        //获取Java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //不需要获取同步的monitor和synchronizer信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
        //遍历线程信息，仅打印线程ID和线程名称信息
        for(ThreadInfo threadInfo : threadInfos){
            System.out.println("[" + threadInfo.getThreadId() + "]" + threadInfo.getThreadName());
        }
        
    }
    }

    output:
    [11]Monitor Ctrl-Break
    [10]Common-Cleaner
    [5]Attach Listener
    [4]Signal Dispatcher
    [3]Finalizer
    [2]Reference Handler
    [1]main
    

从上面可以看出，一个Java程序不仅仅是main()方法的运行，而是main线程和多个其他线程的同时运行。

    package com.zl.Concurrency.demo4;
    
    import java.util.ArrayList;
    import java.util.List;
    import java.util.concurrent.TimeUnit;
    
    public class Priority {
    private static volatile boolean notStart = true;
    private static volatile boolean notEnd = true;

    public static void main(String[] args)throws Exception{
        List<Job> jobs = new ArrayList<Job>();
        for(int i=0;i<10;i++){
            int pripority = i < 5 ? Thread.MIN_PRIORITY:Thread.MAX_PRIORITY;
            Job job = new Job(pripority);
            jobs.add(job);
            Thread thread = new Thread(job,"Thread:" + i);
            thread.setPriority(pripority);
            thread.start();
        }
        notStart = false;
        TimeUnit.SECONDS.sleep(10);
        notEnd = false;
        for(Job job : jobs){
            System.out.println("Job Priority : " + job.priority + ",Count :"+job.jobCount);
        }

    }
    static class Job implements Runnable{
        private int priority;
        private long jobCount;
        public Job(int priority){
            this.priority = priority;
        }

        @Override
        public void run() {
            while(notStart){
                Thread.yield();
            }
            while (notEnd){
                Thread.yield();
                jobCount++;
            }
        }
    }
    }

Job Priority : 1,Count :7446508
Job Priority : 1,Count :5103665
Job Priority : 1,Count :15529731
Job Priority : 1,Count :12993933
Job Priority : 1,Count :7446411
Job Priority : 10,Count :17143169
Job Priority : 10,Count :25792168
Job Priority : 10,Count :16736302
Job Priority : 10,Count :16935396
Job Priority : 10,Count :18582495

书上输出的结构差距不大，但是我的机器输出结果有明显差异。**线程优先级不能作为程序正确性的依赖？？**


**线程状态**
线程创建之后，调用start()方法开始运行。当线程执行wait()方法之后，线程进入等待状态。进入等待状态的线程需要依靠其他线程的通知才能返回到运行状态。而超时等待状态相当于在等待状态上加上超时限制，也就是超时时间到达时将会返回运行状态。当线程调用同步方法时，在没有获取锁的情况下，线程将会进入阻塞状态。线程在执行Runnable的run方法之后将会进入终止状态。

Daemon线程
    package com.zl.Concurrency.demo4;
    
    public class Daemon {
    public static void main(String[] args){
        Thread thread = new Thread(new DaemonRunner(),"DaemonRunner");
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable{
        @Override
        public void run() {
            try{
                SleepUtils.second(10);
            }finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }


    }

Daemon线程被用作完成支持性工作，但是Java虚拟机在退出时Daemon线程中的finally块不一定会执行。

## ThreadLocal ##

ThreadLocal,即线程变量，是一个以ThreadLocal对象为键，任意对象为值的存储结构。这个结构被附带在线程上，也就是说一个线程可以根据一个ThreadLocal对象查询到绑定在这个线程的的一个值。