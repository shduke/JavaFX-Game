import com.sun.glass.events.KeyEvent;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Class that handles the Game.
 * 
 * @assumptions: Only one instance of the game class.
 * @dependancies: Level, Entity, Fowl, Firing, EntityManager, Player, GameOver, Menu, BossLevel
 * @example: RoosterRoasterGame()
 * @author seanhudson
 */
public class RoosterRoasterGame {
    public static final String TITLE = "Rooster Roaster";
    public static final int KEY_INPUT_SPEED = 5;
	private Scene myScene;
	private Player player;
	private Group root;
	private int levelNumber;
	private Level level;
	private EntityManager entityManager;
	private GameOver gameOver;
	private ImageView background;
	
	
    /**
     * Returns name of the game.
     * 
     * @return TITLE
     */
    public String getTitle () {
        return TITLE;
    }
	
    /**
     * Initializes the starting Scene graph with a Menu root node.
     * 
     * @param width Application window width
     * @param height Application window height
     * @return Game scene
     */
	public Scene init(int width, int height) {
        root = new Group();
		myScene = new Scene(root, width, height, Color.WHITE);
		generateBackground();
		startMenu();
		myScene.setRoot(root);
		myScene.setOnKeyPressed(e -> handleGameOverKeyReleased(e.getCode()));
		return myScene;
	}
	
	/**
	 * Sets the scene to the Menu root.
	 * 
	 * @return nothing
	 */
	public void startMenu() {
		levelNumber = 0;
		Menu menu = new Menu();
		root = menu.GenerateSceneGraph(myScene);
		addBackground();
		myScene.setRoot(root); //redundant
		myScene.setOnKeyPressed(e -> {});
		myScene.setOnKeyReleased(e -> handleMenuKeyReleased(e.getCode()));
	}
	
	/**
	 * Generates the scrolling background.
	 * 
	 * @return nothing
	 */
	public void generateBackground() {
        Image imageBackground = new Image(getClass().getClassLoader().getResourceAsStream("star_GIF.gif"), myScene.getWidth(), myScene.getHeight(), false, true);
		ImageView bgNode = new ImageView(imageBackground);
		background = bgNode;
		addBackground();
	}
	
	/**
	 * Adds the background to the current root.
	 * 
	 * @return nothing
	 */
	public void addBackground() {
		root.getChildren().add(0, background);
	}
	
	/**
	 * Checks to see if the game is lost by looking at the player lives remaining.
	 * 
	 * @return nothing
	 */
	public void checkLoss() {
		if(player.getLives() <= 0) {
			startOutcome(false);
		}
	}

	/**
	 * GameLoop - updates every the screen 60 FPS.
	 * 
	 * @param elapsedTime FRAME_DELAY
	 * @return nothing
	 */
	public void step (double elapsedTime) {
		if(levelNumber != 0) {
		entityManager.updateAllCoordinates(elapsedTime);
		entityManager.updateAllPostionsInFrame();
		entityManager.checkAllProjectilesInBounds(new BoundingBox(0, 0, myScene.getWidth(), myScene.getHeight()));
		entityManager.checkAllCollision();
		entityManager.checkAllForDeletion(root);
		updateScore(entityManager.getAdditionalPoints());
		entityManager.setAdditionalPoints(0);
		updateDisplay();
		checkLevelComplete();
		checkLoss();
		}
	}

	/**
	 * Sets the player's score.
	 * 
	 * @param points New score value
	 * @return nothing
	 */
	public void updateScore(int points) {
		player.setScore(player.getScore() + points);
	}
	
	/**
	 * Updates all the display labels
	 * 
	 * @return nothing
	 */
	public void updateDisplay() {
		updateLabel("#score", "Score " + player.getScore());
		updateLabel("#level", "Level " + player.getLevel());
		updateLabel("#lives", "Lives " + player.getLives());
	}
	
	/**
	 * Updates the text field of a specified label.
	 * 
	 * @param id Label id
	 * @param text Label text
	 * @return nothing
	 */
	public void updateLabel(String id, String text) {
		Node score = myScene.lookup(id);
		if(score != null) {
			((Label) score).setText(text); 
		}
	}
	
