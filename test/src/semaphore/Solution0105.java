package semaphore;

import java.util.concurrent.Semaphore;

class Solution0105 {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);
        CommonResource res = new CommonResource();
        new Thread(new CountThread(res, sem, "One")).start();
        new Thread(new CountThread(res, sem, "Two")).start();
        new Thread(new CountThread(res, sem, "Three")).start();
    }

}

class CommonResource {

    int x = 0;

}

class CountThread implements Runnable {

    CommonResource res;
    Semaphore sem;
    String name;

    CountThread(CommonResource res, Semaphore sem, String name) {
        this.res = res;
        this.sem = sem;
        this.name = name;
    }

    @Override
    public void run() {
        
        try {
            System.out.println(name + " ожидает разрешения");
            sem.acquire();
            res.x = 1;
            for (int i = 0; i < 5; i++) {
                System.out.println(this.name + ": " + res.x);
                res.x++;
                Thread.sleep(1000);
            }
        }catch(InterruptedException e) {}
        System.out.println(name + " освобождает разрешение");
        sem.release();
    }

}