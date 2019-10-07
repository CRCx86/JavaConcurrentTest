package phaser;

import java.util.concurrent.Phaser;

public class Solution0108 {

    public static void main(String[] args) {
        
        Phaser phaser = new Phaser(1);
        new Thread(new PhaserThread(phaser, "Thread 1")).start();
        new Thread(new PhaserThread(phaser, "Thread 2")).start();

        // ждем завершения фазы 0
        int phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Фаза " + phase + " завершена");

        // ждем завершения фазы 1
        phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Фаза " + phase + " завершена");

        // ждем завершения фазы 2
        phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Фаза " + phase + " завершена");

        phaser.arriveAndDeregister();
    }

}

class PhaserThread implements Runnable {

    Phaser phaser;
    String name;

    public PhaserThread(Phaser phaser, String name) {
        this.phaser = phaser;
        this.name = name;
        this.phaser.register();
    }

    @Override
    public void run() {
        
        System.out.println(name + " выполняет фазу " + phaser.getPhase());
        phaser.arriveAndAwaitAdvance(); // сообщаем, что первая фаза достигнута

        try{
            Thread.sleep(2000);
        }
        catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }
         
        System.out.println(name + " выполняет фазу " + phaser.getPhase());
        phaser.arriveAndAwaitAdvance(); // сообщаем, что вторая фаза достигнута

        try{
            Thread.sleep(2000);
        }
        catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }
 
        System.out.println(name + " выполняет фазу " + phaser.getPhase());
        phaser.arriveAndDeregister(); // сообщаем о завершении фаз и удаляем с регистрации объекты 

    }

}