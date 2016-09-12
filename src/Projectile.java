import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Interface that Projectiles need to implement to handle collision detection.
 * 
 * @assumptions: checkCollision will have to have a way of signaling to delete the Projectile.
 * @dependancies: Projectile
 * @example: collidable.checkCollision(rooster)
 * @author seanhudson
 */
interface Collidable {
	/**
	 * Checks to see if the Projectile has collided with an Entity.
	 * 
	 * @param Collidable Entity it may have collided with.
	 */
	void checkCollision(Entity Collidable);
}

/**
 * Class that allows for customization of Projectiles.
 * 
 * @assumptions: Needs to be created on the screen, needs to be shot from a Firing delegate
 * @dependencies: Entity, Projectile, EntityManager, Firing
 * @example: BirdPoop(new Point2D(0,0), entityManager)
 * @author seanhudson
 */
abstract public class Projectile extends Entity implements Collidable{
	private Point2D directionVector = new Point2D(0,1);
	
	/**
	 * Creates the Projectile instance.
	 * 
	 * @param entityManager Manages the Projectiles's interaction with other Entity's.
	 * @param type Type id of the Projectile.
	 * @return nothing
	 */
	Projectile(EntityManager entityManager, String type) {
		super(entityManager);
		setName(type);
		entityManager.addProjectile(this);
	}

	/**
	 * Checks to see if the Projectile has collided with an Entity.
	 * 
	 * @param Collidable Entity it may have collided with.
	 * @return nothing
	 */
	public void checkCollision(Entity collider) {
		if(collider instanceof Damaged && ((Damaged) collider).getDamagedByTypes().contains(getName()) ) {
			setDelete(true);
			((Damaged) collider).didCollide();
		}
	}
	/**
	 * Checks to make sure the projectile is still on the screen.
	 * 
	 * @param bounds Bounding area of the screen.
	 * @return nothing
	 */
	public void checkInBounds(Bounds bounds) {
		if(bounds.contains(coordinate) == false) {
			setDelete(true);
		}
	}
	
	/**
	 * Updates the Projectile's location
	 * 
	 * @assumptions This method is supposed to be called in the gameloop;
	 * @param elapsedTime FRAME_DELAY
	 * @return nothing
	 */
	public void move(double elapsedTime) {
		Point2D test = getDirectionVector();
		updateCoordinate(coordinate.getX() + getMoveSpeed() * getDirectionVector().getX() * elapsedTime , coordinate.getY() + getMoveSpeed() * getDirectionVector().getY() * elapsedTime);
	}
	
	/**
	 * Calculates the vector to use for movement from an angle.
	 * 
	 * @param degrees Angle of the shot.
	 * @return nothing
	 */
	public Point2D calcDirectionVector(int degrees) {
		return new Point2D(Math.cos(degrees), Math.sin(degrees));
	}
	
	/**
	 * Sets the direction vector.
	 * 
	 * @param directionVector Projectile's movement direction
	 */
	public void setDirectionVector(Point2D directionVector) {
		this.directionVector = directionVector;
	}
	
	/**
	 * Gets the direction vector.
	 * 
	 * @return directionVector Projectile's movement direction
	 */
	public Point2D getDirectionVector() {
		return directionVector;
	}
	
	/**
	 * Sets the projectiles's move speed.
	 * 
	 * @assumptions: Subclasses of Projectiles must implement this method.
	 * @param Speed of how fast the projectile is traveling.
	 * @return nothing
	 */
	abstract void setMoveSpeed(double speed);
		
	/**
	 * Gets the projectiles's move speed.
	 * 
	 * @assumptions: Subclasses of Projectiles must implement this method.
	 * @return Speed of how fast the projectile is traveling.
	 */
	abstract double getMoveSpeed();
}
