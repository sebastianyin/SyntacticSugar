package Events;

import java.util.Map;

import Attributes.GameAttribute;
import Events.Interfaces.IEvent;

public abstract class GameEvent implements IEvent {
	
	public abstract void handle(Map<String, GameAttribute> map);

}
