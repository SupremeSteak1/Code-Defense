package game;

import java.awt.geom.Line2D;
import java.util.ArrayList;

import engine.backend.GameObject;
import engine.frontend.Renderable;
import engine.frontend.RenderableLine;
import engine.frontend.RenderableOval;
import engine.physics.Vector;
import other.Utilities;

/**
 * 
 * @author Joshua
 * Basic test tower
 * Has a big laser
 */
public class Tower implements GameObject{

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

	@Override
	public ArrayList<Renderable> render() {
		return toRender;
	}
	
	private Enemy enemiesInRange(){
		Enemy closest = null;
		for(Enemy e : EnemyController.getEnemies()){
			if(Utilities.addVectors(e.getCurrentLocation(),this.position.getOppositeVector()).getMagnitude() <= range)
					closest = e;
		}
		return closest;
	}

	private void fire(Enemy e) {
		toRender.add(new RenderableLine(new Line2D.Double(position.getxComp(),position.getyComp(),e.getCurrentLocation().getxComp(),e.getCurrentLocation().getyComp())));
		e.takeDamage(5);
		System.out.println("Fire in the hole!");
		cooldown=maxCooldown;
		
	}

	@Override
	public void update() {
		if(cooldown==0){
			try{
			fire(enemiesInRange());
			}catch(NullPointerException e){
				System.out.println("Nothing in range");
			}
		}else{
			cooldown--;
			if(toRender.size()>1){
				toRender.remove(1);
			}
		}
	}

	
	
}
