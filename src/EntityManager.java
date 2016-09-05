import java.util.ArrayList;

public class EntityManager {
	private ArrayList<Entity> entities;

	EntityManager() {
		entities = new ArrayList<Entity>();
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void updateAllPostionsInFrame() {
		for(Entity entity : entities) {
			entity.updatePositionInFrame();
		}
	}

}
