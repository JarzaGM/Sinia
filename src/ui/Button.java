package ui;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.Launcher;

public abstract class Button {

  public float x, y, sx, sy;
  public String text;
  protected boolean cur, prev;

  /**
   * Custom button interface; X, Y, WIDTH, HEIGHT
   */

  public Button(float x, float y, float sx, float sy, String text) {
    this.x = x;
    this.y = y;
    this.sx = sx;
    this.sy = sy;
    this.text = text;
  }

  public abstract void update();

  public abstract void render(Graphics g);

  public boolean clicked() {
    return (new Rectangle(x, y, sx, sy).contains(
        new Point(Mouse.getX(), Launcher.getGAME_HEIGHT() - Mouse.getY())) && cur && !prev);
  }

  public boolean released() {
    return (new Rectangle(x, y, sx, sy).contains(
        new Point(Mouse.getX(), Launcher.getGAME_HEIGHT() - Mouse.getY())) && !cur && prev);
  }

}
