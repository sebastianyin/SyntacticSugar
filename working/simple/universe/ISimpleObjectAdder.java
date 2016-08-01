package simple.universe;

import simple.obj.ISimpleObject;

public interface ISimpleObjectAdder {

	/**
	 * Adds a single object to the container.
	 * @param gameObject
	 */
	public void addGameObject(ISimpleObject toAdd);
	
}
