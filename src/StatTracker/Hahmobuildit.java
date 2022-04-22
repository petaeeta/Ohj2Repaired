package StatTracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import fi.jyu.mit.fxgui.Dialogs;

/**
 * Luokka taulukolle, johon on tallennettu hahmojen buildeja
 * @author petteri
 * @version 29.3.2019
 *
 */
public class Hahmobuildit implements Iterable<Hahmon_build> {
    private String profiiliNimi = "";
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
    public void lisaaHahmolleBuild(int hid, int bid) {
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
     * Lis‰‰ parametrina annetun Hahmon_buildin
     * @param lisattava hahmon_build
     */
    public void lisaaHahmolleBuild(Hahmon_build lisattava) {
        try {
            for (Hahmon_build b : taulukko) {
                if (b.equals(lisattava)) {
                    throw new SailoException("T‰ll‰ hahmolla on jo kyseinen build");
                }
            }
            taulukko.add(lisattava);
        } catch (Exception e) {
            System.err.print("Odottamaton virhe tapahtui buildin luonnissa: " + e.getMessage() );
        }
    }
    
    /**
     * Palauttaa profiilin nimen
     * @return profiilin nimen
     */
    public String getProfiiliNimi() {
        return profiiliNimi;
    }
    
    /**
     * Asettaa profiilin nimen
     * @param nimi profiilin uusi nimi
     */
    public void setProfiiliNimi(String nimi) {
        profiiliNimi = nimi;
    }
    
    /**
     * Lukee tiedoston jo asetetulla profiilinimell‰
     * @throws SailoException jos ei lˆydy
      */
    public void lueTiedostosta() throws SailoException {
        lueTiedostosta(getProfiiliNimi());
    }
    
    
     /**
      * Lukee tiedoston halutusta paikasta
      * @param hakemisto josta tiedosto luetaan
      * @throws SailoException jos luku ei onnistu
      */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        String tiedostonNimi = hakemisto + "/hahmobuildit.dat";
        File ftied = new File(tiedostonNimi);
        try {
            try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
                while (fi.hasNext()) {
                    String s = "";
                    s = fi.nextLine();
                    Hahmon_build hb = new Hahmon_build();
                    hb.parse(s);
                    lisaaHahmolleBuild(hb);
                }
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Ei saa luettua tiedostoa " + tiedostonNimi);
            
        }
        /*
           * catch (IOException e) { throw new
           * SailoException("Ongelmia tiedoston kanssa: " + e.getMessage()); }
           */
    }
    
    /**
     * Tallentaa tiedot jo asetettuun tiedostonimeen
     * @throws SailoException jos tallennus ep‰onnistuu
     */
    public void tallenna() throws SailoException {
        tallenna(getProfiiliNimi());
    }
    
    /**
     * Tallentaa t‰m‰nhetkiset tiedot tiedostoon
     * @param tiednimi tallennettavan tiedoston nimi
     * @throws SailoException jos talletus ep‰onnistuu
     */
    public void tallenna(String tiednimi) throws SailoException {
        File tied = new File(tiednimi + "/hahmobuildit.dat");
        try {
            try (PrintStream fo = new PrintStream(new FileOutputStream(tied, false))) {
                for (Iterator<Hahmon_build> it = taulukko.iterator(); it.hasNext();) {
                    Hahmon_build hb = it.next();
                    fo.println(hb);
                }
            }
        } catch(Exception e) {
            throw new SailoException("Tiedosto " + tied.getAbsolutePath() + " ei aukea");
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
