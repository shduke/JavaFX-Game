import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

abstract public class Fowl extends Entity{
	private ImageView fowlNode;
	private Firing firingDelegate;
	private Point2D movementVector;
	protected Bounds bounds;
	
	Fowl(EntityManager entityManager, Point2D coordinate) {
		super(entityManager);
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(getFowlType() + ".png"), 30, 30, false, true);
		node = new ImageView(image);
		fowlNode = (ImageView)node;
		Point2D adjustedPoint = adjustCoordinatesToJustOffEdge(coordinate);
		updateCoordinate(adjustedPoint.getX(), adjustedPoint.getY());
		//updateCoordinate(coordinate.getX(), coordinate.getY()); //delete this
		firingDelegate = new Firing(this);
		setMovementVector(new Point2D(0, 0));
		entityManager.addEntity(this);

	}
	
	
	public void bounce() {
		Bounds currentRectPosition = node.localToScene(node.getBoundsInLocal());
		//System.out.println("Bounds " + bounds);
		//System.out.println("Rect " + currentRectPosition);
		//System.out.println(currentRectPosition.getMinX() + " " + currentRectPosition.getMaxX() + " " + currentRectPosition.getMinY() + " " + currentRectPosition.getMaxY());
		//System.out.println("Vector " + movementVector + "  position" + coordinate);
		if((currentRectPosition.getMinX() <= bounds.getMinX() && movementVector.getX() < 0) || (currentRectPosition.getMaxX() >= bounds.getMaxX() && movementVector.getX() > 0)) {
			setMovementVector(new Point2D(movementVector.getX() * -1, movementVector.getY()));
		}
		if((currentRectPosition.getMinY() <= bounds.getMinY() && movementVector.getY() < 0) || (currentRectPosition.getMaxY() >= bounds.getMaxY() && movementVector.getY() > 0)) {
			setMovementVector(new Point2D(movementVector.getX(), movementVector.getY() * -1));
		}
	}
	
	
	public void shoot(Group root) {
		firingDelegate.shoot(root, entityManager);
	}
	
	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}
	
	public void setMovementVector(Point2D movementVector) {
		this.movementVector = movementVector;
	}
	
	public Point2D getMovementVector() {
		return movementVector;
	}
	
	abstract String getFowlType();
	
	abstract double getMoveSpeed();
}
