import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Test cases:\n1-Proper ID is assigned.\n2-Proper ID is reset by unregister()." +
                            "\n3-Not registered tries to getId().\n4-Overflow.\n5-Parallel register().\n\n");

        Scanner in = new Scanner(System.in);
        int test = in.nextInt();

        switch(test) {
            case 1:
                Thread th1 = new MyThread(1);
                th1.start();
                break;

            case 2:
                Thread th2 = new MyThread(2);
                th2.start();
                break;

            case 3:
                Thread th3 = new MyThread(3);
                th3.start();
                break;

            case 4:
                Thread th4 = new MyThread(4);
                Thread th5 = new MyThread(4);
                Thread th6 = new MyThread(4);
                Thread th7 = new MyThread(4);
                Thread th8 = new MyThread(4);
                Thread th9 = new MyThread(4);
                th4.start();
                th5.start();
                th6.start();
                th7.start();
                th8.start();
                th9.start();
                break;

            case 5:
                Thread th10 = new MyThread(5);
                Thread th11 = new MyThread(5);
                th10.start();
                th11.start();
                break;
        }


    }
}
