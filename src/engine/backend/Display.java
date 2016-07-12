package engine.backend;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import engine.frontend.Renderable;

@SuppressWarnings("serial")
/**
 * 
 * @author Thomas Hayden
 * @version 6/02/16
 */
public class Display extends JPanel {
	
	private ArrayList<Renderable> renderQueue = new ArrayList<Renderable>();
	
	public Display() {
		
	}
	
	public void setQueue(ArrayList<Renderable> newRenderQueue) {
		renderQueue = newRenderQueue;
	}
	
	public void addToQueue(ArrayList<Renderable> a) {
		renderQueue.addAll(a);
	}
	
	public synchronized ArrayList<Renderable> getRenderQueue() {
		return renderQueue;
	}
	
	protected synchronized void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		@SuppressWarnings("unchecked")
		ArrayList<Renderable> renderQueueCopy = (ArrayList<Renderable>) getRenderQueue().clone();
		
		for(Renderable r : renderQueueCopy) {
			r.render(g2d);
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
}
