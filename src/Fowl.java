import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Fowl extends Entity{
	private ImageView fowlNode;
	private Firing firingDelegate;
	
	Fowl(EntityManager entityManager, String fowlType, Point2D coordinate) {
		super(entityManager);
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(fowlType + ".png"), 100, 100, false, true);
		node = new ImageView(image);
		fowlNode = (ImageView)node;
		Point2D centerPoint = centerAdjusted(coordinate.getX(), coordinate.getY());
		updateCoordinate(centerPoint.getX(), centerPoint.getY());
		firingDelegate = new Firing(this);
		entityManager.addEntity(this);

	}

	public void move(double elapsedTime) {
		
	}
	
	public void shoot(Group root) {
		firingDelegate.shoot(root, entityManager);
	}
}
