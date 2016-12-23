package cache;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TempCacheSys {
	
	public static Image grass;
	
	public static void init(){
		try {
			grass = new Image("res/tiles/grass.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static Image getGrass(){
		return grass;
	}
	
}
