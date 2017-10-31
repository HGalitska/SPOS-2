import java.util.BitSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public abstract class AbstractFixnumLock implements FixnumLock{
    int numberOfThreads = 2;                                // fixed number of threads that can lock this lock

    private ThreadLocal<Integer> ID = new ThreadLocal<>();  // id of every registered thread
    private BitSet indices = new BitSet(numberOfThreads);   // indicates which indices are free (that is free ids for registering threads)
    private int numberOfSetIDs = 0;


    //-------------------------------------------- constructors
    AbstractFixnumLock(){}

    AbstractFixnumLock(int n) {
        numberOfThreads = n;
        indices = new BitSet(numberOfThreads);
    }

    //-------------------------------------------- methods of FixnumLock interface

    @Override
    public int getId() {
        return register();
    }

    @Override
    public synchronized int register(){
        Thread thread = Thread.currentThread();

        if (ID.get() != null && ID.get() != -1) return ID.get();           //thread is already registered

        if (numberOfSetIDs < numberOfThreads) {
            int freeID;
            synchronized (this) {
                freeID = indices.nextClearBit(0);
                indices.set(freeID);
                ID.set(freeID);
                numberOfSetIDs += 1;

                System.out.println("Thread " + thread.getId() + " has been registered as " + freeID + ".");
            }
            return freeID;
        }
        System.out.println("Couldn't register thread " + thread.getId());
        return -1;
    }

    @Override
    public synchronized int unregister(){
        Thread thread = Thread.currentThread();
        int threadID = getId();

        if (indices.get(threadID)) {
            synchronized (this) {
                indices.clear(threadID);
                numberOfSetIDs -= 1;
                ID.set(-1);

                System.out.println("Thread " + thread.getId() + " has been unregistered. ID " + threadID + " is free.");
            }
            return threadID;
        }
        return -1;
    }

    @Override
    public synchronized void reset() {
        indices.clear();
        numberOfSetIDs = 0;
        System.out.println("\nFixnumLock is reset.");
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
