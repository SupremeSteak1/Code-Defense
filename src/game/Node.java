package game;

import engine.physics.Vector;

public class Node {
	
	public enum NodeType {
		NOT_SET,
		START,
		TRANSIT,
		END
	}
	private NodeType type;
	private Vector position;
	
	public Node(NodeType type, Vector position) {
		this.type = type;
		this.position = position;
	}

	public NodeType getType() {
		return type;
	}

	public void setType(NodeType type) {
		this.type = type;
	}

	public Vector getPosition() {
		return position;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}
}
