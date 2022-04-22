package StatTracker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import fi.jyu.mit.fxgui.Dialogs;

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
     * Lis‰‰ jo tehtyyn taulukkoon uuden hahmo-build -olion.
     * @param hid buildia k‰ytt‰v‰n hahmon id
     * @param bid buildin id
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * #import java.util.*;
     * #import StatTracker.*
     * 
     * //T‰m‰ yksi testi periaatteessa testaa molemmat funktiot, mutta laitan sen nyt varmuuden vuoksi molempiin funktioihin
     * Hahmobuildit hahmobuildit = new Hahmobuildit();
     *
     * Hahmon_build hb1 = new Hahmon_build(1, 3);
     * Hahmon_build hb2 = new Hahmon_build(1, 2);
     * 
     * hahmobuildit.LisaaHahmolleBuild(1, 3);
     * hahmobuildit.LisaaHahmolleBuild(1, 2);
     * hahmobuildit.LisaaHahmolleBuild(2, 3);
     * 
     * List<Integer> vastaus = hahmobuildit.annaHahmonBuildit(1);
     * Iterator<Integer> it = vastaus.iterator();
     * it.next().equals(hb1);
     * it.next().equals(hb2);
     * it.hasNext() === false;
     * </pre>
     */
    public void LisaaHahmolleBuild(int hid, int bid) {
        try {
            Hahmon_build lisattava = new Hahmon_build(hid, bid);
            for (Hahmon_build b : taulukko) {
                if (b.equals(lisattava)) {
                    Dialogs.showMessageDialog("T‰lle hahmolle on jo annettu valittu build");
                    return;
                }
            }
            taulukko.add(lisattava);
        } catch (Exception e) {
            System.err.print("Odottamaton virhe tapahtui buildin luonnissa: " + e.getMessage() );
        }
    }
    
    /**
     * 
     * @param id hahmon id, jonka buildeja etsit‰‰n
     * @return palauttaa listan, jossa on olioita, joille on merkitty sama hahmo-id.
     * @example
     * <pre name="test">
     * 
     * Hahmobuildit hahmobuildit = new Hahmobuildit();
     * 
     * Hahmon_build hb1 = new Hahmon_build(1, 3);
     * Hahmon_build hb2 = new Hahmon_build(1, 2);
     * 
     * hahmobuildit.LisaaHahmolleBuild(1, 3);
     * hahmobuildit.LisaaHahmolleBuild(1, 2);
     * hahmobuildit.LisaaHahmolleBuild(2, 3);
     * 
     * List<Integer> vastaus = hahmobuildit.annaHahmonBuildit(1);
     * Iterator<Integer> it = vastaus.iterator();
     * it.next().equals(hb1);
     * it.next().equals(hb2);
     * it.hasNext() === false;
     * 
     * </pre>
     */
    public List<Integer> annaHahmonBuildit(int id){
        var loydetyt = new ArrayList<Integer>();
        for (Iterator<Hahmon_build> it = taulukko.iterator(); it.hasNext();) {
            Hahmon_build verrattava = it.next();
            if (verrattava.getHid() == id ) loydetyt.add(verrattava.getBid());
        }
        return loydetyt;
    }

    @Override
    public Iterator<Hahmon_build> iterator() {
        return taulukko.iterator();
    }
}
