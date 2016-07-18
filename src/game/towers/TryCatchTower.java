package game.towers;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import other.Utilities;
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
public class TryCatchTower extends Tower {
	
	private ArrayList<Renderable> toRender;
	private double cooldown;
	private long timeFired;
	private Enemy enemyFired;
	private Color towerColor;

	public TryCatchTower(double range, Vector position, double cooldown) {
		super(range, position, cooldown);
		towerColor = Utilities.getNewRandomColor();
		toRender = new ArrayList<>();
		toRender.add(new RenderableOval((int)position.getxComp()-10,(int)position.getyComp()-10,20,20,towerColor));
		toRender.add(new RenderableOval((int)(position.getxComp()-range),(int)(position.getyComp()-range),(int)super.getRange()*2,(int)super.getRange()*2,towerColor));
		cooldown = 0;
	}
	
	@Override
	public ArrayList<Renderable> render() {
		return toRender;
	}

	@Override
	public void fire(Enemy e) {
		timeFired = System.currentTimeMillis();
		enemyFired = e;
		e.applyCondition(0, 500);
		cooldown = 1000;
	}
	
	@Override
	public void update() {
		if(timeFired + cooldown <= System.currentTimeMillis()) {
			try{
				fire(super.enemyInRange());
				System.out.println("FIRED");
			} catch(NullPointerException e) {
			}
		} else {
			if(enemyFired != null && timeFired != 0 && System.currentTimeMillis() - timeFired <= 100) {
				toRender.add(new RenderableLine(new Line2D.Double(super.getPosition().getxComp(), super.getPosition().getyComp(), enemyFired.getCurrentLocation().getxComp(), enemyFired.getCurrentLocation().getyComp()), Color.GREEN));
			} else if(toRender.size()>2) {
				toRender.remove(2);
			}
		}
	}
	
}
