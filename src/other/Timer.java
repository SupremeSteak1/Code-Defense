package other;

public class Timer {

	private static long timeSinceLastFrame;
	private static long currentTime;
	
	public static void update(){
		timeSinceLastFrame = System.currentTimeMillis() - currentTime;
		currentTime = System.currentTimeMillis();
	}
	
	public static long getTimeSinceLastFrame(){
		return timeSinceLastFrame;
	}
	
}
