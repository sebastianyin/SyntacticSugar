package com.syntacticsugar.vooga.gameplayer.objects;

import java.util.Collection;
import java.util.Map;

import com.syntacticsugar.vooga.gameplayer.event.ICollisionEvent;

public interface ICollidable {
	
	/**
	 * Adds an event to be thrown when this object collides with the object type specified.
	 * @param type the object type
	 * @param event the event
	 */
	public void addCollisionBinding(GameObjectType type, ICollisionEvent event);
	
	/**
	 * Method to be called when a collision involving this object is detected.
	 * @param collidedObject the object that was collided with
	 */
	public void onCollision(IGameObject collidedObject);
	
	/**
	 * Returns the object's collision map.
	 * @return the collision map
	 */
	public Map<GameObjectType, Collection<ICollisionEvent>> getCollisionMap();
	
}
