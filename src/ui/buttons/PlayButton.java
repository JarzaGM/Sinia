package ui.buttons;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import gameStates.Translator;
import main.Launcher;
import ui.Button;

public class PlayButton extends Button {


  public PlayButton(float x, float y, float sx, float sy, String text) {
    super(x, y, sx, sy, text);
  }

  public void update() {
    cur = Mouse.isButtonDown(0);

    if (released()) {
      // CLick
      Translator.gotoState(Launcher.ingame);
    }

    prev = cur;
  }

  public void render(Graphics g) {
    g.setColor(Color.blue);
    g.fillRect(x, y, sx, sy);
    g.setColor(Color.white);
    g.drawString(text, x, y);
  }

}
