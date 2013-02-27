package com.robotality.goliath;

import com.robotality.goliath.core.Core;
import com.robotality.goliath.core.Entity;

/**
 * This is the Goliath main interface through which you create, add and remove entities. Goliath doesn't
 * control how you keep your entities updated and how you construct your components. It simply gives you 
 * tools to create a component based system with fast access to other components and entities.
 * 
 * @author Stefan Bachmann
 */
public class Goliath {
	/** The core that manages entities */
	private Core core;
	/** The static instance accessed through Goliath.instance() */
	private static Goliath instance;
	
	private Goliath(){
		core = new Core();
	}
	
	/**
	 * Returns the current Goliath instance for non-static access
	 */
	private static Goliath instance(){
		if(instance == null)
			instance = new Goliath();
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
	 * Adds an Entity to the Core of Goliath. Only use this if you have manually created an Entity via the
	 * Constructor. For ease, Goliath.createEntity() should be used instead.
	 * @param entity The Entity to add
	 */
	public static void addEntity(Entity entity){
		instance().core.add(entity);
	}
	
	/**
	 * Removes an Entity from the Core of Goliath. You only need to call this if you don't want the Entity to
	 * show up on any of the entity lists returned by Goliath.
	 * @param entity The entity to remove
	 */
	public static void removeEntity(Entity entity){
		instance().core.remove(entity);
	}
}
