import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public abstract class AbstractFixnumLock implements FixnumLock{
    public int numberOfThreads = 2;                 //fixed number of threads that can lock this lock
    List<Thread> registered = new ArrayList();      //list of registered threads (threads, that can lock this lock)

    private long locker = -1;                       //id of the thread, that is currently locking a lock


    AbstractFixnumLock(int n) {
        numberOfThreads = n;
    }

    public synchronized boolean isLocked() {
        return locker != -1;
    }

    public synchronized boolean isRegistered(Thread thread) {
        return registered.contains(thread);
    }

    @Override
    public long getId() {
        return locker;
    }

    @Override
    public boolean register(Thread thread){
        if (registered.size() + 1 <= numberOfThreads) {
            registered.add(thread);
            return true;
        }
        System.out.println("Couldn't register thread " + thread.getId());
        return false;
    }

    @Override
    public boolean unregister(Thread thread){
        if (registered.contains(thread)) {
            registered.remove(thread);
            return true;
        }
        return false;
    }

    @Override
    public synchronized void lock() {
        Thread thread = Thread.currentThread();
        long id = thread.getId();

        if (!isRegistered(thread)) {
            System.out.println(id +  " is not registered.");
            return;
        }

        if (locker == id) {
            System.out.println("Lock has already been acquired.");
        }

        while(isLocked()){
            System.out.println("Thread " + id + " is waiting for lock.");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        locker = id;
        System.out.println("Lock is used by thread " + locker);
    }

    @Override
    public synchronized void unlock() {
        Thread thread = Thread.currentThread();
        long id = thread.getId();

        if (!isLocked()) {
            System.out.println("Lock is not locked.");
            return;
        }
        if (id != locker){
            System.out.println(id + " can't unlock this lock.");
            return;
        }
        System.out.println("Lock is unlocked by " + locker);
        locker = -1;
        notify();
    }

    @Override
    public void reset() {
        registered.clear();
        locker = -1;
        numberOfThreads = 2;
    }

//_____________________not implemented methods of Lock interface

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
