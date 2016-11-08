package world;

import java.util.Collections;
import java.util.HashMap;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import entities.Entity;
import entities.solid.PlayerManager;
import main.Launcher;

public class World {

	public String worldName;
	public int maxEntities = -1; // -1 is default for no limit
	public int width, height; // Measured in tiles

	public HashMap<Integer, Entity> entityMap;
	public HashMap<Integer, Entity> worldMap;
	
	public static boolean debugMode;
	
	public World() {
		// Init new world

		worldName = "Nexus";
		width = height = 128; // Blank world, 2DO implement map loading

		entityMap = new HashMap<Integer, Entity>();
		worldMap = new HashMap<Integer, Entity>();

		addEntity(new PlayerManager(0), 32f, 32f);
		addEntity(new PlayerManager(1), 32f, 128f);
	}

	int id, minFree = 0, mfb = 0;

	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		mfb = minFree;
		for (int i = 0; i <= (entityMap.keySet().size() == 0 ? 0 : Collections.max(entityMap.keySet())); i++) {
			if (entityMap.get(i) != null) {
				if (entityMap.get(i).shouldDie()) {
					entityMap.remove(i);
				} else {
					if (!entityMap.get(i).selected) {
						if (entityMap.get(i).getColbox()
								.contains(new Point(Mouse.getX(), Launcher.getGAME_HEIGHT() - Mouse.getY()))) {
							if (Mouse.isButtonDown(0)) {
								entityMap.get(i).selected = true;
								id = i;
							}
						}
					} else {
						if (id != i) {
							entityMap.get(i).selected = false;
						}
					}
					entityMap.get(i).update(delta, this, i);

				}
			} else {
				if (minFree == -1 || entityMap.keySet().size() == 0) {
					minFree = 0;
				} else {
					if (i < minFree) {
						minFree = i;
					}
				}
			}
		}

		if (entityMap.get(minFree) != null) {
			minFree = (entityMap.keySet().size() == 0 ? 0 : Collections.max(entityMap.keySet()) + 1);
		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		int j = 0;
		for (int i = 0; i <= (entityMap.keySet().size() == 0 ? 0 : Collections.max(entityMap.keySet())); i++) {
			if (entityMap.get(i) != null) {
				if (entityMap.get(i).getColbox()
						.intersects(new Rectangle(0, 0, Launcher.getGAME_WIDTH(), Launcher.getGAME_HEIGHT()))) {
					entityMap.get(i).render(g);
				}
				if(debugMode){
					if(j <= 36){
						if (entityMap.get(i).selected) {
							g.setColor(Color.red);
							g.drawRect(10, 58 + 15 * j, 200, 30);
						}
						g.setColor(Color.white);
						g.drawString("Ent. - " + entityMap.get(i).toString(), 10, 56 + 15 * j);
						g.drawString("ID - " + entityMap.get(i).getId(), 10, 70 + 15 * j);
						j += 2;
					}
				}
			} else {
				if(debugMode){
					if(j <= 36){
						g.setColor(Color.red);
						g.fillRect(10, 58 + 15 * j, 200, 30);
		
						g.setColor(Color.white);
						g.drawString("Ent. - NULL", 10, 56 + 15 * j);
						g.drawString("ID - NULL", 10, 70 + 15 * j);
						j += 2;
					}
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
		if(debugMode){
			g.setColor(Color.white);
			g.drawString("Entity map size: " + entityMap.size() + " / minFree: " + minFree, 10, 38);
		}
	}

	public void addEntity(Entity entity, float x, float y) {
		entity.setX(x);
		entity.setY(y);

		if ((maxEntities != -1 && entityMap.size() < maxEntities) || maxEntities == -1) {
			// entityMap.put((entityMap.keySet().size() == 0 ? 0 :
			// Collections.max(entityMap.keySet()) + 1), entity);
			entityMap.put(minFree, entity);
			if (entityMap.get(minFree) != null) {
				minFree = (entityMap.keySet().size() == 0 ? 0 : Collections.max(entityMap.keySet()) + 1);
			}
		}

	}

	public void remEntity(int id) {
		if (id <= (entityMap.keySet().size() == 0 ? 0 : Collections.max(entityMap.keySet()))) {
			if (entityMap.get(id) != null) {
				// System.out.println("Killed: " + id + " which is " +
				// entityMap.get(id).getId() + "\n"
				// + entityMap.get(id).toString());
				entityMap.get(id).kill();
			}
		}
	}

}
