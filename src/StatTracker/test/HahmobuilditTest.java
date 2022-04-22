package StatTracker.test;
// Generated by ComTest BEGIN
import StatTracker.*;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.04.22 11:59:57 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class HahmobuilditTest {



  // Generated by ComTest BEGIN
  /** testLisaaHahmolleBuild29 */
  @Test
  public void testLisaaHahmolleBuild29() {    // Hahmobuildit: 29
    Hahmobuildit hahmobuildit = new Hahmobuildit(); 
    Hahmon_build hb1 = new Hahmon_build(1, 3); 
    Hahmon_build hb2 = new Hahmon_build(1, 2); 
    hahmobuildit.lisaaHahmolleBuild(1, 3); 
    hahmobuildit.lisaaHahmolleBuild(1, 2); 
    hahmobuildit.lisaaHahmolleBuild(2, 3); 
    List<Integer> vastaus = hahmobuildit.annaHahmonBuildit(1); 
    Iterator<Integer> it = vastaus.iterator(); 
    it.next().equals(hb1); 
    it.next().equals(hb2); 
    assertEquals("From: Hahmobuildit line: 46", false, it.hasNext()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaHahmonBuildit69 */
  @Test
  public void testAnnaHahmonBuildit69() {    // Hahmobuildit: 69
    Hahmobuildit hahmobuildit = new Hahmobuildit(); 
    Hahmon_build hb1 = new Hahmon_build(1, 3); 
    Hahmon_build hb2 = new Hahmon_build(1, 2); 
    hahmobuildit.lisaaHahmolleBuild(1, 3); 
    hahmobuildit.lisaaHahmolleBuild(1, 2); 
    hahmobuildit.lisaaHahmolleBuild(2, 3); 
    List<Integer> vastaus = hahmobuildit.annaHahmonBuildit(1); 
    Iterator<Integer> it = vastaus.iterator(); 
    it.next().equals(hb1); 
    it.next().equals(hb2); 
    assertEquals("From: Hahmobuildit line: 84", false, it.hasNext()); 
  } // Generated by ComTest END
}