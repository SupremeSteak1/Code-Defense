package game.towers;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import engine.frontend.Renderable;
import engine.frontend.RenderableLine;
import engine.frontend.RenderableOval;
import engine.physics.Vector;
import game.Enemy;

/**
 * 
 * @author Joshua
 * This tower slows enemies down
 */
public class TryCatchTower extends Tower{
	
	private ArrayList<Renderable> toRender;
	private double cooldown;

	public TryCatchTower(double range, Vector position, double cooldown) {
		super(range, position, cooldown);
		toRender = new ArrayList<>();
		toRender.add(new RenderableOval((int)position.getxComp(),(int)position.getyComp(),20,20));
		cooldown = 0;
	}
	
	@Override
	public ArrayList<Renderable> render(){
		return toRender;
	}

	@Override
	public void fire(Enemy e){
		toRender.add(new RenderableLine(new Line2D.Double(super.getPosition().getxComp(),super.getPosition().getyComp(),e.getCurrentLocation().getxComp(),e.getCurrentLocation().getyComp()), Color.GREEN));
		e.applyCondition(0, 500);
		cooldown = super.getMaxCooldown();
	}
	
	@Override
	public void update() {
		if(cooldown==0){
			try{
			fire(super.enemiesInRange());
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
