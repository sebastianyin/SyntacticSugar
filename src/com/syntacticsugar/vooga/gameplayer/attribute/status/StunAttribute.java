package com.syntacticsugar.vooga.gameplayer.attribute.status;

import com.syntacticsugar.vooga.authoring.parameters.EditableClass;
import com.syntacticsugar.vooga.gameplayer.attribute.IAttribute;
import com.syntacticsugar.vooga.gameplayer.attribute.movement.AbstractMovementAttribute;
import com.syntacticsugar.vooga.util.ResourceManager;

@EditableClass (
		className = "Stun Effect"
		)
public class StunAttribute extends StatusEffectAttribute {
	
	private AbstractMovementAttribute move;
	
	private boolean didStun;
	
	public StunAttribute() {
		super();
	}
	
	public StunAttribute(Integer time) {
		super(time);
		this.move = null;
		this.didStun = false;
	}
	
	private StunAttribute(StunAttribute toCopy) {
		super(toCopy);
		this.move = toCopy.move;
		this.didStun = toCopy.didStun;
	}

	@Override
	protected void startStatus() {
		try {
			move = (AbstractMovementAttribute) getParent().getAttributes().get(ResourceManager.getString(AbstractMovementAttribute.class.getSimpleName()));
			if (move.getSpeed() > .000001) {
				move.setSpeed(move.getSpeed() * .000001);
				didStun = true;
			}
		}
		catch (Exception ex) {
			
		}
	}

	@Override
	protected void endStatus() {
		if (move != null && didStun) {
			move.setSpeed(move.getSpeed() / .000001);
		}
	}
	
	@Override
	public IAttribute copyAttribute() {
		return new StunAttribute(this);
	}
	
}
