package com.syntacticsugar.vooga.gameplayer.event;

public class GameEvent implements IGameEvent {
	
	private GameEventType myType;
	
	public GameEvent(GameEventType type) {
		myType = type;
	}

	@Override
	public GameEventType getEventType() {
		return myType;
	}

}
