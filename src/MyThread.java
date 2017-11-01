public class MyThread extends Thread {
    static AbstractFixnumLock m = new FixnumLockDemo(5);
    private int testCase;

    MyThread(int test){testCase = test;}

    @Override
    public synchronized void run() {
        switch(testCase){
            case 1:
                int id = m.register();
                if (id == 0) System.out.println("Test 1 passed.");
                break;
            case 2:
                int idRegister = m.register();
                int idUnregister = m.unregister();
                if (idRegister == idUnregister) System.out.println("Test 2 passed.");
                break;
            case 3:
                int idForNotRegistered = m.getId(); //registers current thread and returns ID
                if(idForNotRegistered >= 0) System.out.println("Test 3 passed.");;
                break;
            case 4:
                int myId = m.register(); //prints message that at least 1 thread couldn't be registered
                if (m.numberOfSetIDs == m.numberOfThreads) System.out.println("Test 4 passed.");
                break;
            case 5:
                m.register();
                break;
        }

    }
}