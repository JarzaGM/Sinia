package ui.buttons;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import ui.OnOffButton;
import world.World;

public class DebugButton extends OnOffButton {

	public DebugButton(float x, float y, float sx, float sy, String text) {
		super(x, y, sx, sy, text);
	}

	public void update() {
		cur = Mouse.isButtonDown(0);
		if (clicked()) {
			state = !state;
		}
		World.debugMode = state;
		prev = cur;
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.drawRect(x, y, sx, sy);

		g.setColor(Color.blue);
		float of = (state ? sx/2 : 0);
		g.fillRect(x + of, y, sx/2, sy);
		
		String off = "Off";
		String on = "On";
		
		g.setColor(Color.gray);
		g.drawString(off, x + sx/4 - g.getFont().getWidth(off)/2, y + 3*sy/4 - g.getFont().getHeight(off)/2);
		
		g.setColor(Color.white);
		g.drawString(on, x + sx - sx/4 - g.getFont().getWidth(on)/2, y + 3*sy/4 - g.getFont().getHeight(on)/2);
		
		g.setColor(Color.white);
		g.drawString(text, x + sx/2 - g.getFont().getWidth(text)/2, y);
	}

}
