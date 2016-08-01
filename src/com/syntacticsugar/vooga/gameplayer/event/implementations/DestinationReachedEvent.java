package com.syntacticsugar.vooga.gameplayer.event.implementations;

import com.syntacticsugar.vooga.gameplayer.event.GameEvent;
import com.syntacticsugar.vooga.gameplayer.event.GameEventType;

public class DestinationReachedEvent extends GameEvent{

	public DestinationReachedEvent() {
		super(GameEventType.DestinationReached);
		
	}

}
