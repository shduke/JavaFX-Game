import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Class that handles the firing of Projectiles
 * 
 * @assumptions: Relies on other classes to make instances of it.
 * @dependancies: Entity, Firing, EntityManager
 * @example: Firing(player, myScene, entityManager); firingDelegate.shoot(root, entityManager, "egg")
 * @author seanhudson
 */
public class Firing {
	private Entity shooter;
	private Point2D projectileSpawnCoordinate;
	
	/**
	 * Creates the Firing instance.
	 * 
	 * @param shooter The entity that shot the Projectile.
	 */
	Firing(Entity shooter) {
		this.shooter = shooter;
	}
	
	/**
	 * Overloads the shoot() method with an angle.
	 * 
	 * @param root Group node for the scene graph.
	 * @param entityManager Manages the bullets.
	 * @param bulletType Specifies what bullet is shot.
	 * @param degress Angle of the shot.
	 * @return nothing
	 */
	public Projectile shoot(Group root, EntityManager entityManager, String bulletType, int degrees) {
		Projectile bullet = shoot(root, entityManager, bulletType);
		bullet.setDirectionVector(bullet.calcDirectionVector(degrees));
		return bullet;
	}
	
	/**
	 * Fires regular shot of a specified type.
	 * 
	 * @param root Group node for the scene graph.
	 * @param entityManager Manages the bullets.
	 * @param bulletType Specifies what bullet is shot
	 * @return nothing
	 */
	public Projectile shoot(Group root, EntityManager entityManager, String bulletType) {
		projectileSpawnCoordinate = new Point2D(shooter.centerReal().getX(), shooter.centerReal().getY());
		Projectile bullet;
		if(bulletType.equals("fork")) {
			bullet = new Fork(projectileSpawnCoordinate, entityManager);
		}
		else if(bulletType.equals("bird_Poop")) {
			bullet = new BirdPoop(projectileSpawnCoordinate, entityManager);
		}
		else {
			bullet = new Egg(projectileSpawnCoordinate, entityManager);
		}
		bullet.setPlayerNode(root);
		return bullet;
	}
}
