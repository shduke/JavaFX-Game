import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;

public class Entity {
	protected Point2D coordinate; //top left of center
	protected Node node;
	
	
	public void updateCoordinate(double x, double y) {
		coordinate = new Point2D(x, y);
		//Point2D centerCoordinate = center();
		node.setLayoutX(x);
        node.setLayoutY(y);
		//coordinate = center();
		//this.coordinate = coordinate;
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
	
}
