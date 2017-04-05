package lang;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.HashSet;

//import sun.misc.Cleaner;

/**
 * NOTE: 只有 OutOfMemory 的时候才会导致 phantomReference 入队？
 * Created by zhengxiao on 05/04/2017.
 */
public class FinalizeTestbench {

    static class Finalizer {
        byte[] bytes = new byte[500 * 1024 * 1024];

        public Finalizer() {
            System.out.println("Finalizer()");
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("finalize() on " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {

        final ReferenceQueue<Finalizer> referenceQueue = new ReferenceQueue<>();
        Thread reclaimThread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Reference<? extends Finalizer> removed = referenceQueue.remove();
                    System.out.println("" + removed.get() + " is removed on " + Thread.currentThread().getName());
                }
            } catch (InterruptedException ignored) {
            }
        }, "Reclaimer");
        reclaimThread.start();

        HashSet<PhantomReference<Finalizer>> references = new HashSet<>();
        for (int i = 0; i < 1; i++) {
            Finalizer finalizer = new Finalizer();
            references.add(new PhantomReference<>(finalizer, referenceQueue));
//            Cleaner cleaner = Cleaner.create(finalizer, new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("cleaner cleaned! on " + Thread.currentThread().getName());
//                }
//            });
            finalizer = null;
            System.gc();
        }

        // fill the memory
        ArrayList<byte[]> list = new ArrayList<>();
        try {
            while (true) {
                list.add(new byte[1024 * 1024]);
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        System.gc();

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException ignored) {
        }
        System.out.println("main() will exit");
        reclaimThread.interrupt();
    }
}
