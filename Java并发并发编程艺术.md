**Java并发编程艺术知识点**

来源于《Java并发编程的艺术》华科大佬博客Java并发指南：https://blog.csdn.net/column/details/21961.html

## Java并发编程的基本概念 ##：

1.首先应该弄清楚几个名词：并发，多线程，线程安全。理解Java实现多线程的方法，线程相关知识点《第一章》《第四章》。

2.JMM内存模型《第三章》

3.关键字：synchronize，volatile，final关键字。

4.并发编程三要素：

- 原子性：一个或者多个操作要么**全部执行成功，要么全部执行失败**。
- 有序性：程序执行的顺序按照**代码的顺序**(处理器可能对指令进行重排序)。
- 可见性：多个线程在访问同一个变量时，如果其中一个线程对其作了修改，**其他线程能立即获取到最新的值**。

5.线程的五大状态

- 创建状态：当用new创建一个线程的时候。
- 就绪状态：调用start方法，处于就绪状态的线程不一定马上就会执行run方法，还需要等待cpu的调度。
- 运行状态：CPU开始调度线程。并执行run方法
- 阻塞状态:线程的执行过程由于一些原因进入阻塞状态，比如：调用sleep方法，尝试得到一个锁等。
- 死亡状态：run方法执行完或者执行过程中遇到一个异常。


<div align="center"> <img src="threadstate.png" width="450"/> </div><br>

6.活跃性问题

- 死锁：哲学家就餐
- 饥饿：就餐挤不进去，一直得不到资源。
- 活锁：相互礼让，又总是主动放弃资源。


## 6.线程的创建方式 ##

- 1.继承Thread类

Daemon线程使程序退出执行，d1.interrupt();//仅仅调用interrupt不能实现线程终止，可以配合interrupted()中断标志使用。

   package video.threadCreate;

    public class Demo1 extends Thread{
    @Override
    public void run() {
        while (!interrupted()) {//中断标志
            System.out.println(getName() + "线程执行");
        }
    }
    public static void main(String[] args){
        Demo1 d1 = new Demo1();
        Demo1 d2 = new Demo1();
        //
     //        d1.setDaemon(true);//守护线程或者叫支持性线程，
    //        d2.setDaemon(true);

        d1.start();
        d2.start();

        d1.interrupt();//仅仅调用interrupt不能实现线程终止
        //d1.stop();//无限期终止
    //        try {
    //            Thread.sleep(2000);
    //        } catch (InterruptedException e) {
    //            e.printStackTrace();
    //        }
    }
    }
- 2.实现Runable接口

//runable只是作为线程任务传给Thread。

    public class Demo implements Runnable {
    @Override
    public void run() {
        while(true){
            System.out.println("线程执行");
        }
    }
    public static void main(String[] args){
        Thread thread = new Thread(new Demo());
        thread.start();
    }
    }

- 3.使用匿名内部类

 public class Demo2 {

    public static void main(String[] args){
             //使用子类
    //        new Thread(){
    //            @Override
    //            public void run() {
     //                System.out.println("线程执行");
    //            }
    //        }.start();
           //使用Runable对象
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程执行");
            }
        }).start();
    }
    }
- 4.使用Callable接口，有返回值。

public class Demo3 implements Callable<Integer> {

    public static void main(String[] args)throws Exception {
        Demo3 d = new Demo3();
        FutureTask<Integer> task = new FutureTask<>(d);
        Thread t = new Thread(task);
        t.start();
        System.out.println("先做点其他事情");
        Integer res = task.get();
        System.out.println(res);
    }
    //类似于run方法
    @Override
    public Integer call() throws Exception{
        System.out.println("正在进行计算");
        Thread.sleep(3000);
        return 1;
    }
    }

- 5.定时器(quartz框架)

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

- 6.线程池：装有多个线程的容器，降低线程的创建和销毁的时间。

  public class Demo5 {

    public static void main(String[] args){
        //创建带有10个线程的线程池
        //Executor threadPool = Executors.newFixedThreadPool(10);
        ExecutorService threadPool =  Executors.newCachedThreadPool();//认为够用就回收，不够用就创建线程
        //线程任务
        for(int i=0;i<100;i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
    }
    }
- lambda表达式。


# Java内存模型 《第三章》#



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
    Job Priority : 10,Count :17143169·
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

**源码：**

get方法：获取ThreadLocalMap中Entry中的value值，ThreadLocalMap是Thread类中的对象，是ThreadLocal中的静态内部类;。

    public T get() {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null) {
            ThreadLocalMap.Entry e = map.getEntry(this);
            if (e != null) {
                @SuppressWarnings("unchecked")
                T result = (T)e.value;
                return result;
            }
        }
        return setInitialValue();
    }

