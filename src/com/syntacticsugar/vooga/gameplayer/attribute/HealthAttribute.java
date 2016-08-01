package com.syntacticsugar.vooga.gameplayer.attribute;

import com.syntacticsugar.vooga.authoring.parameters.EditableClass;
import com.syntacticsugar.vooga.authoring.parameters.EditableField;
import com.syntacticsugar.vooga.gameplayer.event.implementations.ObjectDespawnEvent;
import com.syntacticsugar.vooga.gameplayer.universe.IEventPoster;
import com.syntacticsugar.vooga.gameplayer.universe.IGameUniverse;
import com.syntacticsugar.vooga.util.ResourceManager;

@EditableClass (
	className = "Health Settings"
)
public class HealthAttribute extends AbstractAttribute {
	
	private static final String HEALTH_CHANGE_FREQ = "health_change_freq";

	private double myMaxHealth;
	
	private double myHealth;
	private int myInvincibleFrames;
	private int myHealthChangeFreq;
	
	/**
	 * Construct a health attribute with the specified starting health 
	 * and a null parent object
	 * @param startingHealth
	 */
	public HealthAttribute() {
		super();
		setDefaults();
	}
	
	private HealthAttribute(HealthAttribute toCopy) {
		super(toCopy);
		this.myMaxHealth = toCopy.myMaxHealth;
		this.myHealth = toCopy.myHealth;
		this.myInvincibleFrames = toCopy.myInvincibleFrames;
		this.myHealthChangeFreq = toCopy.myHealthChangeFreq;
	}
	
	public void setHealth(double set) {
		this.myHealth = set;
		this.myMaxHealth = set;
	}
	
	@Override
	public void setDefaults() {
		this.myInvincibleFrames = Integer.parseInt(ResourceManager.getString(HEALTH_CHANGE_FREQ));
		this.myMaxHealth = 100;
	}

	/**
	 * Checks on every frame to see if the health has gone to zero. Despawns
	 * parent object if health is gone.
	 * @param universe
	 */
	@Override
	public void updateSelf(IGameUniverse universe) {
		checkForDeath(universe);
		if (myInvincibleFrames > 0) {
			myInvincibleFrames -= 1;
		}
	}

	/**
	 * Change the value of this attribute's internal health.
	 * @param healthChange
	 */
	public void changeHealth(Double healthChange) {
		if (healthChange >= 0) {
			restoreHealth(healthChange);
		}
		else {	
			takeDamage(healthChange);
		}
	}

	private void takeDamage(Double damage) {
		if (myInvincibleFrames > 0) {
			return;
		}
		this.myHealth += damage;
		setInvincible(myHealthChangeFreq);
	}

	private void restoreHealth(Double healthInc) {
		if (myHealth + healthInc >= myMaxHealth) {
			myHealth = myMaxHealth;
			return;
		}
		myHealth += healthInc;
	}

	private void checkForDeath(IEventPoster killer) {
		if (isDead()) {
			ObjectDespawnEvent event = new ObjectDespawnEvent(getParent());
			killer.postEvent(event);
		}
	}

	private void setInvincible(int numFrames) {
		this.myInvincibleFrames = numFrames;
	}

	private boolean isDead() {
		return this.myHealth <= 0;
	}
	
	
	/**		  	      EDIT TAGS	     		    **/
	/** *************************************** **/

	@EditableField(
		inputLabel = "Max Health",
		defaultVal = "100"
	)
	private void editMaxHealth(String arg) {
		try {
			double val = Double.parseDouble(arg);
			this.myHealth = val;
			this.myMaxHealth = val;
		} catch (NumberFormatException e) {	}
	}
	
	@Override
	public IAttribute copyAttribute() {
		return new HealthAttribute(this);
	}

}