	/**
	 * Starts the first level.
	 * 
	 * @return nothing
	 */
	public void startGame() {
		levelNumber = 0;
		entityManager = new EntityManager();
		player = new Player(myScene.getWidth() / 2, myScene.getHeight() / 1.1, entityManager);
		nextLevel();
		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		myScene.setOnKeyReleased(e -> handleKeyRelease(e.getCode()));
	}

	/**
	 * Checks to make sure each level is complete before moving to the next one.
	 * 
	 * @return nothing
	 */
	public void checkLevelComplete() {
		if(!entityManager.checkForEnemiesRemaining() && level.getEnemies() <= 0) {
			nextLevel();
		}
	}
	
	/**
	 * Transitions to the next level.
	 * 
	 * @return nothing
	 */
	public void nextLevel() {
		entityManager.ClearAll(true);
		levelNumber += 1;
		player.setLevel(levelNumber);
		if(levelNumber == 4) {
			level = new BossLevel(player, myScene, entityManager);
		} else if(levelNumber >= 5) {
			startOutcome(true);
			return;
		}
		else {
			level = new Level(player, myScene, entityManager);
		}
		root = level.GenerateSceneGraph();
		addBackground();
		myScene.setRoot(root);
	}
	
	/**
	 * Starts the Game ended Screen.
	 * 
	 * @param didWin Whether the player won or not.
	 * @return nothing
	 */
	private void startOutcome(boolean didWin) {
    	level.stopSpawning();
		gameOver = new GameOver(myScene, didWin, player.getScore());
		entityManager.ClearAll(false);
		root = gameOver.GameEnded();
		addBackground();
		myScene.setRoot(root);
		myScene.setOnKeyPressed(e -> {});
		myScene.setOnKeyReleased(e -> handleGameOverKeyReleased(e.getCode()));
	}
	
	/**
	 * Specifies the keyReleased input for the menu screen.
	 * 
	 * @param code Key input
	 * @return nothing
	 */
	private void handleMenuKeyReleased(KeyCode code) {
		switch (code) {
			case SPACE:
				startGame();
				break;
			default:
		}
	}
	
	/**
	 * Specifies the keyReleased input for the GameOver screen.
	 * 
	 * @param code Key input
	 * @return nothing
	 */
	private void handleGameOverKeyReleased(KeyCode code) {
		switch (code) {
			case M:
				startMenu();
				break;
			default:
		}
	}
	
	/**
	 * Specifies the keyReleased input for each Level.
	 * 
	 * @param code Key input
	 * @return nothing
	 */
	private void handleKeyRelease(KeyCode code) {
		switch (code) {
			case SPACE:
            	player.shoot((Group)myScene.getRoot());
            	break;
        	default:
		}
	}
	
	/**
	 * Specifies the key input for each level.
	 * 
	 * @param code Key input
	 * @return nothing
	 */
    private void handleKeyInput (KeyCode code) {
        switch (code) {
            case RIGHT:
            	player.moveRight();
            	break;
            case LEFT:
            	player.moveLeft();
                break;
            case UP:
            	player.moveUp();
                break;
            case DOWN:
            	player.moveDown();
                break;
            case DIGIT0:
            	startMenu();
            	break;
            case DIGIT1:
            	entityManager.ClearAll(false);
            	level.stopSpawning();
            	startGame();
            	break;
            case DIGIT2:
            	level.stopSpawning();
            	nextLevel();
            	break;
            case DIGIT3:
            	levelNumber = 3;
            	level.stopSpawning();
            	nextLevel();
            	break;
            case DIGIT4:
            	player.setLives(0);
            	startOutcome(false);
            	break;
            case DIGIT5:
            	startOutcome(true);
            	break;
           case EQUALS:
            	player.setLives(player.getLives() + 1);
            	break;
            case MINUS:
            	player.setLives(player.getLives() - 1);
            	break;
            default:
        }
    }

}
