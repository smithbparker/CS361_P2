package fa.nfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Represents a state in an NFA.
 * Extends the abstract class State.
 * Provides logic for transtiion maps, and adding/getting transitions.
 * Author: Parker and Sabastian
 */
public class NFAState extends fa.State {
    // Transition map: a symbol maps to a set of destination states
    private Map<Character, Set<NFAState>> transitions;

    // Constructor for NFAState
    public NFAState(String name) {
        super(name); // Call the superclass constructor
        this.transitions = new HashMap<>(); // Initialize the transition map
    }

    /**
     * Adds a transition from this state to another state on the given symbol
     * @param symbol the symbol where the transition occurs
     * @param destination the state to which the transition leads
     */
    public void addTransition(char symbol, NFAState destination) {
        transitions.computeIfAbsent(symbol, k -> new HashSet<>()).add(destination);
    }

    /**
     * Retrieves the set of states reachable from this state on a given symbol
     * @param symbol the symbol where the transitions occur
     * @return a set of states reachable from this state on the given symbol
     */
    public Set<NFAState> getTransitions(char symbol) {
        return transitions.getOrDefault(symbol, new HashSet<>());
    }

    /**
     * Retrieves the transition map of this state
     * @return the transition map where each symbol is a set of destination states
     */
    public Map<Character, Set<NFAState>> getAllTransitions() {
        return transitions;
    }

    /**
     * Retrieves the set of states reachable from X state on the given symbol
     * @param symbol the symbol on which the transitions occur
     * @return a set of states reachable from this state on the given symbol
     */
    public Set<NFAState> toStates(char symbol) {
        return transitions.getOrDefault(symbol, new HashSet<>());
    }

}