set方法

    public void set(T value) {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
    }
remove方法

## 深入理解Java内存模型 ##

在并发编程中，需要处理两个关键问题：线程之间的**通信**以及线程之间的**同步**。通信是指线程之间以何种机制来交换信息。在命令式编程中，线程之间通信机制有两种：**共享内存**和**消息传递**。

在共享内存的并发模型里。线程之间共享程序的公共状态，线程之间通过写-读内存中的公共状态来进行隐式通信。在消息传递的并发模型里，线程之间没有公共状态，线程之间通过明确的发送显式进行通信。

## 1.重排序 ##
编译器和处理器为了提高程序性能而对指令序列进行重新排序的一种手段。

1. 数据依赖性：编译器和处理器在重排序时，不会改变存在数据依赖关系的两个操作的执行顺序。

2. as-if-serial语义：不管怎么重排序，都不能改变程序执行结果。

3. 程序顺序规则：happens-before规则。

## 2.happens-before ##

用来指定两个操作之间的执行顺序，提供跨线程的内存可见性。当一个操作的执行结果对另一个操作可见，那么这两个操作之间必然存在happens-before关系。

**happens-before规则：**

- 1.程序顺序规则：一个线程中的每个操作，happens-before于该线程中的任意后续操作。按程序顺序执行。
- 2.监视器锁规则：（上一个线程的锁解锁，下一个锁的获取）对一个锁的解锁，happens-before于随后对这个锁加锁。
- 3.volatile变量规则：对于一个volatile域的写，happens-before于任意后续对这个volatile域的读。
- 4.传递性：A --->B B--->C  A---->C
- 5.Start规则:如果A线程执行操作ThreadB.start()(启动线程B)，那么A线程的ThreadB.start()操作happens-before于线程B中的任意操作。
- 6.Join规则：如果线程A执行操作ThreadB.join()并成功返回，那么线程B中任意操作happens-before于线程A从ThreadB.join()操作成功返回。

## 3.锁的内存语义 ##

- 锁的释放和获取建立happens-before关系：1.程序顺序规则，2.监视器规则，3.传递性规则。
- 锁的释放和获取的内存语义：1.线程A释放一个锁，其实质上是想接下来要获取的锁的某个线程传递消息(线程A对共享变量所做的修改)；2.线程B获取一个锁，其实质上是线程B接收了之前某个线程的消息(对共享变量所做的修改)。3.线程A释放锁，随后线程B获取这个锁。这个过程实质上是线程A通过线程B发送消息。

## 4.volatile内存语义 ##
volatile的写-读类似于锁的释放和获取。

volatile写和读建立的happens-before关系：1.程序顺序规则，2.volatile规则，3.传递性规则。
volatile写和读的内存语义：当写一个volatile变量时，JMM会把该线程对应的本地内存中共享变量值刷新到主内存。当读一个volatile变量时，JMM会把该线程对应的本地内存置为无效，而该线程接下来从主内存中读取共享变量。

总结：1.线程A写一个volatile变量时，实质上是线程A向接下来要读这个volatile变量的某个线程发出了消息(其对共享变量所做的修改)。2.当线程B读一个volatile变量时，实质上是线程B接收了某个线程发出的消息(在写操作时，volatile变量对主内存中变量的修改)。3.线程A写一个volatile变量，随后线程B读这个volatile变量，这个过程实质上是线程A通过主内存向线程B发送消息。

## 5.final的内存语义 ##
**final域的重排序规则**：1.构造方法内对一个final域的写入，与随后把这个被构造对象的引用赋值给一个引用变量，这两个操作之间不能重排序。2，初次读取一个包含final域的对象引用，与随后初次读这个final域，这两个操作之间不能重排序。

**写final域的重排序规则：**写final域的重排序规则禁止把final域的写重排序到构造函数之外。这个规则的实现包含两个方面：1.JMM禁止编译器把final域的写重排序到构造函数之外，2.编译器会在final域的写之后，构造函数return之前，插入一个StoreStore屏障。这个屏障禁止处理器把final域写重排序到构造函数之外。

