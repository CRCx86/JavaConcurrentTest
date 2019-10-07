package interrupted;

/*

    interrupted imitation

*/

class MyThread extends Thread {

    @Override
    public void run() {

        System.out.printf("%s has been started\n", Thread.currentThread().getName());
        
        int counter = 0;
        try {
            while (!isInterrupted()) {
                System.out.println("Loop counter: " + counter++);
                Thread.sleep(100);
            }
        } catch (Exception e) {
            System.out.printf("%s has been interrupted\n", Thread.currentThread().getName());
        }

        System.out.printf("%s finished\n", Thread.currentThread().getName());
    }

}

public class Solution0102 {
    public static void main(String[] args) {
        System.out.println("Main thread has been started");
        MyThread thread = new MyThread();
        Thread t = new Thread(thread, "MyThread");
        t.start();
        try {

            Thread.sleep(1000);

            t.interrupt();

            Thread.sleep(1000);

        } catch (InterruptedException e) {
            System.out.println("MyThread thread has been interrupted");
        }

        System.out.println("Main thread terminated");
    }
}