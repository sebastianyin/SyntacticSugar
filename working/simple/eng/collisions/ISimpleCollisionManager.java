package simple.eng.collisions;

import java.util.Collection;

import simple.obj.ISimpleObject;

public interface ISimpleCollisionManager {

	/**
	 * Performs O(n) intersection comparison on all the objects in the game.
	 * TODO: Optimize for near-ish neighbor calculations using Game Map
	 * @param objectsToCheck
	 */
	public void checkCollisions(Collection<ISimpleObject> objectsToCheck);
	
}
