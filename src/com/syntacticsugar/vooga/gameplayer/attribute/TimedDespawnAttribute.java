package com.syntacticsugar.vooga.gameplayer.attribute;

import com.syntacticsugar.vooga.authoring.parameters.EditableClass;
import com.syntacticsugar.vooga.authoring.parameters.EditableField;
import com.syntacticsugar.vooga.authoring.parameters.InputParser;
import com.syntacticsugar.vooga.authoring.parameters.InputTypeException;
import com.syntacticsugar.vooga.gameplayer.event.implementations.ObjectDespawnEvent;
import com.syntacticsugar.vooga.gameplayer.universe.IGameUniverse;


@EditableClass (
		className = "Time Despawn Value"
	)
public class TimedDespawnAttribute extends AbstractAttribute {
	
	private int myFrameCount;
	private int timeHere;
	
	public TimedDespawnAttribute() {
		myFrameCount = 0;
	}
	
	private TimedDespawnAttribute(TimedDespawnAttribute toCopy) {
		super(toCopy);
		this.myFrameCount = toCopy.myFrameCount;
		this.timeHere = toCopy.timeHere;
	}
	
	@Override
	protected void setDefaults() {
		this.myFrameCount = 180;
	}
	
	public void setTimeHere (int time) {
		this.timeHere = time;
	}

	@Override
	public void updateSelf(IGameUniverse universe) {
		if (myFrameCount >= timeHere) {
			ObjectDespawnEvent event = new ObjectDespawnEvent(getParent());
			universe.postEvent(event);
		}
		myFrameCount++;
	}
	
	
	/**		  	      EDIT TAGS	     		    **/
	/** *************************************** **/
	
	@EditableField (	
		inputLabel = "Duration (Frames)",
		defaultVal = "180"
		)
	private void editTimeHere(String arg) {
		try {
			this.timeHere = InputParser.parseAsInt(arg);
		} catch (InputTypeException e) { 	}
	}
	
	@Override
	public IAttribute copyAttribute() {
		return new TimedDespawnAttribute(this);
	}
	
}
