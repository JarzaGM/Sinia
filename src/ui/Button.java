package ui;

import org.newdawn.slick.Graphics;

import mouse.MouseClickHandler;

public abstract class Button implements MouseClickHandler{

  public float x, y, sx, sy;
  public String text;

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

  public abstract void render(Graphics g);

}
