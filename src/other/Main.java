package other;

import java.awt.Graphics2D;
import java.util.ArrayList;

import engine.backend.GameObjectHandler;
import engine.backend.Renderer;
import engine.frontend.Renderable;
import engine.physics.Vector;
import game.Enemy;
import game.Node;
import game.Node.NodeType;
import game.Path;

public class Main {
	
	public static int WINDOW_WIDTH = 1280;
	public static int WINDOW_HEIGHT = 720;

	@SuppressWarnings("static-access")
	public static void main(String[] args){
		Renderer r = new Renderer(WINDOW_WIDTH, WINDOW_HEIGHT);
		GameObjectHandler goh = new GameObjectHandler();
		ArrayList<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node(NodeType.START, new Vector(10, 10)));
		nodes.add(new Node(NodeType.TRANSIT, new Vector(500, 30)));
		nodes.add(new Node(NodeType.TRANSIT, new Vector(900, 70)));
		nodes.add(new Node(NodeType.TRANSIT, new Vector(550, 140)));
		nodes.add(new Node(NodeType.TRANSIT, new Vector(200, 240)));
		nodes.add(new Node(NodeType.END, new Vector(90, 300)));
		Path p = new Path(nodes);
		Enemy test = new Enemy(p, "Error!",0,10,1000);
		goh.registerGameObject(test);
		while(true){
			r.setQueue(new ArrayList<Renderable>());
			goh.updateGameObjects();
			goh.renderGameObjects(r);
			r.addToQueue(p);
			r.refreshQueue();
			r.repaint();
			r.revalidate();
			r.setVisible(true);
		}
		
	}
	
}
