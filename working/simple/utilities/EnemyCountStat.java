package simple.utilities;

import simple.obj.ISimpleObject;
import simple.obj.SimpleObjectType;

public class EnemyCountStat implements IStat{
	
	private int enemyCount;

	public EnemyCountStat(){
		enemyCount = 0;
	}

	@Override
	public void receiveObject(ISimpleObject object) {
		if(object.getType().equals(SimpleObjectType.ENEMY)){
			enemyCount++;
		}		
	}

}
