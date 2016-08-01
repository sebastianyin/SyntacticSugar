package com.syntacticsugar.vooga.gameplayer.universe.map.tiles.effects;

import java.io.Serializable;

import com.syntacticsugar.vooga.gameplayer.event.implementations.SlowEvent;
import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;
import com.syntacticsugar.vooga.gameplayer.universe.IGameUniverse;
import com.syntacticsugar.vooga.gameplayer.universe.map.IGameMap;

public class TileSlowEffect extends AbstractTileEffect implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double mySlow;
	private int myLength;

	public TileSlowEffect(Double s) {
		mySlow = s;
		myLength = 15;
	}

	public void setSlow(double slow) {
		mySlow = slow;
	}

	protected void doEffect(IGameUniverse universe) {
		IGameMap map = universe.getMap();
		for (IGameObject obj : universe.getGameObjects()) {
			if (map.getTile(obj.getBoundingBox().getPoint()).equals(myTile)) {
				SlowEvent slow = new SlowEvent(mySlow, myLength);
				slow.executeEvent(obj);
			}
		}
	}

	@Override
	public String getEffectName() {
		return this.getClass().getSimpleName().substring(4, this.getClass().getSimpleName().length() - 6);
	}

}
