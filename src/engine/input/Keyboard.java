package engine.input;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * 
 * @author Thomas Hayden
 * @version 6/02/16
 */
public class Keyboard implements KeyListener {
	
	private static char event;

	public static boolean isKeyPressed(char c) {
		return event == c;
	}
	
	//The space character is not to be used
	public Keyboard() {
		event = ' ';
	}
	
	public void keyPressed(KeyEvent e) {
		event = e.getKeyChar();
	}

	public void keyReleased(KeyEvent e) {
		event = ' ';
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
}
