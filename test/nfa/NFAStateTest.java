package test.nfa;

import static org.junit.Assert.*;
import org.junit.Test;
import fa.nfa.NFAState;
import java.util.Set;

public class NFAStateTest {

    @Test
    public void testStateCreation() {
        NFAState state = new NFAState("q0");
        assertEquals("q0", state.getName());
    }

    @Test
    public void testAddingTransitions() {
        NFAState q0 = new NFAState("q0");
        NFAState q1 = new NFAState("q1");
        NFAState q2 = new NFAState("q2");

        q0.addTransition('0', q1);
        q0.addTransition('1', q2);
        q0.addTransition('0', q2); // Multiple transitions for '0'

        // Verify transitions
        assertEquals(Set.of(q1, q2), q0.getTransitions('0'));
        assertEquals(Set.of(q2), q0.getTransitions('1'));
        assertTrue(q0.getTransitions('e').isEmpty()); // No epsilon transitions added
    }
}
