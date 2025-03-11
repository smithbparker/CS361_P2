package fa.nfa;

import fa.FAInterface;
import java.util.*;

/**
 * Implements a Nondeterministic Finite Automaton (NFA).
 * author: Parker and Sabastian
 */
public class NFA implements fa.nfa.NFAInterface {
    private Set<NFAState> states; // Set of all states in the NFA
    private Set<Character> alphabet; // Set of valid input symbols
    private NFAState startState; // Start state of the NFA
    private Set<NFAState> finalStates; // Set of final (accepting) states

    // Initializes an empty NFA
    public NFA() {
        this.states = new HashSet<>();
        this.alphabet = new HashSet<>();
        this.finalStates = new HashSet<>();
        this.startState = null;
    }

    // Adds a symbol to the alphabet (ignoring 'e' since it's reserved for epsilon transitions)
    @Override
    public void addSigma(char symbol) {
        if (symbol != 'e') { 
            alphabet.add(symbol);
        }
    }

    // Adds a state to the NFA
    @Override
    public boolean addState(String name) {
        if (getState(name) != null) return false; // State already exists
        states.add(new NFAState(name));
        return true;
    }

    // Sets the start state of the NFA
    @Override
    public boolean setStart(String name) {
        NFAState state = getState(name);
        if (state == null) return false;
        startState = state;
        return true;
    }

    // Marks a state as a final (accepting) state
    @Override
    public boolean setFinal(String name) {
        NFAState state = getState(name);
        if (state == null) return false;
        finalStates.add(state);
        return true;
    }

    // Adds a transition from one state to a set of destination states on a given symbol
    @Override
    public boolean addTransition(String fromState, Set<String> toStates, char symbol) {
        NFAState from = getState(fromState);
        if (from == null || (!alphabet.contains(symbol) && symbol != 'e')) return false; // Invalid state or symbol

        Set<NFAState> destinations = new HashSet<>();
        for (String stateName : toStates) {
            NFAState state = getState(stateName);
            if (state == null) return false; // One of the destination states is invalid
            destinations.add(state);
        }

        for (NFAState dest : destinations) {
            from.addTransition(symbol, dest);
        }
        return true;
    }

    // Retrieves a state by name
    @Override
    public NFAState getState(String name) {
        for (NFAState state : states) {
            if (state.getName().equals(name)) return state;
        }
        return null;
    }

    // Checks if a given state is the start state
    @Override
    public boolean isStart(String name) {
        NFAState state = getState(name);
        return state != null && state == startState;
    }

    // Checks if a given state is a final state
    @Override
    public boolean isFinal(String name) {
        NFAState state = getState(name);
        return state != null && finalStates.contains(state);
    }

    // Retrieves all states reachable from a given state on a specific symbol
    @Override
    public Set<NFAState> getToState(NFAState state, char symbol) {
        return state.toStates(symbol);
    }

    // Retrieves the alphabet of the NFA
    @Override
    public Set<Character> getSigma() {
        return alphabet;
    }

    // Computes the epsilon closure of a state using DFS
    @Override
    public Set<NFAState> eClosure(NFAState state) {
        Set<NFAState> closure = new HashSet<>();
        Stack<NFAState> stack = new Stack<>();
        stack.push(state);

        while (!stack.isEmpty()) {
            NFAState current = stack.pop();
            if (!closure.contains(current)) {
                closure.add(current);
                for (NFAState neighbor : current.getTransitions('e')) {
                    stack.push(neighbor);
                }
            }
        }
        return closure;
    }

    // Determines if the NFA accepts a given input string
    @Override
    public boolean accepts(String input) {
        if (startState == null) return false;

        Set<NFAState> currentStates = eClosure(startState);
        for (char symbol : input.toCharArray()) {
            if (!alphabet.contains(symbol)) return false; // Reject if input contains an invalid symbol

            Set<NFAState> nextStates = new HashSet<>();
            for (NFAState state : currentStates) {
                for (NFAState target : state.getTransitions(symbol)) {
                    nextStates.addAll(eClosure(target));
                }
            }

            currentStates = nextStates;
            if (currentStates.isEmpty()) return false; // No valid transitions left
        }

        for (NFAState state : currentStates) {
            if (finalStates.contains(state)) return true;
        }
        return false;
    }

    // Determines the maximum number of copies an input string generates
    @Override
    public int maxCopies(String input) {
        if (startState == null) return 1;

        Set<NFAState> currentStates = eClosure(startState);
        int maxCopies = currentStates.size();

        for (char symbol : input.toCharArray()) {
            Set<NFAState> nextStates = new HashSet<>();
            for (NFAState state : currentStates) {
                for (NFAState target : state.getTransitions(symbol)) {
                    nextStates.addAll(eClosure(target));
                }
            }

            currentStates = nextStates;
            maxCopies = Math.max(maxCopies, currentStates.size());
        }

        return maxCopies;
    }

    // Determines whether the NFA is actually a DFA
    @Override
    public boolean isDFA() {
        for (NFAState state : states) {
            for (char symbol : alphabet) {
                if (state.getTransitions(symbol).size() > 1) return false; // More than one transition per symbol
            }
            if (!state.getTransitions('e').isEmpty()) return false; // Contains epsilon transitions
        }
        return true;
    }
}
