package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import engine.physics.RigidBody;

public class Mob extends RigidBody{

	private double hp, def, atk, id;
	private String name, filePath;
	
	public Mob(int mobID, String mobName, int x, int y, int m, int xLength, int yLength, double hp, double def, double atk, String filePath) {
		super(x, y, m, xLength, yLength);
		this.atk = atk;
		this.def = def;
		this.hp = hp;
		this.id = mobID;
		this.name = name;
		this.filePath = filePath;
		super.setFilePath(filePath);
	}
	
	public static Mob loadMob(int mobID){
		try {
			Scanner scan = new Scanner(new File("res/mobs/mobData.txt"));
			for(int i = 0; i < mobID; i++){
				scan.nextLine();
			}
			String[] line = scan.nextLine().split(";");
			return new Mob(Integer.parseInt(line[0]),line[1],
					Integer.parseInt(line[2]),Integer.parseInt(line[3]),
					Integer.parseInt(line[4]),Integer.parseInt(line[5]),
					Integer.parseInt(line[6]),Double.parseDouble(line[7]),
					Double.parseDouble(line[8]),Double.parseDouble(line[9]),line[10]);
		} catch (FileNotFoundException e) {
			System.err.println("Problem loading mob!!");
			e.printStackTrace();
		}
		return null;
	}
	
	public double getHp() {
		return hp;
	}

	public void setHp(double hp) {
		this.hp = hp;
	}

	public double getDef() {
		return def;
	}

	public void setDef(double def) {
		this.def = def;
	}

	public double getAtk() {
		return atk;
	}

	public void setAtk(double atk) {
		this.atk = atk;
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void move(){
		//TODO: Implement hostile mob behaviour
	}

}
