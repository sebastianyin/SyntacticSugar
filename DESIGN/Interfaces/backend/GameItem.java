package Objects.Items;

import Events.GameEvent;
import Objects.Interfaces.IItem;

public abstract class GameItem implements IItem {

	@Override
	public abstract GameEvent use();

}
