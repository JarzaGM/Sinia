package popup;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import entities.Entity;
import entities.NonDefXY;
import world.World;

public class Popup extends Entity {

  public static boolean defXY = false;

  public String text;
  public Color c;
  Entity e;
  public float x, y;
  public float speed;
  public int t;
  public float off;

  public Popup(String text, Color color, Entity entity) {
    setColbox(new Rectangle(0f, 0f, 0f, 0f));
    setX(entity.getX() + entity.getWidth() / 2);
    setY(entity.getY() + entity.getHeight() / 2);
    setWidth(32f);
    setHeight(32f);
    this.text = text;
    this.c = color;
    setSpeed(0.01f);
    this.e = entity;
    setString("Popup!");
  }

  public void update(int delta, World world, int id) {
    t += delta;
    off += getSpeed() * delta;
    setY(e.getY() - off);
    setX(e.getX());
    if (t > 3000) {
      kill();
    }
  }

  public void render(Graphics g, World world) {
    g.setColor(c);
    g.drawString(text, getX() + World.xo, getY() + World.yo);
  }
}
