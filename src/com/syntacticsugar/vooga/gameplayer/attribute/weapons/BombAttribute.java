package com.syntacticsugar.vooga.gameplayer.attribute.weapons;

import com.syntacticsugar.vooga.authoring.parameters.EditableClass;
import com.syntacticsugar.vooga.authoring.parameters.EditableField;
import com.syntacticsugar.vooga.authoring.parameters.InputParser;
import com.syntacticsugar.vooga.authoring.parameters.InputTypeException;
import com.syntacticsugar.vooga.gameplayer.attribute.AbstractAttribute;
import com.syntacticsugar.vooga.gameplayer.attribute.IAttribute;
import com.syntacticsugar.vooga.gameplayer.attribute.TimedDespawnAttribute;
import com.syntacticsugar.vooga.gameplayer.attribute.control.IUserControlAttribute;
import com.syntacticsugar.vooga.gameplayer.event.implementations.ObjectSpawnEvent;
import com.syntacticsugar.vooga.gameplayer.objects.GameObject;
import com.syntacticsugar.vooga.gameplayer.objects.GameObjectType;
import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;
import com.syntacticsugar.vooga.gameplayer.universe.IEventPoster;
import com.syntacticsugar.vooga.gameplayer.universe.IGameUniverse;
import com.syntacticsugar.vooga.gameplayer.universe.map.IGameMap;
import com.syntacticsugar.vooga.gameplayer.universe.map.tiles.effects.TileDamageTemporaryEffect;
import com.syntacticsugar.vooga.gameplayer.universe.userinput.IKeyInputStorage;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

@EditableClass (
		className = "Bomb"
		)
public class BombAttribute extends AbstractAttribute implements IUserControlAttribute {
	
	protected double myDamage;
	protected String myImagePath;
	protected KeyCode myFireKeyCode;
	protected boolean isFireKeyPressed;
	protected int myFuse; 
	
	private int fireFrameDelay;
	private int delayFrameCounter;
	
	public BombAttribute() {
		super();
	}
	
	private BombAttribute(BombAttribute toCopy) {
		super(toCopy);
		this.myDamage = toCopy.myDamage;
		this.myImagePath = toCopy.myImagePath;
		this.myFireKeyCode = toCopy.myFireKeyCode;
		this.isFireKeyPressed = toCopy.isFireKeyPressed;
		this.myFuse = toCopy.myFuse;
		this.fireFrameDelay = toCopy.fireFrameDelay;
		this.delayFrameCounter = toCopy.delayFrameCounter;
	}
	
	public BombAttribute(String bulletImagePath, double bulletDamage, KeyCode fireKeyCode,
				int fuse, int fireDelay) {
		myImagePath = bulletImagePath;
		myDamage = bulletDamage;
		myFireKeyCode = fireKeyCode;
		myFuse = fuse;
		fireFrameDelay = fireDelay;
		
	}
	public void setDamage(int damage) {
		myDamage = damage;
	}
	
	public double getDamage() {
		return myDamage;
	}
	
	@Override
	protected void setDefaults() {
		myDamage = 30.0;
		myImagePath = "scenery_black.png";
		myFireKeyCode = KeyCode.SPACE;
		isFireKeyPressed = false;
		myFuse = 60;
		fireFrameDelay = 90;
		delayFrameCounter = 0;
	}

	@Override
	public void updateSelf(IGameUniverse universe) {
		updateKeyInput(universe);
		if (isFireKeyPressed) {
			if (fireConditionsMet()) {
				makeBomb(universe);
				delayFrameCounter = 0;
			}
		}
		delayFrameCounter++;
	}
	
	private void makeBomb(IGameUniverse universe) {
		TileDamageTemporaryEffect effect = new TileDamageTemporaryEffect(myDamage, myFuse);
		effect.setHitImagePath("scenery_black.png");
		effect.setImagePersistenceLength(20);
		IGameMap map = universe.getMap(); 
		Point2D tpoint = getParent().getPoint();
		try {
			map.getTileFromIJ(map.getMapIndexFromCoordinate(tpoint)).setTileEffect(effect);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			Point2D point = map.getCoordinateFromMapIndex(map.getMapIndexFromCoordinate(getParent().getPoint()));
			IGameObject obj = new GameObject(GameObjectType.ITEM, point, map.getTileSize(), map.getTileSize(), myImagePath);
			TimedDespawnAttribute timer = new TimedDespawnAttribute();
			timer.setTimeHere(myFuse);
			obj.addAttribute(timer);
			timer.setParent(obj);
			postBomb(universe, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void postBomb(IEventPoster poster, IGameObject obj) {
		ObjectSpawnEvent event = new ObjectSpawnEvent(obj);
		poster.postEvent(event);
	}
	
	@Override
	public void updateKeyInput(IKeyInputStorage universeKeyInput) {
		 isFireKeyPressed = universeKeyInput.getCurrentKeyInput().contains(myFireKeyCode); 
	}
	
	private boolean fireConditionsMet() {
		return (delayFrameCounter > fireFrameDelay);
	}
	
	/**		  	      EDIT TAGS	     		    **/
	/** *************************************** **/
	
	@EditableField
	(	inputLabel = "Bomb Damage",
		defaultVal = "10"	)
	private void editBombDamage(String arg) {
		try {
			this.myDamage = InputParser.parseAsDouble(arg);
		} catch (InputTypeException e) { 	}
	}

	@EditableField
	(	inputLabel = "Fire Button",
		defaultVal = "SPACE"	)
	private void editFireCode(String arg) {
		try {
			this.myFireKeyCode = InputParser.parseAsKeyCode(arg);
		} catch (InputTypeException e) {	}
	}

	@EditableField
	(	inputLabel = "Bomb Image Path",
		defaultVal = "scenery_black.png"	)
	private void editBombImage(String arg) {
		try {
			this.myImagePath = InputParser.parseAsImagePath(this.getClass().getClassLoader(), arg);
		} catch (InputTypeException e) {	}
	}
	
	@EditableField
	(	inputLabel = "Fire Delay (# Frame)",
		defaultVal = "90"	)
	private void editFireDelay(String arg) {
		try {
			this.fireFrameDelay = InputParser.parseAsInt(arg);
		} catch (InputTypeException e) {	}
	}
	
	@EditableField
	(	inputLabel = "Bomb Fuse (# Frame)",
		defaultVal = "60"	)
	private void editFuse(String arg) {
		try {
			this.myFuse = InputParser.parseAsInt(arg);
		} catch (InputTypeException e) {	}
	}
	
	@Override
	public IAttribute copyAttribute() {
		return new BombAttribute(this);
	}


}
