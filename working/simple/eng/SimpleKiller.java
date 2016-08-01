package simple.eng;

import simple.obj.ISimpleObject;
import simple.universe.ISimpleUniverse;

public class SimpleKiller {
	
	public static void removeGraveYard(ISimpleUniverse universe){
		for(ISimpleObject obj: universe.getGraveYard()){
			universe.removeGameObject(obj);
		}
	}

}
