import java.util.ArrayList;

/**
 * Interface that all entities that take damage need to implement.
 * 
 * @assumptions: Subclasses of Entities will implement this.
 * @dependancies: Entity, Firing, EntityManager
 * @example: damaged.didCollide()
 * @author seanhudson
 */
public interface Damaged {
	
	/**
	 * Gets a list all of the type id's of Projectiles that can damage it.
	 * 
	 * @return List of Projeciles type id's it can be damaged by.
	 */
	ArrayList<String> getDamagedByTypes();
	
	/**
	 * Handles actions for when the damaged implementation collides with another Entity.
	 * 
	 * @return nothing
	 */
	void didCollide();
	
	/**
	 * Gets how much health the damaged implementation has
	 * 
	 * @return lives Player's health.
	 */
	int getLives();
	}
