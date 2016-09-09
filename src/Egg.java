import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Egg extends Projectile{
	private ImageView eggNode;
	private double moveSpeed;
	

	Egg(Entity shooterNode, Point2D spawn, EntityManager entityManager) {
		super(shooterNode, spawn, entityManager, "egg");
		eggNode = (ImageView)node;
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
