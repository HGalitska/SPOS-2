import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Test cases:\n1-Proper ID is assigned.\n2-Proper ID is reset by unregister()." +
                            "\n3-Not registered tries to getId().\n4-Overflow.\n5-Parallel register().\n\n");

        Thread th1 = new MyThread(1);
        th1.start();

        Thread th2 = new MyThread(2);
        th2.start();

        Thread th3 = new MyThread(3);
        th3.start();

        Thread th4 = new MyThread(4);
        th4.start();
        Thread th5 = new MyThread(4);
        th5.start();
        Thread th6 = new MyThread(4);
        th6.start();
        Thread th7 = new MyThread(4);
        th7.start();
        Thread th8 = new MyThread(4);
        th8.start();
        Thread th9 = new MyThread(4);
        th9.start();

        Thread th10 = new MyThread(5);
        Thread th11 = new MyThread(5);
        th10.start();
        th11.start();


    }
}
