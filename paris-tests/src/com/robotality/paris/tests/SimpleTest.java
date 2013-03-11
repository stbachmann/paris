package com.robotality.paris.tests;

import com.robotality.paris.Paris;
import com.robotality.paris.core.Component;
import com.robotality.paris.core.Entity;

public class SimpleTest {
	public static void main(String[] args){
		Entity entity = Paris.createEntity();
		
		PositionComponent position = entity.add(PositionComponent.class);
		position.x = 10;
		position.y = 5;
		
		entity.update(20.0f);
	}
	
	public static class PositionComponent extends Component {
		public float x, y;
		
		@Override
		public void create() {
			System.out.println("Hello World!");
		}
		
		@Override
		public void update(float deltaTime) {
			System.out.println("Position: " + x + ", " + y);
		}
	}
}
