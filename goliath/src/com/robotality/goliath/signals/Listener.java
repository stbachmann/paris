package com.robotality.goliath.signals;

/**
 * A simple Listener interface used to listen to a {@link Signal}.
 * @author Stefan Bachmann
 */
public interface Listener<A, B> {
	/**
	 * 
	 * @param signal The Signal that triggered event
	 * @param objectA The first object passed on dispatch
	 * @param objectB The second object passed on dispatch
	 */
	public void receive(Signal<A, B> signal, A objectA, B objectB);
}
