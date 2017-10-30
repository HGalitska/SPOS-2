import java.util.concurrent.locks.Lock;

public interface FixnumLock extends Lock{
    int getId();

    boolean register();
    boolean unregister();

    void lock(int id);
    void unlock(int id);

    void reset();
}
