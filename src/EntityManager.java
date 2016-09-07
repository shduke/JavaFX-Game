import java.util.ArrayList;

public class EntityManager {
	private ArrayList<Entity> entities;
	private ArrayList<Projectile> projectiles;

	EntityManager() {
		entities = new ArrayList<Entity>();
		projectiles = new ArrayList<Projectile>();
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}
	
	public void updateAllPostionsInFrame() {
		for(Entity entity : entities) {
			entity.updatePositionInFrame();
		}
	}
	
	public void updateAllCoordinates(double elapsedTime) {
		for(Entity entity : entities) {
			entity.move(elapsedTime);
		}
	}

}
