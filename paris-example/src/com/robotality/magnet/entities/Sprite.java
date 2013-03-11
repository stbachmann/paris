package com.robotality.magnet.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.robotality.magnet.components.Position;
import com.robotality.magnet.components.Renderable;
import com.robotality.paris.Paris;
import com.robotality.paris.core.Entity;

public class Sprite extends Entity {
	public Position position;
	public Renderable renderable;
	
	public Sprite(TextureRegion region){
		Paris.addEntity(this);
		
		position = add(Position.class);
		renderable = add(Renderable.class);
		renderable.region = region;
	}
}
