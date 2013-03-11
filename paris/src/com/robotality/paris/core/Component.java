package com.robotality.paris.core;

/**
 * Components are the brains that drive Entities. All their logic is defined by the update method. Components have
 * a quick access to the entity they are inserted into via entity.
 * 
 * create() is called when the component is created. The create should contain all the initialisation that need to
 * be done on the component (i.e. retrieving other components, etc.). 
 * 
 * Unless you know what you're doing you shouldn't override the constructor of the component. Ugly things will happen.
 * 
 * @author Stefan Bachmann
 */
public abstract class Component {
	/** The owner of this component */
	protected Entity entity;
	
	/**
	 * Called when the component has been created before the first update() call.
	 */
	public abstract void create();
	
	/**
	 * The "brain" of the component. Called every tick.
	 * @param deltaTime The elapsed frame time since the last frame
	 */
	public abstract void update(float deltaTime);
	
	/**
	 * If the component has any special requirements override this method and implement custom logic. The default
	 * component always returns true.
	 * @param entity The entity the user is trying to insert the component into
	 * @return Whether the entity is a suitable candidate for this component
	 */
	public boolean matchesEntity(Entity entity) { return true; }
}
