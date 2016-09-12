import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.util.Duration;

public class Firing {
	private Entity shooter;
	private Point2D projectileSpawnCoordinate;
	
	Firing(Entity shooter) {
		this.shooter = shooter;
	}
	
	
	//Reflection to figure out what bullet to use?
	public void shoot(Group root, EntityManager entityManager, String bulletType) {
		projectileSpawnCoordinate = new Point2D(shooter.centerReal().getX(), shooter.centerReal().getY());
		Projectile bullet;
		if(bulletType.equals("fork")) {
			bullet = new Fork(shooter, projectileSpawnCoordinate, entityManager);
		}
		else if(bulletType.equals("bird_Poop")) {
			bullet = new BirdPoop(shooter, projectileSpawnCoordinate, entityManager);
		}
		else {
			bullet = new Egg(shooter, projectileSpawnCoordinate, entityManager);
		}
		bullet.setPlayerNode(root);
	}
}
