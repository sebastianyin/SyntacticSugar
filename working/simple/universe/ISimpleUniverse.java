package simple.universe;

import simple.universe.map.ISimpleGameMap;
import simple.universe.userinput.IKeyInputProcessor;

public interface ISimpleUniverse extends ISimpleObjectContainer,
										 ISimpleObjectAdder,
										 ISimpleObjectRemover,
										 ISimpleGameMap, 
										 IKeyInputProcessor,
										 ISimpleSpawner,
										 ISimpleKiller {

	/**
	 * A SimpleUniverse (anything that implements this interface) must contain a 
	 * Collection of GameObjects, add/remove methods, an implementation of ISimpleGameMap,
	 * and be able to send key press/release/mouse events directly to it's objects
	 */

	
	
}
