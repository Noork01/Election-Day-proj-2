import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Voters extends Variables implements Runnable{
	
	private String num;
	private Thread thread;
	int whichLine;
	
	public Voters(String num) {
		this.num = "Voters " + num;
		this.thread = new Thread(this, "Voter " + num);
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
	
	synchronized void get_on_line() {
			msg("Arrives at designated voting place");
			Object arrive = new Object();
			
			first_wait.addElement(arrive);
		    synchronized (arrive) {
				msg("Waits for ID to be checked by ID checker");
		        while (true) {// wait to be notified, not interrupted
		        	
		            try {arrive.wait(); break; }
		            catch (InterruptedException e) { continue; }
		        }
		    }

			msg("has been checked by ID checker and cleared to go");
		
	}
	
	synchronized void get_on_kiosk_line() {
		msg("goes to voting kiosk line");
		Object arrive2 = new Object();
		int a = kiosk_wait.get(0).size();
		int b = kiosk_wait.get(1).size();
		int c = kiosk_wait.get(2).size();
		whichLine = (a<=b)?(a<=c?0:2):(b<=c?1:2);
		kiosk_wait.get(whichLine).addElement(arrive2);
		synchronized (arrive2) {
			msg("waits on smallest line which is currently line "+ (whichLine+1));
			while (true) {// wait to be notified, not interrupted
	            try {arrive2.wait(); break; }
	            catch (InterruptedException e) { continue; }
			}

			msg("goes to kiosk "+(whichLine+1));
		}
	}
	
	public synchronized void get_off_kiosk_line() {
		if(whichLine == 0)
			a = false;
		else if(whichLine == 1)
			b = false;
		else if(whichLine == 2) 
			c = false;
		msg("finishes at kiosk and tells helper they are done");
		if(kiosk_helper_wait.size()>0) {
			synchronized(kiosk_helper_wait.elementAt(0)) {
				kiosk_helper_wait.elementAt(0).notify();
				kiosk_helper_wait.removeElementAt(0);
			}
		}

		msg("goes to scanning machine area");
	}
	
	public synchronized void goes_into_group_and_waits() {
		if(each_group.get(current_group).size() == 4) {
			current_group++;
			each_group.add(new ArrayList());
		}
		Object arrive3 = new Object();
		each_group.get(current_group).add(arrive3);
		synchronized(arrive3) {
			msg("gets into group "+ (current_group+1) +" and waits");
			while (true) // wait to be notified, not interrupted
	            try {arrive3.wait(); break; }
	            catch (InterruptedException e) { continue; }
		}
	}
	
	public synchronized void goes_to_scanner() {
		Object arrive4 = new Object();
		scanners.add(arrive4);
		int scanNumber = scanners.indexOf(arrive4)+1;
		msg("goes to scanner "+ scanNumber);
		
		Random rand = new Random();
		int val = rand.nextInt(4) + 1;
		if (val == 1) { // <-- 1/4 of the time.
			msg("finished with scanner "+ scanNumber);
		} 
		else { // <-- 3/4 of the time.
			synchronized(arrive4) {
				msg("having trouble at scanner "+scanNumber+" and waits");
				synchronized(scanners.elementAt(0)) {
					scannerWait.elementAt(0).notify();
					scannerWait.removeElementAt(0);
				}
				while (true) // wait to be notified, not interrupted
		            try {arrive4.wait(); break; }
		            catch (InterruptedException e) { continue; }
			}
			msg("finished with scanner "+ scanNumber);
			if(scannerWait.size()>0) {
				while(scannerWait.size()>0){
					scannerWait.get(0).notify();
				}
			}
		}each_group.get(current_group).remove(0);
		num_voters_to_be_checked_at_scanner--;
		msg("leaves voting building and drives away");
		
	}


	 
	 
	public void run() {
			
			get_on_line();
			get_on_kiosk_line();
			get_off_kiosk_line();
			goes_into_group_and_waits();
			goes_to_scanner();
			
			
	}




}
