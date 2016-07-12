package other;

import java.util.ArrayList;

import engine.frontend.Renderable;
import engine.frontend.RenderableText;
import engine.physics.Vector;

/**
 * The Utilities class contains useful functions which tend to be accessed from many
 * different classes and lend themselves to benefiting from being in a global public
 * static class.
 * @author Josh Gordon
 *
 */
public class Utilities {
	
	public static final double g = 9.80665; //Acceleration due to gravity
	
	/**
	 * Allows a string to wrap if it hits the end of a given area
	 * @param width the width of the text
	 * @param text the text to display
	 * @param x the x coordinate of the text
	 * @param y the y coordinate of the text
	 * @return an ArrayList<Renderable> of RenderableTexts that make up the given string
	 */
	public static ArrayList<Renderable> renderLongText(int width, String text, int x, int y) {
		int lines = (int) Math.ceil((double) text.length() / (double) width);
		ArrayList<Renderable> toRender = new ArrayList<>();
		for(int i = 0; i < lines; i++){
			try{
			toRender.add(new RenderableText(text.substring(i*width,(i+1)*width), x, y + i*10));
			}catch(Exception e){
				toRender.add(new RenderableText(text.substring(i*width,text.length()), x, y + i*10));
			}
		}
		return toRender;
	}
	
	/**
	 * Gets the angle between two vectors
	 * @param v1 the first vector
	 * @param v2 the second vector
	 * @return the angle between v1 and v2
	 */
	public static double getAngleBetweenVectors(Vector v1, Vector v2){
		double cosine = v1.dotProduct(v2)/(v1.getMagnitude()*v2.getMagnitude());
		return Math.acos(cosine);
	}
	
	public static double getAbsoluteVectorRotation(Vector v){
		double x = v.getxComp();
		double y = v.getyComp();
		if(x>=0&&y>=0){
			//System.out.println("First quadrant");
			return getAngleBetweenVectors(v, new Vector(1,0)) + Math.PI * 1/2; //First quadrant
		}else if(x <= 0 && y >= 0){
			//System.out.println("Second quadrant");
			return -getAngleBetweenVectors(v, new Vector(0,-1)); //Second quadrant
		}else if(x <= 0 && y <= 0){
			//System.out.println("Third quadrant");
			return getAngleBetweenVectors(v, new Vector(-1,0)) + Math.PI * 3/2; //Third quadrant
		}else if(x >= 0 && y <= 0){
			//System.out.println("Fourth quadrant");
			return Math.PI-getAngleBetweenVectors(v, new Vector(0,1)); //Fourth quadrant
		}
		else return 0.0;
	}
	
	/**
	 * Adds two vectors
	 * @param v1 the first vector
	 * @param v2 the second vector
	 * @return the resultant vector of v1 and v2
	 */
	public static Vector addVectors(Vector v1, Vector v2){
		return new Vector(v1.getxComp() + v2.getxComp(), v1.getyComp() + v2.getyComp());
	}
	
	/**
	 * Subtracts two vectors
	 * @param v1 the first vector
	 * @param v2 the second vector (to be subtracted from v1)
	 * @return the resultant vector of v1 and v2
	 */
	public static Vector subtractVectors(Vector v1, Vector v2){
		return new Vector(v1.getxComp() - v2.getxComp(), v1.getyComp() - v2.getyComp());
	}
	
	public static Vector polarToCartesian(double angle, double magnitude){
		double x = magnitude * Math.cos(angle);
		double y = magnitude * Math.sin(angle);
		return new Vector(x,y);
	}
}
