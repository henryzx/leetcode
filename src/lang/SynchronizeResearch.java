package lang;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhengxiao on 05/04/2017.
 */
public class SynchronizeResearch {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
    }
}
