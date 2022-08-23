
public class Scanning_helpers extends Variables implements Runnable{

	private String num;
	private Thread thread;
	
	public Scanning_helpers(String num) {
		this.num = "Scanning_helper " + num;
		this.thread = new Thread(this, "Scanning_helper " + num);
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
	
	public synchronized void help_with_scanners() {
		if(each_group.get(current_group).size() == 4) {
			while(each_group.size()>0) {
				each_group.get(0).notify();
				each_group.remove(0);
			}
		msg("tells next group to go");
		}
		
		
		
		
		Object scanner = new Object();
		scannerWait.addElement(scanner);
		synchronized(scanner) {
			while (true) // wait to be notified, not interrupted
	            try {scanner.wait(); break; }
	            catch (InterruptedException e) { continue; }
		}
		msg("helps voter at scanner");
		
		if(each_group.get(current_group).size()==0) {
			current_group++;
			
		}
		
		
		
		
	}
	 
	 
	public void run() {
		while(num_voters_to_be_checked_at_scanner>0)
		 help_with_scanners();
		
		
	}


}
