import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;

public class BossLevel extends Level{
	BossLevel(Player player, Scene myScene, EntityManager entityManager) {
		super(player, myScene, entityManager);
		setEnemies(1);
	}

	@Override
	public Group GenerateSceneGraph() {
		Group root = new Group();
		getPlayer().setPlayerNode(root);
		Boss boss = spawnBoss();
		setEnemies(getEnemies() - 1);
		boss.setPlayerNode(root);
		boss.startShootTimer(root);
		boss.startSpecialAttackShootTimer(root);
		display(root);
		//bullet.setPlayerNode(root);
		//boss.getFiringDelegate()boss;
				
		return root;
	}
	
	public Boss spawnBoss() {
		Boss boss = new Boss(getEntityManager(), new Point2D(getScene().getWidth() / 2, 0));
		
		boss.setMovementVector(calcVector(boss.coordinate, new Point2D(getScene().getWidth(), getScene().getHeight())));
		boss.setBounds(new BoundingBox(0, 0, getScene().getWidth(), getScene().getHeight() / 2));
		return boss;
	}
}
