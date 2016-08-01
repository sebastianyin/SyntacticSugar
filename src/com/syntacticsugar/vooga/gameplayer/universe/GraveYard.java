package com.syntacticsugar.vooga.gameplayer.universe;

import java.util.ArrayList;
import java.util.Collection;

import com.syntacticsugar.vooga.gameplayer.attribute.MoneyAttribute;
import com.syntacticsugar.vooga.gameplayer.attribute.ScoreAttribute;
import com.syntacticsugar.vooga.gameplayer.event.GameEventListener;
import com.syntacticsugar.vooga.gameplayer.event.IEventListener;
import com.syntacticsugar.vooga.gameplayer.event.IGameEvent;
import com.syntacticsugar.vooga.gameplayer.event.implementations.MoneyChangeEvent;
import com.syntacticsugar.vooga.gameplayer.event.implementations.ObjectDespawnEvent;
import com.syntacticsugar.vooga.gameplayer.event.implementations.ScoreChangeEvent;
import com.syntacticsugar.vooga.gameplayer.manager.IEventManager;
import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;
import com.syntacticsugar.vooga.gameplayer.view.IViewRemover;
import com.syntacticsugar.vooga.util.ResourceManager;

public class GraveYard implements IYard<IViewRemover>, GameEventListener, IEventListener {

	private Collection<IGameObject> objectsInYard;
	private IObjectRemover myUniverse;
	
	private IEventPoster myPoster;

	public GraveYard(IObjectRemover universe) {
		objectsInYard = new ArrayList<IGameObject>();
		myUniverse = universe;


	}
	

	@Override
	public void registerEventManager(IEventManager eventmanager) {
		eventmanager.registerListener(this);
		myPoster = eventmanager;
		
	}

	@Override
	public void alterUniverse(IViewRemover remover) {

		for (IGameObject obj : objectsInYard) {
			remover.removeViewObject(obj);
			myUniverse.removeGameObject(obj);
			
		}
		objectsInYard.clear();

	}

	@Override
	public void addToYard(IGameObject obj) {
		objectsInYard.add(obj);

	}


	@Override
	public void onEvent(IGameEvent e) {
		try {
			ObjectDespawnEvent event = (ObjectDespawnEvent) e;
			IGameObject obj = event.getObj();
			addToYard(obj);
			
			ScoreAttribute score = (ScoreAttribute) obj.getAttributes().get(ResourceManager.getString(ScoreAttribute.class.getSimpleName()));
			if (score != null) {
				Integer scoreNum = score.getScore();
				myPoster.postEvent(new ScoreChangeEvent(scoreNum));
			}
			
			MoneyAttribute money = (MoneyAttribute) obj.getAttributes().get(ResourceManager.getString(MoneyAttribute.class.getSimpleName()));
			if (money != null) {
				Integer moneyAmt = money.getMoney();
				myPoster.postEvent(new MoneyChangeEvent(moneyAmt));
			}
		}
		catch (ClassCastException ex) {
			
		}

	}


}
