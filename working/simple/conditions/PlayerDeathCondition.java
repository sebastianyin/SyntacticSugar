package simple.conditions;

import java.util.Collection;

import simple.obj.ISimpleObject;
import simple.obj.SimpleObjectType;

public class PlayerDeathCondition implements ISimpleCondition {

	private SimpleConditionType type = SimpleConditionType.LOSING;
	private int playersAlive;

	@Override
	public boolean isConditionMet() {
		return playersAlive <= 0;
	}

	public boolean checkCondition(Collection<ISimpleObject> universe) {
		playersAlive = 0;

		for (ISimpleObject object : universe) {
			if (object.getType().equals(SimpleObjectType.PLAYER)) {
				playersAlive++;
				break;
			}
		}
		return isConditionMet();
	}

	@Override
	public SimpleConditionType returnType() {
		return type;
	}


}
