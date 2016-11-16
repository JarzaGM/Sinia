package physics;

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
	 * Main collision mechanic. positions.
	 * 
	 */
	public static void alignHor(Entity a, World w) {
		Entity b = null;

		for (int i = 0; i < w.entityMap.size(); i++) {
			if (w.entityMap.get(i) != null) {
				b = w.entityMap.get(i);

				if (b.getPhyType() == PhysicsType.Solid) {
					if (checkCollision(a, b)) {
						// first we do horizontal checks;
						if (a.getXv() > 0) {
							// going right
							if (a.getColbox().getX() < b.getColbox().getX()
									&& a.getColbox().getX() + a.getColbox().getWidth() > b.getColbox().getX()) {
								if (a.getColbox().getY() + a.getColbox().getHeight() > b.getColbox().getY()) {
									if (a.getColbox().getY() < b.getColbox().getY() + b.getColbox().getHeight()) {
										a.setX(b.getColbox().getX() - a.getColbox().getWidth());
									}
								}
							}
						}

						if (a.getXv() < 0) {
							// going left
							if (a.getColbox().getX() < b.getColbox().getX() + b.getColbox().getWidth()
									&& a.getColbox().getX() + a.getColbox().getWidth() > b.getColbox().getX()
											+ b.getColbox().getWidth()) {
								if (a.getColbox().getY() + a.getColbox().getHeight() > b.getColbox().getY()) {
									if (a.getColbox().getY() < b.getColbox().getY() + b.getColbox().getHeight()) {
										a.setX(b.getColbox().getX() + b.getColbox().getWidth());
									}
								}
							}
						}
					}
				}
			}
		} // end
	}

	public static void alignVer(Entity a, World w) {
		Entity b = null;
		for (int i = 0; i < w.entityMap.size(); i++) {
			if (w.entityMap.get(i) != null) {
				b = w.entityMap.get(i);

				if (b.getPhyType() == PhysicsType.Solid ) {
					if (checkCollision(a, b)) {

						// then we do the vertical checks;
						if (a.getYv() > 0) {
							// going down
							if (a.getColbox().getY() + a.getColbox().getHeight() >= b.getColbox().getY()
									&& a.getColbox().getY() < b.getColbox().getY()) {
								if (a.getColbox().getX() + a.getColbox().getWidth() >= b.getColbox().getX()) {
									if (a.getColbox().getX() < b.getColbox().getX() + b.getColbox().getWidth()) {
										a.setY(b.getColbox().getY() - a.getColbox().getHeight());
									}
								}
							}
						}

						if (a.getYv() < 0) {
							// going up
							if (a.getColbox().getY() + a.getColbox().getHeight() >= b.getColbox().getY()
									+ b.getColbox().getHeight()
									&& a.getColbox().getY() <= b.getColbox().getY() + b.getColbox().getHeight()) {
								if (a.getColbox().getX() + a.getColbox().getWidth() >= b.getColbox().getX()) {
									if (a.getColbox().getX() <= b.getColbox().getX() + b.getColbox().getWidth()) {
										a.setY(b.getColbox().getY() + b.getColbox().getHeight());
									}
								}
							}
						}
					}
				}
			}
		} // end
	}
}
