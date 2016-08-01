package com.syntacticsugar.vooga.gameplayer.attribute.status;

import com.syntacticsugar.vooga.authoring.parameters.EditableClass;
import com.syntacticsugar.vooga.authoring.parameters.EditableField;
import com.syntacticsugar.vooga.authoring.parameters.InputParser;
import com.syntacticsugar.vooga.authoring.parameters.InputTypeException;
import com.syntacticsugar.vooga.gameplayer.attribute.IAttribute;
import com.syntacticsugar.vooga.gameplayer.attribute.movement.AbstractMovementAttribute;
import com.syntacticsugar.vooga.util.ResourceManager;

@EditableClass (
		className = "Slowing Attribute"
		)
public class SlowAttribute extends StatusEffectAttribute {

	private double mySlow;
	
	private AbstractMovementAttribute move;
	
	public SlowAttribute() {
		super();
	}
	
	private SlowAttribute(SlowAttribute toCopy) {
		super(toCopy);
		this.mySlow = toCopy.mySlow;
		this.move = toCopy.move;
	}
	
	public SlowAttribute(Integer time, Double slow) {
		super(time);
		this.move = null;
		this.mySlow = slow;
	}

	@Override
	protected void setDefaults() {
		super.setDefaults();
		this.mySlow = 0.50;
	}
	
	protected void startStatus() {
		try {
			move = (AbstractMovementAttribute) getParent().getAttributes().get(ResourceManager.getString(AbstractMovementAttribute.class.getSimpleName()));
			move.setSpeed(move.getSpeed() * mySlow);
		}
		catch (Exception ex) {	}
	}
	
	protected void endStatus() {
		if (move != null) {
			move.setSpeed(move.getSpeed() / mySlow);
		}
	}

	
	/**		  	      EDIT TAGS	     		    **/
	/** *************************************** **/
	
	@EditableField(
			inputLabel = "Slow Factor",
			defaultVal = "0.50"
			)
	private void editSlow(String arg) {
		try {
			this.mySlow = InputParser.parseAsDouble(arg);
		} catch (InputTypeException e) {	}
	}

	@Override
	public IAttribute copyAttribute() {
		return new SlowAttribute(this);
	}
	
}
