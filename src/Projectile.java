import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

interface Collidable {
	void checkCollision(Entity Collidable);
}

abstract public class Projectile extends Entity implements Collidable{

	Projectile(Entity shooterNode, Point2D spawn, EntityManager entityManager, String type) {
		super(entityManager);
		setName(type);
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(getName() + ".png"), 100, 50, true, true);
		node = new ImageView(image);
		updateCoordinate(spawn.getX(), spawn.getY());
		entityManager.addProjectile(this);
	}
	
//	private ImageView bulletNode;
//	private double moveSpeed;
	

	public void checkCollision(Entity collider) {
		if(collider instanceof Damaged && ((Damaged) collider).getDamagedByTypes().contains(getName()) ) {
			setDelete(true);
			((Damaged) collider).didCollide();
		}
	}
	
	public void move(double elapsedTime) {
		updateCoordinate(coordinate.getX(), coordinate.getY() + getMoveSpeed() * elapsedTime);
	}
	
	abstract void setMoveSpeed(double speed);
		
	abstract double getMoveSpeed();
}
