package StatTracker.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import StatTracker.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.04.20 10:01:32 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class HahmoTest {



  // Generated by ComTest BEGIN
  /** testRekisteroi38 */
  @Test
  public void testRekisteroi38() {    // Hahmo: 38
    Hahmo hahmo1 = new Hahmo(); 
    assertEquals("From: Hahmo line: 40", hahmo1.getSeuraavaHid()-1, hahmo1.getHid()); 
    Hahmo hahmo2 = new Hahmo(); 
    assertEquals("From: Hahmo line: 42", hahmo2.getSeuraavaHid()-1, hahmo2.getHid()); 
    assertEquals("From: Hahmo line: 43", hahmo2.getHid()-1, hahmo1.getHid()); 
  } // Generated by ComTest END
}