package fa.nfa;

import java.util.*;

/**
 * Represents a state in an NFA.
 * Extends the abstract class State.
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

    // Adds a transition from this state to another state on the given symbol
    public void addTransition(char symbol, NFAState destination) {
        transitions.computeIfAbsent(symbol, k -> new HashSet<>()).add(destination);
    }

    // Retrieves the set of states reachable from this state on a given symbol
    public Set<NFAState> getTransitions(char symbol) {
        return transitions.getOrDefault(symbol, new HashSet<>());
    }

    // Retrieves the transition map of this state
    public Map<Character, Set<NFAState>> getAllTransitions() {
        return transitions;
    }

    // Retrieves the set of states reachable from this state on a given symbol
    public Set<NFAState> toStates(char symbol) {
        return transitions.getOrDefault(symbol, new HashSet<>());
    }

}
