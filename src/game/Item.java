package game;

import java.util.ArrayList;
import java.util.Comparator;

public class Item{
	
	private int itemID;
	private itemTypes type;
	private String name;
	
	//Attributes will vary depending on item type
	private ArrayList<String> attributes;
	
	public Item(int itemID, itemTypes type, String name, ArrayList<String> attributes){
		this.itemID = itemID;
		this.type = type;
		this.name = name;
		this.attributes = attributes;
	}
	
	/**
	 * itemTypes are the types of items
	 * @author Joshua
	 *
	 */
	public enum itemTypes{
		NOT_SET,
		WEAPON,
		//Weapon attributes:
		/* File path
		 * Attack damage
		 * Attack speed
		 * Durability ?
		 * EffectID ?
		 * Description ?
		 * Weight
		 */
		EQUIPMENT,
		//Equipment attributes:
		/* File path
		 * Defense
		 * Weight
		 * EffectID ?
		 * EquipmentSlot
		 * Description
		 */
		CONSUMABLE
		//Consumable attributes:
		/* File path
		 * EffectID ?
		 * Description
		 * Weight
		 */
	}
	
	
	
	public int getItemID() {
		return itemID;
	}



	public itemTypes getType() {
		return type;
	}


	public String getName() {
		return name;
	}

	
	
	public ArrayList<String> getAttributes() {
		return attributes;
	}



	public static Item loadItem(String filePath){
		//Not implemented
		return null;
	}
	
}
