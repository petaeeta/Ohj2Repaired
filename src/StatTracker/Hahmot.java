package StatTracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fi.jyu.mit.ohj2.WildChars;

/**
 * 
 * @author petteri
 * @version 29.3.2019
 *
 */
public class Hahmot {
    private int lkm; // Hahmojen lukumäärä
    private int maxlkm = 8; // Hahmojen alkuperäinen maksimimäärä
    private Hahmo[] hahmot = new Hahmo[maxlkm]; // Taulukko hahmoille
    private String profiiliNimi = "profiili"; // Käyttäjän profiilin nimi
    private String tiedostoNimi = "hahmot"; // Tiedoston nimi, johon hahmot tallennetaan
    private boolean muutettu = false; // Flag joka muutetaan trueksi, mikäli hahmotietoja on muutettu
    
    /**
     * Hahmot luokan konstruktori, joka luo taulukon, jonne voi
     * laittaa hahmo-olioita. Oletuksena luo taulukon kahdeksalle
     * alkiolle
     */
    public Hahmot() {
        
    }
    
    /**
     * Metodi hahmojen lisäämiseksi taulukkoon 
     * @param hahmo Hahmo, joka taulukkoon lisätään.
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Hahmot hahmot = new Hahmot();
     * Hahmo hahmo1 = new Hahmo(), hahmo2 = new Hahmo();
     * hahmot.getLkm() === 0;
     * hahmot.lisaaHahmo(hahmo1); hahmot.getLkm() === 1;
     * hahmot.lisaaHahmo(hahmo2); hahmot.getLkm() === 2;
     * hahmot.lisaaHahmo(hahmo1); hahmot.getLkm() === 3;
     * hahmot.anna(0) === hahmo1;
     * hahmot.anna(1) === hahmo2;
     * hahmot.anna(2) === hahmo1;
     * hahmot.anna(1) == hahmo1 === false;
     * hahmot.anna(1) == hahmo2 === true;
     * hahmot.anna(3) === hahmo1; #THROWS IndexOutOfBoundsException
     * hahmot.lisaaHahmo(hahmo1); hahmot.getLkm() === 4;
     * hahmot.lisaaHahmo(hahmo1); hahmot.getLkm() === 5;
     * hahmot.lisaaHahmo(hahmo1); hahmot.getLkm() === 6;
     * hahmot.lisaaHahmo(hahmo1); hahmot.getLkm() === 7;
     * hahmot.lisaaHahmo(hahmo1); hahmot.getLkm() === 8;
     * </pre>
     */
    public void lisaaHahmo(Hahmo hahmo) {
        // Rekisteröi hahmo, mikäli hahmo-id:tä ei ole alustettu
        if (hahmo.getHid() < 0) hahmo.rekisteroi();
        
        // Kasvata taulukkoa mikäli maksimimäärä ylittyy
        if (lkm >= hahmot.length) {
            maxlkm += 20;
            Hahmo[] uusi = new Hahmo[maxlkm];
            for(int i = 0; i < hahmot.length; i++) {
                uusi[i] = hahmot[i];
            }
            hahmot = uusi;
            muutettu = true;
        }
        
        // Lisää hahmo ensimmäiseen tyhjään taulukon alkioon
        for (int i = 0; i < hahmot.length; i++) {
            if (hahmot[i] == null) {
                hahmot[i] = hahmo;
                lkm++;
                muutettu = true;
                return;
            }
        }
    }
    
    /**
     * toString-override, joka tulostaa kaikki listan hahmot näyttöön
     */
    @Override
    public String toString(){
        StringBuilder hahmot_rivit = new StringBuilder();
        for (Hahmo i : hahmot) {
            if (i == null) break;
            hahmot_rivit.append(i.getNimi() + "\n");
        }
        return hahmot_rivit.toString();
    }

    /**
     * palauttaa hahmojen kokonaislukumäärän
     * @return hahmojen lukumäärä
     */
    public int getLkm() {
        return lkm;
    }
    
    /**
     * Palauttaa arvon perustuen siihen onko tallennuskriittisiä tiedostoja muutettu 
     * @return muutettu-arvon
    */
    public boolean getMuutettu() {
        return muutettu;
    }
    
    /**
     * Laskee yhteen kaikkien hahmojen tilastot.
     * @return int-listan
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Hahmot hahmot = new Hahmot();
     * Hahmo hahmo1 = new Hahmo("Kalle", 3, 5, 12, 2);
     * Hahmo hahmo2 = new Hahmo("Ville", 1, 33, 74, 100);
     * hahmot.lisaaHahmo(hahmo1);
     * hahmot.lisaaHahmo(hahmo2);
     * int[] overall = hahmot.getOverallTilastot();
     * overall[0] === 3 + 1;
     * overall[1] === 5 + 33;
     * overall[2] === 12 + 74;
     * overall[3] === 2 + 100;
     * </pre>
     */
    public int[] getOverallTilastot() {
        int[] stats = new int[4];
        for (Hahmo i : hahmot) {
            if (i == null) continue;
            int[] hahmoStats = i.getStats();
            for (int j = 0; j<stats.length; j++) {
                stats[j] += hahmoStats[j];
            }
        }
        return stats;
    }
    
