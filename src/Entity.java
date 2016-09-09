import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;

abstract public class Entity{
	protected Point2D coordinate; //top left of center
	protected Node node;
	protected EntityManager entityManager;
	protected String name;
	protected Boolean delete = false;
	
	Entity(EntityManager entityManager) {
		this.entityManager = entityManager;
		entityManager.addEntity(this);
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
	
	public Point2D adjustCoordinatesToJustOffEdge(Point2D coordinate) {
		double x = coordinate.getX();
		double y = coordinate.getY();
		if(coordinate.getX() == 0) {
			x -= node.getBoundsInLocal().getWidth();
		}
		if(coordinate.getY() == 0) {
			y -= node.getBoundsInLocal().getHeight();
		}
		return new Point2D(x, y);
	}
	
	public Point2D centerReal() {
		Point2D centerRealCoordinate = new Point2D(coordinate.getX() + (node.getBoundsInLocal().getWidth() / 2), coordinate.getY() - (node.getBoundsInLocal().getHeight() / 2));
		return centerRealCoordinate;
	}
	
	public void setPlayerNode(Group root) {
		root.getChildren().add(node);
	}
	
	public void delete(Group root) {
		root.getChildren().remove(node);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Boolean getDelete() {
		return delete;
	}
	
	public void setDelete(Boolean delete) {
		this.delete = delete;
	}
	
	abstract void move(double elapsedTime);

	
}
