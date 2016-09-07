import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;

interface movement {
	void move(double elapsedTime);
}

abstract public class Entity implements movement{
	protected Point2D coordinate; //top left of center
	protected Node node;
	protected EntityManager entityManager;
	
	Entity(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void updateCoordinate(double x, double y) {
		coordinate = new Point2D(x, y);
		//Point2D centerCoordinate = center();
		//node.setLayoutX(x);
        //node.setLayoutY(y);
		//coordinate = center();
		//this.coordinate = coordinate;
	}
	
	public void updatePositionInFrame() {
		node.setLayoutX(coordinate.getX());
        node.setLayoutY(coordinate.getY());
	}
	
	//where the bottom right coordinate of the image has to be for the image to be centered on the specified coordinate
	public Point2D centerAdjusted(double x, double y) {
		Point2D centerAdjustedCoordinate = new Point2D(x - (node.getBoundsInLocal().getWidth() / 2), y - (node.getBoundsInLocal().getHeight() / 2));
		return centerAdjustedCoordinate;
	}
	
	public Point2D centerReal() {
		Point2D centerRealCoordinate = new Point2D(coordinate.getX() + (node.getBoundsInLocal().getWidth() / 2), coordinate.getY() - (node.getBoundsInLocal().getHeight() / 2));
		return centerRealCoordinate;
	}
	
	public void setPlayerNode(Group root) {
		root.getChildren().add(node);
	}
	
	//how to use polymorphism // interfaces
	/*public void move(Double elapsedTime) {
		return;
	}*/
	
}
