import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Boss extends Fowl {
	private Timeline specialAttackShootTimer;

	Boss(EntityManager entityManager, Point2D coordinate) {
		super(entityManager, coordinate, "boss", new Point2D(200, 200));
		setMoveSpeed(30);
		addDamagedByType("fork");
		setLives(20);
		setPoints(500);
		setFireRate(6000);
		//Point2D centerCoordinate = centerAdjusted(coordinate.getX(), coordinate.getY());
		Point2D fowlSpawnCoordinate = adjustCoordinatesToJustOffEdge(coordinate);
		updateCoordinate(fowlSpawnCoordinate.getX(), fowlSpawnCoordinate.getY());
	}

	@Override
	public void didCollide() {
		setLives(getLives() - 1);
		checkForDeletion();
	}
	
	public void startSpecialAttackShootTimer(Group root) {
		specialAttackShootTimer = new Timeline(new KeyFrame(Duration.millis(12000), e -> specialAttack(root)));
		specialAttackShootTimer.setCycleCount(MediaPlayer.INDEFINITE);
		specialAttackShootTimer.play();
		addTimeline(specialAttackShootTimer);
	}
	

	@Override
	public void shoot(Group root) {
		getFiringDelegate().shoot(root, entityManager, "bird_Poop");
	}
	
	public void specialAttack(Group root) {
		getFiringDelegate().shoot(root, entityManager, "egg");
	}


	@Override
	void move(double elapsedTime) {
		if(bounds != null) {
			bounce();
		}
		updateCoordinate(coordinate.getX() + getMovementVector().getX() * getMoveSpeed() * elapsedTime, coordinate.getY() + getMovementVector().getY() * getMoveSpeed() * elapsedTime);
	}		
	
	
}
