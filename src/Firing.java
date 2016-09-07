import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;

public class Firing {
	private Entity shooter;
	//private int angle;
	private Point2D projectileSpawnCoordinate;
	
	Firing(Entity shooter) {
		this.shooter = shooter;
		//angle = 90;
	}
	
	
	//Reflection to figure out what bullet to use?
	public Fork shoot(Group root, EntityManager entityManager) {
		projectileSpawnCoordinate = new Point2D(shooter.centerReal().getX(), shooter.centerReal().getY());
		Fork bullet = new Fork(shooter, projectileSpawnCoordinate, entityManager);
		bullet.setPlayerNode(root);
		return bullet;
	}
}