**读final域的重排序规则：**在一个线程中，初次读对象引用与初次读对象包含final域，JMM禁止重排序这两个操作，编译器会在读final域操作的前面插入一个LoadLoad屏障。

## synchronized ##

作用：

- 放在普通方法上，内置锁作用域该实例对象，所谓的互斥锁即为独占锁。
- 修饰静态方法时，内置锁作用域是当前Class字节码对象。
- 修饰代码块时，内置锁作用Synchronized作用域括号里配置的对象。

synchronized用的锁是存在java对象头里的，如果对象时数组类型，虚拟机用三个字宽(4字节)存储对象头，如果是非数组类型，则用两个字宽存储对象头。

**对象头中的信息**：

Mark Word：存储对象的hashcode或锁信息。
Class Metadata Address:存储对象类型数据的指针。
Array length：数组的长度。

在JVM中的原理：

进入代码块时，会有一个monitorenter指令，退出代码块时，会有一个monitorexit指令。

**偏向锁**

作用：由于每次获取锁和释放锁都会浪费资源，且实际情况是大多数情况下总是由一个线程获取，为了降低获得锁的代价引入偏向锁。

- 线程ID：线程第一次访问同步代码块时，会在对象头和栈帧中的锁记录锁偏向的**线程ID**，后续该线程访问时只需要测试对象头的MarkWord中是否存在该线程的偏向锁，如果测试成功则，表示线程已经获得锁，如果测试失败，检测Mark Word中的偏向**锁标识**是否设置为1，如果没有则使用CAS竞争锁，如果设置了，则尝试使用CAS将对象头的偏向锁指向当前线程。

偏向锁的撤销：偏向锁使用一种等到竞争出现才释放锁的机制，当其他线程尝试竞争偏向锁时，才考虑释放锁。偏向锁撤销时，暂停拥有偏向锁的线程，然后检查所有偏向锁的线程是否活着，如果不处于活跃状态，则将对象头设置为无锁状态，如果线程仍然活着，拥有偏向锁的栈会被执行。

关闭偏向锁：-XX：-UseBiasedLocking=flase，程序默认进入轻量级锁。

**轻量级锁**

轻量级锁加锁：执行同步代码块前，JVM会在当前线程的栈帧中创建用于锁记录的空间，并将对象头中Mark Word替换为指向锁记录的指针，如果成功，当前线程获得锁，如果失败，表示其他线程竞争锁，当前线程便尝试使用**自旋**来获取锁。

轻量级锁解锁：使用原子CAS操作将锁记录中的Mark Word(Displaced Mark Word)替换到对象头,如果成功，则表示没有竞争发生。如果失败，表示当前锁存在竞争，锁会膨胀为重量级锁(自旋失败转化为重量级锁)。

重量级锁：

自旋锁：（自旋就是cpu在空转）。


**Java中的锁**

1. 重入锁：synchronized(隐式的支持重进入)。
2. 自旋锁：

## volatile ##

轻量级锁，被volatile修饰的变量是可见的。所谓可见性就是当一个线程修改一个共享变量时，另一个线程能读到修改这个值。synchronize除了互斥也可以保证可见性

volatile底层实现，

# Lock接口 #

Lock接口需要显示地获取和释放锁，繁琐是代码灵活；实际上对synchronized进行简单的包装。

特点：
1. 非阻塞获取锁。
2. 能被中断的获取锁。
3. 超时获取锁。

## AbstractQueuedSynchronizer(AQS)详解 ##

- tryAcquire(int) ：独占模式获取对象状态。
- tryRelease(int) ：独占模式下释放线程调用。
- tryAcquireShared(int) ：共享模式下获取对象。
- tryReleaseShared(int) ：共享模式释放线程调用。
- isHeldExclusively() ：如果同步是以独占方式进行的，则返回true；其他情况则返回 false 。

## ReentrantLock ##


# 线程之间的通信 #

notify方法会随机叫醒一个处于wait状态的线程，notifyAll叫醒所有处于wait线程，争夺到时间片的线程只有一个。当调用wait方法时，会释放锁，当调用notify方法时会拿到锁。

## 生产者消费者模式 ##







