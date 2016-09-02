import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends Entity{

	private int lives;
	private int score;
	private ImageView playerNode; //not used
	
	Player(int x, int y) {
		lives = 3;
		score = 0;
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("player_Central.png"), 100, 100, false, false);
		node = new ImageView(image);
		playerNode = (ImageView)node;
		updateCoordinate(x, y);
	}
	
	//Is this ok to have? not used
	public ImageView getPlayerNode() {
		return playerNode;
	}
	

}
