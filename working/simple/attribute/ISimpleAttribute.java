package simple.attribute;

import simple.event.ISimpleEvent;
import simple.obj.ISimpleObject;
import simple.universe.ISimpleUniverse;

public interface ISimpleAttribute {

	/**
	 * Updates the state of this Attribute within it's Universe.
	 * @param universe
	 */
	public void updateSelf(ISimpleUniverse universe);
	
	/**
	 * Returns the game object that contains this Attribute (ie. it's parent object)
	 * @return
	 */
	public ISimpleObject getParent();
	
	/**
	 * Receive a generic SimpleEvent. If this event pertains to the 
	 * attribute, then it will execute.
	 * @param event
	 */
	public void receiveEvent(ISimpleEvent event);
	
}
