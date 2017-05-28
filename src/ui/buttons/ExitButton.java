package ui.buttons;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.Launcher;
import ui.Button;

public class ExitButton extends Button {


  public ExitButton(float x, float y, float sx, float sy, String text) {
    super(x, y, sx, sy, text);
  }

  public void clicked() {

  }

  public void released() {
    if (new Rectangle(x, y, sx, sy)
        .contains(new Point(Mouse.getX(), Launcher.getGAME_HEIGHT() - Mouse.getY()))) {
      System.exit(0);
    }
  }


  public void render(Graphics g) {
    g.setColor(Color.blue);
    g.fillRect(x, y, sx, sy);
    g.setColor(Color.white);
    g.drawString(text, x, y);
  }

}
