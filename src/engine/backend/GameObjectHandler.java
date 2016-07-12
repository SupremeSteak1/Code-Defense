package engine.backend;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * 
 * @author Thomas Hayden
 * @version 6/02/16
 */
public class GameObjectHandler {
	
	private static ArrayList<GameObject> gameObjects;
	
	public GameObjectHandler() {
		gameObjects = new ArrayList<GameObject>();
	}
	
	public static void registerGameObject(GameObject go) {
		gameObjects.add(go);
	}
	
	public static void unregisterGameObject(GameObject go) {
		gameObjects.remove(go);
	}
	
	public static void clearGameObjects() {
		gameObjects = new ArrayList<GameObject>();
	}
	
	public static void updateGameObjects() {
		try {
			for(GameObject go : gameObjects) {
				try {
					go.update();
				} catch(Exception e) {
					System.out.println("From updateGameObjects()");
					e.printStackTrace();
				}
			}
		} catch(ConcurrentModificationException e) {
			//System.out.println("HA");
		}
	}
	
	public static void renderGameObjects(Renderer r) {
		for(GameObject go : gameObjects) {
			try {
				r.addToQueue(go.render());
			} catch(Exception e) {
				System.out.println("From renderGameObjects()");
				e.printStackTrace();
			}
		}
	}
}

