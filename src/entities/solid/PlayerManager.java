package entities.solid;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import entities.Entity;
import main.Launcher;
import physics.Collision;
import physics.PhysicsType;
import world.World;

public class PlayerManager extends Entity {

	public int setting = 0;
	public int[][] keymap = { { Input.KEY_W, Input.KEY_A, Input.KEY_S, Input.KEY_D },
			{ Input.KEY_UP, Input.KEY_LEFT, Input.KEY_DOWN, Input.KEY_RIGHT } };

	public PlayerManager(int setting) {
		this.setting = setting;
		setColbox(new Rectangle(0f, 0f, 0f, 0f));
		setWidth(32f);
		setHeight(32f);
		setSpeed(0.4f);
		setString("Player " + (setting == 1 ? "orange" : "red"));
		setPhyType(PhysicsType.Dynamic);
	}

	float fov = 90f;
	boolean charged = false;

	public void update(int delta, World world, int id) {
		setId(id);
		if (Keyboard.isKeyDown(keymap[setting][0])) {
			setYv(-getSpeed() * delta);
		}

		if (Keyboard.isKeyDown(keymap[setting][1])) {
			setXv(-getSpeed() * delta);
		}

		if (Keyboard.isKeyDown(keymap[setting][2])) {
			setYv(getSpeed() * delta);
		}

		if (Keyboard.isKeyDown(keymap[setting][3])) {
			setXv(getSpeed() * delta);
		}

		// int dw = Mouse.getDWheel();
		float c = 5f;

		// if(dw < 0){
		// fov -= c;
		// }
		// if(dw > 0){
		// fov += c;
		// }

		if (Mouse.isButtonDown(0)) {
			fov -= c;
		} else {
			fov = 120f;
			charged = false;
		}

		/*
		 * if(Mouse.isButtonDown(1)){ fov += c; }
		 */
		if (fov > 120f) {
			fov = 120f;
		} else if (fov < 5f) {
			fov = 5f;
			charged = true;

		}

		if (getColbox().contains(new Point(Mouse.getX(), Launcher.getGAME_HEIGHT() - Mouse.getY()))) {
			if (Mouse.isButtonDown(1)) {
//				kill();
			}
		}
		
		setX(getX() + getXv());
		Collision.alignHor(this, world);
		
		setY(getY() + getYv());
		Collision.alignVer(this, world);
		setXv(0f);
		setYv(0f);
	}

	public void render(Graphics g, float xo, float yo) {
		if (setting == 1) {
			g.setColor(Color.orange);
		} else {
			g.setColor(Color.red);
		}
		g.fillRect(Launcher.getGAME_WIDTH()/2 - getWidth()/2, Launcher.getGAME_HEIGHT()/2 - getHeight()/2, getWidth(), getHeight());
		g.draw(getColbox());

		float scw = getColbox().getCenterX();
		float sch = getColbox().getCenterY();
		
		float a = Launcher.getGAME_WIDTH()/2 - 32f/2;
		float b = Launcher.getGAME_HEIGHT()/2 -32f/2;
		
		
		float mpa = (float) Math.toDegrees(Math.atan2(((Mouse.getX()) - scw), (Launcher.getGAME_HEIGHT() - (Mouse.getY()) - sch)));
//		float mpa = (float) Math.toDegrees(0.5);
		mpa *= -1f;
		mpa += 180f;
		g.translate(xo, yo);
		// g.drawString("mpa: " + mpa + "\nFOV: " + fov, 10, 54);
		g.setColor(Color.black);
		g.drawString("-" + getId(), getX(), getY());
		g.setColor(Color.white);

		if (charged) {
			g.setColor(Color.green);
			g.fillOval(Mouse.getX() - a, Launcher.getGAME_HEIGHT() - Mouse.getY() - b, 32, 32);
			g.drawLine(scw, sch, Mouse.getX(), Launcher.getGAME_HEIGHT() - Mouse.getY());
		}

		g.resetTransform();
		g.translate(xo, yo);
		g.rotate(scw, sch, mpa + fov / 2f);
		g.drawLine(scw, sch, scw, sch - 150);
		g.resetTransform();
		g.translate(xo, yo);
		g.rotate(scw, sch, mpa - fov / 2f);
		g.drawLine(scw, sch, scw, sch - 150);
		g.resetTransform();
	}

}
