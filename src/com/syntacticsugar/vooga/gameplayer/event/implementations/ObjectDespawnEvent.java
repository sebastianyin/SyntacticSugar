package com.syntacticsugar.vooga.gameplayer.event.implementations;

import com.syntacticsugar.vooga.gameplayer.event.GameEvent;
import com.syntacticsugar.vooga.gameplayer.event.GameEventType;
import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;

public class ObjectDespawnEvent extends GameEvent {
	
	private IGameObject myObj;

	public ObjectDespawnEvent(IGameObject obj) {
		super(GameEventType.ObjectDespawn);
		myObj = obj;
	}
	
	public IGameObject getObj() {
		return myObj;
	}

}
