package StatTracker.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import StatTracker.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.04.22 12:25:18 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class HahmotTest {



  // Generated by ComTest BEGIN
  /** 
   * testLisaaHahmo29 
   * @throws SailoException when error
   */
  @Test
  public void testLisaaHahmo29() throws SailoException {    // Hahmot: 29
    Hahmot hahmot = new Hahmot(); 
    Hahmo hahmo1 = new Hahmo(), hahmo2 = new Hahmo(); 
    assertEquals("From: Hahmot line: 33", 0, hahmot.getLkm()); 
    hahmot.lisaaHahmo(hahmo1); assertEquals("From: Hahmot line: 34", 1, hahmot.getLkm()); 
    hahmot.lisaaHahmo(hahmo2); assertEquals("From: Hahmot line: 35", 2, hahmot.getLkm()); 
    hahmot.lisaaHahmo(hahmo1); assertEquals("From: Hahmot line: 36", 3, hahmot.getLkm()); 
    assertEquals("From: Hahmot line: 37", hahmo1, hahmot.anna(0)); 
    assertEquals("From: Hahmot line: 38", hahmo2, hahmot.anna(1)); 
    assertEquals("From: Hahmot line: 39", hahmo1, hahmot.anna(2)); 
    assertEquals("From: Hahmot line: 40", false, hahmot.anna(1) == hahmo1); 
    assertEquals("From: Hahmot line: 41", true, hahmot.anna(1) == hahmo2); 
    try {
    assertEquals("From: Hahmot line: 42", hahmo1, hahmot.anna(3)); 
    fail("Hahmot: 42 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    hahmot.lisaaHahmo(hahmo1); assertEquals("From: Hahmot line: 43", 4, hahmot.getLkm()); 
    hahmot.lisaaHahmo(hahmo1); assertEquals("From: Hahmot line: 44", 5, hahmot.getLkm()); 
    hahmot.lisaaHahmo(hahmo1); assertEquals("From: Hahmot line: 45", 6, hahmot.getLkm()); 
    hahmot.lisaaHahmo(hahmo1); assertEquals("From: Hahmot line: 46", 7, hahmot.getLkm()); 
    hahmot.lisaaHahmo(hahmo1); assertEquals("From: Hahmot line: 47", 8, hahmot.getLkm()); 
    try {
    hahmot.lisaaHahmo(hahmo1); 
    fail("Hahmot: 48 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testGetOverallTilastot95 
   * @throws SailoException when error
   */
  @Test
  public void testGetOverallTilastot95() throws SailoException {    // Hahmot: 95
    Hahmot hahmot = new Hahmot(); 
    Hahmo hahmo1 = new Hahmo("Kalle", 3, 5, 12, 2); 
    Hahmo hahmo2 = new Hahmo("Ville", 1, 33, 74, 100); 
    hahmot.lisaaHahmo(hahmo1); 
    hahmot.lisaaHahmo(hahmo2); 
    int[] overall = hahmot.getOverallTilastot(); 
    assertEquals("From: Hahmot line: 103", 3 + 1, overall[0]); 
    assertEquals("From: Hahmot line: 104", 5 + 33, overall[1]); 
    assertEquals("From: Hahmot line: 105", 12 + 74, overall[2]); 
    assertEquals("From: Hahmot line: 106", 2 + 100, overall[3]); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testAnna128 
   * @throws SailoException when error
   */
  @Test
  public void testAnna128() throws SailoException {    // Hahmot: 128
    Hahmot hahmot = new Hahmot(); 
    Hahmo hahmo1 = new Hahmo("Kalle", 3, 5, 12, 2); 
    Hahmo hahmo2 = new Hahmo("Ville", 1, 33, 74, 100); 
    hahmot.lisaaHahmo(hahmo1); 
    hahmot.lisaaHahmo(hahmo2); 
    assertEquals("From: Hahmot line: 135", hahmo1, hahmot.anna(0)); 
    assertEquals("From: Hahmot line: 136", hahmo2, hahmot.anna(1)); 
  } // Generated by ComTest END
}