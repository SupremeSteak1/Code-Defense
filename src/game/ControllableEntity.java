package game;

import java.awt.Point;

import engine.backend.GameObjectHandler;
import engine.input.Keyboard;
import engine.input.Mouse;
import engine.physics.PhysicsController;
import engine.physics.RigidBody;
import engine.physics.Vector;
import other.Utilities;

public class ControllableEntity extends RigidBody {
	
	Vector velocity;
	private double speed;
	private double projectileSpeed;
	
	private boolean moving;
	
	private long lastTimeFired;
	
	int xLength, yLength;
	
	/**
	 * Constructs a new ControllableEntity
	 * @param x the x coordinate of the ControllableEntity
	 * @param y the y coordinate of the ControllableEntity
	 * @param m the mass of the ControllableEntity
	 * @param xLength the length of the ControllableEntity on the x axis
	 * @param yLength the length of the ControllableEntity on the y axis
	 */
	public ControllableEntity(int x, int y, int m, int xLength, int yLength) {
		super(x, y, m, xLength, yLength);
		this.xLength = xLength;
		this.yLength = yLength;
		velocity = new Vector(0,0);
		this.speed = 1;
		this.projectileSpeed = 1;
		moving = true;
	}
	
	/**
	 * Gets the projectile's speed
	 * @return the projectile's speed
	 */
	public double getProjectileSpeed() {
		return projectileSpeed;
	}

	/**
	 * Sets the projectile's speed
	 * @param projectileSpeed the new speed
	 */
	public void setProjectileSpeed(double projectileSpeed) {
		this.projectileSpeed = projectileSpeed;
	}
	
	/**
	 * Sets the ControllableEntity's speed
	 * @param value the new speed
	 */
	public void setSpeed(double value) {
		this.speed = value;
	}
	
	/**
	 * Gets the ControllableEntity's speed
	 * @return this ControllableEntity's speed
	 */
	public double getSpeed() {
		return this.speed;
	}
	
	/**
	 * The move method inherited from RigidBody
	 */
	@Override
	public void move() {
		if(Keyboard.isKeyPressed('w')) {
			velocity = Utilities.addVectors(velocity, new Vector(0,-speed));
			moving = true;
		}
		else if(Keyboard.isKeyPressed('s')) {
			velocity = Utilities.addVectors(velocity, new Vector(0,speed));
			moving = true;
		}
		else if(Keyboard.isKeyPressed('d')) {
			velocity = Utilities.addVectors(velocity, new Vector(speed,0));
			moving = true;
		}
		else if(Keyboard.isKeyPressed('a')) {
			velocity = Utilities.addVectors(velocity, new Vector(-speed,0));
			moving = true;
		}
		super.setVelocity(velocity);
		velocity = new Vector (0,0);
		Point p = Mouse.getRecentClickLocationOnScreen();
		if(!p.equals(new Point(0,0))){
			directedAction(p);
		}
	}
	
	/**
	 * Runs this ControllableEntity's directed actions
	 * @param p the point they are directed towards
	 */
	public void directedAction(Point p){
		Vector direction = Utilities.subtractVectors(new Vector(p.x, p.y), super.getPosition()).normalize();
		//Default action:
		if(System.currentTimeMillis() - lastTimeFired >= 200){
		Vector start = Utilities.addVectors(getPosition(), direction);
		Projectile toFire = new Projectile((int) Math.round(start.getxComp()) + xLength, 
				(int) Math.round(start.getyComp()) + yLength);
		toFire.setFilePath("res/arrow.png");
		GameObjectHandler.registerGameObject(toFire);
		PhysicsController.registerRigidBody(toFire);
		toFire.fire(direction.scalarMultiply(projectileSpeed));
		lastTimeFired = System.currentTimeMillis();
		}
	}

}
