package gameStates;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import world.World;



public class Game extends BasicGameState{
	
	private static int state;
	
	@SuppressWarnings("unused")
	private int up_time, ups, upsc, delta;
	private boolean _init;
	public World world;
	
	public Game(int state){
		Game.state = state;
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
//		gc.setVSync(true);
		gc.setTargetFrameRate(60);
		gc.setMaximumLogicUpdateInterval(1000/61);
		gc.setMinimumLogicUpdateInterval(1000/60);
		
		if(!_init){
			
			world = new World();
			_init = true;
		}	
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// Draw Updates per Second
		g.setColor(Color.white);
		g.drawString("Ticks: " + upsc, 10, 36);
		
		world.render(gc, sbg, g);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		this.delta = delta;
		up_time += delta;
		ups++;
		
		world.update(gc, sbg, delta);
		
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
