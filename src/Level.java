import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

/**
 * Class that handles each level of the Game.
 * 
 * @assumptions: Relies on other classes to handle level transitions.
 * @dependancies: Level, Entity, Fowl, Firing, EntityManager, Player
 * @example: BLevel(player, myScene, entityManager); bossLevel.GenerateSceneGraph()
 * @author seanhudson
 */
public class Level {
	private int enemies;
	private int level;
	private Player player;
	private Scene myScene;
	private EntityManager entityManager;
	private Timeline enemySpawning;
	
	/**
	 * Creates the Level instance.
	 * 
	 * @param player The player controlled entity.
	 * @param myScene The Game's scene.
	 * @param entityManager Manages the level's Entity interactions.
	 * @return nothing
	 */
	Level(Player player, Scene myScene, EntityManager entityManager) {
		this.level = player.getLevel();
		this.player = player;
		this.myScene = myScene;
		this.entityManager = entityManager;
		enemies = level * 10;
	}
	
	/**
	 * Generates the root scene graph node for each level.
	 * 
	 * @return Root node for the level scene graph.
	 */
	public Group GenerateSceneGraph() {
		Group root = new Group();
		spawnEnemies(root);
		player.setPlayerNode(root);
		display(root);
		return root;
	}
	
	/**
	 * Creates a display label.
	 * 
	 * @param text Label text
	 * @param id Label id
	 * @param coordinate Label location
	 * @return Display label
	 */
	private Label displayLabel(String text, String id, Point2D coordinate) {
		Label label = new Label(text);
		label.setTextFill(Paint.valueOf("GREY"));
		label.setLayoutX(coordinate.getX());
		label.setLayoutY(coordinate.getY());
		label.setId(id);
		return label;
	}
	
	/**
	 * Displays all labels on the screen.
	 * 
	 * @param root Group node for the scene graph.
	 * @return nothing
	 */
	protected void display(Group root) {
		Label score = displayLabel("Score " + player.getScore(), "score", new Point2D(0,0));
		Label level = displayLabel("" + player.getLevel(), "level", new Point2D(myScene.getWidth() / 2.1, 0));
		Label lives = displayLabel("Lives " + player.getLives(), "lives", new Point2D(myScene.getWidth() / 1.1,0));
		root.getChildren().addAll(score, level, lives);
	}
	
	/**
	 * Generates a random integer within a range.
	 * 
	 * @param min Minimum value
	 * @param max Maximum value
	 * @return return Random integer
	 */
	private int generateRandIntInRange(int min, int max) {
		Random rn = new Random();
		int x = rn.nextInt(max - min + 1) + min;
		return x;
	}
	
	/**
	 * Generates a random coordinate within a bounding area.
	 * 
	 * @param xMin Minimum x value
	 * @param xMax Maximum x value
	 * @param yMin Minimum y value
	 * @param yMax Maximum y value
	 * @return Random coordinate
	 */
	private Point2D generateRandomCoordinate(int xMin, int xMax, int yMin, int yMax) {
		int x = generateRandIntInRange(xMin, xMax);
		int y = generateRandIntInRange(yMin, yMax);
		Point2D randomCoordinate = new Point2D(x, y);
		return randomCoordinate;
	}
	
	/**
	 * Generates a random coordinate somewhere on the top three edges of the screen for fowl placement.
	 * 
	 * @return fowl spawn coordinate
	 */
	private Point2D generateFowlSpawnCoordinate() {
		int spawnSide = generateRandIntInRange(0,3);
		if (spawnSide == 0) {
			return generateRandomCoordinate(0, 0, 0, (int) myScene.getHeight() / 2);
		}else if (spawnSide == 1) {
			return generateRandomCoordinate(0, (int) myScene.getWidth(), 0, 0);
		}
		return generateRandomCoordinate((int) myScene.getWidth(), (int) myScene.getWidth(), 0, (int) myScene.getHeight() / 2);
	}
	
	/**
	 * Starts the timer for when enemies spawn.
	 * 
	 * @param root Group node for the scene graph.
	 * @return nothing
	 */
	public void spawnEnemies(Group root) {
		enemySpawning = new Timeline(new KeyFrame(Duration.millis(1000), e -> generateEnemy(root).setPlayerNode(root)));
		enemySpawning.setCycleCount(enemies);
		enemySpawning.play();
	}

	/**
	 * Stops spawning enemies.
	 * 
	 * @return nothing
	 */
	public void stopSpawning() {
		if(enemySpawning != null) {
			enemySpawning.stop();
		}
	}
	
	/**
	 * Calculates the normalized distance vector between two points for enemy movement direction.
	 * 
	 * @param start Begin coordinate
	 * @param end End coordinate
	 * @return Normalized direction vector
	 */
	public Point2D calcVector(Point2D start, Point2D end) {
		Point2D directionVector = new Point2D(end.getX() - start.getX(), end.getY() - start.getY());
		return normalizeVector(directionVector);
	}
	
	/**
	 * Normalizes a vector.
	 * 
	 * @param vector Distance vector
	 * @return Normalized vector
	 */
	public Point2D normalizeVector(Point2D vector) {
		double normalizer = Math.sqrt(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2));
		Point2D normalizedVector = new Point2D(vector.getX() / normalizer, vector.getY() / normalizer);
		return normalizedVector;
	}
	
	/**
	 * Creates an enemy.
	 * 
	 * @param root Group node for the scene graph.
	 * @return spawned enemy
	 */
	private Fowl generateEnemy(Group root) {
		Point2D spawnCoordinate = generateFowlSpawnCoordinate();
		//System.out.println("SpawnC: " + spawnCoordinate);
		Fowl enemy;
		if(enemies % 2 == 0) {
			enemy =  new Chicken(entityManager, spawnCoordinate);
		} else {
			enemy = new Rooster(entityManager, spawnCoordinate);
		}
		enemies--;
		enemy.setMovementVector(calcVector(enemy.coordinate, generateRandomCoordinate(0, (int) myScene.getWidth(), 0, (int) myScene.getHeight() / 2)));
		enemy.setBounds(new BoundingBox(0, 0, myScene.getWidth(), myScene.getHeight() / 2));
		enemy.startShootTimer(root);
		return enemy;
	}
	
	/**
	 * Sets the number of enemies left
	 * 
	 * @param enemies Enemies left
	 * @return nothing
	 */
	public void setEnemies(int enemies) {
		this.enemies = enemies;
	}
	
	/**
	 * Gets the player reference.
	 * 
	 * @return Player reference
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Gets the entityManager reference.
	 * 
	 * @return Player reference
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	/**
	 * Gets the myScene reference.
	 * 
	 * @return myScene reference
	 */
	public Scene getScene() {
		return myScene;
	}
	
	/**
	 * Gets the number of enemies left.
	 * 
	 * @return Enemies left
	 */
	public int getEnemies() {
		return enemies;
	}
	
}
