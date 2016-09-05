import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bullet extends Entity{
	private ImageView bulletNode;
	
	Bullet(Entity shooterNode, Point2D spawn, EntityManager entityManager) {
		super(entityManager);
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("fork.png"), 100, 50, true, true);
		node = new ImageView(image);
		bulletNode = (ImageView)node;
		updateCoordinate(spawn.getX(), spawn.getY());
		entityManager.addEntity(this);
	}
}
