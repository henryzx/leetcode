package toutiao;

import java.util.concurrent.SynchronousQueue;

public class ProducerAndConsumer {
    public static void main(String[] args) {
        new Thread(() -> producer(),"producer").start();
        new Thread(() -> consumer(), "consumer1").start();
        new Thread(() -> consumer(), "consumer2").start();
        new Thread(() -> consumer(), "consumer3").start();
    }

    static SynchronousQueue<Integer> queue = new SynchronousQueue<>();

    private static void producer() {
        while(true){
            int item = (int) (Math.random() * 100);
            try { queue.put(item); } catch (InterruptedException ignored) {}
            System.out.println(String.format("thread %s ++ %d", Thread.currentThread().getName(), item));
        }

    }

    private static void consumer() {
        while(true){
            int item = 0;
            try { item = queue.take(); } catch (InterruptedException ignored) { }
            System.out.println(String.format("thread %s -- %d", Thread.currentThread().getName(), item));
        }
    }
}
