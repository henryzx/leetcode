package lang;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zx on 2017/4/4.
 */
public class MonitorResearch {

    static final byte[] monitor = new byte[0];
    static boolean monitorFlag = false;
    static final ReentrantLock lock = new ReentrantLock(true);
    static final Condition condition = lock.newCondition();

    public static void main(String[] args) {

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    try {
                        while (!monitorFlag) {
                            System.out.println("thread 2 entering wait");
                            condition.await();
                            System.out.println("monitor did exit wait");
                        }
                    } finally {
                        lock.unlock();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread 2 will exit");
            }
        });
        t2.start();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (monitor) {
                        while (!monitorFlag) {
                            System.out.println("thread 1 entering wait");
                            monitor.wait();
                            System.out.println("monitor did exit wait");
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread 1 will exit");
            }
        });
        t.start();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        monitorFlag = true;

        synchronized (monitor) {
            monitor.notifyAll();
        }
        lock.lock();
        try {
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
