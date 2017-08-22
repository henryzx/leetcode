package toutiao;

import java.util.LinkedList;

public class ProducerAndConsumerMonitor {
    
    public static void main(String[] args) {
        new Thread(() -> producer(),"producer").start();
        new Thread(() -> consumer(), "consumer1").start();
        new Thread(() -> consumer(), "consumer2").start();
        new Thread(() -> consumer(), "consumer3").start();
    }

    static final int MAX_ITEM_SIZE = 10;
    static final LinkedList<Integer> items = new LinkedList<>();

    private static void producer() {
        while(true){
            // produce item
            int item = (int) (Math.random() * 100);

            synchronized (items) {
                while (items.size() > MAX_ITEM_SIZE) {
                    try { items.wait(); } catch (InterruptedException ignored) {}
                }
                items.addLast(item);
                System.out.println(String.format("Thread %s ++ %s", Thread.currentThread().getName(), item));
                items.notifyAll();
            }
        }
    }

    private static void consumer() {
        while(true) {
            // consume item

            synchronized (items){
                while(items.size() == 0) {
                    try {items.wait();} catch (InterruptedException ignored) {}
                }

                int item = items.removeLast();
                System.out.println(String.format("Thread %s -- %s", Thread.currentThread().getName(), item));

                items.notifyAll();
            }
        }
    }
}
