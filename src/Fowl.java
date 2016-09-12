import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

abstract public class Fowl extends Entity implements Damaged{
	private Firing firingDelegate;
	private Point2D movementVector;
	protected Bounds bounds;
	private ArrayList<String> damagedByType = new ArrayList<String>();
	private int lives;
	private int points;
	private ArrayList<Timeline> timelines = new ArrayList<Timeline>();
	//private Timeline shootTimer;
	private int fireRate;
	private int moveSpeed;
	
	Fowl(EntityManager entityManager, Point2D coordinate, String type, Point2D imageSize) {
		super(entityManager);
		setName(type);
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(getName() + ".png"), imageSize.getX(), imageSize.getY(), false, true);
		node = new ImageView(image);
		Point2D adjustedPoint = adjustCoordinatesToJustOffEdge(coordinate);
		updateCoordinate(adjustedPoint.getX(), adjustedPoint.getY());
		//updateCoordinate(coordinate.getX(), coordinate.getY()); //delete this
		firingDelegate = new Firing(this);
		setMovementVector(new Point2D(0, 0));
	}
	
	
	public void bounce() {
		Bounds currentRectPosition = node.localToScene(node.getBoundsInLocal());
		if((currentRectPosition.getMinX() <= bounds.getMinX() && movementVector.getX() < 0) || (currentRectPosition.getMaxX() >= bounds.getMaxX() && movementVector.getX() > 0)) {
			setMovementVector(new Point2D(movementVector.getX() * -1, movementVector.getY()));
		}
		if((currentRectPosition.getMinY() <= bounds.getMinY() && movementVector.getY() < 0) || (currentRectPosition.getMaxY() >= bounds.getMaxY() && movementVector.getY() > 0)) {
			setMovementVector(new Point2D(movementVector.getX(), movementVector.getY() * -1));
		}
	}
	
	public void startShootTimer(Group root) {
		Timeline shootTimer = new Timeline(new KeyFrame(Duration.millis(getFireRate()), e -> shoot(root)));
		shootTimer.setCycleCount(MediaPlayer.INDEFINITE);
		shootTimer.play();
		timelines.add(shootTimer);	
	}
	
	abstract public void shoot(Group root);
	
	
	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}
	
	public void setMovementVector(Point2D movementVector) {
		this.movementVector = movementVector;
	}
	
	public Point2D getMovementVector() {
		return movementVector;
	}
	
	public void addDamagedByType(String name) {
		damagedByType.add(name);
	}
	
	public void clearDamagedByType() {
		damagedByType.clear();
	}
	
	public void checkForDeletion() {
		if(lives <= 0) {
			setDelete(true);
			stopTimelines();
			entityManager.setAdditionalPoints(entityManager.getAdditionalPoints() + getPoints());
		}
	}
	
	public void stopTimelines() {
		for(Timeline timeline : timelines) {
			timeline.stop();
		}
		timelines.clear();
	}
	
	public void addTimeline(Timeline timeline){
		timelines.add(timeline);
	}
	
	public ArrayList<String> getDamagedByTypes() {
		return damagedByType;
	}
	
	
	public int getLives() {
		return lives;
	}
	
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public int getPoints() {
		return points;
	}
	
	public Firing getFiringDelegate(){
		return firingDelegate;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	}
	
	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
		
	public int getFireRate() {
		return fireRate;
	}
	
	public double getMoveSpeed() {
		return moveSpeed;
	}
	
	
}
