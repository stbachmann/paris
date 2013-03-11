package com.robotality.magnet.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.robotality.paris.core.Component;
import com.robotality.paris.core.Entity;

public class MagnetMouse extends Component {
	private Vector3 currentMouse = new Vector3();
	
	private Vector2 acceleration = new Vector2();
	private Position position;
	
	@Override
	public void create() {
		position = entity.getComponent(Position.class);
	}

	@Override
	public void update(float deltaTime) {
		currentMouse.x = Gdx.input.getX();
		currentMouse.y = 480 - Gdx.input.getY();
		
		acceleration.x = currentMouse.x - position.x;
		acceleration.y = currentMouse.y - position.y;
		 
		if(acceleration.len() < 150) {
			acceleration.nor();
			acceleration.mul(20 + MathUtils.random(5, 65));

			position.x += acceleration.x * deltaTime;
			position.y += acceleration.y * deltaTime;
		}
	}

	@Override
	public boolean matchesEntity(Entity entity) {
		return entity.containsComponent(Position.class);
	}
}
