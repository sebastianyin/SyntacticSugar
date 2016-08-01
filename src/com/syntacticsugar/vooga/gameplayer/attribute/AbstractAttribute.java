package com.syntacticsugar.vooga.gameplayer.attribute;

import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;
import com.syntacticsugar.vooga.gameplayer.universe.IGameUniverse;

public abstract class AbstractAttribute implements IAttribute {

	private IGameObject myParent;
	
	/**
	 * Construct a new SimpleAttribute object with a null parent.
	 * @param parent
	 */
	public AbstractAttribute() {
		this.myParent = null;
	}
	
	/**
	 * Helper constructor used to copy attributes for use in data reconstruction.
	 * @param toCopy
	 */
	public AbstractAttribute(AbstractAttribute toCopy) {
		this.myParent = null;
	}

	/**
	 * Set any necessary instance variables for this object explicitly 
	 * upon construction. Used to avoid null pointers if user input is 
	 * invalid.
	 */
	protected abstract void setDefaults();
	
	@Override
	public abstract void updateSelf(IGameUniverse universe);

	@Override
	public IGameObject getParent() {
		return this.myParent;
	}
	
	@Override
	public void setParent(IGameObject parent) {
		myParent = parent;
	}

	
	
}
