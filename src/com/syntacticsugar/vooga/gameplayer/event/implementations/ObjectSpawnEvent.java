package com.syntacticsugar.vooga.gameplayer.event.implementations;

import com.syntacticsugar.vooga.gameplayer.event.GameEvent;
import com.syntacticsugar.vooga.gameplayer.event.GameEventType;
import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;

public class ObjectSpawnEvent extends GameEvent {
	
	private IGameObject myObj;
	
	public ObjectSpawnEvent(IGameObject obj) {
		super(GameEventType.ObjectSpawn);
		
		myObj = obj;
	}
	
	public IGameObject getObj() {
		return myObj;
	}

}
