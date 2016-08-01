package com.syntacticsugar.vooga.gameplayer.universe;

import java.util.ArrayList;
import java.util.Collection;

import com.syntacticsugar.vooga.gameplayer.event.GameEventListener;
import com.syntacticsugar.vooga.gameplayer.event.IEventListener;
import com.syntacticsugar.vooga.gameplayer.event.IGameEvent;
import com.syntacticsugar.vooga.gameplayer.event.implementations.ObjectSpawnEvent;
import com.syntacticsugar.vooga.gameplayer.manager.IEventManager;
import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;
import com.syntacticsugar.vooga.gameplayer.view.IViewAdder;

public class SpawnYard implements IYard<IViewAdder>, GameEventListener, IEventListener {

	private Collection<IGameObject> objectsInYard;
	private IObjectAdder myUniverse;

	public SpawnYard(IObjectAdder universe) {
		objectsInYard = new ArrayList<IGameObject>();
		myUniverse = universe;
	}

	@Override
	public void registerEventManager(IEventManager eventmanager) {
		eventmanager.registerListener(this);

	}

	@Override
	public void addToYard(IGameObject obj) {
		objectsInYard.add(obj);
	}

	@Override
	public void alterUniverse(IViewAdder adder) {
		for (IGameObject obj : objectsInYard) {
			myUniverse.addGameObject(obj);
			adder.addViewObject(obj);
		}
		objectsInYard.clear();
	}

	@Override
	public void onEvent(IGameEvent e) {
		try {
			ObjectSpawnEvent event = (ObjectSpawnEvent) e;
			addToYard(event.getObj());
		} catch (Exception ex) {

		}
	}

}
