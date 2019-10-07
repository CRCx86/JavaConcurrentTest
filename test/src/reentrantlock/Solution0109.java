package reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Solution0109 {

    public static void main(String[] args) {

        CommonResource commonResource = new CommonResource();
        Lock locker = new ReentrantLock();
        for (int i = 0; i < 6; i++) {
            Thread t = new Thread(new CountThread(commonResource, locker));
            t.setName("Thread " + i);
            t.start();
        }

    }

}

class CommonResource {
    int x = 0;
}

class CountThread implements Runnable {

    CommonResource res;
    Lock locker;

    public CountThread(CommonResource res, Lock locker) {
        this.res = res;
        this.locker = locker;
    }

    @Override
    public void run() {

        try {
            this.locker.lock();
            res.x = 1;
            for (int i = 0; i < 5; i++) {
                System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
                res.x++;
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.locker.unlock();
        }

    }

}