package Interface; 


/**
 * The Subject part of the Observer pattern.
 * All classes implementing this interface MUST have these methods.
 * ========================================================================
 * Below Class has been taken from the course examples by Dr. Michael Lones
 * ========================================================================
 */
public interface Subject {
	
	/**
	 * Register an observer with this subject
	 */
	public void registerObserver(Observer obs);

	/**
	 * De-register an observer with this subject
	 */
	public void removeObserver(Observer obs);

	/**
	 * Inform all registered observers that there's been an update
	 */
	public void notifyObservers();
}