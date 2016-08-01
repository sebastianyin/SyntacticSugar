package com.syntacticsugar.vooga.gameplayer.objects.items.bullets;

import com.syntacticsugar.vooga.gameplayer.event.implementations.SlowEvent;
import com.syntacticsugar.vooga.gameplayer.objects.GameObjectType;

public class SlowBullet extends AbstractBullet {

	public SlowBullet(BulletParams params, double speedDecrease, int time) {
		super(params);
		SlowEvent slow = new SlowEvent(speedDecrease, time);
		addCollisionBinding(GameObjectType.ENEMY, slow);
	}

}
