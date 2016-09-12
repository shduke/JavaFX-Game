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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

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
	
    /**
     * Returns name of the game.
     */
    public String getTitle () {
        return TITLE;
    }
	
	//initializes the starting Scene graph
	public Scene init(int width, int height) {
        root = new Group();
		myScene = new Scene(root, width, height, Color.WHITE);
		//startMenu();
		/*Menu menu = new Menu();
		root = menu.GenerateSceneGraph(myScene);*/
		GameOver go = new GameOver(myScene, true, 100);
		Group root = go.GameEnded();
		myScene.setRoot(root);
		myScene.setOnKeyPressed(e -> handleGameOverKeyReleased(e.getCode()));
		return myScene;
	}
	
	public void startMenu() {
		levelNumber = 0;
		Menu menu = new Menu();
		root = menu.GenerateSceneGraph(myScene);
		myScene.setRoot(root); //redundant
		myScene.setOnKeyPressed(e -> {});
		myScene.setOnKeyReleased(e -> handleMenuKeyReleased(e.getCode()));
	}
	
	public void checkGameOver() {
		Boolean outcome = false;
		//System.out.println(levelNumber + " " + player.getLives());
		if(levelNumber >= 5) {
			outcome = true;
		} else if(player.getLives() > 0){
			return;
		}
		gameOver = new GameOver(myScene, outcome, player.getScore());
		myScene.setRoot(gameOver.GameEnded());
		myScene.setOnKeyPressed(e -> {});
		myScene.setOnKeyReleased(e -> handleGameOverKeyReleased(e.getCode()));
	}

	
    //game-loop
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
		checkGameOver();
		//myScene.setRoot(root);
		}
	}

	
	public void updateScore(int points) {
		player.setScore(player.getScore() + points);
	}
	
	public void updateDisplay() {
		updateLabel("#score", "Score " + player.getScore());
		updateLabel("#level", "Level " + player.getLevel());
		updateLabel("#lives", "Lives " + player.getLives());
	}
	
	public void updateLabel(String id, String text) {
		Node score = myScene.lookup(id);
		if(score != null) {
			((Label) score).setText(text); 
		}
	}
	
	public void startGame() {
		levelNumber = 0;
		entityManager = new EntityManager();
		player = new Player(myScene.getWidth() / 2, myScene.getHeight() / 1.1, entityManager);
		nextLevel();
		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		myScene.setOnKeyReleased(e -> handleKeyRelease(e.getCode()));
	}

	public void checkLevelComplete() {
		if(!entityManager.checkForEnemiesRemaining() && level.getEnemies() <= 0) {
			nextLevel();
		}
	}
	
	public void nextLevel() {
		entityManager.ClearAll(true);
		levelNumber += 1;
		player.setLevel(levelNumber);
		checkGameOver();
		if(levelNumber == 4) {
			level = new BossLevel(player, myScene, entityManager);
		} else if(levelNumber >= 5) {
			checkGameOver();
			return;
		}
		else {
			level = new Level(player, myScene, entityManager);
		}
		root = level.GenerateSceneGraph();
		myScene.setRoot(root);
	}
	
	private void handleMenuKeyReleased(KeyCode code) {
		switch (code) {
			case SPACE:
				startGame();
				break;
			default:
		}
	}
	
	private void handleGameOverKeyReleased(KeyCode code) {
		switch (code) {
			case M:
				startMenu();
				break;
			default:
		}
	}
	
	private void handleKeyRelease(KeyCode code) {
		switch (code) {
			case SPACE:
            	player.shoot((Group)myScene.getRoot());
            	break;
        	default:
		}
	}
	
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
            	//BOSSLEVEL, scrolling background, box user movement, update rules/text;
            case DIGIT4:
        		gameOver = new GameOver(myScene, false, player.getScore());
        		myScene.setRoot(gameOver.GameEnded());
        		myScene.setOnKeyPressed(e -> {});
        		myScene.setOnKeyReleased(e -> handleGameOverKeyReleased(e.getCode()));
            	break;
            case DIGIT5:
        		gameOver = new GameOver(myScene, true, player.getScore());
        		myScene.setRoot(gameOver.GameEnded());
        		myScene.setOnKeyPressed(e -> {});
        		myScene.setOnKeyReleased(e -> handleGameOverKeyReleased(e.getCode()));
            	break;
            	
            default:
        }
    }

}
