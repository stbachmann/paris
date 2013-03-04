package com.robotality.goliath.core;

import com.robotality.goliath.signals.Signal;
import com.robotality.goliath.utils.ObjectMap;
import com.robotality.goliath.utils.ObjectMap.Keys;

/**
 * Entities are an empty container that are defined by the components added to them. Entities themselves
 * only handle the component processing logic but beyond that do not know "what" they are.
 * 
 * An Entity can only hold one instance of a specific ComponentType. Sorry.
 * 
 * @author Stefan Bachmann
 */
public class Entity {
	/** An internal index counter for unique ids -> quick retrieval of entities */
	private static int nextIndex = 0;
	/** This entity's unique index */
	public final int index;
	
	/** The hashmap that holds all the components hashed via their class type */
	private ObjectMap<Class<? extends Component>, Component> components;
	
	/** Will dispatch an event when a component is added. */
	public Signal<Entity, Component> componentAdded;
	/** Will dispatch an event when a component is removed. */
	public Signal<Entity, Component> componentRemoved;
	
	/**
	 * Creates an empty entity. Usually you'd do this via Goliath.createEntity() as that will automatically add it
	 * to a {@link Core}. If you manually instantiate an Entity you need to add it to {@link Core}
	 */
	public Entity(){
		index = nextIndex++;
		components = new ObjectMap<Class<? extends Component>, Component>();
		
		componentAdded = new Signal<Entity, Component>();
		componentRemoved = new Signal<Entity, Component>();
	}
	
	/**
	 * Adds a new Component of the specified type to this Entity.
	 * @param componentType The Component Type to add to this Entity
	 * @return The newly created Component or null
	 */
	public <T extends Component> T add(Class<T> componentType){
		T component = null;
	
		try {
			component = componentType.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		
		if(!component.matchesEntity(this))
			throw new RuntimeException("This entity does not meet the component's requirements.");
		
		components.put(componentType, component);
		component.entity = this;
		
		componentAdded.dispatch(this, component);
		
		component.create();
		
		return component;
	}
	
	/**
	 * Checks whether this entity is suitable for a component type. This is a bit dirty because it creates a new
	 * instance of the component. Don't use this unless GC isn't a concern (i.e. editor).
	 * @param componentType The component type to check against
	 * @return Whether this entity is suitable for the component
	 */
	public <T extends Component> boolean suits(Class<T> componentType){
		T component = null;
		
		try {
			component = componentType.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		
		if(component.matchesEntity(this))
			return true;
	
		return false;
	}
	
	/**
	 * Removes the component of the specified type and returns it
	 * @param componentType The Component type to remove
	 * @return The removed component
	 */
	@SuppressWarnings("unchecked")
	public <T extends Component> T remove(Class<T> componentType){
		T component = (T) components.get(componentType, null);
		if(component != null){
			component.entity = null;
		}
		
		componentRemoved.dispatch(this, component);
		
		return component;
	}
	
	/**
	 * Returns the component of the specified type
	 * @param componentType The component type to return
	 * @return The component
	 */
	@SuppressWarnings("unchecked")
	public <T extends Component> T getComponent(Class<T> componentType){
		return (T) components.get(componentType);
	}
	
	/**
	 * Returns all the components of this Entity as an ObjectMap. Don't remove/add anything 
	 * to the collection, or else!
	 */
	public ObjectMap<Class<? extends Component>, Component> getComponents(){
		return components;
	}
	
	/**
	 * Returns true if this entity contains all of the components specified
	 * @param componentTypes The components to check for
	 * @return Whether the entity contains the components
	 */
	@SuppressWarnings("unchecked")
	public boolean containsComponent(Class<? extends Component>...componentTypes){
		for(Class<? extends Component> componentType : componentTypes){
			if(!components.containsKey(componentType))
				return false;
		}
		return true;
	}
	
	/**
	 * Updates the components in this Entity
	 * @param deltaTime The frame time elapsed since last frame
	 */
	public void update(float deltaTime){
		Keys<Class<? extends Component>> keys = components.keys();
		while(keys.hasNext){
			components.get(keys.next()).update(deltaTime);
		}
	}
}
