package entities.solid;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import entities.Entity;
import gameStates.GameMenu;
import physics.PhysicsType;
import ui.buttons.ColBoxButton;
import ui.buttons.DebugButton;
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

	public void render(Graphics g, World world) {
		g.setColor(Color.gray);
		g.fillRect(getX() + World.xo, getY() + World.yo, getWidth(), getHeight());
		int at = ((ColBoxButton) GameMenu.buts.get(GameMenu.COLBOXDEBUGID)).state;
		boolean bt = ((DebugButton) GameMenu.buts.get(GameMenu.DEBUGID)).state;
		
		if(at == 0 || (at == 2 && bt == true)){
			g.drawRect(getX(), getY(), getWidth(), getHeight());
		}
	}
}
