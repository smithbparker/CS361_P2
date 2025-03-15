# CS 361 - Project 2: NFA

* Author: Smith Parker, Sabastian Leeper
* Class: CS361 Section #1
* Semester: Spring 2025
## Overview
This project implements a Nondeterministic Finite Automaton (NFA) in Java. The NFA supports basic operations such as adding states, transitions, checking if a string is accepted, computing epsilon closures, and determining whether the automaton is actually a DFA.


## Reflection

As mentioned below, we started this project off by adapting as much as we could from project 1, the DFA. I believe this method worked wonderfully, as building off previous, known codebase gives us a clear pathway to adapt towards the final NFA goal by understanding the differences between DFA's and NFA's. The main methodology towards debugging throughout this project was heavy utilization of the provided test commands, seen below. The basic tests given they were instrumental in determining what was working well and what was off. Additonally, on a partnership level, our commenting was consistent and common, so even if new code was added to the commit, the other person knew what the code did. I think the only change I would make about the design process would be prioritizing many test cases first, before focusing on finishing the code to function on basic test cases. This could feel like a hindsight-only prioritization, but changing the methodology to work like this would inherently change our process to work on these edge cases the first time around, instead of having to modify the code at all. Personally, this is similar to what I would do if I could tell my previous self about the project -- build more test cases earlier in the project. Alongside that, I would attempt to force myself to work on the project more earlier -- midterms hampered the process for this project heavily. 

Overall, most of this project was created in a smooth manner, with low amounts of roadbumps. I think the biggest struggle not only for this project, but for most of the projects I undertake is not coding for base and normal circumstances, but instead all the potential edge cases that can pop up rarely. When coding an implementation like NFA's, it is very easy to autopilot an implementation that "works" most of the time, and ignore all the variables that can fail. Personally, I don't fully understand how to wrap my head around covering *every* edge case, and is easily the least-clear portion of the development cycle. Additionally, for this project specifically, most of the time I had dedicated towards it was periods where Onyx was down. We do not have the enviornment set up where I can do these tests locally, so I was afraid to add many test cases that didn't function at all; since we couldn't utilize them. Even though this is a large struggle, it inherently stems from something we can be grateful for -- the project works for all provided base cases, and plenty of other tests.

## Compilation and Testing Instructions
### **Compiling and testing the Code**
   - Assuming the user has the prerequisite .jar's setup (via various methods, most prominently SSH'ing into onyx), they can utilize the below commands to run the program:
```sh
javac -cp .:/usr/share/java/junit4.jar:/usr/share/java/hamcrest-core.jar test/nfa/NFATest.java

java -cp .:/usr/share/java/junit4.jar:/usr/share/java/hamcrest-core.jar org.junit.runner.JUnitCore test.nfa.NFATest
```

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

## Sources used
   - No relevant sources other than the class notes and files were used for the creation of this project.

