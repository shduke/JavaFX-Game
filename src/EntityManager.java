import java.util.ArrayList;
import java.util.Iterator;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class EntityManager {
	private ArrayList<Entity> entities;
	private ArrayList<Projectile> projectiles;
	private int additionalPoints;

	EntityManager() {
		entities = new ArrayList<Entity>();
		projectiles = new ArrayList<Projectile>();
		additionalPoints = 0;
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
		while (iter.hasNext()) {
			Entity entity = iter.next();
			if(entity.getDelete()) {
				entity.delete(root);
				iter.remove();
				if(entity instanceof Projectile) {
					projectiles.remove(entity);
			}
		}
	}
	}
	
	public void checkAllProjectilesInBounds(Bounds bounds) {
		for(Projectile projectile : projectiles) {
			projectile.checkInBounds(bounds);
		}
	}
	
	public void setAdditionalPoints(int additionalPoints) {
		this.additionalPoints = additionalPoints;
	}
	
	public int getAdditionalPoints() {
		return additionalPoints;
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
