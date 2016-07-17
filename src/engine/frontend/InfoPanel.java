package engine.frontend;

import java.awt.Point;
import java.util.ArrayList;

import engine.backend.GameObject;
import engine.input.Mouse;
import game.Enemy;
import game.EnemyController;

public class InfoPanel implements GameObject{

	private Enemy selected;
	private boolean inUse;
	private ArrayList<Renderable> toRender;
	
	public InfoPanel(){
		selected = null;
		inUse = false;
		toRender = new ArrayList<>();
	}
	
	@Override
	public ArrayList<Renderable> render() {
		return toRender;
	}

	@Override
	public void update() {
		if(inUse && !Mouse.getRecentClickLocationOnScreen().equals(new Point(0,0))){
			inUse = false;
		}
		for(Enemy e : EnemyController.getEnemies()){
			if(Mouse.getRecentClickLocationOnScreen().distance(new Point((int)e.getCurrentLocation().getxComp(),(int)e.getCurrentLocation().getyComp())) <= 15){
				selected = e;
				inUse = true;
			}
		}
		if(inUse){
			//Edit toRender here
		}else{
			toRender.clear();
		}
	}

	
	
}
