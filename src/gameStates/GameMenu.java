package gameStates;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ui.Button;
import ui.buttons.ColBoxButton;
import ui.buttons.DebugButton;
import ui.buttons.ExitButton;
import ui.buttons.PlayButton;


public class GameMenu extends BasicGameState {

  private static int state;

  public static ArrayList<Button> buts = new ArrayList<Button>();
  public static int PLAYID, EXITID, DEBUGID, COLBOXDEBUGID;
  @SuppressWarnings("unused")
  private int up_time, ups, upsc;
  private boolean _init;

  public GameMenu(int state) {
    GameMenu.state = state;
  }

  public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    // gc.setVSync(true);
    gc.setMaximumLogicUpdateInterval(1000 / 61);
    gc.setMinimumLogicUpdateInterval(1000 / 60);
    gc.setVSync(true);

    if (!_init) {
      buts.add(new PlayButton(32, 100, 128, 32, "Play"));
      PLAYID = 0;
      buts.add(new ExitButton(32, 164, 128, 32, "Exit"));
      EXITID = 1;
      buts.add(new DebugButton(32, 228, 128, 32, "Debug Mode"));
      DEBUGID = 2;
      buts.add(new ColBoxButton(32, 292, 128, 32, "Collision Boxes"));
      COLBOXDEBUGID = 3;
      _init = true;
    }
  }

  public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    g.setColor(Color.white);

    for (Button b : buts) {
      b.render(g);
    }

    // Draw Updates per Second
    // g.drawString("Ticks: " + upsc, 10, 36);
  }

  public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    up_time += delta;
    ups++;

    if (up_time > 1000) {
      // Updates per second
      upsc = ups;
      ups = 0;
      up_time = 0;
    }

    for (Button b : buts) {
      b.update();
    }

    Translator.update(sbg);
  }

  public int getID() {
    return state;
  }



}
