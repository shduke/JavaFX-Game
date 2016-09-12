import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.Group;

public class Chicken extends Fowl{
	//private double moveSpeed;
	
	Chicken(EntityManager entityManager, Point2D coordinate) {
		super(entityManager, coordinate, "chicken", new Point2D(30, 30));
		setMoveSpeed(30);
		addDamagedByType("fork");
		setLives(2);
		setPoints(10);
		setFireRate(10000);
	}

	public void move(double elapsedTime) {
		if(bounds != null) {
			bounce();
		}
		updateCoordinate(coordinate.getX() + getMovementVector().getX() * getMoveSpeed() * elapsedTime, coordinate.getY() + getMovementVector().getY() * getMoveSpeed() * elapsedTime);
	}
	
	public void shoot(Group root) {
		getFiringDelegate().shoot(root, entityManager, "egg");
	}
	
	public void didCollide() {
		setLives(getLives() - 1);
		checkForDeletion();
	}

}
