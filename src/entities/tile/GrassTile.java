package entities.tile;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import cache.TempCacheSys;
import entities.Tile;
import physics.PhysicsType;
import world.World;

public class GrassTile extends Tile{
	
	public GrassTile(){ // THIS IS ONLY VERY TEMPORARY!
		setColbox(new Rectangle(0f, 0f, 0f, 0f));
		setWidth(32);
		setHeight(32);
		setString("Grass Tile");
		setPhyType(PhysicsType.Tile);
		setTileimg(TempCacheSys.getGrass());
	}
	
	public void update(int id) {
		
	}

	public void render(Graphics g, World world) {
		float xa = getX() + World.xo;
		float ya = getY() + World.yo;
		g.drawImage(getTileimg(), xa, ya, xa + getWidth(), ya + getHeight(), 0, 0, getTileimg().getWidth(), getTileimg().getHeight());
	}
	
}
