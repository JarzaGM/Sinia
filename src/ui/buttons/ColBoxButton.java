package ui.buttons;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.Launcher;
import mouse.MouseClickHandler;
import ui.MultiStateButton;

public class ColBoxButton extends MultiStateButton {

  public ColBoxButton(float x, float y, float sx, float sy, String text) {
    super(x, y, sx, sy, text);
    maxState = 3; // it's 3 (0, 1, 2)
    state = 1;
  }

  public void clicked() {
    if (new Rectangle(x, y, sx, sy)
        .contains(new Point(Mouse.getX(), Launcher.getGAME_HEIGHT() - Mouse.getY()))) {
      state = (state > (maxState - 1) || (state + 1) > (maxState - 1) ? 0 : state + 1);
    }
  }

  public void released() {

  }

  public void render(Graphics g) {
    if (g.getFont().getWidth(text) > sx) {
      sx = (float) (g.getFont().getWidth(text) * 1.25);
    }

    g.setColor(Color.blue);
    g.drawRect(x, y, sx, sy);

    g.setColor(Color.blue);
    float of = (sx / (maxState));
    g.fillRect(x + of * state, y, sx / maxState, sy);

    String[] a = {"On", "Off", "Debug"};

    for (int i = 0; i < maxState; i++) {
      if (state == i) {
        g.setColor(Color.white);
      } else {
        g.setColor(Color.gray);
      }
      g.drawString(a[i], x + of * i + of / 2 - g.getFont().getWidth(a[i]) / 2,
          y + 3 * sy / 4 - g.getFont().getHeight(a[i]) / 2);

    }

    g.setColor(Color.white);
    g.drawString(text, x + sx / 2 - g.getFont().getWidth(text) / 2, y);
  }

}
