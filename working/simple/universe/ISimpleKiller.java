package simple.universe;

import java.util.Collection;

import simple.obj.ISimpleObject;

public interface ISimpleKiller {

	public void addToGraveYard(ISimpleObject toRemove);
	
	public void clearGraveYard();
	
	public Collection<ISimpleObject> getGraveYard();

}
