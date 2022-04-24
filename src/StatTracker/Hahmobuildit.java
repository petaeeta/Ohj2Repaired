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
 * @version 23.4.2022
 * @Version 24.4.2022
 *
 */
public class Hahmobuildit implements Iterable<Hahmon_build> {
    private String tiedostoNimi = "hahmobuildit";
    private String profiiliNimi = "";
    private final Collection<Hahmon_build> taulukko = new ArrayList<Hahmon_build>();
    private boolean muutettu = false;
    /**
     * konstruktori hahmon buildien tallentamiseksi
     */
    public Hahmobuildit() {
    }

    /**
     * Lisää jo tehtyyn taulukkoon uuden hahmo-build -olion.
     * @param hid buildia käyttävän hahmon id
     * @param bid buildin id
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * #import java.util.*;
     * #import StatTracker.*
     * 
     * //Tämä yksi testi periaatteessa testaa molemmat funktiot, mutta laitan sen nyt varmuuden vuoksi molempiin funktioihin
     * Hahmobuildit hahmobuildit = new Hahmobuildit();
     *
     * Hahmon_build hb1 = new Hahmon_build(1, 3);
     * Hahmon_build hb2 = new Hahmon_build(1, 2);
     * 
     * hahmobuildit.lisaaHahmolleBuild(1, 3);
     * hahmobuildit.lisaaHahmolleBuild(1, 2);
     * hahmobuildit.lisaaHahmolleBuild(2, 3);
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
                    Dialogs.showMessageDialog("Tälle hahmolle on jo annettu valittu build");
                    return;
                }
            }
            taulukko.add(lisattava);
            muutettu = true;
        } catch (Exception e) {
            System.err.print("Odottamaton virhe tapahtui buildin luonnissa: " + e.getMessage() );
        }
    }
    
    /**
     * Lisää parametrina annetun Hahmon_buildin
     * @param lisattava hahmon_build
     */
    public void lisaaHahmolleBuild(Hahmon_build lisattava) {
        try {
            for (Hahmon_build b : taulukko) {
                if (b.equals(lisattava)) {
                    throw new SailoException("Tällä hahmolla on jo kyseinen build");
                }
            }
            taulukko.add(lisattava);
            muutettu = true;
        } catch (Exception e) {
            System.err.print("Odottamaton virhe tapahtui buildin luonnissa: " + e.getMessage() );
        }
    }
    
    /**
     * Palauttaa arvon perustuen siihen onko tallennuskriittisiä tiedostoja muutettu 
     * @return muutettu-arvon
    */
    public boolean getMuutettu() {
        return muutettu;
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
     * Lukee tiedoston jo asetetulla profiilinimellä
     * @throws SailoException jos ei löydy
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
            throw new SailoException("Tällä profiilinimellä ei ole aikaisempia tiedostoja, tai ne ovat tuhoutuneet. Ne luodaan kun tallennat tässä sessiossa.");
            
        }
        /*
           * catch (IOException e) { throw new
           * SailoException("Ongelmia tiedoston kanssa: " + e.getMessage()); }
           */
    }
    
    /**
     * Tallentaa tiedot jo asetettuun tiedostonimeen
     * @throws SailoException jos tallennus epäonnistuu
     */
    public void tallenna() throws SailoException {
        tallenna(getProfiiliNimi());
    }
    
    /**
     * Tallentaa tämänhetkiset tiedot tiedostoon
     * @param tiednimi tallennettavan tiedoston nimi
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallenna(String tiednimi) throws SailoException {
        File tied = new File(tiednimi + "/" + tiedostoNimi + ".dat");
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
        muutettu = false;
    }
    

    /**
     * Antaa kaikki buildit joihin hahmo on kytketty
     * @param id hahmon id jonka buildit haetaan
     * @return Lista jossa kaikki hahmon buildit
     */
    public List<Integer> annaHahmonBuildit(int id){
        var loydetyt = new ArrayList<Integer>();
        for (Iterator<Hahmon_build> it = taulukko.iterator(); it.hasNext();) {
            Hahmon_build verrattava = it.next();
            if (verrattava.getHid() == id ) loydetyt.add(verrattava.getBid());
        }
        return loydetyt;
    }
    
    /**
     * Poistaa tietyt hid-bid esiintymät.
     * @param hid hahmon buildit jotka poistetaan
     * @param bid hahmobuildin build-id joka poistetaan
     */
    public void poistaHahmonBuildit(int hid, int bid) {
        Iterator<Hahmon_build> it = taulukko.iterator();
        while (it.hasNext()) {
            Hahmon_build poistettava = it.next();
            if (poistettava.getHid() == hid && poistettava.getBid() == bid) {
                it.remove();
                muutettu = true;
            }
        }

    }
    
    /**
     * Poistaa kaikki hahmon buildit
     * @param hid hahmon buildit jotka poistetaan
     */
    public void poistaHahmonBuildit(int hid) {
        Iterator<Hahmon_build> it = taulukko.iterator();
        while (it.hasNext()) {
            Hahmon_build poistettava = it.next();
            if (poistettava.getHid() == hid) {
                it.remove();
                muutettu = true;
            }
        }

    }
    
    /**
     * Poistaa kaikki buildin esiintymät
     * @param bid hahmon buildit jotka poistetaan
     */
    public void poistaBuildit(int bid) {
        Iterator<Hahmon_build> it = taulukko.iterator();
        while (it.hasNext()) {
            Hahmon_build poistettava = it.next();
            if (poistettava.getBid() == bid) {
                it.remove();
                muutettu = true;
            }
        }
    }

    @Override
    public Iterator<Hahmon_build> iterator() {
        return taulukko.iterator();
    }
}
