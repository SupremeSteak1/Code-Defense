package engine.physics;

import java.util.ArrayList;

import engine.frontend.Renderable;
import engine.frontend.RenderableImage;
import other.Utilities;

public class RigidBody extends Entity {

	private int mass;
	private Vector acceleration;
	
	private double frictionCoefficient;
	
	private CollisionBox box;
	
	private String filePath;
	
	/**
	 * Constructs a new RigidBody
	 * @param x the x coordinate of the RigidBody
	 * @param y the y coordinate of the RigidBody
	 * @param m the mass of the RigidBody
	 * @param xLength the length of the RigidBody on the x-axis
	 * @param yLength the length of the RigidBody on the y-axis
	 */
	public RigidBody(int x, int y, int m, int xLength, int yLength) {
		super(x, y);
		this.mass = m;
		acceleration = new Vector(0,0);
		frictionCoefficient = 0;
		filePath = "res/emptyTexture";
		box = new CollisionBox(xLength, yLength, this);
	}
	
	/**
	 * Set the image file path
	 * @param s the new file path
	 */
	public void setFilePath(String s) {
		filePath = s;
	}
	
	/**
	 * The inherited render method from Entity
	 */
	@Override
	public ArrayList<Renderable> render() {
		ArrayList<Renderable> toRender = new ArrayList<>();
		RenderableImage sprite = new RenderableImage(filePath,(int) Math.round(super.getPosition().getxComp()),(int) Math.round(super.getPosition().getyComp()));
		toRender.add(sprite);
		return toRender;
	}
	
	/**
	 * Apply a force to this RigidBody
	 * @param f the vector of the force
	 */
	public void applyForce(Vector f) {
		acceleration = other.Utilities.addVectors(acceleration, f.scalarMultiply(1 / this.mass));
	}
	
	/**
	 * Set the friction coefficient of this RigidBody
	 * @param value the new friction coefficient
	 */
	public void setFrictionCoefficient(double value) {
		this.frictionCoefficient = value;
	}
	
	/**
	 * This method is used only in subclasses.
	 * Not for use in raw RigidBody
	 */
	public void move() {
		//For class heirarchy
	}
	
	/**
	 * The act method inherited from Entity
	 */
	@Override
	public void act() {
		move();
		box.refresh();
		//acceleration = Utilities.addVectors(acceleration, acceleration.getOppositeVector().normalize().scalarMultiply(frictionCoefficient *(mass * Utilities.g)));
		//The above line simulates friction by reducing the acceleration's magnitude an amount equal 
		//to the coefficient of friction multiplied by the mass and the acceleration due to gravity.
		super.setVelocity(Utilities.addVectors(super.getVelocity(), acceleration));
		//The above line simulates acceleration by incrementing the velocity vector by the acceleration vector
		super.setPosition(Utilities.addVectors(super.getPosition(), super.getVelocity()));
		//box.isOnBoundary();
	}
	
	/**
	 * Get the momentum vector of this RigidBody
	 * @return the momentum vector
	 */
	public Vector getMomentum() {
		return super.getVelocity().scalarMultiply(getMass());
	}
	
	/**
	 * Get this RigidBody's mass
	 * @return this RigidBody's mass
	 */
	public double getMass() {
		return mass;
	}
	
	/**
	 * Gets the CollisionBox of this RigidBody
	 * @return this RigidBody's CollisionBox
	 */
	public CollisionBox getCollisionBox() {
		return this.box;
	}

}
