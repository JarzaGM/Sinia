package cache;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TempCacheSys {

  public static HashMap<String, String[]> imgdata = new HashMap<>();
  public static HashMap<String, Image> imgs = new HashMap<>();

  public static void init() {
    String[] s =  {"res/tiles/grass.png","0"};
    
    imgdata.put("grass", s);
    
    
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
