package com.syntacticsugar.vooga.gameplayer.event;

import java.util.EventListener;

public interface GameEventListener extends EventListener{

	public void onEvent(IGameEvent e);
}
