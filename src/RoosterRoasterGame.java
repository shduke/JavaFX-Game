import javafx.animation.Timeline;
import javafx.application.Application;
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
	
    /**
     * Returns name of the game.
     */
    public String getTitle () {
        return TITLE;
    }
	
	//initializes the starting Scene graph
	public Scene init(int width, int height) {
		//player = new Player(myScene.getWidth() / 2 - player.getPlayerNode().getBoundsInLocal().getWidth() / 2, myScene.getHeight() / 2  - player.getPlayerNode().getBoundsInLocal().getHeight() / 2);
        root = new Group();
		myScene = new Scene(root, width, height, Color.WHITE);
		entityManager = new EntityManager();

        Canvas canvas = new Canvas( 400, 200 );
        root.getChildren().add( canvas );
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill( Color.RED );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(2);
        Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
        gc.setFont( theFont );
        gc.fillText( "Rooster Roaster", 60, 50 );
        gc.strokeText( "Rooster Roaster", 60, 50 );
        
       // HBox hbox = new HBox();
        //hbox.getChildren().addAll(new Label("Score"), new Label("Level"), new Label("Lives"));
        
		player = new Player(width / 2, height / 1.1, entityManager);
		//player.setPlayerNode(root);
		level = new Level(1, player, myScene, entityManager);
		root = level.GenerateSceneGraph();
		myScene.setRoot(root);
		/*for(int i = 0; i < 10; i++) {
			Point2D spawnPoint = new Point2D(width / 2 - i * 2, height / 2 - i * 2);
			Chicken Enemy = new Chicken(entityManager, spawnPoint);
		}*/
		
		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		myScene.setOnKeyReleased(e -> handleKeyRelease(e.getCode()));
		return myScene;
	}
	

	
    //game-loop
	public void step (double elapsedTime) {
		entityManager.updateAllPostionsInFrame();
		entityManager.updateAllCoordinates(elapsedTime);
		entityManager.checkAllCollision();
		entityManager.checkAllForDeletion(root);
		updateScore(entityManager.getAdditionalPoints());
		entityManager.setAdditionalPoints(0);
		updateDisplay();
		//myScene.setRoot(root);
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
            default:
                // do nothing
        }
    }

}
