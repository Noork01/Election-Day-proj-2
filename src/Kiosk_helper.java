
public class Kiosk_helper extends Variables implements Runnable{
	
	private String num;
	private Thread thread;

	
	public Kiosk_helper() {
		this.num = "Kiosk_helper ";
		this.thread = new Thread(this, "Kiosk_helper ");
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
	
	public synchronized void move_line_along() {
		if(a == false) {
			a = true;
			if(kiosk_wait.get(0).size()>0) {
				synchronized(kiosk_wait.get(0).elementAt(0)) {
					msg("tells next person on line 1 to move to kiosk 1");
					kiosk_wait.get(0).elementAt(0).notify();
				}
				kiosk_wait.get(0).removeElementAt(0);
			}
		}
		else if(b == false) {
			b = true;
			if(kiosk_wait.get(1).size()>0) {
				synchronized(kiosk_wait.get(1).elementAt(0)) {
					msg("tells next person on line 2 to move to kiosk 2");
					kiosk_wait.get(1).elementAt(0).notify();
				}
				kiosk_wait.get(1).removeElementAt(0);
			}
		}
		else if(c == false) {
			c = true;
			if(kiosk_wait.get(2).size()>0) {
				synchronized(kiosk_wait.get(2).elementAt(0)) {
					msg("tells next person on line 3 to move to kiosk 3");
					kiosk_wait.get(2).elementAt(0).notify();
				}
				kiosk_wait.get(2).removeElementAt(0);
			}
		}
		else {
			Object helper = new Object();
			kiosk_helper_wait.add(helper);
			synchronized(helper) {
				msg("starts waiting");
				while (true) // wait to be notified, not interrupted
					try {helper.wait(); break; }
	            	catch (InterruptedException e) { continue; }
				msg("stops waiting");
			}
		}
		
	}
	synchronized void finishesWork() {
		msg("finishes work");
	}
	 
	 
	public void run() {
		while(num_voters_to_be_checked_at_kiosk > 0) {
			num_voters_to_be_checked_at_kiosk--;
			move_line_along();
			
		}
		finishesWork();
	}


}
