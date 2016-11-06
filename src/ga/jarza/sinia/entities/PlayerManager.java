package ga.jarza.sinia.entities;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import ga.jarza.sinia.main.Launcher;
import ga.jarza.sinia.physics.Entity;

public class PlayerManager extends Entity {

	public PlayerManager() {
		setColbox(new Rectangle(0f, 0f, 0f, 0f));
		setX(32f);
		setY(32f);
		setWidth(32f);
		setHeight(32f);
		setSpeed(0.5f);
	}

	public void update(int delta) {
		if (Keyboard.isKeyDown(Input.KEY_W)) {
			setYv(-getSpeed() * delta);
		}
		
		if (Keyboard.isKeyDown(Input.KEY_A)) {
			setXv(-getSpeed() * delta);
		}
		
		if (Keyboard.isKeyDown(Input.KEY_S)) {
			setYv(getSpeed() * delta);
		}
		
		if (Keyboard.isKeyDown(Input.KEY_D)) {
			setXv(getSpeed() * delta);
		}

		setX(getX() + getXv());
		setY(getY() + getYv());
		setXv(0f);
		setYv(0f);
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(Color.red);
		g.draw(getColbox());

	}

}
