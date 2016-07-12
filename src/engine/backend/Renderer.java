package engine.backend;
import java.util.ArrayList;

import javax.swing.JFrame;

import engine.frontend.Renderable;
import engine.input.Keyboard;
import engine.input.Mouse;


@SuppressWarnings("serial")
/**
 * 
 * @author Joshua and Thomas
 * This class is the JFrame that displays all the images.
 */
public class Renderer extends JFrame {
	
	private Display display;
	private ArrayList<Renderable> queue;
	
	public Renderer(int windowWidth, int windowHeight) {
		addKeyListener(new Keyboard());
		addMouseListener(new Mouse());
		display = new Display();
		add(display);
		setSize(windowWidth, windowHeight);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		queue = new ArrayList<Renderable>();
	}
	
	/**
	 * Set the staging queue to an entirely new one
	 * @param newRenderQueue the new render queue
	 */
	public void setQueue(ArrayList<Renderable> newRenderQueue) {
		queue = new ArrayList<Renderable>();
		addToQueue(newRenderQueue);
	}
	
	/**
	 * Add Renderables to the current render queue
	 * @param a a list of Renderables to add to the current queue
	 */
	public void addToQueue(ArrayList<Renderable> a) {
		for(Renderable r : a) {
				queue.add(r);
		}
	}
	
	/**
	 * This pushes the staged render queue to the display class for rendering.
	 * This method is used to remove flickering
	 */
	public void refreshQueue() {
		display.setQueue(queue);
		queue = new ArrayList<Renderable>();
	}
}
