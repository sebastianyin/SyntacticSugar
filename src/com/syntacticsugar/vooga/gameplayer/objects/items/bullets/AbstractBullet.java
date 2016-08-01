package com.syntacticsugar.vooga.gameplayer.objects.items.bullets;

import com.syntacticsugar.vooga.gameplayer.attribute.IAttribute;
import com.syntacticsugar.vooga.gameplayer.attribute.movement.ConstantMovementAttribute;
import com.syntacticsugar.vooga.gameplayer.event.implementations.HealthChangeEvent;
import com.syntacticsugar.vooga.gameplayer.event.implementations.ObjectDespawnEvent;
import com.syntacticsugar.vooga.gameplayer.objects.GameObject;
import com.syntacticsugar.vooga.gameplayer.objects.GameObjectType;
import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;
import com.syntacticsugar.vooga.gameplayer.universe.IGameUniverse;
import com.syntacticsugar.vooga.util.ResourceManager;

import javafx.geometry.Point2D;

public abstract class AbstractBullet extends GameObject {
	
	private boolean despawnFlag;
	
	private GameObjectType myFoe;
	
	public AbstractBullet(BulletParams params) {
		super(GameObjectType.ITEM, params.getStartPoint(), params.getWidth(), params.getHeight(), params.getImagePath());
		HealthChangeEvent bulletDamage = new HealthChangeEvent(-1.0 * params.getDamage());
		myFoe = params.getType();
		addAttribute(new ConstantMovementAttribute(params.getMove(), params.getSpeed()));
		addCollisionBinding(params.getType(), bulletDamage);
		for (IAttribute att: getAttributes().values()) {
			att.setParent(this);
		}
		despawnFlag = false;
	}
	
	@Override
	public void updateSelf(IGameUniverse universe) {
		super.updateSelf(universe);
		if (outOfBounds()) {
			despawnFlag = true;
		}
		if (despawnFlag) {
			ObjectDespawnEvent event = new ObjectDespawnEvent(this);
			universe.postEvent(event);
		}
	};
	
	@Override
	public void onCollision(IGameObject obj){
		super.onCollision(obj);
		
		if (obj.getType().equals(myFoe)) {
			setDespawnFlag(true);
		}
	}
	
	protected void setDespawnFlag(boolean flag) {
		this.despawnFlag = flag;
	}
	
	private boolean outOfBounds() {
		Point2D point = getPoint();
		boolean cond1 = (point.getX() < 0 || point.getX() > Double.parseDouble(ResourceManager.getString("WIDTH")));
		boolean cond2 = (point.getY() < 0 || point.getY() > Double.parseDouble(ResourceManager.getString("HEIGHT")));
		return (cond1 || cond2);
	}

}
