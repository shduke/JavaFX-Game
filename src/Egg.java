import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Egg extends Projectile{
	private double moveSpeed;
	

	Egg(Entity shooterNode, Point2D spawn, EntityManager entityManager) {
		super(shooterNode, entityManager, "egg");
		setName("egg");
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(getName() + ".png"), 20, 30, true, true);
		node = new ImageView(image);
		updateCoordinate(spawn.getX(), spawn.getY());
		setMoveSpeed(60);
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
