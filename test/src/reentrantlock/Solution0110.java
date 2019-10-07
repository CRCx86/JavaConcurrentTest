package reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Solution0110 {

    public static void main(String[] args) {
        Store store = new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);
        new Thread(producer).start();
        new Thread(consumer).start();
    }

}

class Store {

    private int product = 0;

    Lock locker;
    Condition condition;

    Store() {
        this.locker = new ReentrantLock();
        this.condition = this.locker.newCondition();
    }

    public void put() {

        this.locker.lock();

        try {

            while (product >= 3) {
                this.condition.await();
            }

            product++;

            System.out.println("Производитель добавил товар");
            System.out.println("Товаров на складе: " + product);

            this.condition.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.locker.unlock();
        }

    }

    public void get() {

        this.locker.lock();

        try {
            while (product < 1) {
                this.condition.await();
            }
            product--;
            System.out.println("Покупатель купил товар");
            System.out.println("Товаров на складе: " + product);
            this.condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.locker.unlock();
        }

    }
}

class Producer implements Runnable {

    Store store;

    Producer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i < 6; i++) {
            this.store.put();
        }
    }

}

class Consumer implements Runnable {

    Store store;

    Consumer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i < 6; i++) {
            this.store.get();
        }
    }

}