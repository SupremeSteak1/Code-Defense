package game.towers;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import engine.frontend.Renderable;
import engine.frontend.RenderableLine;
import engine.frontend.RenderableOval;
import engine.physics.Vector;
import game.Enemy;
import other.Utilities;

public class GotoTower extends Tower {

	private double damage;
	private ArrayList<Renderable> toRender;
	
	public GotoTower(double range, Vector position, double cooldown) {
		super(range, position, cooldown);
		this.damage = 10; //Default starting value. Can be upgraded.
		toRender = new ArrayList<>();
		toRender.add(new RenderableOval((int)position.getxComp()-10,(int)position.getyComp()-10,20,20,Utilities.getNewRandomColor()));
	}
	
	public void setDamage(double value){
		damage = value;
	}
	
	public double getDamage(){
		return damage;
	}
	
	@Override
	public void fire(Enemy e){
		toRender.add(new RenderableLine(new Line2D.Double(super.getPosition().getxComp(),super.getPosition().getyComp(),e.getCurrentLocation().getxComp(),e.getCurrentLocation().getyComp()), Color.RED));
		e.takeDamage(damage);
		super.setCooldown(getMaxCooldown());
	}
	
	@Override
	public ArrayList<Renderable> render(){
		return toRender;
	}

	@Override
	public void update() {
		if(super.getCooldown()==0){
			try{
			fire(enemyInRange());
			}catch(NullPointerException e){
			}
		}else{
			super.setCooldown(getCooldown()-1);
			if(toRender.size()>1 && System.currentTimeMillis() % 50 == 0){
				toRender.remove(1);
			}
		}
	}
	
}
