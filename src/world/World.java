package world;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import entities.PlayerManager;
import main.Launcher;
import physics.Entity;

public class World {

	public String worldName;
	public int maxEntities;
	public int width, height; // Measured in tiles

	public HashMap<Integer, Entity> entityMap;
	public HashMap<Integer, Entity> worldMap;

	public World() {
		// Init new world

		worldName = "Nexus";
		width = height = 128; // Blank world, 2DO implement map loading

		entityMap = new HashMap<Integer, Entity>();
		worldMap = new HashMap<Integer, Entity>();
		
		entityMap.put(entityMap.size(), new PlayerManager(0));
		entityMap.put(entityMap.size(), new PlayerManager(1));
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		for (int i = 0; i < entityMap.size(); i++) {
			if(entityMap.get(i) != null)
				entityMap.get(i).update(delta);
		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		for (int i = 0; i < entityMap.size(); i++) {
			if (entityMap.get(i) != null) {
				if (entityMap.get(i).getColbox()
						.intersects(new Rectangle(0, 0, Launcher.getGAME_WIDTH(), Launcher.getGAME_HEIGHT()))) {
					entityMap.get(i).render(g);
				}
			}
		}
		for (int i = 0; i < worldMap.size(); i++) {
			if (worldMap.get(i) != null) {
				if (worldMap.get(i).getColbox()
						.intersects(new Rectangle(0, 0, Launcher.getGAME_WIDTH(), Launcher.getGAME_HEIGHT()))) {
					worldMap.get(i).render(g);
				}
			}
		}
		
	}

}
