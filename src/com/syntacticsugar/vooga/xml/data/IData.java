package com.syntacticsugar.vooga.xml.data;

import java.util.Collection;
import java.util.Map;

import com.syntacticsugar.vooga.gameplayer.attribute.IAttribute;
import com.syntacticsugar.vooga.gameplayer.event.ICollisionEvent;
import com.syntacticsugar.vooga.gameplayer.objects.GameObjectType;

public interface IData {
	
	public String getImagePath();
	
	public void setImagePath(String imagePath);
	
	public GameObjectType getType();
	
	public void setType(GameObjectType type);
	
	public Collection<IAttribute> getAttributes();
	
	public Map<GameObjectType, Collection<ICollisionEvent>> getCollisionMap();
	
	public void setObjectName(String name);
	
	public String getObjectName();
	
	public void setAttributes(Collection<IAttribute> attributes);

	public void setCollisionMap(Map<GameObjectType, Collection<ICollisionEvent>> collisionMap);

}
