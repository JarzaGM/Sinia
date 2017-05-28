package entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import physics.PhysicsType;
import world.World;

public abstract class Tile {

  private float x, y;
  private float width, height;
  private Rectangle colbox;
  private PhysicsType phyType;
  private String string;
  public boolean selected = false;
  private String tileimg;

  private int id;
  private boolean kill;

  public abstract void update(int id);

  public abstract void render(Graphics g, World world);

  public float getX() {
    return x;
  }

  public void setX(float x) {
    this.x = x;
    this.setColbox(
        new Rectangle(x, getColbox().getY(), getColbox().getHeight(), getColbox().getWidth()));
  }

  public float getY() {
    return y;
  }

  public void setY(float y) {
    this.y = y;
    this.setColbox(
        new Rectangle(getColbox().getX(), y, getColbox().getHeight(), getColbox().getWidth()));
  }

  public float getWidth() {
    return width;
  }

  public void setWidth(float width) {
    this.width = width;
    this.setColbox(
        new Rectangle(getColbox().getX(), getColbox().getY(), width, getColbox().getHeight()));
  }

  public float getHeight() {
    return height;
  }

  public void setHeight(float height) {
    this.height = height;
    this.setColbox(
        new Rectangle(getColbox().getX(), getColbox().getY(), getColbox().getWidth(), height));
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

  public String getString() {
    return string;
  }

  public void setString(String string) {
    this.string = string;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  public String getTileimg() {
    return tileimg;
  }

  public void setTileimg(String tileimg) {
    this.tileimg = tileimg;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean shouldDie() {
    return kill;
  }

  public void kill() {
    this.kill = true;
  }

}
