package StatTracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * 
 * @author petteri
 * @version 29.3.2019
 *
 */
public class Hahmot {
    private int lkm;
    private int maxlkm = 8; // k‰tet‰‰n vasta kun tietorakennetta aletaan kasvattamaan rajatta
    private Hahmo[] hahmot = new Hahmo[maxlkm];
    private String profiiliNimi = "profiili";
    private String tiedostoNimi = "hahmot";
    
    /**
     * Hahmot luokan konstruktori, joka luo taulukon, jonne voi
     * laittaa hahmo-olioita. Oletuksena luo taulukon kahdeksalle
     * alkiolle
     */
    public Hahmot() {
        
    }
    
    
    /**
     * Metodi hahmojen lis‰‰miseksi taulukkoon 
     * @param hahmo Hahmo, joka taulukkoon lis‰t‰‰n.
     * @throws SailoException jos tietorakenne on t‰ynn‰
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Hahmot hahmot = new Hahmot();
     * Hahmo hahmo1 = new Hahmo(), hahmo2 = new Hahmo();
     * hahmot.getLkm() === 0;
     * hahmot.LisaaHahmo(hahmo1); hahmot.getLkm() === 1;
     * hahmot.LisaaHahmo(hahmo2); hahmot.getLkm() === 2;
     * hahmot.LisaaHahmo(hahmo1); hahmot.getLkm() === 3;
     * hahmot.anna(0) === hahmo1;
     * hahmot.anna(1) === hahmo2;
     * hahmot.anna(2) === hahmo1;
     * hahmot.anna(1) == hahmo1 === false;
     * hahmot.anna(1) == hahmo2 === true;
     * hahmot.anna(3) === hahmo1; #THROWS IndexOutOfBoundsException
     * hahmot.LisaaHahmo(hahmo1); hahmot.getLkm() === 4;
     * hahmot.LisaaHahmo(hahmo1); hahmot.getLkm() === 5;
     * hahmot.LisaaHahmo(hahmo1); hahmot.getLkm() === 6;
     * hahmot.LisaaHahmo(hahmo1); hahmot.getLkm() === 7;
     * hahmot.LisaaHahmo(hahmo1); hahmot.getLkm() === 8;
     * hahmot.LisaaHahmo(hahmo1); #THROWS SailoException
     * </pre>
     */
    public void lisaaHahmo(Hahmo hahmo) throws SailoException {
        if (lkm >= hahmot.length) {
            maxlkm += 20;
            Hahmo[] uusi = new Hahmo[maxlkm];
            for(int i = 0; i < hahmot.length; i++) {
                uusi[i] = hahmot[i];
            }
            hahmot = uusi;
        }
        hahmot[lkm] = hahmo;
        lkm++;
    }
    
    
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
     * palauttaa hahmojen lukum‰‰r‰n
     * @return hahmojen lukum‰‰r‰
     */
    public int getLkm() {
        return lkm;
    }
    
    /**
     * Laskee yhteen kaikkien hahmojen tilastot.
     * @return paketin jossa on kaikkien hahmojen yhteenlasketut tilastot. Palautetaan Hahmo-oliona sill‰ Hahmo-oliosta lˆytyy kaikki tarvittavat attribuutit, oikeata hahmoa ei kuitenkaan luoda.
     */
    /*
     * public Hahmo getOverallTilastot() { Hahmo hahmo = new Hahmo(false); for
     * (Hahmo i : hahmot) { if (i == null) break;
     * hahmo.setVoitot(hahmo.getVoitot() + i.getVoitot());
     * hahmo.setHaviot(hahmo.getHaviot() + i.getHaviot());
     * hahmo.setTapot(hahmo.getTapot() + i.getTapot());
     * hahmo.setKuolemat(hahmo.getKuolemat() + i.getKuolemat()); } return hahmo;
     * }
     */
    
    /**
     * Laskee yhteen kaikkien hahmojen tilastot.
     * @return int-listan
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Hahmot hahmot = new Hahmot();
     * Hahmo hahmo1 = new Hahmo("Kalle", 3, 5, 12, 2);
     * Hahmo hahmo2 = new Hahmo("Ville", 1, 33, 74, 100);
     * hahmot.LisaaHahmo(hahmo1);
     * hahmot.LisaaHahmo(hahmo2);
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
            if (i == null) break;
            int[] hahmoStats = i.getStats();
            for (int j = 0; j<stats.length; j++) {
                stats[j] += hahmoStats[j];
            }
        }
        return stats;
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
     * Palauttaa viitteen i:teen hahmoon.
     * @param i monennenko hahmon viite halutaan
     * @return viite hahmoon, jonka indeksi on i
     * @throws IndexOutOfBoundsException mik‰li i ei ole sallitulla alueella
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Hahmot hahmot = new Hahmot();
     * Hahmo hahmo1 = new Hahmo("Kalle", 3, 5, 12, 2);
     * Hahmo hahmo2 = new Hahmo("Ville", 1, 33, 74, 100);
     * hahmot.LisaaHahmo(hahmo1);
     * hahmot.LisaaHahmo(hahmo2);
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
     * @throws SailoException jos tallennus ep‰onnistuu
     */
    public void tallenna() throws SailoException {
        tallenna(getProfiiliNimi());
    }
    
    /**
     * @param tiednimi tallennettavan tiedoston nimi
     * @throws SailoException jos talletus ep‰onnistuu
     */
    public void tallenna(String tiednimi) throws SailoException {
        File tied = new File(tiednimi + "/" + tiedostoNimi + ".dat");
        try {
            try (PrintStream fo = new PrintStream(new FileOutputStream(tied, false))) {
                for (int i = 0; i<getLkm(); i++) {
                    Hahmo hahmo = anna(i);
                    fo.println(hahmo);
                }
            }
        } catch(Exception e) {
            throw new SailoException("Tiedosto " + tied.getAbsolutePath() + " ei aukea");
        }
    }
    
    /**
     * Lukee tiedostosta jolla on jo asetettu profiilinimi
     * @throws SailoException pik‰li ei lˆydy
     */
    public void lueTiedostosta() throws SailoException {
        lueTiedostosta(getProfiiliNimi());
    }
    
    /**
     * Lukee tiedostosta profiilinime‰ vastaan
     * @param hakemisto josta luetaan
     * @throws SailoException jos ei lˆydy
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
            throw new SailoException("Ei saa luettua tiedostoa " + tiedostonNimi);
            
        } /*
           * catch (IOException e) { throw new
           * SailoException("Ongelmia tiedoston kanssa: " + e.getMessage()); }
           */
    }
    
    /**
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        Hahmot hahmot = new Hahmot();
        
        try {
            hahmot.lueTiedostosta("Esimerkkiprofiili");
            
        }catch(SailoException e) {
            System.err.println("Ei voi lukea " + e.getMessage());
        }
        
        Hahmo hahmo1 = new Hahmo();
        Hahmo hahmo2 = new Hahmo();
        
        hahmo1.tulosta(System.out);
        hahmo2.tulosta(System.out);
        try {
            hahmot.lisaaHahmo(hahmo1);
            hahmot.lisaaHahmo(hahmo2);
        } catch (SailoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try {
            hahmot.tallenna("Esimerkkiprofiili");
        } catch (SailoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
    }

}
