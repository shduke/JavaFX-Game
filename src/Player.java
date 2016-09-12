import java.util.ArrayList;
import java.util.HashMap;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class that allows for the customization of the Player Entity.
 * 
 * @assumptions: Needs to have a governing class to call its shoot methods.
 * @dependancies: Entity, Firing, EntityManager
 * @example: Player(entityManager, new Point2D(0,0))
 * @author seanhudson
 */
public class Player extends Entity implements Damaged{

	private int lives;
	private int score;
	private int level;
	private int moveSpeed;
	private int direction;
	private ImageView playerNode;
	private Firing firingDelegate;
	private ArrayList<String> damagedByType = new ArrayList<String>();
	
	private HashMap<String, Image> animation;
	
	/**
	 * Creates the Player instance.
	 * 
	 * @param entityManager Manages the player's interaction with other Entity's.
	 * @param x X-Coordinate of the player's spawn point.
	 * @param y Y-Coordinate of the player's spawn point.
	 * @return nothing
	 */
	Player(double x, double y, EntityManager entityManager) {
		super(entityManager);
		lives = 3;
		score = 0;
		moveSpeed = 10;
		loadAnimations();
		node = new ImageView(animation.get("CENTER"));
		playerNode = (ImageView)node;
		Point2D centerPoint = centerAdjusted(x, y);
		updateCoordinate(centerPoint.getX(), centerPoint.getY());
		firingDelegate = new Firing(this);
		addDamagedByType("egg");
		addDamagedByType("bird_Poop");
		setName("player");
	}
	
	/**
	 * Fires regular shot of a Fork.
	 * 
	 * @param root Group node for the scene graph.
	 * @return nothing
	 */
	public void shoot(Group root) {
		firingDelegate.shoot(root, entityManager, "fork");
	}
	
	/**
	 * Loads all of the movement animation images.
	 *
	 * @return nothing
	 */
	private void loadAnimations(){
		animation = new HashMap<String, Image>();
		animation.put("LEFT", new Image(getClass().getClassLoader().getResourceAsStream("player_Left.png"), 100, 100, false, true));
		animation.put("RIGHT", new Image(getClass().getClassLoader().getResourceAsStream("player_Right.png"), 100, 100, false, true));
		animation.put("CENTER", new Image(getClass().getClassLoader().getResourceAsStream("player_Central.png"), 100, 100, false, true));

	}
	
	/**
	 * Adds a type id by which the player can take damage from.
	 * 
	 * @assumptions Can only handle Projectile type id's.
	 * @param name Type id of a Projectile that can damage it.
	 * @return nothing
	 */
	public void addDamagedByType(String name) {
		damagedByType.add(name);
	}
	
	/**
	 * Clears the player's list of type id's of Projectiles that can damage it.
	 * 
	 * @assumptions Relies on an external class to set its delete property.
	 * @return nothing
	 */
	public void clearDamagedByType() {
		damagedByType.clear();
	}
	
	/**
	 * Handles actions for when the player collides with another Entity.
	 * 
	 * @return nothing
	 */
	public void didCollide() {
		setLives(getLives() - 1);
		if(lives <= 0) {
			setDelete(true);
		}
	}
	
	/**
	 * Gets a list all of the type id's of Projectiles that can damage it.
	 * 
	 * @return List of Projeciles type id's it can be damaged by.
	 */
	public ArrayList<String> getDamagedByTypes() {
		return damagedByType;
	}
	
	/**
	 * Updates the player's location
	 * 
	 * @assumptions This method is supposed to be called in the gameloop, the player primarily moves based on keyboard input as opposed to this function;
	 * @param elapsedTime FRAME_DELAY
	 * @return nothing
	 */
	public void move(double elapsedTime) {
		return;
	}
	
	/**
	 * Moves the player right.
	 * 
	 * @return nothing
	 */
	public void moveRight() {
		if(direction != 1) {
			playerNode.setImage(animation.get("RIGHT"));
			direction = 1;
		}
		updateCoordinate(Math.min(coordinate.getX() + moveSpeed, 600 - node.getBoundsInLocal().getWidth()), coordinate.getY());
	}
	
	/**
	 * Moves the player left.
	 * 
	 * @return nothing
	 */
	public void moveLeft() {
		if(direction != -1) {
			playerNode.setImage(animation.get("LEFT"));
			direction = -1;
		}
		updateCoordinate(Math.max(coordinate.getX() - moveSpeed, 0), coordinate.getY());
	}
	
	/**
	 * Moves the player up.
	 * 
	 * @return nothing
	 */
	public void moveUp() {
		if(direction != 2) {
			playerNode.setImage(animation.get("CENTER"));
			direction = 2;
		}
		updateCoordinate(coordinate.getX(), Math.max(coordinate.getY() - moveSpeed, 300));
	}
	
	/**
	 * Moves the player down.
	 * 
	 * @return nothing
	 */
	public void moveDown() {
		if(direction != -2) {
			playerNode.setImage(animation.get("CENTER"));
			direction = -2;
		}
		updateCoordinate(coordinate.getX(), Math.min(coordinate.getY() + moveSpeed, 600 - node.getBoundsInLocal().getHeight()));
	}
	
	/**
	 * Sets the player's score.
	 * 
	 * @param score Player's new score.
	 * @return nothing
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Sets the player's lives.
	 * 
	 * @param lives Player's current lives.
	 * @return nothing
	 */
	public void setLives(int newLives) {
		lives = newLives;
	}
	
	/**
	 * Sets the player's current level.
	 * 
	 * @param level Player's current level.
	 * @return nothing
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * Gets the player's score.
	 * 
	 * @return score Player's score.
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Gets the player's current level.
	 * 
	 * @return level Player's level.
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Gets the player's Lives.
	 * 
	 * @return lives Player's Lives.
	 */
	public int getLives() {
		return lives;
	}

}
