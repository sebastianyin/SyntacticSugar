package com.syntacticsugar.vooga.gameplayer.attribute.status;

import com.syntacticsugar.vooga.authoring.parameters.EditableClass;
import com.syntacticsugar.vooga.authoring.parameters.EditableField;
import com.syntacticsugar.vooga.authoring.parameters.InputParser;
import com.syntacticsugar.vooga.authoring.parameters.InputTypeException;
import com.syntacticsugar.vooga.gameplayer.attribute.AbstractAttribute;
import com.syntacticsugar.vooga.gameplayer.attribute.IAttribute;
import com.syntacticsugar.vooga.gameplayer.universe.IGameUniverse;

@EditableClass (
		className = "Timed Status Effect"
		)
public abstract class StatusEffectAttribute extends AbstractAttribute {
	
	private int timeLeft;
	
	private boolean isStarted;
	
	public StatusEffectAttribute(int time) {
		super();
		this.timeLeft = time;
		this.isStarted = false;
	}
	
	public StatusEffectAttribute() {
		super();
	}
	
	protected StatusEffectAttribute(StatusEffectAttribute toCopy) {
		super(toCopy);
		this.timeLeft = toCopy.timeLeft;
		this.isStarted = toCopy.isStarted;
	}

	@Override
	protected void setDefaults() {
		this.timeLeft = 60;
		this.isStarted = false;
	}

	@Override
	public void updateSelf(IGameUniverse universe) {
		if (!isStarted) {
			startStatus();
			isStarted = true;
		}
		if (timeLeft <= 0) {
			kill();
		}
		timeLeft--;
	}
	
	protected abstract void startStatus();
	
	protected abstract void endStatus();
	
	public void kill() {
		endStatus();
		getParent().removeAttribute(this);
	}
	
	
	/**		  	      EDIT TAGS	     		    **/
	/** *************************************** **/
	
	@EditableField(
			inputLabel = "Frame Duration",
			defaultVal = "60"
			)
	private void editDuration(String arg) {
		try {
			this.timeLeft = InputParser.parseAsInt(arg);
		} catch (InputTypeException e) {	}
	}
	
	@Override
	public abstract IAttribute copyAttribute();
	
}
