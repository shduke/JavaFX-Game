import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;

public class Entity {
	protected Point2D coordinate;
	protected Node node;
	
	public void updateCoordinate(int x, int y) {
		coordinate = new Point2D(x - node.getBoundsInLocal().getWidth() / 2, y - node.getBoundsInLocal().getHeight() / 2);
		node.setLayoutX(coordinate.getX());
        node.setLayoutY(coordinate.getY());
	}
	
	public void setPlayerNode(Group root) {
		root.getChildren().add(node);
	}
}
