import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BirdPoop extends Projectile{
	private ImageView birdPoopNode;
	private double moveSpeed;
	

	BirdPoop(Entity shooterNode, Point2D spawn, EntityManager entityManager) {
		super(shooterNode, spawn, entityManager, "bird_poop");
		birdPoopNode = (ImageView)node;
		setName("bird_poop");
	}
	
	public void move(double elapsedTime) {
		updateCoordinate(coordinate.getX(), coordinate.getY() + moveSpeed * elapsedTime);
	}
	
	public void setMoveSpeed(double speed) {
		moveSpeed = speed;
	}

	@Override
	double getMoveSpeed() {
		return moveSpeed;
	}
}