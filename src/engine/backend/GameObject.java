package engine.backend;
import java.util.ArrayList;

import engine.frontend.Renderable;

/**
 * 
 * @author Thomas Hayden
 * @version 6/02/16
 */
public interface GameObject {
	public ArrayList<Renderable> render();
	public void update();
}
