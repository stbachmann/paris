package com.robotality.goliath.core;

/**
 * Components are the brains that drive Entities. All their logic is defined by the update method. Components have
 * a quick access to the entity they are inserted into via entity.
 * 
 * @author Stefan Bachmann
 */
public abstract class Component {
	/** The owner of this component */
	protected Entity entity;
	
	/**
	 * Called when this component is added to an entity. Override to define custom behaviour.
	 * @param entity The entity this component was added to
	 */
	protected void added(Entity entity) { this.entity = entity; };
	
	/**
	 * Called when this component is removed from an entity. Override to define custom behaviour.
	 * @param entity
	 */
	protected void removed(Entity entity) { this.entity = null; };
	
	/**
	 * The "brain" of the component. Called every tick.
	 * @param deltaTime The elapsed frame time since the last frame
	 */
	public abstract void update(float deltaTime);
}
