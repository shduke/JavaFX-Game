import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RoosterRoasterGame {
    public static final String TITLE = "Rooster Roaster";
    public static final int KEY_INPUT_SPEED = 5;
	private Scene myScene;
	private Player player;
	private Group root;
	private int level;
	
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
        
		player = new Player(width / 2, height / 2);
		player.setPlayerNode(root);
		
		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		return myScene;
	}
	

    //game-loop
	public void step (double elapsedTime) {
		
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
            case SPACE:
            	player.shoot(root);
            	break;
            default:
                // do nothing
        }
    }

}
