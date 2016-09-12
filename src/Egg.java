import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class that allows for customization of the Egg Projectile
 * 
 * @assumptions: Needs to be created on the screen, needs to be shot from a Firing delegate
 * @dependencies: Entity, Projectile, EntityManager, Firing
 * @example: Egg(new Point2D(0,0), entityManager)
 * @author seanhudson
 */
public class Egg extends Projectile{
	private double moveSpeed;
	
	/**
	 * Creates the Egg instance.
	 * 
	 * @param spawn Where the egg is created.
	 * @param entityManager Manages the egg's interaction with other Entity's.
	 * @return nothing
	 */
	Egg(Point2D spawn, EntityManager entityManager) {
		super(entityManager, "egg");
		setName("egg");
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(getName() + ".png"), 20, 30, true, true);
		node = new ImageView(image);
		updateCoordinate(spawn.getX(), spawn.getY());
		setMoveSpeed(60);
	}
	
	/**
	 * Sets the egg's move speed.
	 * 
	 * @param speed How fast the egg is traveling.
	 * @return nothing
	 */
	public void setMoveSpeed(double speed) {
		moveSpeed = speed;
	}

	/**
	 * Gets the egg's move speed.
	 * 
	 * @return Speed of how fast the egg is traveling.
	 */
	@Override
	double getMoveSpeed() {
		return moveSpeed;
	}

}
