package engine.physics;

public class Vector {
	
	private double xComp;
	private double yComp;
	
	/**
	 * Constructs a new vector with origin (0,0)
	 * @param x the x coordinate of the vector
	 * @param y the y coordinate of the vector
	 */
	public Vector(double x, double y) {
		this.xComp = x;
		this.yComp = y;
	}
	
	/**
	 * Gets the magnitude of the vector
	 * @return the magnitude of this vector
	 */
	public double getMagnitude() {
		return Math.sqrt(Math.pow(xComp, 2) + Math.pow(yComp, 2));
	}
	
	/**
	 * Computes the dot product of this vector with another
	 * @param other the other vector to use with the dot product
	 * @return the result of the dot product
	 */
	public double dotProduct(Vector other) {
		return this.xComp * other.xComp + this.yComp * other.yComp;
	}

	/**
	 * Gets the x component of the vector
	 * @return the x component of this vector
	 */
	public double getxComp() {
		return xComp;
	}
	
	/**
	 * Gets the y component of the vector
	 * @return the y component of this vector
	 */
	public double getyComp() {
		return yComp;
	}
	
	/**
	 * Scales this vector by a double amount
	 * @param scalar the amount to scale this vector by
	 * @return the resultant vector
	 */
	public Vector scalarMultiply(double scalar) {
		return new Vector(this.xComp * scalar, this.yComp * scalar);
	}
	
	/**
	 * Gets the opposite of this vector
	 * @return the resultant vector
	 */
	public Vector getOppositeVector() {
		return new Vector(-this.xComp, -this.yComp);
	}
	
	/**
	 * Normalizes this vector
	 * @return the resultant vector
	 */
	public Vector normalize() {
		return new Vector(this.xComp / this.getMagnitude(), this.yComp / this.getMagnitude());
	}
	
	public String toString(){
		return "Vector[x="+getxComp()+",y="+getyComp()+"]";
	}
	
}
