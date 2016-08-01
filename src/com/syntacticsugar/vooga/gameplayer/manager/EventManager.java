package com.syntacticsugar.vooga.gameplayer.manager;

import java.util.ArrayList;
import java.util.List;

import com.syntacticsugar.vooga.gameplayer.event.GameEventListener;
import com.syntacticsugar.vooga.gameplayer.event.IGameEvent;

public class EventManager implements IEventManager {
	
	private List<GameEventListener> myListeners;
	
	public EventManager() {
		myListeners = new ArrayList<>();
	}

	@Override
	public void postEvent(IGameEvent event) {
		for (GameEventListener l: myListeners) {
			l.onEvent(event);
		}
	}

	@Override
	public void registerListener(GameEventListener obj) {
		myListeners.add(obj);
	}
	
	@Override
	public void removeListener(GameEventListener obj) {
		myListeners.remove(obj);
	}

}
