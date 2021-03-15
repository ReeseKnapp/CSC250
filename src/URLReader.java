import java.net.URL;
import java.util.Scanner;

public class URLReader 
{
	private String urlString;
	private String theURLContents;
	
	public URLReader(String urlString)
	{
		this.urlString = urlString;
		this.theURLContents = "";
		
		this.readFromURL();
	}
	
	public String getTheURLContents() 
	{
		return theURLContents;
	}



	public void display()
	{
		System.out.println(this.theURLContents);
	}
	
	private void readFromURL()
	{
		String line = "";
		try
		{
			URL url = new URL(this.urlString);
		    Scanner input = new Scanner(url.openStream());

		    while (input.hasNext())
		    {
		    	line += input.nextLine();
		    }

		    input.close();
		    
		}
	    catch(Exception e)
		{
	    	e.printStackTrace();
	    	line = "Can't Connect";
		}
		this.theURLContents = line;
	}
}