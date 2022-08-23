import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.concurrent.Semaphore;


public class Variables{
	 public static long time = System.currentTimeMillis();
	 public static Vector first_wait = new Vector();
	 public static int num_voters_to_be_checked = 20;

	 public static int currentVoter = 0;
	 public static int num_voters_to_be_checked_at_kiosk = 20;
	 public static boolean check_ID_bool = true;

	 public static boolean ID_bool = true;
	 
	 public static ArrayList<Vector> kiosk_wait = new ArrayList<Vector>(Arrays.asList(new Vector(),new Vector(), new Vector()));
	public static boolean a = false;
	public static boolean b = false;
	public static boolean c = false;
	public static Vector kiosk_helper_wait = new Vector();
	
	
	public static ArrayList<ArrayList> each_group = new ArrayList<ArrayList>(Arrays.asList((new ArrayList())));
	public static int current_group = 0;
	
	public static Vector scanners = new Vector();
	public static Vector scannerWait = new Vector();
	public static int num_voters_to_be_checked_at_scanner = 20;
	 
	 

}
