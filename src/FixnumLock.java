import java.util.concurrent.locks.Lock;

public interface FixnumLock extends Lock{
    long getId();
    boolean register(Thread thread);
    boolean unregister(Thread thread);
    void reset();
}
