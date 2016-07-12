package engine.physics;

import java.util.ArrayList;

import engine.backend.GameObject;
import engine.frontend.Renderable;
import engine.frontend.RenderableImage;
import other.Utilities;

public abstract class Entity implements GameObject{

	private Vector position, velocity;
	private String filePath; //Image
	
	public Entity(int x, int y) {
		this.position = new Vector(x,y);
		this.velocity = new Vector(0,0);
		this.filePath = "res/emptyTexture.png";
	}
	
	public String getFilePath(){
		return filePath;
	}
	
	public void setFilePath(String value){
		filePath = value;
	}

	public Vector getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}

	public Vector getPosition() {
		return position;
	}
	
	public void setPosition(Vector pos){
		this.position = pos;
	}
	
	@Override
	public ArrayList<Renderable> render(){
		ArrayList<Renderable> toRender = new ArrayList<>();
		RenderableImage sprite = new RenderableImage(filePath,(int) Math.round(position.getxComp()),(int) Math.round(position.getyComp()));
		toRender.add(sprite);
		return toRender;
	}
	
	public void act(){
		//For class inheritance
	}
	
	@Override
	public void update(){
		act();
		this.position = Utilities.addVectors(position, velocity);
	}
	
}
