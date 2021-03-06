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

/**
 * Class that handles the menu of the Game.
 * 
 * @assumptions: Relies on other classes to handle level transitions.
 * @example: Menu(); menu.GenerateSceneGraph(myScene);
 * @author seanhudson
 */
public class Menu {

	/**
	 * Creates the Menu root node.
	 * 
	 * @param myScene The Game's scene.
	 * @return Group root node for the Scene graph
	 */
	public Group GenerateSceneGraph(Scene myScene) {
		Group root = new Group();
		generateMenuText(root, new Point2D(myScene.getWidth() / 2, myScene.getHeight() / 2));
		generateBanner(root, new Point2D(25, 100));
		generateRoosterImage(root, new Point2D(myScene.getWidth() / 2, myScene.getHeight() / 1.2));
		return root;
	}
	
	/**
	 * Generates the Menu display text.
	 * 
	 * @param root Group node for the scene graph.
	 * @param coordinate Location of display text.
	 * @return nothing
	 */
	private void generateMenuText(Group root, Point2D coordinate) {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("menu_Text.png"), 450, 200, true, true);
		ImageView menuText = new ImageView(image);
		menuText.setLayoutX(coordinate.getX() - (menuText.getBoundsInLocal().getWidth() / 2));
		menuText.setLayoutY(coordinate.getY() - (menuText.getBoundsInLocal().getHeight() / 2));
        root.getChildren().add(menuText);
	}
	
	/**
	 * Generates the Menu banner display.
	 * 
	 * @param root Group node for the scene graph.
	 * @param coordinate Location of display text.
	 * @return nothing
	 */
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
	
	/**
	 * Generates the rooster image.
	 * 
	 * @param root Group node for the scene graph.
	 * @param coordinate Location of display text.
	 * @return nothing
	 */
	private void generateRoosterImage(Group root, Point2D coordinate) {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("rooster.png"), 100, 100, true, true);
		ImageView rooster = new ImageView(image);
		rooster.setLayoutX(coordinate.getX() - (rooster.getBoundsInLocal().getWidth() / 2));
		rooster.setLayoutY(coordinate.getY() - (rooster.getBoundsInLocal().getHeight() / 2));
        root.getChildren().add(rooster);
	}
	
}
