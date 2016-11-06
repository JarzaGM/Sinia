package physics;

import org.newdawn.slick.geom.Rectangle;

import entities.Entity;
import world.World;

public class Collision {

	/**
	 * Checks if Shape A is intersecting Shape B
	 * 
	 */
	public static boolean checkCollision(Entity a, Entity b) {
		return (a.getColbox().intersects(b.getColbox()));
	}

	/**
	 * Main collision mechanic.
	 * positions.
	 * 
	 */
	public static void align(Entity a, World w) {
		Rectangle c = a.getColbox(); // As colBox
		Entity b = null;
		Rectangle d = null;
		
		for (int i = 0; i < w.entityMap.size(); i++) {
			b = w.entityMap.get(i);
			d = b.getColbox();
			
			if (b.getPhyType() == PhysicsType.Solid && a.getPhyType() == PhysicsType.Solid) {
				if (checkCollision(a, b)) {
					// first we do horizontal checks;
					if (a.getXv() > 0) {
						// gobg right
						if (c.getX() <= d.getX()) {
							if (c.getY() + c.getHeight() > d.getY()) {
								if (c.getY() < d.getY() + d.getHeight()) {
									c.setX(d.getX() - c.getWidth());
								}
							}
						}
					}
					
					if (a.getXv() < 0) {
						// gobg left
						if (c.getX() + c.getWidth() >= d.getX() + d.getWidth()) {
							if (c.getY() + c.getHeight() > d.getY()) {
								if (c.getY() < d.getY() + d.getHeight()) {
									c.setX(d.getX() + d.getWidth());
								}
							}
						}
					}

					// then we do the vertical checks;
					if (a.getYv() > 0) {
						// gobg down
						if (c.getY() <= d.getY()) {
							if (c.getX() + c.getWidth() > d.getX()) {
								if (c.getX() < d.getX() + d.getWidth()) {
									c.setY(d.getY() - c.getHeight());
								}
							}
						}
					}
					
					if (a.getYv() < 0) {
						// gobg up
						if (c.getY() + c.getHeight() >= d.getY() + d.getHeight()) {
							if (c.getX() + c.getWidth() > d.getX()) {
								if (c.getX() < d.getX() + d.getWidth()) {
									c.setY(d.getY() + d.getHeight());
								}
							}
						}
					}
				}
			}
		}
	}
}
