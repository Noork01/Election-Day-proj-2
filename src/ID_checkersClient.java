
public class ID_checkersClient {
	private static final int numCheckers = 3;
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
				if (methodCount <= 2) 
					pw.println("ID Checker " + methodCount);
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
		for (int i=0; i<numCheckers; i++)
			new VotersClient().start();
	}
}