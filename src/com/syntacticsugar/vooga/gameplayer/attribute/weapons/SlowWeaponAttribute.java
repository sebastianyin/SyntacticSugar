package com.syntacticsugar.vooga.gameplayer.attribute.weapons;

import com.syntacticsugar.vooga.authoring.parameters.EditableClass;
import com.syntacticsugar.vooga.authoring.parameters.EditableField;
import com.syntacticsugar.vooga.authoring.parameters.InputParser;
import com.syntacticsugar.vooga.authoring.parameters.InputTypeException;
import com.syntacticsugar.vooga.gameplayer.attribute.IAttribute;
import com.syntacticsugar.vooga.gameplayer.objects.GameObjectType;
import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;
import com.syntacticsugar.vooga.gameplayer.objects.items.bullets.BulletParams;
import com.syntacticsugar.vooga.gameplayer.objects.items.bullets.SlowBullet;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

@EditableClass (
		className = "Slow Weapon"
		)
public class SlowWeaponAttribute extends AbstractWeaponAttribute {

	private double mySpeedDecrease;
	private int myTime;

	public SlowWeaponAttribute(String bulletImagePath, Double bulletDamage, KeyCode fireKeyCode, Double bulletSpeed,
			Double bulletWidth, Double bulletHeight, Double speedDecrease, Integer time) {
		super(bulletImagePath, bulletDamage, fireKeyCode, bulletSpeed, bulletWidth, bulletHeight);
		mySpeedDecrease = speedDecrease;
		myTime = time;
	}
	
	public SlowWeaponAttribute() {
		super();
	}
	
	private SlowWeaponAttribute(SlowWeaponAttribute toCopy) {
		super(toCopy);
		this.mySpeedDecrease = toCopy.mySpeedDecrease;
		this.myTime = toCopy.myTime;
	}
	
	@Override
	protected void setDefaults() {
		super.setDefaults();
		mySpeedDecrease = 0.50;
	}

	@Override
	protected IGameObject makeBullet() {

		SlowBullet bullet = null;
		if (getParent().getType().equals(GameObjectType.PLAYER)) {
			Point2D bulletInitPos = new Point2D(
					getParent().getBoundingBox().getPoint().getX() + getParent().getBoundingBox().getWidth() / 2,
					getParent().getBoundingBox().getPoint().getY() + getParent().getBoundingBox().getHeight() / 2);
			BulletParams params = makeParams(bulletInitPos);
			bullet = new SlowBullet(params, mySpeedDecrease, myTime);

		}

		return bullet;
	}
	
	
	/**		  	      EDIT TAGS	     		    **/
	/** *************************************** **/
	
	@EditableField (	
		inputLabel = "Bullet Slowdown Effect",
		defaultVal = "0.50"
		)
	private void editSlowDown(String arg) {
		try {
			this.mySpeedDecrease = InputParser.parseAsDouble(arg);
		} catch (InputTypeException e) { 	}
	}
	
	public IAttribute copyAttribute() {
		return new SlowWeaponAttribute(this);
	}

}
