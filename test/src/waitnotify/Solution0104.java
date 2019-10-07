package waitnotify;

public class Solution0104 {
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

    public synchronized void put() {
        while (product >= 3) {
            try {
                wait();
            } catch (InterruptedException e) {                
                e.printStackTrace();
            }
        }
        product++;
        System.out.println("Производитель добавил товар");
        System.out.println("Товаров на складе: " + product);
        notify();

    }

    public synchronized void get() {

        while (product < 1) {
            try {
                wait();
            } catch (InterruptedException e) {                
                e.printStackTrace();
            }
        }
        product--;
        System.out.println("Покупатель купил товар");
        System.out.println("Товаров на складе: " + product);
        notify();

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