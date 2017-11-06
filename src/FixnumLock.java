import java.util.concurrent.locks.Lock;

public interface FixnumLock extends Lock{
    int getId();

    int register();
    int unregister();

    void lock(int id);
    void unlock(int id);

    void reset(int number);
}
