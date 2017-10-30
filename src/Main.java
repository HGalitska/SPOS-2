public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread th1 = new MyThread();
        Thread th2 = new MyThread();
        Thread th3 = new MyThread();
        Thread th4 = new MyThread();
        Thread th5 = new MyThread();
        Thread th6 = new MyThread();
        Thread th7 = new MyThread();
        Thread th8 = new MyThread();

        th1.start();
        th2.start();
        th3.start();
        th4.start();
        th5.start();
        th6.start();
        th7.start();
        th8.start();
    }
}
