package com.robotality.paris;

import com.robotality.paris.core.Core;
import com.robotality.paris.core.Entity;

/**
 * This is the Paris main interface through which you create, add and remove entities. Paris doesn't
 * control how you keep your entities updated and how you construct your components. It simply gives you 
 * tools to create a component based system with fast access to other components and entities.
 * 
 * @author Stefan Bachmann
 */
public class Paris {
	/** The core that manages entities */
	private Core core;
	/** The static instance accessed through Paris.instance() */
	private static Paris instance;
	
	private Paris(){
		core = new Core();
	}
	
	/**
	 * Returns the current Paris instance for non-static access
	 */
	private static Paris instance(){
		if(instance == null)
			instance = new Paris();
		return instance;
	}
	
	/**
	 * Creates an empty Entity and returns it. The Entity is automatically added to the core.
	 */
	public static Entity createEntity(){
		Entity entity = new Entity();
		instance().core.add(entity);
		
		return entity;
	}
	
	/**
	 * Adds an Entity to the Core of Paris. Only use this if you have manually created an Entity via the
	 * Constructor. For ease, Paris.createEntity() should be used instead.
	 * @param entity The Entity to add
	 */
	public static void addEntity(Entity entity){
		instance().core.add(entity);
	}
	
	/**
	 * Removes an Entity from the Core of Paris. You only need to call this if you don't want the Entity to
	 * show up on any of the entity lists returned by Paris.
	 * @param entity The entity to remove
	 */
	public static void removeEntity(Entity entity){
		instance().core.remove(entity);
	}
}
