package cache;

import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class TempCacheSys {

  public static HashMap<String, String[]> imgdata = new HashMap<>();
  public static HashMap<String, Image> imgs = new HashMap<>();
  private static SpriteSheet chars;
  
  public static void init() {
    String[] s =  {"res/tiles/grass.png","0", "0", "-"};
    String[] wiz = {"res/charsheet.png", "0", "0", "10,0;10,5;15,5;15,20"};
    
    imgdata.put("grass", s);
    imgdata.put("wizard", wiz);
    
    try {
      chars = new SpriteSheet("res/charsheet.png", 8, 8, new Color(255, 0, 255));
    } catch (SlickException e) {
      e.printStackTrace();
    }
    imgs.put("char", chars.getSprite(0, 0));
    // grass = new Image("res/tiles/grass.png", false, Image.FILTER_NEAREST);
  }

  public static void update() {

  }

  public static Image loadImage(String path, String name) throws SlickException {
    Image temp = imgs.get(name);

    if (temp == null) {
      imgs.put(name, new Image(path, false, Image.FILTER_NEAREST));
      temp = imgs.get(name);
    }
    
    imgdata.get(name)[1] = (Integer.parseInt(imgdata.get(name)[1]) + 1) + "";
    return temp;
  }

  public static Image getImage(String name) throws SlickException {
    String path = imgdata.get(name)[0];
    Image fin = loadImage(path, name);
    return fin;
  }

}
