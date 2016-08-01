package simple.universe;

import java.util.Collection;

import simple.obj.ISimpleObject;

public interface ISimpleSpawner {
	
	public void addToSpawnYard(ISimpleObject toAdd);
	
	public void clearSpawnYard();

	public Collection<ISimpleObject> getSpawnYard();
	
}
