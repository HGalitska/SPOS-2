import java.util.BitSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public abstract class AbstractFixnumLock implements FixnumLock{
    int numberOfThreads = 2;                                // fixed number of threads that can lock this lock

    private ThreadLocal<Integer> ID = new ThreadLocal<>();  // id of every registered thread
    private BitSet indices = new BitSet(numberOfThreads);   // indicates which indices are free (that is free ids for registering threads)
    private long locker = -1;                               // id of the thread, that is currently locking a lock


    //-------------------------------------------- constructors
    AbstractFixnumLock(){}

    AbstractFixnumLock(int n) {
        numberOfThreads = n;
        indices = new BitSet(numberOfThreads);
        ID.set(-1);
    }

    //-------------------------------------------- helping methods

    private synchronized boolean isLocked() {
        return locker != -1;
    }

    private synchronized boolean isRegistered(int id) {
        return indices.get(id);
    }

    //-------------------------------------------- methods of FixnumLock interface

    @Override
    public int getId() {
        return ID.get();
    }

    @Override
    public boolean register(){
        Thread thread = Thread.currentThread();

        if (/*indices.size() == numberOfThreads && */indices.cardinality() < numberOfThreads) {
            int freeID = indices.nextClearBit(0);
            indices.set(freeID);
            ID.set(freeID);

            System.out.println("Thread " + thread.getId() + " has been registered as " + getId());
            return true;
        }
        System.out.println("Couldn't register thread " + thread.getId());
        return false;
    }

    @Override
    public boolean unregister(){
        Thread thread = Thread.currentThread();
        int threadID = getId();

        if (indices.get(threadID)) {
            indices.clear(threadID);
            ID.set(0);

            System.out.println("Thread " + thread.getId() + " has been unregistered.");
            return true;
        }
        return false;
    }

    @Override
    public synchronized void lock(int id) {
        if (!isRegistered(id)) {
            System.out.println(id +  " is not registered.");
            return;
        }

        if (locker == id) {
            System.out.println("Lock has already been acquired.");
        }

        while(isLocked()){
            System.out.println("Thread " + id + " is waiting for lock." + " (" + Thread.currentThread().getId() + ")");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        locker = id;
        System.out.println("Lock is used by thread " + locker + " (" + Thread.currentThread().getId() + ")");
    }

    @Override
    public synchronized void unlock(int id) {
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
        indices.clear();
        locker = -1;
        numberOfThreads = 2;
    }

    //-------------------------------------------- methods of Lock interface

    @Override
    public void lock() {
        lock(getId());
    }

    @Override
    public void unlock() {
        unlock(getId());
    }


    //-------------------------------------------- not implemented methods of Lock interface

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
