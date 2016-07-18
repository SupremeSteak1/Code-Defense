package game.towers;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import engine.backend.GameObject;
import engine.frontend.Renderable;
import engine.frontend.RenderableLine;
import engine.frontend.RenderableOval;
import engine.physics.Vector;
import game.Enemy;
import game.EnemyController;
import other.Utilities;

/**
 * 
 * @author Joshua
 * Basic test tower
 * Has a big laser
 */
public class Tower implements GameObject {

	private double range;
	private Vector position;
	private double maxCooldown;
	private double cooldown;
	private ArrayList<Renderable> toRender;
	
	public Tower(double range, Vector position, double cooldown) {
		super();
		this.range = range;
		this.position = position;
		this.maxCooldown = cooldown;
		this.cooldown = 0;
		toRender = new ArrayList<>();
		toRender.add(new RenderableOval((int)position.getxComp(),(int)position.getyComp(),20,20));
	}
	
	public Vector getPosition(){
		return position;
	}
	
	public double getRange(){
		return range;
	}
	
	public double getMaxCooldown(){
		return maxCooldown;
	}

	public double getCooldown() {
		return cooldown;
	}

	public void setCooldown(double cooldown) {
		this.cooldown = cooldown;
	}

	@Override
	public ArrayList<Renderable> render() {
		return toRender;
	}
	
	protected Enemy enemyInRange() {
		Enemy closest = null;
		for(Enemy e : EnemyController.getEnemies()){
			if(Utilities.addVectors(e.getCurrentLocation(),this.position.getOppositeVector()).getMagnitude() <= range)
					closest = e;
		}
		return closest;
	}

	public void fire(Enemy e) {
		toRender.add(new RenderableLine(new Line2D.Double(position.getxComp(),position.getyComp(),e.getCurrentLocation().getxComp(),e.getCurrentLocation().getyComp()), Color.RED));
		e.takeDamage(5);
		System.out.println("Fire in the hole!");
		cooldown=maxCooldown;
		
	}

	@Override
	public void update() {
		if(cooldown==0){
			try{
			fire(enemyInRange());
			}catch(NullPointerException e){
			}
		}else{
			cooldown--;
			if(toRender.size()>1){
				System.out.println("Delete");
				toRender.remove(1);
			}
		}
	}

	
	
}
