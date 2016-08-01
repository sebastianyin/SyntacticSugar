package com.syntacticsugar.vooga.gameplayer.attribute.movement;

import java.awt.Point;
import java.util.List;

import com.syntacticsugar.vooga.authoring.parameters.EditableClass;
import com.syntacticsugar.vooga.gameplayer.attribute.IAttribute;
import com.syntacticsugar.vooga.gameplayer.universe.map.IGameMap;

@EditableClass (
		className = "AI Player Following"
		)
public class AIPlayerFollowAttribute extends AIMovementAttribute {

	public AIPlayerFollowAttribute() {
		super();
	}
	
	private AIPlayerFollowAttribute(AIPlayerFollowAttribute toCopy) {
		super(toCopy);
	}
	
	@Override
	protected List<Point> getDestinations(IGameMap map) {
		return map.getPlayerLocations();
	}
	
	@Override
	public IAttribute copyAttribute() {
		return new AIPlayerFollowAttribute(this);
	}
	
}
