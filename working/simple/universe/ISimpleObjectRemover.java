package simple.universe;

import simple.obj.ISimpleObject;

public interface ISimpleObjectRemover {

	/**
	 * Remove this game object from the container.
	 * @param toRemove
	 */
	public void removeGameObject(ISimpleObject toRemove);
	
}
