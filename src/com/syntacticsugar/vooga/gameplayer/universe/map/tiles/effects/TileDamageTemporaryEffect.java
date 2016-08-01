package com.syntacticsugar.vooga.gameplayer.universe.map.tiles.effects;

import com.syntacticsugar.vooga.gameplayer.attribute.TimedDespawnAttribute;
import java.io.Serializable;

import com.syntacticsugar.vooga.gameplayer.event.implementations.HealthChangeEvent;
import com.syntacticsugar.vooga.gameplayer.event.implementations.ObjectSpawnEvent;
import com.syntacticsugar.vooga.gameplayer.objects.GameObject;
import com.syntacticsugar.vooga.gameplayer.objects.GameObjectType;
import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;
import com.syntacticsugar.vooga.gameplayer.universe.IGameUniverse;
import com.syntacticsugar.vooga.gameplayer.universe.map.IGameMap;

public class TileDamageTemporaryEffect extends AbstractTileEffect implements Serializable {

	private static final long serialVersionUID = 10L;

	private Double myDamage;
	
	private int myDelay;
	private int myFrameCount;
	
	private String myHitImagePath;

	private int myImagePersistenceLength;
	
	public TileDamageTemporaryEffect(Double d, int time) {
		myDamage = d;
		myDelay = time;
		myFrameCount = 0;
		myHitImagePath = null;
		myImagePersistenceLength = 20;
	}

	@Override
	protected void doEffect(IGameUniverse universe) {
		if (myFrameCount >= myDelay) {
			IGameMap map = universe.getMap();
			for (IGameObject obj: universe.getGameObjects()){
				if (map.getTile(obj.getBoundingBox().getPoint()).equals(myTile)) {
					HealthChangeEvent health = new HealthChangeEvent(myDamage);
					health.executeEvent(obj);
				}
			}
			if (myHitImagePath != null) {
				IGameObject obj = new GameObject(GameObjectType.ITEM, myTile.getPoint(), map.getTileSize(), map.getTileSize(), myHitImagePath);
				TimedDespawnAttribute timer = new TimedDespawnAttribute();
				timer.setTimeHere(myImagePersistenceLength);
				obj.addAttribute(timer);
				timer.setParent(obj);
				ObjectSpawnEvent event = new ObjectSpawnEvent(obj);
				universe.postEvent(event);
			}
			
			myTile.setTileEffect(null);
		}
		myFrameCount++;
	}

	@Override
	public String getEffectName() {
		return this.getClass().getSimpleName().substring(4, this.getClass().getSimpleName().length() - 6);
	}

	public String getHitImagePath() {
		return myHitImagePath;
	}
	
	public void setHitImagePath(String hitImagePath) {
		this.myHitImagePath = hitImagePath;
	}
	
	public int getImagePersistenceLength() {
		return myImagePersistenceLength;
	}
	
	public void setImagePersistenceLength(int ImagePersistenceLength) {
		this.myImagePersistenceLength = ImagePersistenceLength;
	}
	
}
