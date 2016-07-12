package game;

public class Player extends ControllableEntity{

	private Inventory inv;
	
	public Player(int x, int y, int m, int xLength, int yLength) {
		super(x, y, m, xLength, yLength);
		inv = new Inventory(this);
	}
	
	public Inventory getInventory(){
		return inv;
	}

}
