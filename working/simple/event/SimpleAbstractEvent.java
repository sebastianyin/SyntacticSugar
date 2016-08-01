package simple.event;

import java.util.Collection;

import simple.attribute.ISimpleAttribute;

public abstract class SimpleAbstractEvent implements ISimpleEvent {

	private SimpleEventType myType;
	
	public SimpleAbstractEvent(SimpleEventType type) {
		this.myType = type;
	}
	
	@Override
	public SimpleEventType getEventType() {
		return this.myType;
	}
	
	@Override
	public abstract void executeEvent(Collection<ISimpleAttribute> targetAttributes);
	
}
