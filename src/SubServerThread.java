import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

public class SubServerThread extends Thread {
	private static final String VOTER = "Voter";
	private static final String ID_CHECKER = "ID_Checker";
	private static final String KIOSK_HELPER = "Kiosk Helper";
	private static final String SCANNING_HELPER = "Scanning Helper";
	public static Voters [] students = new Voters [20];
	public static ID_checkers [] ID_checkers = new ID_checkers [3];
	public static Scanning_helpers [] Scanning_helpers = new Scanning_helpers [2];
	private static Object lock = new Object();
	public static Object goHome = new Object();
	public static Kiosk_helper kiosk_helper;
	public static int numVoters = 0;
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;
	private Voters voter;
	private ID_checkers IDcheck;
	private Scanning_helpers scanHelp;
	private String threadtype;
	
	// constructor
	
	public void run () {
	  try{
		// get input stream
		// get output stream
			
			//other code
					
			
			
			//runMethod(methodNumber);
			pw.println("DONE");
			
			while (true) 
			{
				
				//runMethod(methodNumber);
				
			}//while
			
		}//try
		catch (IOException e) {
			 System.out.println(e);
		}
	}
	
	public void runMethod (int methodNumber)
	{
		if (threadtype.equals(VOTER))
		{
			switch (methodNumber)
			{
				case 0: 
							voter.get_on_line();
							break;
				case 1:
							voter.get_on_kiosk_line();
							break;
				case 2:
							voter.get_off_kiosk_line();
							break;	
				case 3:
							voter.goes_into_group_and_waits();
							break;			
				case 4:
							voter.goes_to_scanner();
							break;		

			}//switch		
		}//if
		else if (threadtype.equals(ID_CHECKER))
		{
			switch (methodNumber)
			{
				case 0: 
							IDcheck.wait_for_voters();
							break;
				case 1:
							//IDcheck.
							break;
				case 2:
							IDcheck.finishesWork();
							break;	
			}//switch		
		}//if
		else if (threadtype.equals(KIOSK_HELPER))
		{
			switch (methodNumber)
			{
				case 0: 
							//kiosk_helper.
							break;
				case 1:
							kiosk_helper.finishesWork();
							break;		

			}//switch		
		}//if
		else 
		{
			switch (methodNumber)
			{
				case 0: 
							//scanHelp.
							break;
		
			}//else
		}//else
	}//runMethod
}