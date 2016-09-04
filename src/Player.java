import java.util.HashMap;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends Entity{

	private int lives;
	private int score;
	private int moveSpeed;
	private String Direction;
	private ImageView playerNode; //not used
	private Firing firingDelegate;
	
	private HashMap<String, Image> animation;
	
	
	Player(int x, int y) {
		lives = 3;
		score = 0;
		moveSpeed = 5;
		loadAnimations();
		node = new ImageView(animation.get("CENTER"));
		playerNode = (ImageView)node;
		Point2D centerPoint = centerAdjusted(x, y);
		updateCoordinate(centerPoint.getX(), centerPoint.getY());
		firingDelegate = new Firing(this);
	}
	
	//Is this ok to have? not used
	public ImageView getPlayerNode() {
		return playerNode;
	}
	
	public Bullet shoot(Group root) {
		return firingDelegate.shoot(root);
	}
	
	private void loadAnimations(){
		animation = new HashMap<String, Image>();
		animation.put("LEFT", new Image(getClass().getClassLoader().getResourceAsStream("player_Left.jpeg"), 100, 100, false, true));
		animation.put("RIGHT", new Image(getClass().getClassLoader().getResourceAsStream("player_Right.jpg"), 100, 100, false, true));
		animation.put("CENTER", new Image(getClass().getClassLoader().getResourceAsStream("player_Central.png"), 100, 100, false, true));

	}
	
	//how to consolidate this?
	/*public void move(String direction) {
		if(Direction != direction) {
			playerNode.setImage(animation.get(direction));
		}
		updateCoordinate(coordinate.getX() + moveSpeed, coordinate.getY() + moveSpeed);
	}*/
	
	public void moveRight() {
		if(Direction != "RIGHT") {
			playerNode.setImage(animation.get("RIGHT"));
		}
		updateCoordinate(coordinate.getX() + moveSpeed, coordinate.getY());
	}
	public void moveLeft() {
		if(Direction != "LEFT") {
			playerNode.setImage(animation.get("LEFT"));
		}
		updateCoordinate(coordinate.getX() - moveSpeed, coordinate.getY());
	}
	public void moveUp() {
		if(Direction != "CENTER") {
			playerNode.setImage(animation.get("CENTER"));
		}
		updateCoordinate(coordinate.getX(), coordinate.getY() - moveSpeed);
	}
	public void moveDown() {
		if(Direction != "CENTER") {
			playerNode.setImage(animation.get("CENTER"));
		}
		updateCoordinate(coordinate.getX(), coordinate.getY() + moveSpeed);
	}

}
