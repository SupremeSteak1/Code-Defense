package engine.physics;

import java.util.ArrayList;

public class PhysicsController {
	
	private static ArrayList<RigidBody> rbs;
	
	public PhysicsController(){
		this.rbs = new ArrayList<>();
	}
	
	public ArrayList<RigidBody> getRBs(){
		return this.rbs;
	}
	
	public static void unregisterRigidBody(RigidBody rb){
		rbs.remove(rb);
	}
	
	public static void registerRigidBody(RigidBody rb){
		if(!rbs.contains(rb)) rbs.add(rb);
	}
	
	public void checkForCollisions(){
		for (int i = 0; i < rbs.size(); i++){  
		    for (int j = 0; j < rbs.size(); j++){  
		        if (rbs.get(i).getCollisionBox().isColliding(rbs.get(j).getCollisionBox()) &&! rbs.get(j).equals(rbs.get(i))){
		            rbs.get(i).getCollisionBox().collide(rbs.get(j).getCollisionBox());
		        }
		    }
		}
	}
	
	public void update(){
		checkForCollisions();
	}

}
