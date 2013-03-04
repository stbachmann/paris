package com.robotality.goliath.tests;

import com.robotality.goliath.Goliath;
import com.robotality.goliath.core.Component;
import com.robotality.goliath.core.Entity;

public class RequirementsTest {
	public static void main(String[] args){
		Entity entity = Goliath.createEntity();
		
		entity.add(PositionComponent.class);
		entity.add(MovementComponent.class);
		
		try {
			Entity entity2 = Goliath.createEntity();
			
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
			System.out.println("PositionComponent: update.");
		}

	}
	
	public static class MovementComponent extends Component {
		@Override
		public void create() {
			System.out.println("Movement component created.");
		}
		
		@Override
		public void update(float deltaTime) {
			System.out.println("MovementComponent: update.");
		}

		@Override
		public boolean matchesEntity(Entity entity) {
			return entity.containsComponent(PositionComponent.class);
		}
	}
}
