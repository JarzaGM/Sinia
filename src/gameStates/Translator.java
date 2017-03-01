package gameStates;

import org.newdawn.slick.state.StateBasedGame;

public class Translator {

  private static int a = -1;

  public static void gotoState(int state) {
    a = state;
  }

  public static void update(StateBasedGame sbg) {
    if (a != -1) {
      sbg.enterState(a);
      a = -1;
    }
  }

}
