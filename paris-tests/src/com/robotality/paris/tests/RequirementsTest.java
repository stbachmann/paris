package com.robotality.paris.tests;

import com.robotality.paris.Paris;
import com.robotality.paris.core.Component;
import com.robotality.paris.core.Entity;

public class RequirementsTest {
	public static void main(String[] args){
		Entity entity = Paris.createEntity();
		
		entity.add(PositionComponent.class);
		MovementComponent component = entity.add(MovementComponent.class);
		component.velocity = 10;
		
		entity.update(0.25f);
		entity.update(0.25f);
		
		try {
			Entity entity2 = Paris.createEntity();
			
			entity2.add(MovementComponent.class);
			entity2.add(PositionComponent.class);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static class PositionComponent extends Component {
		public float x, y;

		@Override
		public void create() {
			System.out.println("Position component created.");
		}
		
		@Override
		public void update(float deltaTime) {
			System.out.println("Position: " + x + ", " + y);
		}

	}
	
	public static class MovementComponent extends Component {
		private PositionComponent position;
		public float velocity;
		
		@Override
		public void create() {
			System.out.println("Movement component created.");
			
			position = entity.getComponent(PositionComponent.class);
		}
		
		@Override
		public void update(float deltaTime) {
			System.out.println("MovementComponent: update.");
			
			position.x += velocity * deltaTime;
			position.y += velocity * deltaTime;
		}

		@Override
		public boolean matchesEntity(Entity entity) {
			return entity.containsComponent(PositionComponent.class);
		}
	}
}
