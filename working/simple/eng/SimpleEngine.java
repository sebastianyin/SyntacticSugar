package simple.eng;

import simple.eng.collisions.SimpleCollisionManager;
import simple.universe.ISimpleUniverse;

public class SimpleEngine {
	
	public static void frameUpdate(ISimpleUniverse universe) {
		SimpleCollisionManager.checkCollisions(universe.getGameObjects());
		SimpleStateManager.updateState(universe);
	}


}
