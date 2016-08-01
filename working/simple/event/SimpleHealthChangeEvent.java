package simple.event;

import java.util.Collection;

import simple.attribute.ISimpleAttribute;

public class SimpleHealthChangeEvent extends SimpleAbstractEvent {

	private double myDeltaHealth;
	
	public SimpleHealthChangeEvent(double deltaHealth) {
		super(SimpleEventType.HealthChange);
		this.myDeltaHealth = deltaHealth;
	}
	
	@Override
	public void executeEvent(Collection<ISimpleAttribute> targetAttributes) {
		for (ISimpleAttribute attribute : targetAttributes) {
			attribute.receiveEvent(this);
		}
	}
	
	public double getDeltaHealth() {
		return this.myDeltaHealth;
	}

}
