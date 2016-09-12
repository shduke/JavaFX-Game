import javafx.geometry.Point2D;
import javafx.scene.Group;

public class Rooster extends Fowl{
	
	Rooster(EntityManager entityManager, Point2D coordinate) {
		super(entityManager, coordinate, "rooster", new Point2D(30, 30));
		setMoveSpeed(30);
		addDamagedByType("fork");
		setLives(1);
		setPoints(20);
		setFireRate(7000);
	}

	public void move(double elapsedTime) {
		//System.out.println(coordinate + "   " + getMovementVector());
		updateCoordinate(coordinate.getX() + getMovementVector().getX() * getMoveSpeed() * elapsedTime, coordinate.getY() + getMovementVector().getY() * getMoveSpeed() * elapsedTime);
		if(bounds != null) {
			bounce();
		}
	}
	
	public void shoot(Group root) {
		getFiringDelegate().shoot(root, entityManager, "bird_Poop");
	}

	public void didCollide() {
		setLives(getLives() - 1);
		checkForDeletion();	
	}
}
