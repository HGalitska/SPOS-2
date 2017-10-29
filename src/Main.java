

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread th1 = new MyThread();
        Thread th2 = new MyThread();
        Thread th3 = new MyThread();

        th1.start();
        th2.start();
        th3.start();
    }
}
