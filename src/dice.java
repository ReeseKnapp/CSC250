import java.util.Random;

public class dice 
{
	int sides;
		
	dice(int sides)
	{
		//self.sides = sides in python
		this.sides = sides;
	}
		
	int roll()
	{
		Random r = new Random();
		return r.nextInt(this.sides) + 1;
			
	}
}