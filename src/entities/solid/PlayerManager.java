package entities.solid;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import entities.Entity;
import main.Launcher;
import world.World;

public class PlayerManager extends Entity {
	
	public int setting = 0;
	public int[][] keymap = {{Input.KEY_W, Input.KEY_A, Input.KEY_S, Input.KEY_D}
							,{Input.KEY_UP, Input.KEY_LEFT, Input.KEY_DOWN, Input.KEY_RIGHT}};
	
	public PlayerManager(int setting) {
		this.setting = setting;
		setColbox(new Rectangle(0f, 0f, 0f, 0f));
		setWidth(32f);
		setHeight(32f);
		setSpeed(0.5f);
		setString("Player " + setting);
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
		
//		int dw = Mouse.getDWheel();
		float c = 5f;
		
//		if(dw < 0){
//			fov -= c;
//		}
//		if(dw > 0){
//			fov += c;
//		}
		
		if(Mouse.isButtonDown(0)){
			fov -= c;
		}else{
			fov = 120f;
			charged = false;
		}
			
			
		/*if(Mouse.isButtonDown(1)){
			fov += c;
		}*/
		if(fov > 120f){
			fov = 120f;
		}else if(fov < 5f){
			fov = 5f;
			charged = true;
			
		}
		
		setX(getX() + getXv());
		setY(getY() + getYv());
		setXv(0f);
		setYv(0f);
	}

	public void render(Graphics g) {
		if(setting == 1){
			g.setColor(Color.orange);
		}else{
			g.setColor(Color.red);
		}
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		
		float scw = getColbox().getCenterX();
		float sch = getColbox().getCenterY();
		
		float mpa = (float) Math.toDegrees(Math.atan2((Mouse.getX() - scw), (Launcher.getGAME_HEIGHT() - Mouse.getY() - sch)));
		mpa *= -1f;
		mpa += 180f;
		
//		g.drawString("mpa: " + mpa + "\nFOV: " + fov, 10, 54);
		g.setColor(Color.black);
		g.drawString("-" + getId(), getX(), getY());
		g.setColor(Color.white);
		
		if(charged){
			g.setColor(Color.green);
			g.fillOval(Mouse.getX() - 16, Launcher.getGAME_HEIGHT() - Mouse.getY()- 16, 32, 32);
			g.drawLine(scw, sch, Mouse.getX(), Launcher.getGAME_HEIGHT() - Mouse.getY());
		}
		
		g.resetTransform();
		g.rotate(scw, sch, mpa + fov/2f);
		g.drawLine(scw, sch, scw, sch - 150);
		g.resetTransform();
		g.rotate(scw, sch, mpa - fov/2f);
		g.drawLine(scw, sch, scw, sch - 150);
		
		g.resetTransform();
	}

}
