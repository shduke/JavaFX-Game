import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class that allows for customization of the Fork Projectile
 * 
 * @assumptions: Needs to be created on the screen, needs to be shot from a Firing delegate
 * @dependencies: Entity, Projectile, EntityManager, Firing
 * @example: Fork(new Point2D(0,0), entityManager)
 * @author seanhudson
 */
public class Fork extends Projectile{
	private double moveSpeed;
	
	/**
	 * Creates the fork instance.
	 * 
	 * @param spawn Where the fork is created.
	 * @param entityManager Manages the fork's interaction with other Entity's.
	 * @return nothing
	 */
	Fork(Point2D spawn, EntityManager entityManager) {
		super(entityManager, "fork");
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(getName() + ".png"), 50, 50, true, true);
		node = new ImageView(image);
		updateCoordinate(spawn.getX(), spawn.getY());
		setMoveSpeed(-70);
	}
	
	/**
	 * Sets the fork's move speed.
	 * 
	 * @param speed How fast the fork is traveling.
	 * @return nothing
	 */
	public void setMoveSpeed(double speed) {
		moveSpeed = speed;
	}

	/**
	 * Gets the fork's move speed.
	 * 
	 * @return Speed of how fast the fork is traveling.
	 */
	@Override
	public double getMoveSpeed() {
		return moveSpeed;
	}
	
}
