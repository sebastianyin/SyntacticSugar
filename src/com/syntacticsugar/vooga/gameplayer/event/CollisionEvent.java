package com.syntacticsugar.vooga.gameplayer.event;

import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;

public abstract class CollisionEvent implements ICollisionEvent {

	private CollisionEventType myType;
	
	public CollisionEvent(CollisionEventType type) {
		this.myType = type;
		setDefaults();
	}
	
	@Override
	public CollisionEventType getEventType() {
		return this.myType;
	}
	
	@Override
	abstract public void executeEvent(IGameObject obj);
	
	abstract protected void setDefaults();
}
