
public class MagictheGathering 
{
	int cost;
	int attack;
	int defense;
	String name;
	
	public MagictheGathering(String name, int cost, int attack, int defense)
	{
		this.cost = cost;
		this.attack = attack;
		this.defense = defense;
		this.name = name;
	}
	
	void display()
	{
		System.out.println("Name: " + this.name + "\nCost: " + this.cost + "\nAttack: " + this.attack + " Defense: " + this.defense);
		System.out.format("Name: %s\n Cost: %d Attack: %d Defense: %d \n", this.name, this.cost, this.attack, this.defense);
	}

	public void setName(String string) {
		
	}
}