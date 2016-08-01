package simple.eng;

import simple.obj.ISimpleObject;
import simple.universe.ISimpleUniverse;

public class SimpleStateManager {

	public static void updateState(ISimpleUniverse universe) {
		for (ISimpleObject object : universe.getGameObjects()) {
			object.updateSelf(universe);
		}
	}

}
