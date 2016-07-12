package engine.frontend;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class RenderableLine implements Renderable{

	private Line2D l;
	
	public RenderableLine(Line2D l){
		this.l = l;
	}
	
	@Override
	public void render(Graphics2D g2d) {
		g2d.draw(l);
	}

}
