package simple.obj;

import java.util.Collection;

import simple.event.ISimpleEvent;

public interface ISimpleCollidable {

	/**
	 * Returns a Collection of Events associated with colliding with a specific type
	 * of object.
	 * @param type
	 * @return
	 */
	public Collection<ISimpleEvent> getEventsFromCollision(SimpleObjectType type);
	
	/**
	 * Adds an event to be thrown when this object collides with the object type specified.
	 * @param type
	 * @param event
	 */
	public void addCollisionBinding(SimpleObjectType type, ISimpleEvent event);
	
}
