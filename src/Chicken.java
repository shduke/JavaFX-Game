import javafx.geometry.Point2D;

public class Chicken extends Fowl{
	private static final String FOWL_TYPE = "chicken";
	private String bulletType;
	private double moveSpeed;
	
	Chicken(EntityManager entityManager, Point2D coordinate) {
		super(entityManager, coordinate);
		setBulletType("egg");
		setMoveSpeed(30);
	}

	public void move(double elapsedTime) {
		if(bounds != null) {
			bounce();
		}
		updateCoordinate(coordinate.getX() + getMovementVector().getX() * moveSpeed * elapsedTime, coordinate.getY() + getMovementVector().getY() * moveSpeed * elapsedTime);
	}
	
	public void setBulletType(String bulletType) {
		this.bulletType = bulletType;
	}
	
	public void setMoveSpeed(double moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	String getFowlType() {
		return FOWL_TYPE;
	}

	double getMoveSpeed() {
		return moveSpeed;
	}
}
