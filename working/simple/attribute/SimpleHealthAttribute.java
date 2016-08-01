package simple.attribute;

import simple.event.ISimpleEvent;
import simple.event.SimpleEventType;
import simple.event.SimpleHealthChangeEvent;
import simple.obj.ISimpleObject;
import simple.universe.ISimpleKiller;
import simple.universe.ISimpleUniverse;

public class SimpleHealthAttribute extends SimpleAbstractAttribute {

	private double myHealth;
	
	/**
	 * Construct a health attribute with default 100 starting health 
	 * and the specified parent object.
	 * @param parent
	 */
	public SimpleHealthAttribute(ISimpleObject parent) {
		this(100, parent);
	}
	
	/**
	 * Construct a health attribute with the specified starting health 
	 * and parent object.
	 * @param startingHealth
	 * @param parent
	 */
	public SimpleHealthAttribute(double startingHealth, ISimpleObject parent) {
		super(parent);
		this.myHealth = startingHealth;
	}
	
	@Override
	public void updateSelf(ISimpleUniverse universe) {
		checkForDeath(universe);
	}
	
	/**
	 * Change the value of this attribute's internal health.
	 * @param healthChange
	 */
	public void changeHealth(double healthChange) {
		System.out.println("OLD HEALTH = " + myHealth);
		this.myHealth += healthChange;
		System.out.println("NEW HEALTH = " + myHealth);
	}
	
	private void checkForDeath(ISimpleKiller killer) {
		if (isDead()) {
			killer.addToGraveYard(getParent());
		}
	}
	
	private boolean isDead() {
		return this.myHealth <= 0;
	}

	@Override
	public void receiveEvent(ISimpleEvent event) {
//********************OPTION 1**************************
//		SimpleHealthChangeEvent healthEvent;
//		try {
//			healthEvent = (SimpleHealthChangeEvent) event;
//			changeHealth(healthEvent.getDeltaHealth());
//			return;
//		} catch (Exception e) { }
		
//********************OPTION 2**************************
		if (event.getEventType().equals(SimpleEventType.HealthChange)) {
			SimpleHealthChangeEvent healthEvent = (SimpleHealthChangeEvent) event;
			changeHealth(healthEvent.getDeltaHealth());
			return;
		}
	}

}
