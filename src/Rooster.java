import javafx.geometry.Point2D;

public class Rooster extends Fowl{
	private static final String FOWL_TYPE = "rooster";
	private String bulletType;
	private double moveSpeed;
	
	Rooster(EntityManager entityManager, Point2D coordinate) {
		super(entityManager, coordinate);
		setBulletType("bird_poop");
		setMoveSpeed(30);
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

	String getFowlType() {
		return FOWL_TYPE;
	}

	double getMoveSpeed() {
		return moveSpeed;
	}
}
