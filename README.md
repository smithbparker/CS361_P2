# CS 361 - Project 2: NFA

## Overview
This project implements a Nondeterministic Finite Automaton (NFA) in Java. The NFA supports basic operations such as adding states, transitions, checking if a string is accepted, computing epsilon closures, and determining whether the automaton is actually a DFA.

## Files Included
- `fa/nfa/NFA.java`: Implements the NFA using `NFAInterface`.
- `fa/nfa/NFAState.java`: Represents individual NFA states and manages transitions.
- `test/nfa/NFATest.java`: JUnit tests.

## Features Implemented
- Adding states, start states, and final states
- Managing transitions, including epsilon (`ε`) transitions
- Simulating input processing to determine if an input string would be accepted
- Computing epsilon closures using DFS
- Determining if the automaton functions as a Deterministic Finite Automaton (DFA)

## Reuse of Code from Previous DFA Assignment
Some parts of this project were adapted from project 1 where a **Deterministic Finite Automaton (DFA)** was implemented. The DFA project included similar methods for adding states, transitions, and checking if a string was accepted. However, changes were necessary to support nondeterminism

### Key Differences from the DFA Implementation
1. **State Transitions**  
   - In a DFA, each state has at most **one** transition per input symbol.
   - In an NFA, a state can transition to **multiple** states on the same input symbol, requiring `Set<NFAState>` instead of a single `NFAState` reference.

2. **Epsilon (`ε`) Transitions**  
   - DFAs do not support epsilon transitions, so this concept did not exist in the previous assignment.
   - The NFA implementation required an `eClosure` method to handle transitions that occur without consuming an input.

3. **String Acceptance**  
   - The DFA's `accepts()` method processed the input string by following a **single** transition path.
   - In the NFA, the `accepts()` method must keep track of **multiple active states** at each step, which is done using sets and traversal.

4. **Checking for DFA Behavior**  
   - The previous DFA implementation only followed DFA rules.
   - In the NFA, the `isDFA()` method checks whether the properties are DFA type properties

By modifying these parts of the previous project, the implementation was changed to support NFAs while maintaining the core structure of a finite automaton.

## Compilation and Testing Instructions
### **Compiling and testing the Code**
```sh
javac -cp .:/usr/share/java/junit4.jar:/usr/share/java/hamcrest-core.jar test/nfa/NFATest.java

java -cp .:/usr/share/java/junit4.jar:/usr/share/java/hamcrest-core.jar org.junit.runner.JUnitCore test.nfa.NFATest

