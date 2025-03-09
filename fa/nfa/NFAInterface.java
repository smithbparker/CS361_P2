package fa.nfa;


import java.util.Set;

import fa.FAInterface;


public interface NFAInterface extends FAInterface {
	
	
	/**
	 * Return delta entries
	 * @param from - the source state
	 * @param onSymb - the label of the transition
	 * @return a set of sink states
	 */
	public Set<NFAState> getToState(NFAState from, char onSymb);
	
	/**
	 * Traverses all epsilon transitions and determine
	 * what states can be reached from s through e
	 * @param s
	 * @return set of states that can be reached from s on epsilon trans.
	 */
	
	public Set<NFAState> eClosure(NFAState s);
	
	/**
	 * Determines the maximum number of NFA copies
	 * created when processing string s
	 * @param s - the input string
	 * @return - the maximum number of NFA copies created.
	 */
	public int maxCopies(String s);
	
	/**
	 * Adds the transition to the NFA's delta data structure
	 * @param fromState is the label of the state where the transition starts
	 * @param toState is the set of labels of the states where the transition ends
	 * @param onSymb is the symbol from the NFA's alphabet.
	 * @return true if successful and false if one of the states don't exist or the symbol in not in the alphabet
	 */
	public  boolean addTransition(String fromState, Set<String> toStates, char onSymb);
	
	
	/**
	 * Determines if NFA is an instance of a DFA
	 * @return - true if NFA's transition function has DFA's properties.
	 */
	public boolean isDFA();
}
