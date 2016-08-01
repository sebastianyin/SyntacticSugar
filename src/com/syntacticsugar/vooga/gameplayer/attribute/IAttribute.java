package com.syntacticsugar.vooga.gameplayer.attribute;

import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;
import com.syntacticsugar.vooga.gameplayer.universe.IGameUniverse;

public interface IAttribute {

	/**
	 * Updates the state of this Attribute within it's Universe.
	 * @param universe the universe
	 */
	public void updateSelf(IGameUniverse universe);
	
	/**
	 * Returns the game object that contains this Attribute (ie. it's parent object)
	 * @return the game object
	 */
	public IGameObject getParent();
	
	/**
	 * Set the parent game object for the this Attribute
	 * @param  IGameObject the object
	 */
	public void setParent(IGameObject parent);
	
	/**
	 * Return an identical copy of this Attribute without sharing references (useful
	 * for rebuilding from data).
	 * @return the copy of the attribute
	 */
	public IAttribute copyAttribute();
	
	
}
