package simple.eng.collisions;

import java.util.Collection;

import simple.event.ISimpleEvent;
import simple.obj.ISimpleObject;
import simple.obj.SimpleBoundingBox;

public class SimpleCollisionManager {

	public static void checkCollisions(Collection<ISimpleObject> collection) {
		for (ISimpleObject a : collection) {
			for (ISimpleObject b : collection) {
				if (b.equals(a)) {
					continue;
				}
				if (intersects(a, b)) {
					if (a.getEventsFromCollision(b.getType()) != null) {
						for (ISimpleEvent e : a.getEventsFromCollision(b.getType())) {
							e.executeEvent(b.getAttributes());
						}
					}
				}
			}
		}
	}

	private static Boolean intersects(ISimpleObject obj, ISimpleObject other) {
		SimpleBoundingBox abox = obj.getBoundingBox();
		SimpleBoundingBox bbox = other.getBoundingBox();

		double ax = abox.getPoint().getX();
		double bx = bbox.getPoint().getX();
		double ay = abox.getPoint().getY();
		double by = bbox.getPoint().getY();

		double aw = abox.getWidth();
		double bw = bbox.getWidth();
		double ah = abox.getHeight();
		double bh = bbox.getHeight();

		Boolean aInsideBX = (ax + aw >= bx && ax <= bx + bw);
		Boolean aInsideBY = (ay + ah >= by && ay <= by + bh);
		Boolean bInsideAX = (bx + bw >= ax && bx <= ax + aw);
		Boolean bInsideAY = (by + bh >= ay && by <= ay + ah);
		return ((aInsideBX && aInsideBY) || (bInsideAX && bInsideAY));
	}

}
