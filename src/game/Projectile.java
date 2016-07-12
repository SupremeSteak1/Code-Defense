package game;

import java.awt.Point;
import java.util.ArrayList;

import engine.backend.GameObjectHandler;
import engine.frontend.Renderable;
import engine.frontend.RenderableImage;
import engine.physics.PhysicsController;
import engine.physics.RigidBody;
import engine.physics.Vector;
import other.Utilities;

public class Projectile extends RigidBody {

	private boolean inMotion;
	private String filePath;
	
	private long timeOut;
	private long timeWhenFired;
	
	private Point placeFired;
	
	private double range;
	
	/**
	 * Constructs a new projectile at coordinates (x,y)
	 * @param x the x position of the projectile
	 * @param y the y position of the projectile
	 */
	public Projectile(int x, int y) {
		super(x, y, 1, 1, 1);
		super.setVelocity(new Vector(0,0));
		inMotion = false;
		filePath = "res/emptyTexture.png";
		timeOut = 2000;
		timeWhenFired = Long.MAX_VALUE;
		//placeFired = new Point(0,0);
		range = 2000.0;
	}
	
	/**
	 * Sets the projectile's image file path
	 * @param s the new file path
	 */
	public void setFilePath(String s) {
		filePath = s;
	}
	
	/**
	 * Fires this projectile with given velocity
	 * @param velocity the velocity to fire this projectile at
	 */
	public void fire(Vector velocity) {
		super.setVelocity(velocity);
		inMotion = true;
		timeWhenFired = System.currentTimeMillis();
		placeFired = new Point((int)super.getPosition().getxComp(), (int)super.getPosition().getyComp());
	}
	
	/**
	 * Checks if this projectile is in motion
	 * @return 
	 */
	public boolean getInMotion() {
		return inMotion;
	}
	
	public void destruct(){
		GameObjectHandler.unregisterGameObject(this);
		PhysicsController.unregisterRigidBody(this);
	}
	
	/**
	 * The render method inherited from RigidBody
	 */
	@Override
	public ArrayList<Renderable> render() {
		ArrayList<Renderable> toRender = new ArrayList<>();
		RenderableImage sprite = new RenderableImage(filePath,(int) Math.round(super.getPosition().getxComp()),(int) Math.round(super.getPosition().getyComp()));
		sprite.rotate(Utilities.getAbsoluteVectorRotation(getVelocity()));
		toRender.add(sprite);
		return toRender;
	}
	
	/**
	 * The act method inherited from RigidBody
	 */
	@Override
	public void move() {
		if(super.getCollisionBox().getCollisionInProgress()){
			inMotion = false;
		}
		if(System.currentTimeMillis() - timeWhenFired >= timeOut || 
				placeFired.distance(new Point((int)super.getPosition().getxComp(), (int)super.getPosition().getyComp())) >= range){
			
			this.destruct();
		}
		if(this.getCollisionBox().getRecentCollisionType().equals(this.getClass())){
			//this.destruct();
		}
		//super.setPosition(Utilities.addVectors(super.getPosition(), super.getVelocity()));
	}

}
