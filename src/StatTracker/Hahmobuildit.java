package StatTracker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Luokka taulukolle, johon on tallennettu hahmojen buildeja
 * @author petteri
 * @version 29.3.2019
 *
 */
public class Hahmobuildit implements Iterable<Hahmon_build> {
    private final Collection<Hahmon_build> taulukko = new ArrayList<Hahmon_build>();
    
    
    /**
     * konstruktori hahmon buildien tallentamiseksi
     */
    public Hahmobuildit() {
    }

    /**
     * Lis‰‰ jo tehtyyn taulukkoon uuden hahmo-build -olion
     * @param hid buildia k‰ytt‰v‰n hahmon id
     * @param bid buildin id
     */
    public void LisaaHahmolleBuild(int hid, int bid) {
        try { taulukko.add(new Hahmon_build(hid, bid));
        } catch (Exception e) {
            System.err.print("Odottamaton virhe tapahtui buildin luonnissa: " + e.getMessage() );
        }
    }
    
    /**
     * 
     * @param id hahmon id, jonka buildeja etsit‰‰n
     * @return palauttaa listan, jossa on olioita, joille on merkitty sama hahmo-id.
     */
    public List<Integer> annaHahmonBuildit(int id){
        var loydetyt = new ArrayList<Integer>();
        for (Hahmon_build b : taulukko) {
            if (b.getHid() == id ) loydetyt.add(b.getBid());
        }
        return loydetyt;
    }

    @Override
    public Iterator<Hahmon_build> iterator() {
        return taulukko.iterator();
    }
}
