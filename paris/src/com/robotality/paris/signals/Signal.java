package com.robotality.paris.signals;

import com.badlogic.gdx.utils.Array;

/**
 * A Signal is a basic event class then can dispatch an event to multiple listeners. It uses
 * generics to allow any type of object to be passed around on dispatch.
 * 
 * @author Stefan Bachmann
 */
public class Signal<A, B> {
	private Array<Listener<A, B>> listeners;
	
	public Signal(){
		listeners = new Array<Listener<A, B>>();
	}
	
	/**
	 * Add a Listener to this Signal
	 * @param listener The Listener to be added
	 */
	public void add(Listener<A, B> listener){
		listeners.add(listener);
	}
	
	/**
	 * Remove a listener from this Signal
	 * @param listener The Listener to remove
	 */
	public void remove(Listener<A, B> listener){
		listeners.removeValue(listener, true);
	}
	
	/**
	 * Dispatches an event to all Listeners registered to this Signal
	 * @param objectA The first object to send off
	 * @param objectB The second object to send off
	 */
	public void dispatch(A objectA, B objectB){
		for(int i=0; i<listeners.size; i++){
			listeners.get(i).receive(this, objectA, objectB);
		}
	}
}
