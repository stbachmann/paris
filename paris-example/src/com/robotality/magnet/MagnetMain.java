package com.robotality.magnet;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.robotality.magnet.components.MagnetMouse;
import com.robotality.magnet.entities.Sprite;
import com.robotality.magnet.systems.Renderer;
import com.robotality.paris.utils.MathUtils;

public class MagnetMain {
	
	
	public static void main(String[] args){
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 640;
		config.height = 480;
		config.useGL20 = true;
		
		new LwjglApplication(new MainClass(), config);
	}
	
	public static class MainClass extends ApplicationAdapter {
		Array<Sprite> crates = new Array<Sprite>();
		Array<Sprite> coins = new Array<Sprite>();
		
		OrthographicCamera camera;
		Renderer renderer;
		
		@Override
		public void create() {
			camera = new OrthographicCamera(640, 480);
			camera.position.set(320, 240, 0);
			camera.update();
			
			renderer = new Renderer(camera);
			
			Texture crateTexture = new Texture("assets/crate.png");
			Texture coinTexture = new Texture("assets/coin.png");
			
			TextureRegion coinRegion = new TextureRegion(coinTexture);
			
			for(int i=0; i<250; i++){
				Sprite sprite = new Sprite(coinRegion);
				sprite.position.x = MathUtils.random(640);
				sprite.position.y = MathUtils.random(480);
				
				sprite.add(MagnetMouse.class);
				
				coins.add(sprite);
			}
		}

		@Override
		public void render() {
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			
			renderer.begin();
			
			for(Sprite sprite:coins){
				sprite.update(Gdx.graphics.getDeltaTime());
				renderer.renderSprite(sprite);
			}
			
			renderer.end();
		}
	}
}
