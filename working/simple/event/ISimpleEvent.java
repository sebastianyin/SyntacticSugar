package simple.event;

import java.util.Collection;

import simple.attribute.ISimpleAttribute;
import simple.attribute.SimpleAbstractAttribute;
import simple.obj.ISimpleObject;

public interface ISimpleEvent {
	
	/**
	 * Return the enum type of this event.
	 * @return
	 */
	public SimpleEventType getEventType();
	
	/**
	 * Execute this event on the specified Target attributes.
	 */
	public void executeEvent(Collection<ISimpleAttribute> targetAttributes);

}
