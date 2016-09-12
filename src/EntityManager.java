import java.util.ArrayList;
import java.util.Iterator;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;

/**
 * Class that handles the interactions between entities.
 * 
 * @assumptions: Every created entity will be added to an entityManager
 * @dependancies: Entity, EntityManager, Projectile
 * @example: EntityManager(), entityManger.checkAllCollisions()
 * @author seanhudson
 */
public class EntityManager {
	private ArrayList<Entity> entities;
	private ArrayList<Projectile> projectiles;
	private int additionalPoints;

	/**
	 * Creates the entityManager instance.
	 * 
	 * @return nothing
	 */
	EntityManager() {
		entities = new ArrayList<Entity>();
		projectiles = new ArrayList<Projectile>();
		additionalPoints = 0;
	}
	
	/**
	 * Loops through every Projectile and checks if it is colliding with another entity.
	 * 
	 * @return nothing
	 */
	public void checkAllCollision() {
		for(Projectile projectile : projectiles) {
			for(Entity entity : entities) {
				if (projectile.node.getBoundsInParent().intersects(entity.node.getBoundsInParent())) {
					projectile.checkCollision(entity);
				}
			}
		}
	}
	
	/**
	 * Checks if every node has been set for deletion and removes it from both the scene graph root node and the entityManager.
	 * 
	 * @param root Group node for the scene graph.
	 * @return nothing
	 */
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
	
	/**
	 * Checks if every Projectile is on the screen.
	 * 
	 * @param bounds Bounding area of the screen.
	 * @return nothing
	 */
	public void checkAllProjectilesInBounds(Bounds bounds) {
		for(Projectile projectile : projectiles) {
			projectile.checkInBounds(bounds);
		}
	}
	
	/**
	 * Adds a new Entity to the entityManager.
	 * 
	 * @param entity a new Entity.
	 * @return nothing
	 */
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	/**
	 * Adds a new Projectile to the entityManager.
	 * 
	 * @param projectile a new Projectile.
	 * @return nothing
	 */
	public void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}
	
	/**
	 * Moves all of the entities to the new coordinate.
	 * 
	 * @return nothing
	 */
	public void updateAllPostionsInFrame() {
		for(Entity entity : entities) {
			entity.updatePositionInFrame();
		}
	}
	
	/**
	 * Sets the next coordinate value for each Entity.
	 * 
	 * @return nothing
	 */
	public void updateAllCoordinates(double elapsedTime) {
		for(Entity entity : entities) {
			entity.move(elapsedTime);
		}
	}
	
	/**
	 * Clears all entities from the scene root node and from the entityManager.
	 * 
	 * @param keepPlayer Keeps the Player entity if set to true.
	 * @return nothing
	 */
	public void ClearAll(Boolean keepPlayer) {
		Player player = null;
		for(Entity entity: entities) {
			entity.setDelete(true);
			if(entity instanceof Player && keepPlayer) {
				player = (Player)entity;
				player.setDelete(false);
			} else if (entity instanceof Fowl) {
				((Fowl) entity).stopTimelines();
			}
		}
		entities.clear();
		if(player != null) {
			entities.add(player);
		}
		projectiles.clear();
		setAdditionalPoints(0);
	}
	
	
	/**
	 * Checks to see if there are any enemies left.
	 * If there is one instance of an enemey it returns true, otherwise it must loop 
	 * through the entire entities list before confirming that there are no more enemeies.
	 * 
	 * @return If there are enemies left.
	 */
	public Boolean checkForEnemiesRemaining() {
		for(Entity entity : entities) {
			if(entity instanceof Fowl) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets points to be added to the player's score on the next iteration of the game loop.
	 * 
	 * @return additionalPoints Points to be added to player's score.
	 */
	public int getAdditionalPoints() {
		return additionalPoints;
	}
	
	/**
	 * Sets points to be added to the player's score on the next iteration of the game loop.
	 * 
	 * @param additionalPoints Points to be added to player's score.
	 * @return nothing
	 */
	public void setAdditionalPoints(int additionalPoints) {
		this.additionalPoints = additionalPoints;
	}

}
