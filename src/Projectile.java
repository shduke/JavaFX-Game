import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

interface Collidable {
	void checkCollision(Entity Collidable);
}

abstract public class Projectile extends Entity implements Collidable{
	private Point2D directionVector = new Point2D(0,1);
	
	Projectile(EntityManager entityManager, String type) {
		super(entityManager);
		setName(type);
		entityManager.addProjectile(this);
	}
	
//	private ImageView bulletNode;
//	private double moveSpeed;
	
//not reaching checkCollisions but lives going down
	public void checkCollision(Entity collider) {
		if(collider instanceof Damaged && ((Damaged) collider).getDamagedByTypes().contains(getName()) ) {
			setDelete(true);
			((Damaged) collider).didCollide();
			//if(this instanceof Fork && collider instanceof Fowl)
		}
	}
	
	public void checkInBounds(Bounds bounds) {
		if(bounds.contains(coordinate) == false) {
			setDelete(true);
		}
	}
	
	public void move(double elapsedTime) {
		Point2D test = getDirectionVector();
		updateCoordinate(coordinate.getX() + getMoveSpeed() * getDirectionVector().getX() * elapsedTime , coordinate.getY() + getMoveSpeed() * getDirectionVector().getY() * elapsedTime);
	}
	
	public Point2D calcDirectionVector(int degrees) {
		return new Point2D(Math.cos(degrees), Math.sin(degrees));
	}
	
	public void setDirectionVector(Point2D directionVector) {
		this.directionVector = directionVector;
	}
	
	public Point2D getDirectionVector() {
		return directionVector;
	}
	
	abstract void setMoveSpeed(double speed);
		
	abstract double getMoveSpeed();
}
