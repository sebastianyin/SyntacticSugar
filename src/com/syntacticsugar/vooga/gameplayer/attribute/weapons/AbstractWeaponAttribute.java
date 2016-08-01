package com.syntacticsugar.vooga.gameplayer.attribute.weapons;

import com.syntacticsugar.vooga.authoring.parameters.EditableClass;
import com.syntacticsugar.vooga.authoring.parameters.EditableField;
import com.syntacticsugar.vooga.authoring.parameters.InputParser;
import com.syntacticsugar.vooga.authoring.parameters.InputTypeException;
import com.syntacticsugar.vooga.gameplayer.attribute.AbstractAttribute;
import com.syntacticsugar.vooga.gameplayer.attribute.IAttribute;
import com.syntacticsugar.vooga.gameplayer.attribute.control.IUserControlAttribute;
import com.syntacticsugar.vooga.gameplayer.attribute.movement.Direction;
import com.syntacticsugar.vooga.gameplayer.event.implementations.ObjectSpawnEvent;
import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;
import com.syntacticsugar.vooga.gameplayer.objects.items.bullets.BulletParams;
import com.syntacticsugar.vooga.gameplayer.universe.IEventPoster;
import com.syntacticsugar.vooga.gameplayer.universe.IGameUniverse;
import com.syntacticsugar.vooga.gameplayer.universe.userinput.IKeyInputStorage;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

@EditableClass (	
	className = "General Weapon Settings"	
)
public abstract class AbstractWeaponAttribute extends AbstractAttribute implements IUserControlAttribute {

	private double myBulletDamage;
	private double myBulletSpeed;
	private KeyCode myFireKeyCode;
	private String myBulletImagePath;
	private double myBulletWidth;
	private double myBulletHeight;
	private int fireFrameDelay;

	private boolean isFireKeyPressed;
	private int delayFrameCounter;

	public AbstractWeaponAttribute() {
		super();
	}
	
	protected AbstractWeaponAttribute(AbstractWeaponAttribute toCopy) {
		super(toCopy);
		this.myBulletDamage = toCopy.myBulletDamage;
		this.myBulletSpeed = toCopy.myBulletSpeed;
		this.myBulletImagePath = toCopy.myBulletImagePath;
		this.myFireKeyCode = toCopy.myFireKeyCode;
		this.myBulletWidth = toCopy.myBulletWidth;
		this.myBulletHeight = toCopy.myBulletHeight;
		this.fireFrameDelay = toCopy.fireFrameDelay;
	}
	
	@Override
	protected void setDefaults() {
		this.myBulletDamage = 10;
		this.myBulletSpeed = 10;
		this.myFireKeyCode = KeyCode.SPACE;
		this.myBulletImagePath = "scenery_black.png";
		this.myBulletWidth = 5;
		this.myBulletHeight = 10;
		this.fireFrameDelay = 15;
	}
	
	public AbstractWeaponAttribute(String bulletImagePath, Double bulletDamage, KeyCode fireKeyCode, 
			Double bulletSpeed, Double bulletWidth, Double bulletHeight) {
		this.myBulletImagePath = bulletImagePath;
		this.myBulletDamage = bulletDamage;
		this.myFireKeyCode = fireKeyCode;
		this.isFireKeyPressed = false;
		this.fireFrameDelay = 10;
		this.delayFrameCounter = 0;
		myBulletSpeed = bulletSpeed;
		myBulletWidth = bulletWidth;
		myBulletHeight = bulletHeight;
	}

	@Override
	public void updateSelf(IGameUniverse universe) {
		updateKeyInput(universe);
		if (isFireKeyPressed) {
			if (fireConditionsMet()) {
				IGameObject bullet = makeBullet();
				fireBullet(universe, bullet);
				delayFrameCounter = 0;
			}
		}
		delayFrameCounter++;
	}

	@Override
	public void updateKeyInput(IKeyInputStorage universeKeyInput) {
		isFireKeyPressed = universeKeyInput.getCurrentKeyInput().contains(myFireKeyCode); 
	}

	protected boolean fireConditionsMet() {
		return (delayFrameCounter > fireFrameDelay);
	}

	/**
	 * Makes the bullet.
	 * @return the bullet
	 */
	protected abstract IGameObject makeBullet();

	protected void fireBullet(IEventPoster poster, IGameObject bullet) {
		if (bullet != null) {
			ObjectSpawnEvent event = new ObjectSpawnEvent(bullet);
			poster.postEvent(event);			
		}
	}

	private Direction getCurrentDirection() {
		Direction dir = getParent().getBoundingBox().getDirection();
		return dir;
	}

	protected BulletParams makeParams(Point2D bulletInitPos) {
		BulletParams params = new BulletParams();
		params.setMove(getCurrentDirection());
		params.setStartPoint(bulletInitPos);
		params.setImagePath(myBulletImagePath);
		params.setSpeed(myBulletSpeed);
		System.out.println(myBulletSpeed);
		params.setDamage(myBulletDamage);
		params.setWidth(myBulletWidth);
		params.setHeight(myBulletHeight);
		params.setType(getParent().getFoe());

		return params;
	}
	
	protected boolean getIsFireKeyPressed() {
		return isFireKeyPressed;
	}
	
	protected void setDelayFrameCounter(int c) {
		delayFrameCounter = c;
	}
	
	protected void incDelayFrameCounter() {
		delayFrameCounter++;
	}

	
	/**		  	      EDIT TAGS	     		    **/
	/** *************************************** **/
	
	@EditableField
	(	inputLabel = "Bullet Damage",
		defaultVal = "10"	)
	private void editBulletDamage(String arg) {
		try {
			this.myBulletDamage = InputParser.parseAsDouble(arg);
		} catch (InputTypeException e) { 	}
	}
	
	@EditableField
	(	inputLabel = "Bullet Speed",
		defaultVal = "10"	)
	private void editBulletSpeed(String arg) {
		try {
			this.myBulletSpeed = InputParser.parseAsDouble(arg);
		} catch (InputTypeException e) {	}
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
	(	inputLabel = "Bullet Image Path",
		defaultVal = "scenery_black.png"	)
	private void editBulletImage(String arg) {
		try {
			this.myBulletImagePath = InputParser.parseAsImagePath(this.getClass().getClassLoader(), arg);
		} catch (InputTypeException e) {	}
	}

	@EditableField
	(	inputLabel = "Bullet Width (px)",
		defaultVal = "5"	)
	private void editBulletWidth(String arg) {
		try {
			this.myBulletWidth = InputParser.parseAsDouble(arg);
		} catch (InputTypeException e) {	}
	}
	
	@EditableField
	(	inputLabel = "Bullet Height (px)",
		defaultVal = "10"	)
	private void editBulletHeight(String arg) {
		try {
			this.myBulletHeight = InputParser.parseAsDouble(arg);
		} catch (InputTypeException e) {	}
	}
	
	@EditableField
	(	inputLabel = "Fire Delay (# Frame)",
		defaultVal = "15"	)
	private void editFireDelay(String arg) {
		try {
			this.fireFrameDelay = InputParser.parseAsInt(arg);
		} catch (InputTypeException e) {	}
	}
	
	@Override
	public abstract IAttribute copyAttribute();
	
}
