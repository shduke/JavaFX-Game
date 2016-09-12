import javafx.geometry.Point2D;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class that allows for customization of the BirdPoop Projectile
 * 
 * @assumptions: Needs to be created on the screen, needs to be shot from a Firing delegate
 * @dependencies: Entity, Projectile, EntityManager, Firing
 * @example: BirdPoop(new Point2D(0,0), entityManager)
 * @author seanhudson
 */
public class BirdPoop extends Projectile{
	private double moveSpeed;
	
	/**
	 * Creates the BirdPoop instance.
	 * 
	 * @param spawn Where the birdPoop is created.
	 * @param entityManager Manages the birdPoop's interaction with other Entity's.
	 * @return nothing
	 */
	BirdPoop(Point2D spawn, EntityManager entityManager) {
		super(entityManager, "bird_Poop");
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(getName() + ".png"), 15, 15, true, true);
		node = new ImageView(image);
		updateCoordinate(spawn.getX(), spawn.getY());
		setMoveSpeed(30);
	}
	
	/**
	 * Sets the birdPoop's move speed.
	 * 
	 * @param speed How fast the birdPoop is traveling.
	 * @return nothing
	 */
	public void setMoveSpeed(double speed) {
		moveSpeed = speed;
	}

	/**
	 * Gets the birdPoop's move speed.
	 * 
	 * @return Speed of how fast the birdPoop is traveling.
	 */
	@Override
	double getMoveSpeed() {
		return moveSpeed;
	}
}