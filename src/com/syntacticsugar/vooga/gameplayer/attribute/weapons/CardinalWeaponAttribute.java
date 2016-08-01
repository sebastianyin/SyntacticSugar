package com.syntacticsugar.vooga.gameplayer.attribute.weapons;

import com.syntacticsugar.vooga.authoring.parameters.EditableClass;
import com.syntacticsugar.vooga.gameplayer.attribute.IAttribute;
import com.syntacticsugar.vooga.gameplayer.attribute.movement.Direction;
import com.syntacticsugar.vooga.gameplayer.objects.GameObjectType;
import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;
import com.syntacticsugar.vooga.gameplayer.objects.items.bullets.BulletParams;
import com.syntacticsugar.vooga.gameplayer.objects.items.bullets.PlayerBullet;
import com.syntacticsugar.vooga.gameplayer.universe.IGameUniverse;

import javafx.geometry.Point2D;

@EditableClass (
		className = "Cardinal (4-dir) Weapon"
		)
public class CardinalWeaponAttribute extends AbstractWeaponAttribute {
	
	public CardinalWeaponAttribute() {
		super();
	}
	
	private CardinalWeaponAttribute(CardinalWeaponAttribute toCopy) {
		super(toCopy);
	}
	
	@Override
	public void updateSelf(IGameUniverse universe) {
		updateKeyInput(universe);
		if (getIsFireKeyPressed()) {
			if (fireConditionsMet()) {
				for (int i = 0; i < 4; i++) {
					IGameObject bullet = makeBullet(i);
					fireBullet(universe, bullet);
				}
				setDelayFrameCounter(0);
			}
		}
		incDelayFrameCounter();
	}

	protected IGameObject makeBullet(int i) {
		PlayerBullet bullet = null;
		if (getParent().getType().equals(GameObjectType.PLAYER)) {
			Point2D bulletInitPos = new Point2D(getParent().getBoundingBox().getPoint().getX() + getParent().getBoundingBox().getWidth()/2,
											getParent().getBoundingBox().getPoint().getY() + getParent().getBoundingBox().getHeight()/2);
			BulletParams params = makeParams(bulletInitPos);
			switch(i) {
			case 0:
				params.setMove(Direction.DOWN);
				break;
			case 1:
				params.setMove(Direction.LEFT);
				break;
			case 2:
				params.setMove(Direction.RIGHT);
				break;
			case 3:
				params.setMove(Direction.UP);
				break;
			}
			bullet = new PlayerBullet(params);
			
		}
		return bullet;
	}
	

	@Override
	protected IGameObject makeBullet() {
		return null;
	}

	@Override
	public IAttribute copyAttribute() {
		return new CardinalWeaponAttribute(this);
	}

}
