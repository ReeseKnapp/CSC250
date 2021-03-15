import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class CardParser 
{
	private String urlString;
	private ArrayList<HearthstoneCard> theMinions;
	
	public CardParser(String urlString)
	{
		this.urlString = urlString;
		theMinions = new ArrayList<HearthstoneCard>();
		
		URLReader hearthstoneURLReader = new URLReader(this.urlString);
		Object obj = JSONValue.parse(hearthstoneURLReader.getTheURLContents());
		
	    if(obj instanceof JSONArray)
	    {
	    	JSONArray array = (JSONArray)obj;
	    	
		    for(int i = 0; i < array.size(); i++)
		    {
		    	JSONObject cardData = (JSONObject)array.get(i);
		    	if(cardData.containsKey("cost") && cardData.containsKey("name"))
		    	{
		    		if(cardData.containsKey("type") && cardData.get("type").equals("MINION"))
		    		{
		    			String name = (String)cardData.get("name");
		    			int cost = Integer.parseInt(cardData.get("cost").toString());
		    			int attack = Integer.parseInt(cardData.get("attack").toString());
		    			int health = Integer.parseInt(cardData.get("health").toString());
		    			HearthstoneCard temp = new HearthstoneCard(name, cost, attack, health);
		    			theMinions.add(temp);
		    		}
		    	}
		    	
		    }
	    }
	}
	
	public void showMinions()
	{
		for(int i = 0; i < this.theMinions.size(); i++)
		{
			this.theMinions.get(i).display();
		}
	}
	
	public void selectionSortHighestCostToLowestCost()
	{
		for(int max = 0; max < this.theMinions.size(); max++)
		{
			int maxIndex = this.findIndexOfLargestCostFromPosition(max);
			HearthstoneCard temp = this.theMinions.get(max);
			this.theMinions.set(max, this.theMinions.get(maxIndex));
			this.theMinions.set(maxIndex, temp);
		}
	}
	
	public void insertionSortLowestCostToHighestCost()
	{
		for(int currStart = 1; currStart < this.theMinions.size(); currStart++)
		{
			int currIndex = currStart;
			HearthstoneCard temp;
			while(currIndex > 0 && this.theMinions.get(currIndex).getCost() < 
					this.theMinions.get(currIndex-1).getCost())
			{
				temp = this.theMinions.get(currIndex);
				this.theMinions.set(currIndex, this.theMinions.get(currIndex-1));
				this.theMinions.set(currIndex-1, temp);
				currIndex--;
				
			}	
		}
	}
	
	public HearthstoneCard binarySearchOnAttack(int attack)
	{
		this.insertionSortLowestAttackToHighestAttack();
		int currStart = 0;
		int currEnd = this.theMinions.size()-1;
		int middle;
		HearthstoneCard temp;
		
		while(currStart <= currEnd)
		{
			middle = (currStart + currEnd)/2; 
			temp = this.theMinions.get(middle);
			if(temp.getAttack() == attack)
			{
				return this.theMinions.get(middle);
			}
			else if(attack < temp.getAttack())
			{
				currEnd = middle - 1;
			}
			else
			{
				currStart = middle + 1;
			}
		}
		return null;
	}
	
	public void insertionSortLowestAttackToHighestAttack()
	{
		for(int currStart = 1; currStart < this.theMinions.size(); currStart++)
		{
			int currIndex = currStart;
			HearthstoneCard temp;
			while(currIndex > 0 && this.theMinions.get(currIndex).getAttack() < 
					this.theMinions.get(currIndex-1).getAttack())
			{
				temp = this.theMinions.get(currIndex);
				this.theMinions.set(currIndex, this.theMinions.get(currIndex-1));
				this.theMinions.set(currIndex-1, temp);
				currIndex--;
				
			}	
		}
	}
	
	public void sortLowestCostToHighestCost()
	{
		ArrayList<HearthstoneCard> theSortedList = new ArrayList<HearthstoneCard>();
		HearthstoneCard nextSmallest;
		while(this.theMinions.size() > 0)
		{
			nextSmallest = this.findSmallest();
			theSortedList.add(nextSmallest);
		}
		
		this.theMinions = theSortedList;  

	}
	
	private int findIndexOfLargestCostFromPosition(int pos)
	{
		HearthstoneCard currWinner = this.theMinions.get(pos);
		int indexOfWinner = pos;
		for(int i = pos+1; i < this.theMinions.size(); i++)
		{
			if(this.theMinions.get(i).getCost() > currWinner.getCost())
			{
				currWinner = this.theMinions.get(i);
				indexOfWinner = i;
			}
		}
		return indexOfWinner;
	}
	
	private HearthstoneCard findSmallest()
	{
		HearthstoneCard currWinner = this.theMinions.get(0);
		int indexOfWinner = 0;
		
		for(int i = 1; i < this.theMinions.size(); i++)
		{
			if(this.theMinions.get(i).getCost() < currWinner.getCost())
			{
				currWinner = this.theMinions.get(i);
				indexOfWinner = i;
			}
		}
		this.theMinions.remove(indexOfWinner);
		return currWinner;
	}
}