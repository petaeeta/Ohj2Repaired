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
public class BuildTest {



  // Generated by ComTest BEGIN
  /** testRekisteroi83 */
  @Test
  public void testRekisteroi83() {    // Build: 83
    Build build1 = new Build(); 
    assertEquals("From: Build line: 85", build1.getSeuraavaBid()-1, build1.getBid()); 
    Build build2 = new Build(); 
    assertEquals("From: Build line: 87", build2.getSeuraavaBid()-1, build2.getBid()); 
    assertEquals("From: Build line: 88", build2.getBid()-1, build1.getBid()); 
    assertEquals("From: Build line: 89", "Pavillion", build1.getNimi()); 
    assertEquals("From: Build line: 90", "SneakyGank", build2.getNimi()); 
    assertEquals("From: Build line: 91", "Build, jossa k�ytet��n jokaista hahmoa jolla on luonnollista armoria", build1.getKuvaus()); 
    assertEquals("From: Build line: 92", "Lorem Ipsum", build2.getKuvaus());
  } // Generated by ComTest END
}