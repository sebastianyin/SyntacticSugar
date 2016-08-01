package simple.obj;

import java.util.Collection;

import simple.attribute.ISimpleAttribute;
import simple.event.ISimpleEvent;
import simple.universe.ISimpleUniverse;

public interface ISimpleObject extends ISimpleAttributeContainer, ISimpleCollidable {

	/**
	 * Update this object within the context of the whole universe.
	 * 
	 * @param universe
	 */
	public void updateSelf(ISimpleUniverse universe);

	/**
	 * Returns the Type of this object as specified by the SimpleObjectType
	 * enum.
	 * 
	 * @return
	 */
	public SimpleObjectType getType();

	public SimpleBoundingBox getBoundingBox();

	// ISimpleAttributeContainer methods

	@Override
	public Collection<ISimpleAttribute> getAttributes();

	@Override
	public void addAttribute(ISimpleAttribute attribute);

	// ISimpleCollidable methods

	@Override
	public Collection<ISimpleEvent> getEventsFromCollision(SimpleObjectType type);

	@Override
	public void addCollisionBinding(SimpleObjectType type, ISimpleEvent event);

}