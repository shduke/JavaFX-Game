import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.Group;

public class Chicken extends Fowl{
	private String bulletType;
	private double moveSpeed;
	
	Chicken(EntityManager entityManager, Point2D coordinate) {
		super(entityManager, coordinate, "chicken");
		setMoveSpeed(30);
		addDamagedByType("fork");
		setLives(1);
		setPoints(10);
		setFireRate(10000);
	}

	public void move(double elapsedTime) {
		if(bounds != null) {
			bounce();
		}
		updateCoordinate(coordinate.getX() + getMovementVector().getX() * moveSpeed * elapsedTime, coordinate.getY() + getMovementVector().getY() * moveSpeed * elapsedTime);
	}
	
	public void shoot(Group root) {
		getFiringDelegate().shoot(root, entityManager, "egg");
	}
	
	public void didCollide() {
		setLives(getLives() - 1);
		checkForDeletion();
	}
	
	public void setMoveSpeed(double moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
	

	double getMoveSpeed() {
		return moveSpeed;
	}
}
