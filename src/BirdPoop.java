import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BirdPoop extends Projectile{
	private double moveSpeed;
	

	BirdPoop(Entity shooterNode, Point2D spawn, EntityManager entityManager) {
		super(shooterNode, entityManager, "bird_Poop");
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(getName() + ".png"), 15, 15, true, true);
		node = new ImageView(image);
		updateCoordinate(spawn.getX(), spawn.getY());
		setMoveSpeed(30);
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