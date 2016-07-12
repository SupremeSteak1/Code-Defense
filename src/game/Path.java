package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import engine.frontend.Renderable;

public class Path implements Renderable {
	private ArrayList<Node> nodes;
	
	private boolean debug = true;
	private int d_nodeSize = 14;
	
	public Path(Node... nodes) {
		this.nodes = new ArrayList<Node>();
		for(Node n : nodes)
			this.nodes.add(n);
	}
	
	public Path(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}
	
	public Node getNode(int n){
		return nodes.get(n);
	}

	@Override
	public void render(Graphics2D g2d) {
		if(debug) {
			for(int x = 0; x < nodes.size(); x++) {
				Node n = getNode(x);
				if(x == 0) {
					g2d.setColor(Color.GREEN);
				} else if(x == nodes.size()-1) {
					g2d.setColor(Color.RED);
				}
				g2d.drawOval((int) n.getPosition().getxComp(), (int) n.getPosition().getyComp(), d_nodeSize, d_nodeSize);
				g2d.setColor(Color.BLACK);
			}
			for(int x = 0; x < nodes.size()-1; x++) {
				g2d.drawLine((int) getNode(x).getPosition().getxComp()+d_nodeSize/2, (int) getNode(x).getPosition().getyComp()+d_nodeSize/2, (int) getNode(x+1).getPosition().getxComp()+d_nodeSize/2, (int) getNode(x+1).getPosition().getyComp()+d_nodeSize/2);
			}
		}
	}
}
