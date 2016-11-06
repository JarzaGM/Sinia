package ga.jarza.sinia.states;


import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ga.jarza.sinia.main.Launcher;
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
	
	float fov = 90f;
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// Draw Updates per Second
		g.drawString("Ticks: " + upsc, 10, 36);
		
		world.render(gc, sbg, g);
		
		float scw = world.entityMap.get(0).getColbox().getCenterX();
		float sch = world.entityMap.get(0).getColbox().getCenterY();
		
		float mpa = (float) Math.toDegrees(Math.atan2((Mouse.getX() - scw), (Launcher.getGAME_HEIGHT() - Mouse.getY() - sch)));
		mpa *= -1f;
		mpa += 180f;
		g.drawString("mpa: " + mpa + "\nFOV: " + fov, 10, 64);
		
//		g.drawLine(scw, sch, Mouse.getX(), sch*2 - Mouse.getY());
		
		g.resetTransform();
		g.rotate(scw, sch, mpa + fov/2f);
		g.drawLine(scw, sch, scw, sch - 300);
		g.resetTransform();
		g.rotate(scw, sch, mpa - fov/2f);
		g.drawLine(scw, sch, scw, sch - 300);
		
		g.resetTransform();
		g.drawString(Mouse.getX() + ", " + Mouse.getY(), Mouse.getX(),Launcher.getGAME_HEIGHT() -  Mouse.getY());
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		this.delta = delta;
		up_time += delta;
		ups++;
		
		world.update(gc, sbg, delta);
		
		int dw = Mouse.getDWheel();
		float c = 2.5f;
		
		if(dw < 0){
			fov -= c;
		}
		if(dw > 0){
			fov += c;
		}
		
		if(Mouse.isButtonDown(0)){
			fov -= c;
		}else if(Mouse.isButtonDown(1)){
			fov += c;
		}
		if(fov > 120f){
			fov = 120f;
		}else if(fov < 5f){
			fov = 5f;
		}
		
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
