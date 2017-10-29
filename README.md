SPOS. Lab 2. Var 6.

Created a new interface FixnumLock that allows mutual exclusion between fixed number of threads.
Used Lock interfase as a base, added 4 new methods: 
	getId() - returns id of the thread that is locking lock at the moment
	register() - registers a thread that can lock this lock
	unregister() - opposite to register()
	reset() - resets lock's state to initial

Logic for methods is provided in abstract class FLock. This class implements Lock's methods (mainly lock() and unlock()) as well.
Class Mutex extends class FLock, that is creates a new FixnumLock instance, to serve fixed number of threads.
