package game;

import java.awt.geom.Line2D;
import java.util.ArrayList;

import engine.backend.GameObject;
import engine.frontend.Renderable;
import engine.frontend.RenderableLine;
import other.Main;

public class Level implements GameObject{
	
	private static Line2D.Double[] bounds = new Line2D.Double[4];
	
	public Level(){
		bounds[0] = new Line2D.Double(0,0,0,Main.WINDOW_WIDTH);
		bounds[1] = new Line2D.Double(0,Main.WINDOW_WIDTH,Main.WINDOW_WIDTH,Main.WINDOW_HEIGHT);
		bounds[2] = new Line2D.Double(Main.WINDOW_WIDTH,Main.WINDOW_HEIGHT,Main.WINDOW_HEIGHT, 0);
		bounds[3] = new Line2D.Double(Main.WINDOW_HEIGHT,0,0,0);
	}
	
	public static Line2D.Double[] getBounds(){
		return bounds;
	}
	
	/**
	 * Loads a level from a file
	 * @param filePath the file to load from
	 * @return the loaded Level
	 */
	public static Level loadLevel(String filePath) {
		return null;
	}

	@Override
	public ArrayList<Renderable> render() {
		ArrayList<Renderable> toRender = new ArrayList<>();
		for(Line2D l : bounds){
			toRender.add(new RenderableLine(l));
		}
		return toRender;
	}

	@Override
	public void update() {
		
	}

}
