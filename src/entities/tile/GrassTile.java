package entities.tile;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import cache.TempCacheSys;
import entities.Tile;
import physics.PhysicsType;
import world.World;

public class GrassTile extends Tile {

  public GrassTile() { // THIS IS ONLY VERY TEMPORARY!
    setColbox(new Rectangle(0f, 0f, 0f, 0f));
    setWidth(32);
    setHeight(32);
    setString("Grass Tile");
    setPhyType(PhysicsType.Tile);
    setTileimg("grass");
  }

  public void update(int id) {

  }

  public void render(Graphics g, World world) {
    float xa = getX() + World.xo;
    float ya = getY() + World.yo;
    
    Image img = null;
    
    try {
      img = TempCacheSys.getImage(getTileimg());
    } catch (SlickException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    g.drawImage(img, xa, ya, xa + getWidth(), ya + getHeight(), 0, 0,
        img.getWidth(), img.getHeight());
  }

}