    /**
     * Palauttaa profiilin nimen.
     * @return profiilin nimen
     */
    public String getProfiiliNimi() {
        return profiiliNimi;
    }
    
    /**
     * Asettaa profiilin nimen.
     * @param nimi profiilin uusi nimi
     */
    public void setProfiiliNimi(String nimi) {
        profiiliNimi = nimi;
    }

    /**
     * Palauttaa viitteen i:nteen hahmoon.
     * @param i monennenko hahmon viite halutaan.
     * @return viite hahmoon, jonka indeksi on i.
     * @throws IndexOutOfBoundsException mikäli i ei ole sallitulla alueella.
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Hahmot hahmot = new Hahmot();
     * Hahmo hahmo1 = new Hahmo("Kalle", 3, 5, 12, 2);
     * Hahmo hahmo2 = new Hahmo("Ville", 1, 33, 74, 100);
     * hahmot.lisaaHahmo(hahmo1);
     * hahmot.lisaaHahmo(hahmo2);
     * hahmot.anna(0) === hahmo1;
     * hahmot.anna(1) === hahmo2;
     * </pre>
     */
    public Hahmo anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return hahmot[i];
    }
    
    /**
     * Tallentaa tiedot jo asetettuun tiedostonimeen
     * @throws SailoException jos tallennus epäonnistuu
     */
    public void tallenna() throws SailoException {
        tallenna(getProfiiliNimi());
    }
    
    /**
     * @param tiednimi tallennettavan tiedoston nimi.
     * @throws SailoException jos talletus epäonnistuu.
     */
    public void tallenna(String tiednimi) throws SailoException {
        File tied = new File(tiednimi + "/" + tiedostoNimi + ".dat");
        try {
            try (PrintStream fo = new PrintStream(new FileOutputStream(tied, false))) {
                for (int i = 0; i < hahmot.length; i++) {
                    if (hahmot[i] != null)fo.println(hahmot[i]);
                }
            }
        } catch(Exception e) {
            throw new SailoException("Tiedosto " + tied.getAbsolutePath() + " ei aukea");
        }
        muutettu = false;
    }
    
    /**
     * Etsii korvattavan hahmon. Jos ei löydy, lisää hahmon.
     * @param hahmo uusi lisättävä hahmo
     * @throws SailoException jos jotain menee vikaan
     */
    public void korvaaTaiLisaa(Hahmo hahmo) throws SailoException {
        int id = hahmo.getHid();
        for (int i = 0; i < hahmot.length; i++) {
            if (hahmot[i] != null && hahmot[i].getHid() == id) {
                hahmot[i] = hahmo;
                muutettu = true;
                return;
            }
        }
        lisaaHahmo(hahmo);
        muutettu = true;
    }
    
    /**
     * Poistaa halutun hahmon id-numeroa vastaan
     * @param hid hahmon id joka poistetaan
     * @return 1 jos onnistui, 0 jos ei
     */
    public int poista(int hid) {
        for (int i = 0; i < hahmot.length; i++) {
            if (hahmot[i] != null && hahmot[i].getHid() == hid) {
                hahmot[i] = null;
                lkm--;
                muutettu = true;
                return 1;
            }
        }
        return 0;
    }
    
    /**
     * Etsii hahmot joiden nimi sopeutuu hakuehtoon
     * @param hakuehto Jonka mukaan etsitään
     * @return listan hahmoista
     */
    public List<Hahmo> etsi(String hakuehto) {
        String ehto = "*";
        if (hakuehto != null && hakuehto.length() > 0) ehto = '*' +  hakuehto + '*';
        List<Hahmo> vastaus = new ArrayList<Hahmo>();
        for (Hahmo hahmo : hahmot) {
            if (hahmo != null && WildChars.onkoSamat(hahmo.getNimi(), ehto)) vastaus.add(hahmo);
        }
        return vastaus;
    }
    
    /**
     * Lukee tiedostosta jolla on jo asetettu profiilinimi
     * @throws SailoException pikäli ei löydy
     */
    public void lueTiedostosta() throws SailoException {
        lueTiedostosta(getProfiiliNimi());
    }
    
    /**
     * Lukee tiedostosta profiilinimeä vastaan
     * @param hakemisto josta luetaan
     * @throws SailoException jos ei löydy
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        String tiedostonNimi = hakemisto + "/" + tiedostoNimi + ".dat";
        File ftied = new File(tiedostonNimi);
        try {
            try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
                while (fi.hasNext()) {
                    String s = "";
                    s = fi.nextLine();
                    Hahmo hahmo = new Hahmo(false);
                    hahmo.parse(s);
                    lisaaHahmo(hahmo);
                }
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Tällä profiilinimellä ei ole aikaisempia tiedostoja, tai ne ovat tuhoutuneet. Ne luodaan kun tallennat tässä sessiossa.");
            
        }
    }
}
