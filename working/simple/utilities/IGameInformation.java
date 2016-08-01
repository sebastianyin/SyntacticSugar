package simple.utilities;

import java.util.Collection;

import simple.obj.ISimpleObject;

public interface IGameInformation {
	
	public void updateGameInformation(Collection<ISimpleObject> universe);
	
	public void getObservableStats();

}
