package com.syntacticsugar.vooga.gameplayer.universe.score;

import java.util.Observable;

import com.syntacticsugar.vooga.gameplayer.event.IGameEvent;
import com.syntacticsugar.vooga.gameplayer.event.implementations.ScoreChangeEvent;
import com.syntacticsugar.vooga.gameplayer.event.implementations.ScoreUpdateEvent;
import com.syntacticsugar.vooga.gameplayer.universe.IEventPoster;
import com.syntacticsugar.vooga.xml.data.LevelSettings;

public class Score extends Observable implements IScore {

	int myScore;
	
	IEventPoster myPoster;

	public Score(LevelSettings settings) {
		myScore = 0;
	}
	
	public void registerEventManager(IEventPoster poster) {
		myPoster = poster;
	}

	@Override
	public void onEvent(IGameEvent e) {
		try {
			ScoreChangeEvent event = (ScoreChangeEvent) e;
			changeScore(event);
			ScoreUpdateEvent ev2 = new ScoreUpdateEvent(myScore);
			myPoster.postEvent(ev2);
		} catch (ClassCastException ex) {

		}
	}

	private void changeScore(ScoreChangeEvent event) {
		myScore += event.getScore();
		setChanged();
		notifyObservers(myScore);
		System.out.println(myScore);
	}

	@Override
	public int getScore() {
		return myScore;
		
	}


}
