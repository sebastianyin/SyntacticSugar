package com.syntacticsugar.vooga.gameplayer.objects.items.bullets;

import com.syntacticsugar.vooga.gameplayer.event.implementations.StunEvent;
import com.syntacticsugar.vooga.gameplayer.objects.GameObjectType;

public class StunBullet extends AbstractBullet {
	
	public StunBullet (BulletParams params, int stunTime) {
		super(params);
		StunEvent stun = new StunEvent(stunTime);
		addCollisionBinding(GameObjectType.ENEMY, stun);
	}

}
