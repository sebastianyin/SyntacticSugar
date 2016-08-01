package com.syntacticsugar.vooga.gameplayer.event.implementations;

import com.syntacticsugar.vooga.gameplayer.event.GameEvent;
import com.syntacticsugar.vooga.gameplayer.event.GameEventType;

public class ScoreChangeEvent extends GameEvent {
	
	private Integer myScore;

	public ScoreChangeEvent(Integer score) {
		super(GameEventType.ScoreChange);
		myScore = score;
	}
	
	public Integer getScore() {
		return myScore;
	}

}
