
public class ID_checkers extends Variables implements Runnable{

	private String num;
	private Thread thread;
	
	
	public ID_checkers(String num) {
		this.num = "ID_checkers " + num;
		this.thread = new Thread(this, "ID_checkers " + num);
	}
	  
	public String getName() {
		return num;
	}
	
	public void msg(String m) {
		System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
	}
	
	public void start() {
	    thread.start();
	}
	
	private synchronized void check_ID() {
		
			if(first_wait.size()>0 && num_voters_to_be_checked > 0) {
				msg("checks ID of voter" + first_wait.elementAt(0));//see how to add name 
				num_voters_to_be_checked--;
				System.out.println(first_wait.size());
				synchronized(first_wait.elementAt(0)) {
					if(first_wait.size()>0) {
					first_wait.elementAt(0).notify();
					first_wait.removeElementAt(0);}
				}
			}
	}
	
	synchronized void wait_for_voters() {
		msg("waits for voters");
	}	
	synchronized void finishesWork() {
		msg("finishes work");
	}
	 
	 
	public void run() {
		wait_for_voters();
		while(num_voters_to_be_checked > 0 && first_wait.size()>0) {
				check_ID();
			
		}
		finishesWork();
	}


}
