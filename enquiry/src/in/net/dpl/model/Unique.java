package in.net.dpl.model;


public class Unique {

	static long current= System.currentTimeMillis();
	  static public synchronized long get(){
	    return current++;
	    }
	
}
