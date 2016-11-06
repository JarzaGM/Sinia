package ui.buttons;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import main.Launcher;
import ui.Button;

public class ExitButton extends Button{
	
	private boolean cur, prev;
	
	public ExitButton(float x, float y, float sx, float sy, String text) {
		super(x, y, sx, sy, text);
	}

	public void update() {
		cur = Mouse.isButtonDown(0);
		
		if(new Rectangle(x, y, sx, sy).contains(Mouse.getX(), Launcher.getGAME_HEIGHT() - Mouse.getY())){
			if(cur && !prev){
				// CLick
				System.exit(0);
			}
		}
		
		prev = cur;
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x,y,sx,sy);
		g.setColor(Color.white);
		g.drawString(text, x, y);
	}
	
	
	
}
