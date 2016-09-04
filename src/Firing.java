import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;

public class Firing {
	private Entity shooter;
	private int angle;
	private Point2D projectileSpawnCoordinate;
	
	Firing(Entity shooter) {
		this.shooter = shooter;
		angle = 90;
	}
	
	public Bullet shoot(Group root) {
		projectileSpawnCoordinate = new Point2D(shooter.centerReal().getX(), shooter.centerReal().getY());
		Bullet bullet = new Bullet(shooter, projectileSpawnCoordinate);
		bullet.setPlayerNode(root);
		return bullet;
	}
}
