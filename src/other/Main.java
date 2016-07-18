package other;

import java.util.ArrayList;
import java.awt.Color;

import engine.backend.GameObjectHandler;
import engine.backend.Renderer;
import engine.frontend.Renderable;
import engine.physics.Vector;
import game.Enemy;
import game.EnemyController;
import game.Node;
import game.Node.NodeType;
import game.Path;
import game.towers.GotoTower;
import game.towers.Tower;
import game.towers.TryCatchTower;

public class Main {
	
	public static int WINDOW_WIDTH = 1280;
	public static int WINDOW_HEIGHT = 720;

	@SuppressWarnings("static-access")
	public static void main(String[] args){
		Renderer r = new Renderer(WINDOW_WIDTH, WINDOW_HEIGHT);
		GameObjectHandler goh = new GameObjectHandler();
		EnemyController ec = new EnemyController();
		
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		nodes.add(new Node(NodeType.START, new Vector(10, 10)));
		nodes.add(new Node(NodeType.TRANSIT, new Vector(500, 30)));
		nodes.add(new Node(NodeType.TRANSIT, new Vector(900, 70)));
		nodes.add(new Node(NodeType.TRANSIT, new Vector(550, 140)));
		nodes.add(new Node(NodeType.TRANSIT, new Vector(200, 240)));
		nodes.add(new Node(NodeType.END, new Vector(90, 300)));
		Path p = new Path(nodes);
		Enemy test = new Enemy(p, "Error!",0,50,2, Color.RED);
		Enemy test2 = new Enemy(p, "Error!",1,50,2, Color.RED);
		Tower temp = new GotoTower(150, new Vector(870,100), 200);
		Tower temp2 = new TryCatchTower(150, new Vector(770,200), 100);
		goh.registerGameObject(test);
		goh.registerGameObject(test2);
		ec.registerEnemy(test);
		ec.registerEnemy(test2);
		goh.registerGameObject(temp);
		goh.registerGameObject(temp2);
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
