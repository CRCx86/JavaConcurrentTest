package racecondition;

public class Solution0103 {

    public static void main(String[] args) {
        
        CommonResource commonResource = new CommonResource();

        for (int i = 0; i < 6; i++) {
            Thread t = new Thread(new CountThread(commonResource));
            t.setName("Thread " + i);
            t.start();
        }
    }

}

class CommonResource {

    private int x = 0;

    public synchronized void increment() {
        this.x = 1;
        for (int i = 0; i < 5; i++) {
            System.out.printf("%s %d \n", Thread.currentThread().getName(), x);
            this.x++;
            try {
                Thread.sleep(100);
            }catch(InterruptedException e) {

            }
        }
    }

}

class CountThread implements Runnable {

    private CommonResource commonResource;

    CountThread(CommonResource commonResource) {
        this.commonResource = commonResource;
    }

    @Override
    public void run() {
        commonResource.increment();    
    }

}