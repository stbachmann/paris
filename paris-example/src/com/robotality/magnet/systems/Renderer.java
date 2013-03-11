package com.robotality.magnet.systems;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.robotality.magnet.entities.Sprite;

public class Renderer {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	public Renderer(OrthographicCamera camera){
		batch = new SpriteBatch();
		this.camera = camera;
	}
	
	public void begin(){
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
	}
	
	public void renderSprite(Sprite sprite){
		batch.draw(sprite.renderable.region, sprite.position.x, sprite.position.y);
	}
	
	public void end(){
		batch.end();
	}
}
