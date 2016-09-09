import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Fork extends Projectile{
	//private ImageView forkNode;
	private double moveSpeed;
	

	Fork(Entity shooterNode, Point2D spawn, EntityManager entityManager) {
		super(shooterNode, spawn, entityManager, "fork");
		//forkNode = (ImageView)node;
		setMoveSpeed(-50);
	}
	
	public void move(double elapsedTime) {
		updateCoordinate(coordinate.getX(), coordinate.getY() + moveSpeed * elapsedTime);
	}
	
	public void setMoveSpeed(double speed) {
		moveSpeed = speed;
	}

	@Override
	public double getMoveSpeed() {
		return moveSpeed;
	}
	
}
