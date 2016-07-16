package engine.frontend;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class RenderableLine implements Renderable{

	private Line2D l;
	private Color color;
	
	public RenderableLine(Line2D l){
		color = Color.BLACK;
		this.l = l;
	}
	
	public RenderableLine(Line2D l, Color c){
		this.color = c;
		this.l = l;
	}
	
	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.draw(l);
	}

}
