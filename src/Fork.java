import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Fork extends Projectile{
	private static final String PROJECTILE_TYPE = "fork";
	private ImageView forkNode;
	private double moveSpeed;
	

	Fork(Entity shooterNode, Point2D spawn, EntityManager entityManager) {
		super(shooterNode, spawn, entityManager);
		forkNode = (ImageView)node;
		setMoveSpeed(-50);
	}
	
	public void move(double elapsedTime) {
		updateCoordinate(coordinate.getX(), coordinate.getY() + moveSpeed * elapsedTime);
	}
	
	public void setMoveSpeed(double speed) {
		moveSpeed = speed;
	}

	
	String getBulletType() {
		return PROJECTILE_TYPE;
	}

	@Override
	double getMoveSpeed() {
		return moveSpeed;
	}
}
