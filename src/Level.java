import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Level {
	private int enemies;
	private int level;
	private Player player;
	private Scene myScene;
	
	//Should I just inherit from RRGame?
	Level(int level, Player player, Scene myScene) {
		this.level = level;
		this.player = player;
		this.myScene = myScene;
		enemies = level * 10;
	}
	
	public Group GenerateSceneGraph() {
		Group root = new Group();
		return root;
	}
	
	
}
