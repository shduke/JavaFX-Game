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
import javafx.util.Duration;

public class Level {
	private int enemies;
	private int level;
	private Player player;
	private Scene myScene;
	private EntityManager entityManager;
	
	//Should I just inherit from RRGame?
	Level(Player player, Scene myScene, EntityManager entityManager) {
		this.level = player.getLevel();
		this.player = player;
		this.myScene = myScene;
		this.entityManager = entityManager;
		enemies = level * 10;
	}
	
	public Group GenerateSceneGraph() {
		Group root = new Group();
		spawnEnemies(root);
		player.setPlayerNode(root);
		display(root);
		return root;
	}
	
	private Label displayLabel(String text, String id, Point2D coordinate) {
		Label label = new Label(text);
		label.setLayoutX(coordinate.getX());
		label.setLayoutY(coordinate.getY());
		label.setId(id);
		return label;
	}
	
	private void display(Group root) {
		Label score = displayLabel("Score " + player.getScore(), "score", new Point2D(0,0));
		Label level = displayLabel("" + player.getLevel(), "level", new Point2D(myScene.getWidth() / 2.1, 0));
		Label lives = displayLabel("Lives " + player.getLives(), "lives", new Point2D(myScene.getWidth() / 1.1,0));
		root.getChildren().addAll(score, level, lives);
	}
	
	
	private int generateRandIntInRange(int min, int max) {
		Random rn = new Random();
		int x = rn.nextInt(max - min + 1) + min;
		return x;
	}
	
	private Point2D generateRandomCoordinate(int xMin, int xMax, int yMin, int yMax) {
//		int x = rn.nextInt((int)myScene.getWidth() / 1);
//		int y = rn.nextInt((int)myScene.getHeight() / 2);
		int x = generateRandIntInRange(xMin, xMax);
		int y = generateRandIntInRange(yMin, yMax);
		Point2D randomCoordinate = new Point2D(x, y);
		return randomCoordinate;
	}
	
	private Point2D generateFowlSpawnCoordinate() {
		int spawnSide = generateRandIntInRange(0,3);
		if (spawnSide == 0) {
			return generateRandomCoordinate(0, 0, 0, (int) myScene.getHeight() / 2);
		}else if (spawnSide == 1) {
			return generateRandomCoordinate(0, (int) myScene.getWidth(), 0, 0);
		}
		return generateRandomCoordinate((int) myScene.getWidth(), (int) myScene.getWidth(), 0, (int) myScene.getHeight() / 2);
	}
	
	public void spawnEnemies(Group root) {
		Timeline timer = new Timeline(new KeyFrame(Duration.millis(1000), e -> generateEnemy(root).setPlayerNode(root)));
		timer.setCycleCount(enemies);
		timer.play();
	}
	
	public Point2D calcVector(Point2D start, Point2D end) {
		//return new Point2D(end.getX() - start.getX(), end.getY() - start.getY());
		//System.out.println(start + "  " + end);
		Point2D directionVector = new Point2D(end.getX() - start.getX(), end.getY() - start.getY());
		//System.out.println(directionVector);
		return normalizeVector(directionVector);
	}
	
	public Point2D normalizeVector(Point2D vector) {
		double normalizer = Math.sqrt(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2));
		Point2D normalizedVector = new Point2D(vector.getX() / normalizer, vector.getY() / normalizer);
		return normalizedVector;
	}
	
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
	
	
}
