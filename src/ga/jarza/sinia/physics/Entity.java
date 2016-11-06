package ga.jarza.sinia.physics;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public abstract class Entity {

	private float x, y;
	private float xv, yv;
	private float speed;
	private float width, height;
	private Rectangle colbox;
	private PhysicsType phyType;
	
	public abstract void update(int delta);

	public abstract void render(Graphics g);
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
		this.setColbox(new Rectangle(x,getColbox().getY(),getColbox().getHeight(),getColbox().getWidth()));
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
		this.setColbox(new Rectangle(getColbox().getX(),y,getColbox().getHeight(),getColbox().getWidth()));
	}

	public float getXv() {
		return xv;
	}

	public void setXv(float xv) {
		this.xv = xv;
	}

	public float getYv() {
		return yv;
	}

	public void setYv(float yv) {
		this.yv = yv;
	}
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
		this.setColbox(new Rectangle(getColbox().getX(),getColbox().getY(),width,getColbox().getHeight()));
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
		this.setColbox(new Rectangle(getColbox().getX(),getColbox().getY(),getColbox().getWidth(),height));
	}

	public Rectangle getColbox() {
		return colbox;
	}

	public void setColbox(Rectangle colbox) {
		this.colbox = colbox;
	}

	public PhysicsType getPhyType() {
		return phyType;
	}

	public void setPhyType(PhysicsType phyType) {
		this.phyType = phyType;
	}

}
