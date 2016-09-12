import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Class that allows for the customization of the Boss Entity.
 * 
 * @assumptions: Needs to have a governing class to call its shoot methods, needs to be given a spawn
 * coordinate on the edge of the map.
 * @dependancies: Entity, Fowl, Firing, EntityManager
 * @example: Boss(entityManager, new Point2D(0,0))
 * @author seanhudson
 */
public class Boss extends Fowl {
	private Timeline specialAttackShootTimer;

	/**
	 * Creates the Boss instance.
	 * 
	 * @param entityManager Manages the boss's interaction with other Entity's.
	 * @param coordinate Edge spawn point for the boss.
	 * @return nothing
	 */
	Boss(EntityManager entityManager, Point2D coordinate) {
		super(entityManager, coordinate, "boss", new Point2D(200, 200));
		setMoveSpeed(30);
		addDamagedByType("fork");
		setLives(20);
		setPoints(500);
		setFireRate(5000);
		//Point2D centerCoordinate = centerAdjusted(coordinate.getX(), coordinate.getY());
		Point2D fowlSpawnCoordinate = adjustCoordinatesToJustOffEdge(coordinate);
		updateCoordinate(fowlSpawnCoordinate.getX(), fowlSpawnCoordinate.getY());
	}

	
	/**
	 * Handles actions for when the boss collides with another Entity.
	 * 
	 * @return nothing
	 */
	@Override
	public void didCollide() {
		setLives(getLives() - 1);
		checkForDeletion();
	}
	
	/**
	 * Starts the timer for when the boss uses his special attack.
	 * 
	 * @param root Group node for the scene graph.
	 * @return nothing
	 */
	public void startSpecialAttackShootTimer(Group root) {
		specialAttackShootTimer = new Timeline(new KeyFrame(Duration.millis(10000), e -> specialAttack(root)));
		specialAttackShootTimer.setCycleCount(MediaPlayer.INDEFINITE);
		specialAttackShootTimer.play();
		addTimeline(specialAttackShootTimer);
	}
	
	/**
	 * Fires regular shot of BirdPoop.
	 * 
	 * @param root Group node for the scene graph.
	 * @return nothing
	 */
	@Override
	public void shoot(Group root) {
		getFiringDelegate().shoot(root, entityManager, "bird_Poop");
	}
	
	/**
	 * Fires special shot of Egg's in a circle.
	 * 
	 * @param root Group node for the scene graph.
	 * @return nothing
	 */
	public void specialAttack(Group root) {
		for(int i = 0; i < 360; i += 45) {
			getFiringDelegate().shoot(root, entityManager, "egg", i);
		}
	}

	/**
	 * Updates the boss's location
	 * 
	 * @assumptions This method is supposed to be called in the gameloop;
	 * @param elapsedTime FRAME_DELAY
	 * @return nothing
	 */
	@Override
	void move(double elapsedTime) {
		if(bounds != null) {
			bounce();
		}
		updateCoordinate(coordinate.getX() + getMovementVector().getX() * getMoveSpeed() * elapsedTime, coordinate.getY() + getMovementVector().getY() * getMoveSpeed() * elapsedTime);
	}		
	
	
}
