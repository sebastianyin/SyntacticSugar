package simple.utilities;

import java.util.Collection;

import javafx.collections.ObservableList;
import simple.obj.ISimpleObject;

public class GameInformation implements IGameInformation {

	private ObservableList<IStat> myStats;

	@Override
	public void updateGameInformation(Collection<ISimpleObject> universe) {
		for (IStat stat : myStats) {
			for (ISimpleObject obj : universe) {
				stat.receiveObject(obj);
			}
		}

	}

	@Override
	public void getObservableStats() {
		// TODO Auto-generated method stub
		
	}
	
	

}
