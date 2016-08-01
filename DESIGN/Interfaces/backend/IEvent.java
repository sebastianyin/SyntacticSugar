package Events.Interfaces;

import java.util.Map;

import Attributes.GameAttribute;

public interface IEvent {

	public void handle(Map<String, GameAttribute> map);
	
}
