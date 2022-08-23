
public class Kiosk_helperClient {
	//private static final int portNumber = 
	//private static final String address = 
	
	public void run ()
	{
		try 
		{
			// connect to server			
			// create input and output streams			
			int methodCount = 0;
			String line;
			
			
			
			while ((line = br.readLine()) != null)
			{
				if (methodCount <= 1) 
					pw.println("Kiosk Helper " + methodCount);
				else
				{
					
					break;
				}
				methodCount++;
			}
			
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void main (String [] args)
	{
		
			new VotersClient().start();
	}
}