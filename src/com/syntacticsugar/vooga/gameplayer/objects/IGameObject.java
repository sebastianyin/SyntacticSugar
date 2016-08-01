package com.syntacticsugar.vooga.gameplayer.objects;

import com.syntacticsugar.vooga.gameplayer.universe.IGameUniverse;

public interface IGameObject extends IAttributeCollection, ICollidable, IViewableObject {

	/**
	 * Update this object within the context of the whole universe.
	 * @param universe the universe
	 */
	public void updateSelf(IGameUniverse universe);
	
	/**
	 * Returns the Type of this object as specified by the GameObjectType enum.
	 * @return the object type
	 */
	public GameObjectType getType();
	
	/**
	 * Returns the type of this object's foe (as specified by the resource file).
	 * 
	 * @return the foe type
	 */
	public GameObjectType getFoe();

}