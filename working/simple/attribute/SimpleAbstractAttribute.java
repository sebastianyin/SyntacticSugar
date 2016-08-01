package simple.attribute;

import simple.obj.ISimpleObject;
import simple.universe.ISimpleUniverse;

public abstract class SimpleAbstractAttribute implements ISimpleAttribute {

	private ISimpleObject myParent;
	
	/**
	 * Construct a new SimpleAttribute object with a parent object.
	 * @param parent
	 */
	public SimpleAbstractAttribute(ISimpleObject parent) {
		this.myParent = parent;
	}
	
	@Override
	public abstract void updateSelf(ISimpleUniverse universe);

	@Override
	public ISimpleObject getParent() {
		return this.myParent;
	}
	
}
