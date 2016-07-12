package game;

import java.util.ArrayList;

import engine.frontend.Renderable;

public class Inventory {
	
	//For sorting
	//0 is unassigned for error avoidance
	public static final int ITEM_TYPE = 1;
	public static final int ITEM_ID = 2;
	public static final int ITEM_NAME = 3;
	
	public static final int INVENTORY_SIZE = 20;

	private ArrayList<Item> items;
	private Item[] equipped;
	private Player player;
	
	public Inventory(Player player){
		this.player = player;
		items = new ArrayList<>();
		equipped = new Item[5];
	}
	
	public ArrayList<Item> getItems(){
		return items;
	}
	
	public void equipItem(Item i){
		if(!i.getType().equals(Item.itemTypes.EQUIPMENT)){
			return;
		}else{
			int slot = Integer.parseInt(i.getAttributes().get(4));
			if(equipped[slot].equals(null)){
				equipped[slot] = i;
			}else{
				addItem(equipped[slot]);
				equipped[slot] = i;
			}
		}
	}
	
	public boolean isFull(){
		return items.size()>=INVENTORY_SIZE;
	}
	
	public void addItem(Item i){
		if(isFull())
		items.add(i);
		else{
			return;
		}
	}
	
	public void sort(int sortBy){
		ArrayList<Item> sorted = new ArrayList<Item>();
		ArrayList<Item> itemsCopy = items;
		Item min;
		switch(sortBy){
		//InsertionSort ftw on small lists
		case 1:
			for(int i = 0; i < items.size(); i++){
				min = itemsCopy.get(0);
				for(Item it : itemsCopy){
					if(it.getType().ordinal() < min.getType().ordinal()){
						min = it;
					}
				}
				sorted.add(itemsCopy.remove(itemsCopy.indexOf(min)));
			}
			break;
		case 2:
			for(int i = 0; i < items.size(); i++){
				min = itemsCopy.get(0);
				for(Item it : itemsCopy){
					if(it.getItemID() < min.getItemID()){
						min = it;
					}
				}
				sorted.add(itemsCopy.remove(itemsCopy.indexOf(min)));
			}
			break;
		case 3:
			for(int i = 0; i < items.size(); i++){
				min = itemsCopy.get(0);
				for(Item it : itemsCopy){
					if(it.getName().compareTo(min.getName()) < 0){
						min = it;
					}
				}
				sorted.add(itemsCopy.remove(itemsCopy.indexOf(min)));
			}
			break;
		default:
			System.err.println("Bad input on inventory sorting");
		}
		items = sorted;
	}
	
	/**
	 * This method is called by the player to render the inventory gui
	 * @return the images that will be rendered
	 */
	public ArrayList<Renderable> render(){
		//Not yet implemented
		//Make the rest of the game pause when this is called
		return null;
	}
	
}
