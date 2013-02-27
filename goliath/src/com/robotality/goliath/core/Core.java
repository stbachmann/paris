package com.robotality.goliath.core;

import com.robotality.goliath.signals.Listener;
import com.robotality.goliath.signals.Signal;
import com.robotality.goliath.utils.IntMap;
import com.robotality.goliath.utils.ObjectMap;
import com.robotality.goliath.utils.ObjectMap.Keys;

/**
 * The Core manages all entities currently in use and provides access to entities grouped by components. Usually
 * you don't need to touch this and just use the Goliath static interface.
 * 
 * @author Stefan Bachmann
 */
public class Core {
	/** All the entities currently active in the system */
	private IntMap<Entity> entities;
	/** All the entities grouped by their components */
	private ObjectMap<Class<? extends Component>, IntMap<Entity>> entitiesByComponent;
	
	/** A listener that is called every time a component is added. */
	public final Listener<Entity, Component> componentAddedListener;
	/** A listener that is called every time a component is removed. */
	public final Listener<Entity, Component> componentRemovedListener;
	
	public Core(){
		entities = new IntMap<Entity>();
		
		entitiesByComponent = new ObjectMap<Class<? extends Component>, IntMap<Entity>>();
		
		componentAddedListener = new Listener<Entity, Component>() {
			@Override
			public void receive(Signal<Entity, Component> signal, Entity entity, Component component) {
				componentAdded(entity, component);
			}
		};
		
		componentRemovedListener = new Listener<Entity, Component>() {
			@Override
			public void receive(Signal<Entity, Component> signal, Entity entity, Component component) {
				componentRemoved(entity, component);
			}
		};
	}

	/**
	 * Called when a component is added to an entity in this Core
	 * @param entity The Entity that has changed
	 * @param component The Component that was added to the Entity
	 */
	private void componentAdded(Entity entity, Component component) {
		IntMap<Entity> entities = entitiesByComponent.get(component.getClass(), null);
		if(entities == null)
			entities = new IntMap<Entity>();
		entities.put(entity.index, entity);
	}
	
	/**
	 * Called when a component is removed from an entity in this Core
	 * @param entity The Entity that has changed
	 * @param component The Component that was removed from the Entity
	 */
	private void componentRemoved(Entity entity, Component component) {
		IntMap<Entity> entities = entitiesByComponent.get(component.getClass());
		if(entities != null)
			entities.remove(entity.index);
	}

	/**
	 * Adds an Entity to this Core.
	 * @param entity The Entity to add
	 */
	public void add(Entity entity){
		entities.put(entity.index, entity);
		
		ObjectMap<Class<? extends Component>, Component> components = entity.getComponents();
		Keys<Class<? extends Component>> keys = components.keys();
		while(keys.hasNext){
			componentAdded(entity, components.get(keys.next()));
		}
	
		entity.componentAdded.add(componentAddedListener);
		entity.componentRemoved.add(componentRemovedListener);
	}
	
	/**
	 * Removes an Entity from this Core.
	 * @param entity The Entity to remove
	 */
	public void remove(Entity entity){
		entities.remove(entity.index);
		
		ObjectMap<Class<? extends Component>, Component> components = entity.getComponents();
		Keys<Class<? extends Component>> keys = components.keys();
		while(keys.hasNext){
			componentRemoved(entity, components.get(keys.next()));
		}
	
		entity.componentAdded.remove(componentAddedListener);
		entity.componentRemoved.remove(componentRemovedListener);
	}
}
