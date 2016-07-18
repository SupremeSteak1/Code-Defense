package game;

import java.util.Vector;

import engine.backend.GameObjectHandler;

public class EnemyController {

	private static Vector<Enemy> enemies; //Java util vector
	private GameObjectHandler goh;
	
	public EnemyController(){
		enemies = new Vector<Enemy>();
	}
	
	public static synchronized Vector<Enemy> getEnemies(){
		return enemies;
	}
	
	public static void registerEnemy(Enemy e){
		enemies.add(e);
	}
	
	public static void unregisterEnemy(Enemy e){
		enemies.remove(e);
	}
	
}
