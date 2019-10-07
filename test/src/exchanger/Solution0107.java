package exchanger;

import java.util.concurrent.Exchanger;

public class Solution0107 {

    public static void main(String[] args) {

        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(new PutThread(exchanger)).start();
        new Thread(new GetThread(exchanger)).start();

    }

}

class PutThread implements Runnable {

    Exchanger<String> exchanger;
    String message;

    PutThread(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
        this.message = "Hello Java!";
    }

    @Override
    public void run() {
        try {
            message = this.exchanger.exchange(this.message);
            System.out.println("PutThread has recieved message: " + message);
        } catch (InterruptedException e) {            
            e.printStackTrace();
        }

    }

}

class GetThread implements Runnable {

    Exchanger<String> exchanger;
    String message;

    GetThread(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
        this.message = "Hello world!";
    }

    @Override
    public void run() {
        try {
            message = this.exchanger.exchange(this.message);
            System.out.println("GetThread has recieved message: " + message);
        } catch (InterruptedException e) {            
            e.printStackTrace();
        }

    }

}