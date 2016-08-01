package com.syntacticsugar.vooga.gameplayer.objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.syntacticsugar.vooga.gameplayer.attribute.IAttribute;
import com.syntacticsugar.vooga.gameplayer.attribute.movement.Direction;
import com.syntacticsugar.vooga.gameplayer.event.ICollisionEvent;
import com.syntacticsugar.vooga.gameplayer.universe.IGameUniverse;
import com.syntacticsugar.vooga.util.ResourceManager;
import com.syntacticsugar.vooga.xml.data.ObjectData;

import javafx.geometry.Point2D;

public class GameObject extends AbstractViewableObject implements IGameObject {

	protected GameObjectType myType;
	private Map<String, IAttribute> myAttributeMap;
	private Map<GameObjectType, Collection<ICollisionEvent>> myCollisionEventMap;

	public GameObject(GameObjectType type, Point2D point, double width, double height, String path) {
		super(point, width, height, path);
		myType = type;
		myAttributeMap = new HashMap<String, IAttribute>();
		myCollisionEventMap = new HashMap<GameObjectType, Collection<ICollisionEvent>>();
		getBoundingBox().setDirection(Direction.DOWN);
	}
	
	public GameObject(ObjectData data) {
		super(data.getSpawnPoint(), data.getWidth(), data.getHeight(), data.getImagePath());
		//Collection<IAttribute> attributes = data.getAttributes();
		Collection<IAttribute> attributes = new ArrayList<IAttribute>();
		for (IAttribute i : data.getAttributes()) {
			attributes.add(i.copyAttribute());
		}
		Map<GameObjectType, Collection<ICollisionEvent>> collisions = data.getCollisionMap();
		myType = data.getType();
		if (data.getDirection() == null) {
			getBoundingBox().setDirection(Direction.DOWN);
		}
		else {
			getBoundingBox().setDirection(data.getDirection());
		}
		myAttributeMap = new HashMap<String, IAttribute>();
		myCollisionEventMap = new HashMap<GameObjectType, Collection<ICollisionEvent>>();
		for (IAttribute att: attributes) {
			att.setParent(this);
			addAttribute(att);
		}
		myCollisionEventMap.putAll(collisions);
		
	}

	@Override
	public void updateSelf(IGameUniverse universe) {
		for (IAttribute attribute : myAttributeMap.values()) {
			attribute.updateSelf(universe);
		}
	}

	private Collection<ICollisionEvent> getEventsFromCollision(GameObjectType type) {
		return myCollisionEventMap.get(type);
	}

	@Override
	public void addCollisionBinding(GameObjectType type, ICollisionEvent event) {
		if (myCollisionEventMap.containsKey(type)) {
			getEventsFromCollision(type).add(event);
		} else {
			Collection<ICollisionEvent> onCollisionEvents = new ArrayList<ICollisionEvent>();
			onCollisionEvents.add(event);
			myCollisionEventMap.put(type, onCollisionEvents);
		}
	}
	
	@Override
	public void onCollision(IGameObject obj) {
		if (getEventsFromCollision(obj.getType()) != null) {
			for (ICollisionEvent e : getEventsFromCollision(obj.getType())) {
				e.executeEvent(obj);
			}
		}
	}

	@Override
	public GameObjectType getType() {
		return myType;
	}

	@Override
	public Map<String, IAttribute> getAttributes() {
		return myAttributeMap;
	}
	
	@Override
	public Map<GameObjectType, Collection<ICollisionEvent>> getCollisionMap() {
		return Collections.unmodifiableMap(myCollisionEventMap);
	}

	@Override
	public void addAttribute(IAttribute attribute) {
		String key = ResourceManager.getString(attribute.getClass().getSimpleName());
		myAttributeMap.put(key, attribute);
	}
	
	@Override
	public void removeAttribute(IAttribute attribute) {
		String key = ResourceManager.getString(attribute.getClass().getSimpleName());
		myAttributeMap.remove(key);
	}

	@Override
	public GameObjectType getFoe() {
		String type = ResourceManager.getString(getType().toString() + "_FOE");
		return GameObjectType.valueOf(type);
	}

}
