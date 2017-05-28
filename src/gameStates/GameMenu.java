package gameStates;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.Launcher;
import mouse.MouseHandler;
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
  private MouseHandler mouseHandler = new MouseHandler();

  public GameMenu(int state) {
    GameMenu.state = state;
  }

  public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    // gc.setVSync(true);
    gc.setMaximumLogicUpdateInterval(1000 / 61);
    gc.setMinimumLogicUpdateInterval(1000 / 60);
    gc.setVSync(true);

    if (!_init) {
      // Button initialization
      Button playb = new PlayButton(32, 100, 128, 32, "Play");
      Button exitb = new ExitButton(32, 164, 128, 32, "Exit");
      Button debugb = new DebugButton(32, 228, 128, 32, "Debug Mode");
      Button colboxb = new ColBoxButton(32, 292, 128, 32, "Collision Boxes");

      mouseHandler.addListener(playb);
      mouseHandler.addListener(exitb);
      mouseHandler.addListener(debugb);
      mouseHandler.addListener(colboxb);

      buts.add(playb);
      buts.add(exitb);
      buts.add(debugb);
      buts.add(colboxb);

      PLAYID = 0;
      EXITID = 1;
      DEBUGID = 2;
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
    // if(new Rectangle(b.x, b.y, b.sx, b.sy).contains(new Point(Mouse.getX(),
    // Launcher.getGAME_HEIGHT() - Mouse.getY()))){
    
    mouseHandler.tickMouse();

    Translator.update(sbg);
  }

  public int getID() {
    return state;
  }



}
