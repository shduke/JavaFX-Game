import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Fork extends Projectile{
	private double moveSpeed;
	

	Fork(Point2D spawn, EntityManager entityManager) {
		super(entityManager, "fork");
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(getName() + ".png"), 50, 50, true, true);
		node = new ImageView(image);
		updateCoordinate(spawn.getX(), spawn.getY());
		setMoveSpeed(-70);
	}
	
	public void setMoveSpeed(double speed) {
		moveSpeed = speed;
	}

	@Override
	public double getMoveSpeed() {
		return moveSpeed;
	}
	
}
