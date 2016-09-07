import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BirdPoop extends Projectile{
	private static final String PROJECTILE_TYPE = "bird_poop";
	private ImageView birdPoopNode;
	private double moveSpeed;
	

	BirdPoop(Entity shooterNode, Point2D spawn, EntityManager entityManager) {
		super(shooterNode, spawn, entityManager);
		birdPoopNode = (ImageView)node;
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