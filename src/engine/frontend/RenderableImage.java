package engine.frontend;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author Thomas Hayden
 * @version 6/02/16
 */
public class RenderableImage implements Renderable {
	
	private BufferedImage image;
	private int x;
	private int y;
	
	/**
	 * A RenderableImage is an image that can be rendered using our custom render engine.
	 * @param filePath the path to the image
	 * @param x the starting x coordinate
	 * @param y the starting y coordinate
	 */
	public RenderableImage(String filePath, int x, int y){
		this.x = x;
		this.y = y;
		try {
			image = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Render the given image on the screen using the given graphics object
	 */
	public void render(Graphics2D g2d) {
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);
		g2d.drawImage(image, x, y, null);
	}

	/**
	 * This allows objects to be rendered in rotated positions
	 * @param angle the angle to be rotated
	 */
	public void rotate(double angle){
		AffineTransform transform = new AffineTransform();
		transform.rotate(angle, image.getWidth()/2, image.getHeight()/2);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		image = op.filter(image, null);
	}
	
}
