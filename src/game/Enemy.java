package game;

import java.awt.Color;
import java.util.ArrayList;

import engine.backend.GameObject;
import engine.backend.GameObjectHandler;
import engine.frontend.Renderable;
import engine.frontend.RenderableText;
import engine.physics.Vector;
import other.Utilities;

public class Enemy implements GameObject{

	private Path path;
	private String text; //Enemies are text
	private int position;
	private Vector currentLocation;
	private double hp;
	private double speed; //Lower speed is higher
	private Color color;
	private boolean[] conditions;
	/* CONDITIONS:
	 * 0 - SLOWED
	 * 1 -
	 * 2 -
	 * 3 -
	 * 4 -
	 * 5 -
	 * 6 -
	 * 7 -
	 * 9 -
	 */
	private double[] conditionTime;
	
	public Enemy(Path path, String text, int position, double hp, double speed) {
		super();
		this.path = path;
		this.text = text;
		this.position = position;
		this.hp = hp;
		currentLocation = path.getNode(position).getPosition();
		this.speed = speed;
		conditions = new boolean[10];
		for(boolean b : conditions){
			b = false;
		}
		conditionTime = new double[conditions.length];
		color = Color.BLACK;
	}
	
	public Enemy(Path path, String text, int position, double hp, double speed, Color c) {
		super();
		this.path = path;
		this.text = text;
		this.position = position;
		this.hp = hp;
		currentLocation = path.getNode(position).getPosition();
		this.speed = speed;
		conditions = new boolean[10];
		for(boolean b : conditions){
			b = false;
		}
		conditionTime = new double[conditions.length];
		color = c;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Use getCurrentLocation for exact position
	 * @return previous node
	 */
	public int getPosition() {
		return position;
	}
	
	public Vector getCurrentLocation(){
		return currentLocation;
	}

	public double getHp() {
		return hp;
	}
	
	public void applyCondition(int condition, int time){
		conditions[condition] = true;
		conditionTime[condition] = time;
	}
	
	public void takeDamage(double amount){
		hp-=amount;
		if(hp <= 0){
			die();
		}
	}

	private void die() {
		System.out.println("Dead!");
		GameObjectHandler.unregisterGameObject(this);
		EnemyController.unregisterEnemy(this);
	}
	
	private void move(){
		if(position < path.getSize()-1){
			Vector pos = path.getNode(position).getPosition();
			Vector to = path.getNode(position+1).getPosition();
			
			double dx = (to.getxComp()-pos.getxComp());
			double dy = (to.getyComp()-pos.getyComp());
			Vector change = new Vector(dx,dy);
			change = change.normalize().scalarMultiply(1/speed);
			if(to.getxComp()-currentLocation.getxComp() <= Math.abs(change.getxComp()) && to.getyComp()-currentLocation.getyComp() <= Math.abs(change.getyComp())){
				position++;
				System.out.println("Changing direction!");
				return;
			}
			currentLocation = Utilities.addVectors(currentLocation, change);
			//System.out.println(currentLocation.toString());
		}else{
			die();
		}
	}

	@Override
	public ArrayList<Renderable> render() {
		RenderableText image = new RenderableText(text, (int) currentLocation.getxComp(), (int) currentLocation.getyComp(), color);
		ArrayList<Renderable> toRender = new ArrayList<>();
		toRender.add(image);
		return toRender;
	}

	@Override
	public void update() {
		if(conditions[0]){
			//System.out.println("GOTTA GO FAST");
			speed*=1.5;
			move();
			speed/=1.5;
		}else{
			move();
		}
		for(int i = 0; i < conditions.length; i++){
			if(conditionTime[i] > 0){
				conditionTime[i]--;
				if(conditionTime[i] <=0){
					conditions[i] = false;
				}
			}
		}
	}
	
	

}
