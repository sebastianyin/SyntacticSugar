package com.syntacticsugar.vooga.gameplayer.attribute.weapons;

import com.syntacticsugar.vooga.authoring.parameters.EditableClass;
import com.syntacticsugar.vooga.authoring.parameters.EditableField;
import com.syntacticsugar.vooga.authoring.parameters.InputParser;
import com.syntacticsugar.vooga.authoring.parameters.InputTypeException;
import com.syntacticsugar.vooga.gameplayer.attribute.IAttribute;
import com.syntacticsugar.vooga.gameplayer.objects.GameObjectType;
import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;
import com.syntacticsugar.vooga.gameplayer.objects.items.bullets.BulletParams;
import com.syntacticsugar.vooga.gameplayer.objects.items.bullets.StunBullet;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

@EditableClass (	
		className = "Stun Weapon"	
	)
public class StunWeaponAttribute extends AbstractWeaponAttribute {
	
	private int myStunTime;
	
	public StunWeaponAttribute(String bulletImagePath, Double bulletDamage, KeyCode fireKeyCode,
			Double bulletSpeed, Double bulletWidth, Double bulletHeight, Integer stunTime) {
		super(bulletImagePath, bulletDamage, fireKeyCode, bulletSpeed, bulletWidth, bulletHeight);
		myStunTime = stunTime;
	}

	public StunWeaponAttribute() {
		super();
	}
	
	private StunWeaponAttribute(StunWeaponAttribute toCopy) {
		super(toCopy);
		this.myStunTime = toCopy.myStunTime;
	}
	
	@Override
	protected void setDefaults() {
		super.setDefaults();
		this.myStunTime = 30;
	}

	@Override
	protected IGameObject makeBullet() {
		StunBullet bullet = null;
		if (getParent().getType().equals(GameObjectType.PLAYER)) {
			Point2D bulletInitPos = new Point2D(getParent().getBoundingBox().getPoint().getX() + getParent().getBoundingBox().getWidth()/2,
											getParent().getBoundingBox().getPoint().getY() + getParent().getBoundingBox().getHeight()/2);
			BulletParams params = makeParams(bulletInitPos);
			bullet = new StunBullet(params, myStunTime);
		}
		return bullet;
	}
	
	/**		  	      EDIT TAGS	     		    **/
	/** *************************************** **/
	
	@EditableField
	(	inputLabel = "Stun Duration",
		defaultVal = "30"	)
	private void editStunDamage(String arg) {
		try {
			this.myStunTime = InputParser.parseAsInt(arg);
		} catch (InputTypeException e) { 	}
	}
	
	@Override
	public IAttribute copyAttribute() {
		return new StunWeaponAttribute(this);
	}

}
