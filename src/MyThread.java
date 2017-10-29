public class MyThread extends Thread {
    static Mutex m = new Mutex(2);

    @Override
    public synchronized void run() {
        System.out.println(Thread.currentThread().getId() + "...is running.");

        m.register(this);
        m.lock();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        m.unlock();

    }
}