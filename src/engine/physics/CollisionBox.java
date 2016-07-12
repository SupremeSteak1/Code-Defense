package engine.physics;

import java.awt.Rectangle;
import java.awt.geom.Line2D;

import game.Level;
import game.Mob;
import other.Main;
import other.Utilities;

public class CollisionBox {
	
	private Rectangle box;
	private RigidBody rb;
	
	private int x, y;
	
	@SuppressWarnings("rawtypes")
	private Class recentCollisionType;
	
	private boolean collisionInProgress;
	
	public CollisionBox(int x, int y, RigidBody rb){
		this.x = x;
		this.y = y;
		collisionInProgress = false;
		this.rb = rb;
		this.box = new Rectangle((int)Math.round(rb.getPosition().getxComp()- x/2),(
				int)Math.round(rb.getPosition().getyComp()- y/2), 
				(int)Math.round(rb.getPosition().getxComp()+ x/2), 
				(int)Math.round(rb.getPosition().getyComp()- y/2));
		recentCollisionType = rb.getClass();
	}
	
	public Rectangle getCollisionBox(){
		return box;
	}
	
	public boolean isColliding(CollisionBox other){
		collisionInProgress = this.box.intersects(other.getCollisionBox());
		return collisionInProgress;
	}
	
	public boolean isOnBoundary(){
		Line2D.Double line;
		for(int i = 0; i < 4; i++){
			line = Level.getBounds()[i];
			if(box.intersectsLine(line)) collideWithWall(i);
		}
		for(Line2D.Double l : Level.getBounds()){
			if(box.intersectsLine(l)) return true;
		}
		return false;
	}
	
	public RigidBody getRB(){
		return rb;
	}
	
	@SuppressWarnings("rawtypes")
	public Class getRecentCollisionType(){
		return recentCollisionType;
	}
	
	public void collide(CollisionBox other){ //For the love of God, don't ask.
											 //This may have been the single most
											 //difficult line of code I've ever written.
		Vector fuck = Utilities.subtractVectors(rb.getVelocity(),Utilities.subtractVectors(rb.getPosition(), 
				other.getRB().getPosition()).scalarMultiply(((2 * other.getRB().getMass()) / (rb.getMass() + other.getRB().getMass())) * 
				(Utilities.subtractVectors(rb.getVelocity(), other.getRB().getVelocity()).dotProduct
				(Utilities.subtractVectors(rb.getPosition(), other.getRB().getPosition()))/
				Math.pow(Utilities.subtractVectors(rb.getPosition(), other.getRB().getPosition()).getMagnitude(),2))));
		rb.setVelocity(fuck);
		collisionInProgress = false;
		recentCollisionType = other.getRB().getClass();
	}
	
	public void collideWithWall(int wall){
		assert(wall > 0 && wall < 5);
		//1 = -----
		//2 = 	  |
		//3 = _____
		//4 = |
		double angle = 0;
		switch(wall){
		case 1:
			angle = Utilities.getAngleBetweenVectors(rb.getVelocity(), new Vector(1,0));
			break;
		case 2:
			angle = Utilities.getAngleBetweenVectors(rb.getVelocity(), new Vector(0,-1));
			break;
		case 3:
			angle = Utilities.getAngleBetweenVectors(rb.getVelocity(), new Vector(1,0));
			break;
		case 4:
			angle = Utilities.getAngleBetweenVectors(rb.getVelocity(), new Vector(0,-1));
			break;
		}
		double newAngle = Math.PI - angle;
		rb.setVelocity(Utilities.polarToCartesian(newAngle, rb.getVelocity().getMagnitude()));
	}
	
	public boolean getCollisionInProgress(){
		return collisionInProgress;
	}
	
	public void refresh(){
		this.box = new Rectangle((int)Math.round(rb.getPosition().getxComp()- x/2),(
				int)Math.round(rb.getPosition().getyComp()- y/2), 
				(int)Math.round(rb.getPosition().getxComp()+ x/2), 
				(int)Math.round(rb.getPosition().getyComp()- y/2));
	}

}
