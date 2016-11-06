package world;

import java.util.HashMap;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import entities.Entity;
import entities.solid.PlayerManager;
import main.Launcher;

public class World {

	public String worldName;
	public int maxEntities = 4; // -1 is default for no limit
	public int width, height; // Measured in tiles

	public HashMap<Integer, Entity> entityMap;
	public HashMap<Integer, Entity> worldMap;
	public int entityCount = 0;
	
	public World() {
		// Init new world

		worldName = "Nexus";
		width = height = 128; // Blank world, 2DO implement map loading

		entityMap = new HashMap<Integer, Entity>();
		worldMap = new HashMap<Integer, Entity>();

		addEntity(new PlayerManager(entityCount, 0), 32f, 32f);
		addEntity(new PlayerManager(entityCount, 1), 32f, 128f);
	}
	

	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		for (int i = 0; i < entityMap.size(); i++) {
			if (entityMap.get(i) != null) {
				if (entityMap.get(i).shouldDie()) {
					entityMap.remove(i);
				} else {
					entityMap.get(i).update(delta, this);
				}
			}
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
		
		g.drawString("Entity map size: " + entityMap.size() + "\nEntity count: " + entityCount, 10, 46);

	}

	public void addEntity(Entity entity, float x, float y) {
		entity.setX(x);
		entity.setY(y);
		
		if (maxEntities != -1) {
			if (entityMap.size() < maxEntities) {
				entityMap.put(entityMap.size(), entity);
				entityCount++;
			}
		} else {
			entityMap.put(entityMap.size(), entity);
			entityCount++;
		}
		
	}

	public void remEntity(int id) {
		if (id <= entityMap.size()) {
			if (entityMap.get(id) != null) {
				entityMap.get(id).kill();
				entityCount--;
			}
		}
	}

}
