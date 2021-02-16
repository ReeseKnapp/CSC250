import java.net.URL;
import java.util.Scanner;

public class driver 
{

	public static void main(String[] args)
	{ 
		MagictheGathering c1 = new MagictheGathering("Armor Vendor", 1, 1, 3);
		MagictheGathering c2 = new MagictheGathering("Wandmaker", 2, 2, 2);
		c1.display();
		c2.display();
		
		c1.setName("woot");
		c1.display();
		
		String cardJson = driver.getJSON("https://api.hearthstonejson.com/v1/25770/enUS/cards.json");
		System.out.println(cardJson);
		
	}
	
	static String getJSON(String urlString)
	{	    
		String line = "";
		try
		{
			URL url = new URL(urlString);
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
		return line;
	}
}