import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.Group;

/**
 * Class that allows for the customization of the Chicken Entity.
 * 
 * @assumptions: Needs to have a governing class to call its shoot methods, needs to be given a spawn
 * coordinate on the edge of the map.
 * @dependancies: Entity, Fowl, Firing, EntityManager
 * @example: Chicken(entityManager, new Point2D(0,0))
 * @author seanhudson
 */
public class Chicken extends Fowl{
	
	/**
	 * Creates the Boss instance.
	 * 
	 * @param entityManager Manages the birdPoop's interaction with other Entity's.
	 * @param coordinate Edge spawn point for the chicken.
	 * @return nothing
	 */
	Chicken(EntityManager entityManager, Point2D coordinate) {
		super(entityManager, coordinate, "chicken", new Point2D(30, 30));
		setMoveSpeed(30);
		addDamagedByType("fork");
		setLives(2);
		setPoints(10);
		setFireRate(10000);
	}

	/**
	 * Handles actions for when the chicken collides with another Entity.
	 * 
	 * @return nothing
	 */
	public void didCollide() {
		setLives(getLives() - 1);
		checkForDeletion();
	}
	
	/**
	 * Fires regular shot of a Egg.
	 * 
	 * @param root Group node for the scene graph.
	 * @return nothing
	 */
	public void shoot(Group root) {
		getFiringDelegate().shoot(root, entityManager, "egg");
	}
	
	/**
	 * Updates the chicken's location
	 * 
	 * @assumptions This method is supposed to be called in the gameloop;
	 * @param elapsedTime FRAME_DELAY
	 * @return nothing
	 */
	public void move(double elapsedTime) {
		if(bounds != null) {
			bounce();
		}
		updateCoordinate(coordinate.getX() + getMovementVector().getX() * getMoveSpeed() * elapsedTime, coordinate.getY() + getMovementVector().getY() * getMoveSpeed() * elapsedTime);
	}
}
