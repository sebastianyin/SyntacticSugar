package com.syntacticsugar.vooga.gameplayer.objects;

import java.util.Map;

import com.syntacticsugar.vooga.gameplayer.attribute.IAttribute;

public interface IAttributeCollection {

	/**
	 * Returns a Map of ISimpleAttribute objects contained
	 * by the specific SimpleGameObject instance.
	 * @return the map of attributes
	 */
	public Map<String, IAttribute> getAttributes();
	
	/**
	 * Adds an attribute to this SimpleAttributeContainer.
	 * @param attribute the attribute
	 */
	public void addAttribute(IAttribute attribute);
	
	/**
	 * Removes an attribute from this SimpleAttributeContainer
	 * @param attribute the attribute
	 */
	public void removeAttribute(IAttribute attribute);
	
}
