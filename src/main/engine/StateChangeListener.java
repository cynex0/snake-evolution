package main.engine;

/**
 * Interface to allow state switching in the engine from the states (Observer).
 * @author Maksims Orlovs
 */
public interface StateChangeListener {
    void changeState(GameState newState);
}
