import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;

/**
 * Class that handles the boss level of the Game.
 * 
 * @assumptions: Relies on other classes to handle level transitions.
 * @dependancies: Level, Entity, Fowl, Firing, EntityManager, Boss
 * @example: BossLevel(player, myScene, entityManager); bossLevel.GenerateSceneGraph()
 * @author seanhudson
 */
public class BossLevel extends Level{
	
	/**
	 * Creates the BossLevel instance.
	 * 
	 * @param player The player controlled entity.
	 * @param myScene The Game's scene.
	 * @param entityManager Manages the bossLevel's Entity interactions.
	 * @return nothing
	 */
	BossLevel(Player player, Scene myScene, EntityManager entityManager) {
		super(player, myScene, entityManager);
		setEnemies(1);
	}

	/**
	 * Generates the root scene graph node for the boss level.
	 * 
	 * @return Root node for the boss level scene graph.
	 */
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
		return root;
	}
	
	/**
	 * Spawns the Boss.
	 * 
	 * @return The Boss.
	 */
	public Boss spawnBoss() {
		Boss boss = new Boss(getEntityManager(), new Point2D(getScene().getWidth() / 2, 0));
		
		boss.setMovementVector(calcVector(boss.coordinate, new Point2D(getScene().getWidth(), getScene().getHeight())));
		boss.setBounds(new BoundingBox(0, 0, getScene().getWidth(), getScene().getHeight() / 2));
		return boss;
	}
}
