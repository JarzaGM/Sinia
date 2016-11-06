package ga.jarza.sinia.states;


import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ga.jarza.sinia.main.Launcher;



public class Game extends BasicGameState{
	
	private static int state;
	
	@SuppressWarnings("unused")
	private int up_time, ups, upsc, delta;
	private boolean _init;
	
	public Game(int state){
		Game.state = state;
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
//		gc.setVSync(true);
//		gc.setTargetFrameRate(60);
		gc.setMaximumLogicUpdateInterval(1000/61);
		gc.setMinimumLogicUpdateInterval(1000/60);
		
		if(!_init){
			
			
			_init = true;
		}	
	}
	
	float ang;
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// Draw Updates per Second
		g.drawString("Ticks: " + upsc, 10, 36);
		
		float scw = Launcher.getGAME_WIDTH()/2;
		float sch = Launcher.getGAME_HEIGHT()/2;
		
		float mpa = (float) Math.toDegrees(Math.atan2((Mouse.getX() - scw), ((Mouse.getY()) - sch)));
		g.rotate(scw, sch, mpa);
		g.drawLine(scw, sch, 200, 200);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		this.delta = delta;
		up_time += delta;
		ups++;
		
		if(up_time > 1000){
			// Updates per second
			upsc = ups;
			ups = 0;
			up_time = 0;
		}
		
		Translator.update(sbg);
	}

	public int getID() {
		return state;
	}
	
	
	
}
