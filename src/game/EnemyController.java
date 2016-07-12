package game;

import java.util.Vector;

public class EnemyController {

	private static Vector<Enemy> enemies; //Java util vector
	
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
