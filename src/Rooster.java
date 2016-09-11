import javafx.geometry.Point2D;

public class Rooster extends Fowl{
	private String bulletType;
	private double moveSpeed;
	
	Rooster(EntityManager entityManager, Point2D coordinate) {
		super(entityManager, coordinate, "rooster");
		setBulletType("bird_poop");
		setMoveSpeed(30);
		addDamagedByType("fork");
		setLives(2);
		setPoints(20);
	}

	public void move(double elapsedTime) {
		//System.out.println(coordinate + "   " + getMovementVector());
		updateCoordinate(coordinate.getX() + getMovementVector().getX() * moveSpeed * elapsedTime, coordinate.getY() + getMovementVector().getY() * moveSpeed * elapsedTime);
		if(bounds != null) {
			bounce();
		}
	}
	
	public void setBulletType(String bulletType) {
		this.bulletType = bulletType;
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
