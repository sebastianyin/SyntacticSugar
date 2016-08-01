package com.syntacticsugar.vooga.gameplayer.event.implementations;

import java.util.Map;

import com.syntacticsugar.vooga.authoring.parameters.EditableClass;
import com.syntacticsugar.vooga.authoring.parameters.EditableField;
import com.syntacticsugar.vooga.authoring.parameters.InputParser;
import com.syntacticsugar.vooga.gameplayer.attribute.HealthAttribute;
import com.syntacticsugar.vooga.gameplayer.attribute.IAttribute;
import com.syntacticsugar.vooga.gameplayer.event.CollisionEvent;
import com.syntacticsugar.vooga.gameplayer.event.CollisionEventType;
import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;
import com.syntacticsugar.vooga.util.ResourceManager;

@EditableClass(
		className = "Health Change Event"
		)
public class HealthChangeEvent extends CollisionEvent {

	private final String TARGET_ATTRIBUTE = ResourceManager.getString("HealthAttribute");
	private double myDeltaHealth;
	
	public HealthChangeEvent(Double deltaHealth) {
		super(CollisionEventType.HealthChange);
		this.myDeltaHealth = deltaHealth;
	}
	
	public HealthChangeEvent() {
		super(CollisionEventType.HealthChange);
	}
	
	@Override
	protected void setDefaults() {
		this.myDeltaHealth = -50;
	}
	
	@Override
	public void executeEvent(IGameObject obj) {
		Map<String, IAttribute> targetAttributes = obj.getAttributes();
		HealthAttribute health = (HealthAttribute) targetAttributes.get(TARGET_ATTRIBUTE);
		if (health != null) {
			health.changeHealth(myDeltaHealth);
		}
	}
	
	public double getDeltaHealth() {
		return this.myDeltaHealth;
	}
	
	
	/**		  	      EDIT TAGS	     		    **/
	/** *************************************** **/

	@EditableField(
		inputLabel = "Health Change Value",
		defaultVal = "-50.0"
	)
	private void editMaxHealth(String arg) {
		try {
			this.myDeltaHealth = InputParser.parseAsDouble(arg);
		} catch (NumberFormatException e) {	}
	}


}
