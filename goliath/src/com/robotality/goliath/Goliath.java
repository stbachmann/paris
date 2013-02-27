package com.robotality.goliath;

import com.robotality.goliath.core.Core;
import com.robotality.goliath.core.Entity;

public class Goliath {
	private Core core;
	private static Goliath instance;
	
	private Goliath(){
		core = new Core();
	}
	
	private static Goliath instance(){
		if(instance == null)
			instance = new Goliath();
		return instance;
	}
	
	public static Entity createEntity(){
		Entity entity = new Entity();
		instance().core.add(entity);
		
		return entity;
	}
	
	public static void addEntity(Entity entity){
		instance().core.add(entity);
	}
	
	public static void removeEntity(Entity entity){
		instance().core.remove(entity);
	}
}
