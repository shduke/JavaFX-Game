import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Abstract class that handles methods and common fields for the enemy subclasses of Fowl.
 * 
 * @assumptions: Subclasses must implement the abstract method.
 * @dependancies: Entity, Firing, EntityManager, (Subclasses to implement the abstract methods)
 * @example: Rooster(entityManager, new Point2D(0,0)); Rooster.startShootTimer(root)
 * @author seanhudson
 */
abstract public class Fowl extends Entity implements Damaged{
	private Firing firingDelegate;
	private Point2D movementVector;
	protected Bounds bounds;
	private ArrayList<String> damagedByType = new ArrayList<String>();
	private int lives;
	private int points;
	private ArrayList<Timeline> timelines = new ArrayList<Timeline>();
	private int fireRate;
	private int moveSpeed;
	
	/**
	 * Creates the Fowl subclass instance.
	 * 
	 * @param entityManager Manages the fowl's interaction with other Entity's.
	 * @param coordinate Edge spawn point for the fowl.
	 * @param type String type id for the subclass.
	 * @param imageSize Size of the imageView.
	 * @return nothing
	 */
	Fowl(EntityManager entityManager, Point2D coordinate, String type, Point2D imageSize) {
		super(entityManager);
		setName(type);
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(getName() + ".png"), imageSize.getX(), imageSize.getY(), false, true);
		node = new ImageView(image);
		Point2D adjustedPoint = adjustCoordinatesToJustOffEdge(coordinate);
		updateCoordinate(adjustedPoint.getX(), adjustedPoint.getY());
		//updateCoordinate(coordinate.getX(), coordinate.getY()); //delete this
		firingDelegate = new Firing(this);
		setMovementVector(new Point2D(0, 0));
	}
	
	/**
	 * Changes direction when the fowl collides with its boundary so it stays on the screen.
	 * 
	 * @return nothing
	 */
	public void bounce() {
		Bounds currentRectPosition = node.localToScene(node.getBoundsInLocal());
		if((currentRectPosition.getMinX() <= bounds.getMinX() && movementVector.getX() < 0) || (currentRectPosition.getMaxX() >= bounds.getMaxX() && movementVector.getX() > 0)) {
			setMovementVector(new Point2D(movementVector.getX() * -1, movementVector.getY()));
		}
		if((currentRectPosition.getMinY() <= bounds.getMinY() && movementVector.getY() < 0) || (currentRectPosition.getMaxY() >= bounds.getMaxY() && movementVector.getY() > 0)) {
			setMovementVector(new Point2D(movementVector.getX(), movementVector.getY() * -1));
		}
	}
	
	/**
	 * Starts the timer for when the fowl will shoot a Projectile.
	 * 
	 * @param root Group node for the scene graph.
	 * @return nothing
	 */
	public void startShootTimer(Group root) {
		Timeline shootTimer = new Timeline(new KeyFrame(Duration.millis(getFireRate()), e -> shoot(root)));
		shootTimer.setCycleCount(MediaPlayer.INDEFINITE);
		shootTimer.play();
		timelines.add(shootTimer);	
	}
	
	/**
	 * Fires regular shot.
	 * 
	 * @assumptions Subclass will customize their implementation of the shoot method.
	 * @param root Group node for the scene graph.
	 * @return nothing
	 */
	abstract public void shoot(Group root);
	
	
	/**
	 * Deletes the fowl if it has been scheduled to be deleted.
	 * 
	 * @assumptions Relies on an external class to set its delete property.
	 * @return nothing
	 */
	public void checkForDeletion() {
		if(lives <= 0) {
			setDelete(true);
			stopTimelines();
			entityManager.setAdditionalPoints(entityManager.getAdditionalPoints() + getPoints());
		}
	}
	
	/**
	 * Stops all of the Fowl's shooting timers for when it gets deleted.
	 * 
	 * @assumptions Fowl has been set to delete.
	 * @return nothing
	 */
	public void stopTimelines() {
		for(Timeline timeline : timelines) {
			timeline.stop();
		}
		timelines.clear();
	}
	
	/**
	 * Adds a timer to the fowl's timer list.
	 * 
	 * @assumptions List needs to be cleared when the Fowl is deleted
	 * @param timeline Timer to be added
	 * @return nothing
	 */
	public void addTimeline(Timeline timeline){
		timelines.add(timeline);
	}
	
	/**
	 * Adds a type id by which the fowl can take damage from.
	 * 
	 * @assumptions Can only handle Projectile type id's.
	 * @param name Type id of a Projectile that can damage it.
	 * @return nothing
	 */
	public void addDamagedByType(String name) {
		damagedByType.add(name);
	}
	
	/**
	 * Clears the Fowl's list of type id's of Projectiles that can damage it.
	 * 
	 * @assumptions Relies on an external class to set its delete property.
	 * @return nothing
	 */
	public void clearDamagedByType() {
		damagedByType.clear();
	}
	
	/**
	 * Sets the bounds with which the Fowl can be in.
	 * 
	 * @param bounds Allowable area for the Fowl.
	 * @return nothing
	 */
	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}
	
	/**
	 * Sets the normalized movement direction for the fowl.
	 * 
	 * @param movementVector Normalized movement direction.
	 * @return nothing
	 */
	public void setMovementVector(Point2D movementVector) {
		this.movementVector = movementVector;
	}
	
	/**
	 * Sets the lives for the fowl.
	 * 
	 * @param lives How much health it has.
	 * @return nothing
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	/**
	 * Sets the number of points the fowl is worth.
	 * 
	 * @param points How much the fowl is worth.
	 * @return nothing
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	
	/**
	 * Sets the fire rate for the fowl.
	 * 
	 * @param fireRate How fast the fowl can shoot.
	 * @return nothing
	 */
	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	}
	
	/**
	 * Sets the move speed for the fowl.
	 * 
	 * @param moveSpeed How fast the fowl can move.
	 * @return nothing
	 */
	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
	
	/**
	 * Gets the movement direction for the fowl.
	 * 
	 * @return Movement vector
	 */
	public Point2D getMovementVector() {
		return movementVector;
	}
	
	/**
	 * Gets a list all of the type id's of Projectiles that can damage it.
	 * 
	 * @return List of Projeciles type id's it can be damaged by.
	 */
	public ArrayList<String> getDamagedByTypes() {
		return damagedByType;
	}
	
	/**
	 * Gets the number of lives of the fowl.
	 * 
	 * @return How much health it has.
	 */
	public int getLives() {
		return lives;
	}
	
	/**
	 * Gets the point value for destroying the fowl.
	 * 
	 * @return Number of points the fowl is worth.
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Gets Firing delegate of the fowl.
	 * 
	 * @return Fowl's Firing delegate
	 */
	public Firing getFiringDelegate(){
		return firingDelegate;
	}
	
	/**
	 * Gets firing rate of the fowl.
	 * 
	 * @return How fast the fowl can shoot.
	 */
	public int getFireRate() {
		return fireRate;
	}
	
	/**
	 * Gets the move speed of the fowl.
	 * 
	 * @return How fast the fowl can move.
	 */
	public double getMoveSpeed() {
		return moveSpeed;
	}
	
	
}
