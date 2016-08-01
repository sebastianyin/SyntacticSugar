package simple.conditions;

import java.util.Collection;

import simple.obj.ISimpleObject;
import simple.obj.SimpleObjectType;

public class EnemyDeathCondition implements ISimpleCondition {

	private int enemiesAlive;
	private static final SimpleConditionType type = SimpleConditionType.WINNING;


	@Override
	public boolean isConditionMet() {
		return enemiesAlive <= 0;
	}

	@Override
	public boolean checkCondition(Collection<ISimpleObject> universe) {
		enemiesAlive = 0;
		for(ISimpleObject object: universe){
			if(object.getType().equals(SimpleObjectType.ENEMY)){
				enemiesAlive++;
			}
		}
		return isConditionMet();
	}

	@Override
	public SimpleConditionType returnType() {
		return type;
	}

}
