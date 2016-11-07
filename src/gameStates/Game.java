package gameStates;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.solid.PlayerManager;
import world.World;

public class Game extends BasicGameState {

	private static int state;

	@SuppressWarnings("unused")
	private int up_time, ups, upsc, delta;
	private boolean _init;
	public World world;
	private int[] timer = new int[1000];

	public Game(int state) {
		Game.state = state;
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// gc.setVSync(true);
		gc.setTargetFrameRate(60);
		gc.setMaximumLogicUpdateInterval(1000 / 61);
		gc.setMinimumLogicUpdateInterval(1000 / 60);

		if (!_init) {

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
		timer[0] += delta;
		timer[1] += delta;
		timer[2] += delta;
		timer[3] += delta;
		ups++;

		world.update(gc, sbg, delta);
		
		int del = 1000;
		
		if (up_time > del) {
			// Updates per second
			upsc = ups;
			ups = 0;
			up_time = 0;
		}
		if (timer[0] >= del) {
			if (Keyboard.isKeyDown(Input.KEY_1)) {
				world.remEntity(2);
				timer[0] = 0;
			}
		}
		if (timer[1] >= del) {
			if (Keyboard.isKeyDown(Input.KEY_2)) {
				world.remEntity(3);
				timer[1] = 0;
			}
		}
		if (timer[2] >= del) {
			if (Keyboard.isKeyDown(Input.KEY_3)) {
				world.addEntity(new PlayerManager(0), 32f, 32f);
				timer[2] = 0;
			}
		}
		if (timer[3] >= del) {
			if (Keyboard.isKeyDown(Input.KEY_4)) {
				world.addEntity(new PlayerManager(1), 32f, 64f);
				timer[3] = 0;
			}
		}

		Translator.update(sbg);
	}

	public int getID() {
		return state;
	}

}
