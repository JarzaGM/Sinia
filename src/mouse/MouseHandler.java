package mouse;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

public class MouseHandler {

  private List<MouseClickHandler> listeners = new ArrayList<MouseClickHandler>();
  private boolean cur, prev;

  public void addListener(MouseClickHandler mch) {
    listeners.add(mch);
  }

  public void tickMouse() {
    cur = Mouse.isButtonDown(0);

    if (cur && !prev) {
      notifyClick();
    } else if (prev && !cur) {
      notifyRelease();
    }

    prev = cur;
  }

  public void notifyClick() {
    for (MouseClickHandler mch : listeners)
      mch.clicked();
  }

  public void notifyRelease() {
    for (MouseClickHandler mch : listeners)
      mch.released();
  }

}
