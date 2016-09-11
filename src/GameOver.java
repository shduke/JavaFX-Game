import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameOver {
	Boolean win;
	int score;
	Scene myScene;
	GameOver(Scene myScene, Boolean win, int score) {
		this.win = win;
		this.score = score;
		this.myScene = myScene;
	}
	
	public Group GameEnded() {
		Group root = new Group();
		generateOutcomeSceen(root, new Point2D(150, 100));
		generateScoreLabel(root, new Point2D(myScene.getWidth() / 2, myScene.getHeight() / 2));
		generatePlayAgainLabel(root, new Point2D(105, myScene.getHeight() / 1.3));
		return root;
	}
	
	private void generateOutcomeSceen(Group root, Point2D coordinate) {
        Canvas canvas = new Canvas(600, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill( Color.GREEN);
        gc.setStroke( Color.BLACK);
        gc.setLineWidth(2);
        Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 80 );
        gc.setFont( theFont );
        conditionBasedGraphics(gc, coordinate);
        root.getChildren().add(canvas);
	}
	
	private void conditionBasedGraphics(GraphicsContext gc, Point2D coordinate) {
		String outcome;
		if(win) {
			gc.setFill(Color.GREEN);
			outcome = "win";
		} else {
			gc.setFill(Color.RED);
			outcome = "lose";
		}
        gc.fillText("You " + outcome + "!", coordinate.getX(), coordinate.getY());
        gc.strokeText("You " + outcome + "!", coordinate.getX(), coordinate.getY());
	}
	
	private void generateScoreLabel(Group root, Point2D coordinate) {
		Label scoreLabel = new Label("Score " + score);
		scoreLabel.setFont(new Font(48));
		scoreLabel.setLayoutX(coordinate.getX() - 125);
		scoreLabel.setLayoutY(coordinate.getY());
        root.getChildren().add(scoreLabel);
	}
	
	private void generatePlayAgainLabel(Group root, Point2D coordinate) {
		Label playAgainLabel = new Label("Press M to return to the menu");
		playAgainLabel.setFont(new Font(28));
		playAgainLabel.setLayoutX(coordinate.getX());
		playAgainLabel.setLayoutY(coordinate.getY());
        root.getChildren().add(playAgainLabel);
		
	}
	

}
