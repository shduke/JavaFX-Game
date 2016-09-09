import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.Group;

public class EntityManager {
	private ArrayList<Entity> entities;
	private ArrayList<Projectile> projectiles;

	EntityManager() {
		entities = new ArrayList<Entity>();
		projectiles = new ArrayList<Projectile>();
	}
	
	public void checkAllCollision() {
		for(Projectile projectile : projectiles) {
			for(Entity entity : entities) {
				if (projectile.node.getBoundsInParent().intersects(entity.node.getBoundsInParent())) {
				 	projectile.checkCollision(entity);
				}
			}
		}
	}
	
	public void checkAllForDeletion(Group root) {
		Iterator<Entity> iter = entities.iterator();
		int before = entities.size();
		while (iter.hasNext()) {
			Entity entity = iter.next();
			if(entity.getDelete()) {
				entity.delete(root);
				Boolean rootTest = root.getChildren().contains(entity);
				iter.remove();
				if(entity instanceof Projectile) {
					projectiles.remove(entity);
			}
		}
	}
		int after = entities.size();
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
