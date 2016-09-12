import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;

/**
 * Abstract class that handles methods and common fields for all node root objects.
 * 
 * @assumptions: Subclasses must implement the abstract method.
 * @dependancies: EntityManager, (Subclasses to implement the abstract methods)
 * @example: Rooster(entityManager, new Point2D(0,0)); Rooster.updateCoordinate(10, 10)
 * @author seanhudson
 */
abstract public class Entity{
	protected Point2D coordinate; //top left of center
	protected Node node;
	protected EntityManager entityManager;
	protected String name;
	protected Boolean delete = false;
	
	/**
	 * Creates the Entity subclass instance.
	 * 
	 * @param entityManager Manages the entity's interaction with other Entity's.
	 * @return nothing
	 */
	Entity(EntityManager entityManager) {
		this.entityManager = entityManager;
		entityManager.addEntity(this);
	}
	
	/**
	 * Set the node where the Entity is going to be moved to.
	 * 
	 * @param x X-Coordinate of future point.
	 * @param y Y-Coordinate of future point.
	 * @return nothing
	 */
	public void updateCoordinate(double x, double y) {
		coordinate = new Point2D(x, y);
	}
	
	/**
	 * Moves the entity to its new location
	 * 
	 * @assumptions This is going to be called in the gameloop since that is where all movement is.
	 * @return nothing
	 */
	public void updatePositionInFrame() {
		node.setLayoutX(coordinate.getX());
        node.setLayoutY(coordinate.getY());
	}
	
	/**
	 * Adjusts the coordinate of the entity such that it is centered on the inputed coordinates.
	 * 
	 * @return The adjusted coordinates such that the Entity is centered.
	 */
	public Point2D centerAdjusted(double x, double y) {
		Point2D centerAdjustedCoordinate = new Point2D(x - (node.getBoundsInLocal().getWidth() / 2), y - (node.getBoundsInLocal().getHeight() / 2));
		return centerAdjustedCoordinate;
	}
	
	/**
	 * Adjusts the coordinate of the entity such that it is just off the edge of the inputed coordinates.
	 * 
	 * @assumptions at least one of the coordinates needs to be on the upper three edges of the screen.
	 * @param coordinate The desired location for the entity to be touching.
	 * @return The adjusted coordinates such that the entity is just off the screen, but still touching the specified point.
	 */
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
	
	/**
	 * Reverses the centerAdjusted() process to get the point at the center of the entity.
	 * 
	 * @return Center of the entity.
	 */
	public Point2D centerReal() {
		Point2D centerRealCoordinate = new Point2D(coordinate.getX() + (node.getBoundsInLocal().getWidth() / 2), coordinate.getY() + (node.getBoundsInLocal().getHeight() / 2));
		return centerRealCoordinate;
	}
	
	/**
	 * Adds the entity to the node scene graph.
	 * 
	 * @param root Group node for the scene graph.
	 * @return nothing
	 */
	public void setPlayerNode(Group root) {
		root.getChildren().add(node);
	}
	
	/**
	 * Removes the entity from the node scene graph.
	 * 
	 * @param root Group node for the scene graph.
	 */
	public void delete(Group root) {
		root.getChildren().remove(node);
	}
	
	/**
	 * Sets the entity type id.
	 * 
	 * @param name Entity type id from subclasses.
	 * @return nothing
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the entity for deletion.
	 * 
	 * @param name Should the entity be scheduled for deletion.
	 * @return nothing
	 */
	public void setDelete(Boolean delete) {
		this.delete = delete;
	}
	
	/**
	 * Gets the entity's type id.
	 * 
	 * @return name Entity type id from subclasses.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets if the entity should be deleted.
	 * 
	 * @return name Should the entity be deleted value.
	 */
	public Boolean getDelete() {
		return delete;
	}
	
	/**
	 * Updates the entity's location
	 * 
	 * @assumptions Subclasses are suppose to customize their implementation of this. This method is supposed to be called in the gameloop.
	 * @param elapsedTime FRAME_DELAY
	 * @return nothing
	 */
	abstract void move(double elapsedTime);

	
}
