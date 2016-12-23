package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import gameStates.Game;
import gameStates.GameMenu;

public class Launcher extends StateBasedGame{

	public static final String gamename = "Sinia";
	public static final String version = "v0.1.0";
	public static int GAME_WIDTH = 800;
	public static int GAME_HEIGHT = 600;
	public static double RESOLUTION = GAME_WIDTH / GAME_HEIGHT;
	public static final boolean FULLSCREEN = false;
	public static final int menu = 0;
	public static final int ingame = 1;
	
	public Launcher(String name) {
		super(name);
		
//		INITIALIZE STATES IN HERE
		this.addState(new GameMenu(menu));
		this.addState(new Game(ingame));
	}

	public static void main(String[] args) {
		AppGameContainer appgc;
		try {
			 appgc = new AppGameContainer(new Launcher(gamename));
			 if(FULLSCREEN){
				 GAME_WIDTH = appgc.getScreenWidth();
				 GAME_HEIGHT = appgc.getScreenHeight();
			 }
			 appgc.setUpdateOnlyWhenVisible(true);
			 appgc.setDisplayMode(GAME_WIDTH, GAME_HEIGHT, FULLSCREEN);
			 appgc.start();
		}catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menu).init(gc,  this);
		this.getState(ingame).init(gc,  this);
		
		this.enterState(menu);
	}
	
	public static String getVersion() {
		return version;
	}

	public static int getGAME_WIDTH() {
		return GAME_WIDTH;
	}

	public static int getGAME_HEIGHT() {
		return GAME_HEIGHT;
	}

	public static boolean isFullscreen() {
		return FULLSCREEN;
	}
}
