import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

//name, levels, rules, controls
public class Menu {

	
	public Group GenerateSceneGraph(Scene myScene) {
		Group root = new Group();
		generateMenuText(root, new Point2D(myScene.getWidth() / 2, myScene.getHeight() / 2));
		generateBanner(root, new Point2D(25, 100));
		generateRoosterImage(root, new Point2D(myScene.getWidth() / 2, myScene.getHeight() / 1.2));
		return root;
	}
	
	private void generateMenuText(Group root, Point2D coordinate) {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("menu_Text.png"), 450, 200, true, true);
		ImageView menuText = new ImageView(image);
		menuText.setLayoutX(coordinate.getX() - (menuText.getBoundsInLocal().getWidth() / 2));
		menuText.setLayoutY(coordinate.getY() - (menuText.getBoundsInLocal().getHeight() / 2));
        root.getChildren().add(menuText);
	}
	
	private void generateBanner(Group root, Point2D coordinate) {
        Canvas canvas = new Canvas(600, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill( Color.RED );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(2);
        Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 80 );
        gc.setFont( theFont );
        gc.fillText( "Rooster Roaster", coordinate.getX(), coordinate.getY());
        gc.strokeText( "Rooster Roaster", coordinate.getX(), coordinate.getY());
        root.getChildren().add(canvas);
	}
	
	private void generateRoosterImage(Group root, Point2D coordinate) {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("rooster.png"), 100, 100, true, true);
		ImageView rooster = new ImageView(image);
		rooster.setLayoutX(coordinate.getX() - (rooster.getBoundsInLocal().getWidth() / 2));
		rooster.setLayoutY(coordinate.getY() - (rooster.getBoundsInLocal().getHeight() / 2));
        root.getChildren().add(rooster);
	}
	
}
