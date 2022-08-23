
public class Scanning_helpersClient {
	private static final int numHelpers = 2;
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
				if (methodCount <= 0) 
					pw.println("Scanning Helper " + methodCount);
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
		for (int i=0; i<numHelpers; i++)
			new VotersClient().start();
	}
}