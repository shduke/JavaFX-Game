import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BirdPoop extends Projectile{
	private double moveSpeed;
	

	BirdPoop(Point2D spawn, EntityManager entityManager) {
		super(entityManager, "bird_Poop");
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(getName() + ".png"), 15, 15, true, true);
		node = new ImageView(image);
		updateCoordinate(spawn.getX(), spawn.getY());
		setMoveSpeed(30);
	}
	
	public void setMoveSpeed(double speed) {
		moveSpeed = speed;
	}

	@Override
	double getMoveSpeed() {
		return moveSpeed;
	}
}