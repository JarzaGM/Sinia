package entities.solid;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import entities.Entity;
import physics.PhysicsType;
import world.World;

public class TestWall extends Entity{
	
	public TestWall(){
		setColbox(new Rectangle(0f, 0f, 0f, 0f));
		setWidth(32f);
		setHeight(32f);
		setString("Solid wall test obj");
		setPhyType(PhysicsType.Solid);
	}
	
	public void update(int delta, World world, int id) {
		
	}

	public void render(Graphics g, float xo, float yo) {
		g.setColor(Color.gray);
		g.fillRect(getX() + xo, getY() + yo, getWidth(), getHeight());
	}
	
	
	
}
