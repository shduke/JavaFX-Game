import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

interface Collidable {
	void checkCollision(Entity Collidable);
}

abstract public class Projectile extends Entity implements Collidable{
	private Entity shooterNode;
	
	
	Projectile(Entity shooterNode, EntityManager entityManager, String type) {
		super(entityManager);
		setName(type);
		this.shooterNode = shooterNode;
		entityManager.addProjectile(this);
	}
	
//	private ImageView bulletNode;
//	private double moveSpeed;
	

	public void checkCollision(Entity collider) {
		if(collider instanceof Damaged && ((Damaged) collider).getDamagedByTypes().contains(getName()) ) {
			setDelete(true);
			((Damaged) collider).didCollide();
			//if(this instanceof Fork && collider instanceof Fowl)
		}
	}
	
	public void move(double elapsedTime) {
		updateCoordinate(coordinate.getX(), coordinate.getY() + getMoveSpeed() * elapsedTime);
	}
	
	abstract void setMoveSpeed(double speed);
		
	abstract double getMoveSpeed();
}
