package semaphore;

import java.util.concurrent.Semaphore;

class Solution0106 {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(2);
        for (int i = 0; i < 6; i++) {
            new Philosopher(sem, i).start();
        }
    }

}

class Philosopher extends Thread {

    Semaphore sem;
    int id; // id философа
    int num; // количество приёмов пищи

    Philosopher(Semaphore sem, int id) {
        this.sem = sem;
        this.id = id;
    }

    public void run() {

        try {

            while (num < 3) {
                sem.acquire();
                System.out.println("философ " + id + " садится за стол");

                // ест
                sleep(1000);
                num++;

                System.out.println("философ " + id + " выходит из-за стола");
                sem.release();
                
                // не ест
                sleep(1000);

            }
        } catch (InterruptedException e) {
            System.out.println("философ " + id + " переел");
        }


    
    }

}