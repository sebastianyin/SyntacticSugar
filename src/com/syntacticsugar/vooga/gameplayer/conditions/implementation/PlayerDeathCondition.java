package com.syntacticsugar.vooga.gameplayer.conditions.implementation;

import com.syntacticsugar.vooga.gameplayer.conditions.AbstractCondition;
import com.syntacticsugar.vooga.gameplayer.conditions.ConditionType;
import com.syntacticsugar.vooga.gameplayer.event.IGameEvent;
import com.syntacticsugar.vooga.gameplayer.event.implementations.LevelChangeEvent;
import com.syntacticsugar.vooga.gameplayer.event.implementations.ObjectDespawnEvent;
import com.syntacticsugar.vooga.gameplayer.objects.GameObjectType;

public class PlayerDeathCondition extends AbstractCondition {

	public PlayerDeathCondition() {
		super(ConditionType.LOSING);
	}

	@Override
	protected void setDefaults() {

	}

	@Override
	public void onEvent(IGameEvent e) {
		try {
			ObjectDespawnEvent event = (ObjectDespawnEvent) e;
			if (event.getObj().getType().equals(GameObjectType.PLAYER)) {
				postEvent(new LevelChangeEvent(ConditionType.LOSING));
			}

		} catch (ClassCastException ce) {

		}

	}

}
