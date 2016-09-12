import javafx.geometry.Point2D;
import javafx.scene.Group;

/**
 * Class that allows for the customization of the Rooster Entity.
 * 
 * @assumptions: Needs to have a governing class to call its shoot methods, needs to be given a spawn
 * coordinate on the edge of the map.
 * @dependancies: Entity, Fowl, Firing, EntityManager
 * @example: Rooster(entityManager, new Point2D(0,0))
 * @author seanhudson
 */
public class Rooster extends Fowl{
	
	/**
	 * Creates the Rooster instance.
	 * 
	 * @param entityManager Manages the rooster's interaction with other Entity's.
	 * @param coordinate Edge spawn point for the rooster.
	 * @return nothing
	 */
	Rooster(EntityManager entityManager, Point2D coordinate) {
		super(entityManager, coordinate, "rooster", new Point2D(30, 30));
		setMoveSpeed(30);
		addDamagedByType("fork");
		setLives(1);
		setPoints(20);
		setFireRate(7000);
	}

	/**
	 * Handles actions for when the rooster collides with another Entity.
	 * 
	 * @return nothing
	 */
	public void didCollide() {
		setLives(getLives() - 1);
		checkForDeletion();	
	}
	
	/**
	 * Fires regular shot of a BirdPoop.
	 * 
	 * @param root Group node for the scene graph.
	 * @return nothing
	 */
	public void shoot(Group root) {
		getFiringDelegate().shoot(root, entityManager, "bird_Poop");
	}
	
	/**
	 * Updates the rooster's location
	 * 
	 * @assumptions This method is supposed to be called in the gameloop;
	 * @param elapsedTime FRAME_DELAY
	 * @return nothing
	 */
	public void move(double elapsedTime) {
		updateCoordinate(coordinate.getX() + getMovementVector().getX() * getMoveSpeed() * elapsedTime, coordinate.getY() + getMovementVector().getY() * getMoveSpeed() * elapsedTime);
		if(bounds != null) {
			bounce();
		}
	}
}
