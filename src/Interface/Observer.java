package Interface; 
/**
 * The Observer part of the Observer pattern.
 * All classes implementing this interface MUST have this method.
 * ========================================================================
 * Below Class has been taken from the course examples by Dr. Michael Lones
 * ========================================================================
 */
public interface Observer {
	
	/**
	 * Tell Observer to update itself
	 */
	public void update();
}