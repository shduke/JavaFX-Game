import javafx.geometry.Point2D;
import javafx.scene.Group;

public class Rooster extends Fowl{
	private String bulletType;
	private double moveSpeed;
	
	Rooster(EntityManager entityManager, Point2D coordinate) {
		super(entityManager, coordinate, "rooster");
		setMoveSpeed(30);
		addDamagedByType("fork");
		setLives(2);
		setPoints(20);
		setFireRate(4000);
	}

	public void move(double elapsedTime) {
		//System.out.println(coordinate + "   " + getMovementVector());
		updateCoordinate(coordinate.getX() + getMovementVector().getX() * moveSpeed * elapsedTime, coordinate.getY() + getMovementVector().getY() * moveSpeed * elapsedTime);
		if(bounds != null) {
			bounce();
		}
	}
	
	public void shoot(Group root) {
		getFiringDelegate().shoot(root, entityManager, "bird_poop");
	}
	
	public void setMoveSpeed(double moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	double getMoveSpeed() {
		return moveSpeed;
	}

	public void didCollide() {
		setLives(getLives() - 1);
		checkForDeletion();	
	}
}
