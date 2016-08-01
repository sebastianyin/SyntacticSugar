package com.syntacticsugar.vooga.gameplayer.event.implementations;

import com.syntacticsugar.vooga.gameplayer.event.GameEvent;
import com.syntacticsugar.vooga.gameplayer.event.GameEventType;

public class ScoreUpdateEvent extends GameEvent {
	
	private int myScore;
	
	public ScoreUpdateEvent(int score) {
		super(GameEventType.ScoreUpdate);
		myScore = score;
	}
	
	public int getScore() {
		return myScore;
	}

}
