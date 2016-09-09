import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract public class Projectile extends Entity {

	Projectile(Entity shooterNode, Point2D spawn, EntityManager entityManager) {
		super(entityManager);
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(getBulletType() + ".png"), 100, 50, true, true);
		node = new ImageView(image);
		updateCoordinate(spawn.getX(), spawn.getY());
		entityManager.addEntity(this);
		entityManager.addProjectile(this);
	}
	
//	private ImageView bulletNode;
//	private double moveSpeed;
	

	
	
	public void move(double elapsedTime) {
		updateCoordinate(coordinate.getX(), coordinate.getY() + getMoveSpeed() * elapsedTime);
	}
	
	abstract void setMoveSpeed(double speed);
	
	abstract String getBulletType();
	
	abstract double getMoveSpeed();
}
