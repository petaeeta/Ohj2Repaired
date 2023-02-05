package StatTracker.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import StatTracker.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2023.02.05 21:01:36 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class BuilditTest {



  // Generated by ComTest BEGIN
  /** 
   * testLisaaBuild30 
   * @throws SailoException when error
   */
  @Test
  public void testLisaaBuild30() throws SailoException {    // Buildit: 30
    Buildit buildit = new Buildit(); 
    Build build1 = new Build(); 
    Build build2 = new Build(); 
    assertEquals("From: Buildit line: 35", 0, buildit.getLkm()); 
    buildit.lisaaBuild(build1); assertEquals("From: Buildit line: 36", 1, buildit.getLkm()); 
    buildit.lisaaBuild(build2); assertEquals("From: Buildit line: 37", 2, buildit.getLkm()); 
    buildit.lisaaBuild(build1); assertEquals("From: Buildit line: 38", 3, buildit.getLkm()); 
    assertEquals("From: Buildit line: 39", build1, buildit.anna(0)); 
    assertEquals("From: Buildit line: 40", build2, buildit.anna(1)); 
    assertEquals("From: Buildit line: 41", build1, buildit.anna(2)); 
    assertEquals("From: Buildit line: 42", false, buildit.anna(1) == build1); 
    assertEquals("From: Buildit line: 43", true, buildit.anna(0) == build1); 
    try {
    assertEquals("From: Buildit line: 44", build1, buildit.anna(3)); 
    fail("Buildit: 44 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    buildit.lisaaBuild(build1); assertEquals("From: Buildit line: 45", 4, buildit.getLkm()); 
    buildit.lisaaBuild(build1); assertEquals("From: Buildit line: 46", 5, buildit.getLkm()); 
  } // Generated by ComTest END
}