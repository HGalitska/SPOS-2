public class MyThread extends Thread {
    static AbstractFixnumLock m = new FixnumLockDemo(5);

    @Override
    public synchronized void run() {
        System.out.println(Thread.currentThread().getId() + " is running.");

        boolean registered = m.register();
        if (registered) {
            m.lock();
        }
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(registered) m.unlock();

    }
}