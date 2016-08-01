package com.syntacticsugar.vooga.gameplayer.event;

import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;


public interface ICollisionEvent {
	
	/**
	 * Return the enum type of this event.
	 * @return
	 */
	public CollisionEventType getEventType();
	
	/**
	 * Execute this event on the specified Target attributes.
	 */
	public void executeEvent(IGameObject obj);

}
