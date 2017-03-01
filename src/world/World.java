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

import cache.TempCacheSys;
import entities.Entity;
import entities.Tile;
import entities.solid.PlayerManager;
import entities.solid.TestWall;
import entities.tile.GrassTile;
import gameStates.GameMenu;
import main.Launcher;
import popup.Popup;
import ui.buttons.DebugButton;

public class World {

  public String worldName;
  public int maxEntities = -1; // -1 is default for no limit
  public int width, height; // Measured in tiles

  public HashMap<Integer, Entity> entityMap;
  public HashMap<Integer, Tile> worldMap;

  public static boolean debugMode;

  public static float xo, yo;
  public static PlayerManager player;

  public World() {
    // Init new world
    TempCacheSys.init();

    worldName = "Nexus";
    width = height = 128; // Blank world, 2DO implement map loading

    entityMap = new HashMap<Integer, Entity>();
    worldMap = new HashMap<Integer, Tile>();

    player = new PlayerManager(0);

    addEntity(new TestWall(), 128f, 128f);
    addEntity(new PlayerManager(1), 128f, 160f); // 0
    addEntity(new TestWall(), 128f, 192f);
    addEntity(new TestWall(), 128f, 224f);
    addEntity(new TestWall(), 1200f, 224f);
    addEntity(player, Launcher.getGAME_WIDTH() / 2 - 32f / 2,
        Launcher.getGAME_HEIGHT() / 2 - 32f / 2); // 0
    // addEntity(new PlayerManager(1), 32f, 128f);
    int cap = 20;

    for (int i = 0; i < cap; i++) {
      for (int j = 0; j < cap; j++) {
        addWEntity(new GrassTile(), i * 32f, j * 32f);
      }
    }
  }

  int id, minFree = 0;

  public void update(GameContainer gc, StateBasedGame sbg, int delta) {
    for (int i =
        0; i <= (entityMap.keySet().size() == 0 ? 0 : Collections.max(entityMap.keySet())); i++) {
      if (entityMap.get(i) != null) {
        Boolean bt = ((DebugButton) GameMenu.buts.get(GameMenu.DEBUGID)).state;
        if (entityMap.get(i).shouldDie()) {
          entityMap.remove(i);
        } else {
          if (bt) {
            if (!entityMap.get(i).selected) {
              if (entityMap.get(i).getColbox().contains(
                  new Point(Mouse.getX() - xo, Launcher.getGAME_HEIGHT() - (Mouse.getY() + yo)))) {
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

    xo = -player.getX() + Launcher.getGAME_WIDTH() / 2 - 32f / 2;
    yo = -player.getY() + Launcher.getGAME_HEIGHT() / 2 - 32f / 2;
  }

  public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
    int j = 0;

    for (int i = 0; i < worldMap.size(); i++) {
      if (worldMap.get(i) != null) {
        if (worldMap.get(i).getColbox().intersects(
            new Rectangle(-xo, -yo, Launcher.getGAME_WIDTH(), Launcher.getGAME_HEIGHT()))) {
          worldMap.get(i).render(g, this);
        }
      }
    }

    for (int i =
        0; i <= (entityMap.keySet().size() == 0 ? 0 : Collections.max(entityMap.keySet())); i++) {
      if (entityMap.get(i) != null) {
        if (entityMap.get(i).getColbox().intersects(
            new Rectangle(-xo, -yo, Launcher.getGAME_WIDTH(), Launcher.getGAME_HEIGHT()))) {
          entityMap.get(i).render(g, this);
        }
        if (debugMode) {
          if (j <= 36) {
            String ents = entityMap.get(i).toString();
            int entid = entityMap.get(i).getId();

            String fins = "Ent. - " + ents;
            String finid = "ID - " + entid;

            if (entityMap.get(i).selected) {
              g.setColor(Color.red);
              g.drawRect(10, 58 + 15 * j, (float) (g.getFont().getWidth(fins) + 10), 30);
            }

            g.setColor(Color.white);
            g.drawString(fins, 10, 56 + 15 * j);
            g.drawString(finid, 10, 70 + 15 * j);
            j += 2;
          }
        }
      } else {
        if (debugMode) {
          if (j <= 36) {
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

    if (debugMode) {
      g.setColor(Color.white);
      g.drawString("Entity map size: " + entityMap.size() + " / minFree: " + minFree, 10, 38);
    }
    g.translate(xo, yo);
  }

  public void addEntity(Entity entity, float x, float y) {
    if (!entity.getClass().isInstance(Popup.class)) {
      entity.setX(x);
      entity.setY(y);
    }

    if ((maxEntities != -1 && entityMap.size() < maxEntities) || maxEntities == -1) {
      // entityMap.put((entityMap.keySet().size() == 0 ? 0 :
      // Collections.max(entityMap.keySet()) + 1), entity);
      entityMap.put(minFree, entity);
      if (entityMap.get(minFree) != null) {
        entity.setId(minFree);
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

  public void addWEntity(Tile tile, float x, float y) {
    tile.setX(x);
    tile.setY(y);

    if ((maxEntities != -1 && worldMap.size() < maxEntities) || maxEntities == -1) {
      // worldMap.put((worldMap.keySet().size() == 0 ? 0 :
      // Collections.max(worldMap.keySet()) + 1), entity);
      worldMap.put(worldMap.keySet().size() == 0 ? 0 : Collections.max(worldMap.keySet()) + 1,
          tile);
    }

  }

  public void remWEntity(int id) {
    if (id <= (worldMap.keySet().size() == 0 ? 0 : Collections.max(worldMap.keySet()))) {
      if (worldMap.get(id) != null) {
        // System.out.println("Killed: " + id + " which is " +
        // worldMap.get(id).getId() + "\n"
        // + worldMap.get(id).toString());
        worldMap.get(id).kill();
      }
    }
  }


  public float getMouseX() {
    return (Mouse.getX() + xo);
  }

  public float getMouseY() {
    return (Mouse.getY() + yo);
  }
}
