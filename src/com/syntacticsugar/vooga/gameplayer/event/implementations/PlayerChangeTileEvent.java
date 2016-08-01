package com.syntacticsugar.vooga.gameplayer.event.implementations;

import java.awt.Point;

import com.syntacticsugar.vooga.gameplayer.event.GameEvent;
import com.syntacticsugar.vooga.gameplayer.event.GameEventType;

public class PlayerChangeTileEvent extends GameEvent {

	private Point myOldPoint;
	private Point myNewPoint;
	
	public PlayerChangeTileEvent(Point oldPoint, Point newPoint) {
		super(GameEventType.PlayerMoved);
		this.myOldPoint = oldPoint;
		this.myNewPoint = newPoint;
	}

	public Point getOld() {
		return myOldPoint;
	}
	
	public Point getNew() {
		return myNewPoint;
	}
	
}
