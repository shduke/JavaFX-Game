import java.util.HashMap;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends Entity{

	private int lives;
	private int score;
	private int moveSpeed;
	private int direction;
	private ImageView playerNode; //not used
	private Firing firingDelegate;
	
	private HashMap<String, Image> animation;
	
	
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
		entityManager.addEntity(this);
	}
	
	//Is this ok to have? not used
	public ImageView getPlayerNode() {
		return playerNode;
	}
	
	public void shoot(Group root) {
		firingDelegate.shoot(root, entityManager);
	}
	
	private void loadAnimations(){
		animation = new HashMap<String, Image>();
		animation.put("LEFT", new Image(getClass().getClassLoader().getResourceAsStream("player_Left.jpeg"), 100, 100, false, true));
		animation.put("RIGHT", new Image(getClass().getClassLoader().getResourceAsStream("player_Right.jpg"), 100, 100, false, true));
		animation.put("CENTER", new Image(getClass().getClassLoader().getResourceAsStream("player_Central.png"), 100, 100, false, true));

	}
	
	//how to consolidate this?
	/*public void move(String direction) {
		if(this.direction != direction) {
			playerNode.setImage(animation.get(direction));
		}
		updateCoordinate(coordinate.getX() + moveSpeed, coordinate.getY() + moveSpeed);
	}*/
	
	public void move(double elapsedTime) {
		return;
	}
	
	public void moveRight() {
		if(direction != 1) {
			playerNode.setImage(animation.get("RIGHT"));
			direction = 1;
		}
		//myBouncer.setX(myBouncer.getX() + myBouncerDirection * BOUNCER_SPEED * elapsedTime);
		updateCoordinate(coordinate.getX() + moveSpeed, coordinate.getY());
	}
	public void moveLeft() {
		if(direction != -1) {
			playerNode.setImage(animation.get("LEFT"));
			direction = -1;
		}
		updateCoordinate(coordinate.getX() - moveSpeed, coordinate.getY());
	}
	public void moveUp() {
		if(direction != 2) {
			playerNode.setImage(animation.get("CENTER"));
			direction = 2;
		}
		updateCoordinate(coordinate.getX(), coordinate.getY() - moveSpeed);
	}
	public void moveDown() {
		if(direction != -2) {
			playerNode.setImage(animation.get("CENTER"));
			direction = -2;
		}
		updateCoordinate(coordinate.getX(), coordinate.getY() + moveSpeed);
	}

}
