package com.robotality.magnet.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.robotality.goliath.Goliath;
import com.robotality.goliath.core.Entity;
import com.robotality.magnet.components.Position;
import com.robotality.magnet.components.Renderable;

public class Sprite extends Entity {
	public Position position;
	public Renderable renderable;
	
	public Sprite(TextureRegion region){
		Goliath.addEntity(this);
		
		position = add(Position.class);
		renderable = add(Renderable.class);
		renderable.region = region;
	}
}
